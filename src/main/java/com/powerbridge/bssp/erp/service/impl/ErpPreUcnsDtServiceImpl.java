package com.powerbridge.bssp.erp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.powerbridge.bssp.common.constants.CommonConstants;
import com.powerbridge.bssp.common.security.SystemAuthorizingUser;
import com.powerbridge.bssp.common.util.HttpClientUtil;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.json.JSONException;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.ems.entity.EmsCusUcnsDt;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;
import com.powerbridge.bssp.erp.dao.ErpPreUcnsDtMapper;
import com.powerbridge.bssp.erp.entity.ErpMidUcnsDt;
import com.powerbridge.bssp.erp.entity.ErpPreUcnsDt;
import com.powerbridge.bssp.erp.service.IErpMidUcnsDtService;
import com.powerbridge.bssp.erp.service.IErpPreUcnsDtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * 项目名称：bssp-admin
 * 类名称：ErpPreUcnsDtServiceImpl
 * 类描述：企业单耗预录入表 服务实现类
 * 创建人：lindapeng
 * 创建时间：2017/6/3 21:23
 * 修改人：
 * 修改时间：
 */
@Service("erpPreUcnsDtService")
public class ErpPreUcnsDtServiceImpl extends ErpBaseServiceImpl<ErpPreUcnsDtMapper, ErpPreUcnsDt> implements IErpPreUcnsDtService {
    @Autowired
    ErpPreUcnsDtMapper erpPreUcnsDtMapper;

    @Autowired
    private IErpMidUcnsDtService erpMidUcnsDtService;


    public Page<ErpPreUcnsDt> getListData(Page<ErpPreUcnsDt> page, ErpPreUcnsDt erpPreUcnsDt) {
        page.setRecords(erpPreUcnsDtMapper.getListData(page, erpPreUcnsDt));
        return page;
    }

    @Override
    public ErpPreUcnsDt getDataById(String uid) {
        return erpPreUcnsDtMapper.getDataById(uid);
    }

    /**
     * 从料件中间表取数据插入企业原始单耗
     *
     * @return
     */
    @Override
    public String getMidUcnsData() throws JSONException {
        _errorList.clear();
        //服务调用
        Logger logger = LoggerFactory.getLogger(this.getClass());

        JSONObject result = null;
        try {
            result = new JSONObject();
            EntityWrapper entityWrapper = new EntityWrapper();
            List<ErpMidUcnsDt> erpMidUcnsDtList = erpMidUcnsDtService.selectList(entityWrapper);
            if (erpMidUcnsDtList.size()==0) {
                result.put("code","0");
                result.put("message","中间表没有数据！");
                return result.toJSONString();
            }

            List <String> idList = new ArrayList<String>();
            HashMap keyMap = new HashMap();
            //未备案需要覆盖的成品序号+料件序号
            List <String>  delKeyList = new ArrayList<String>();
            //获得中间表所有的数据，分别取得uid串和成品序号 + 料号序号 + 单耗版本串,用于判断后序唯一主键
            for (ErpMidUcnsDt erpMidUcnsDt : erpMidUcnsDtList){
                idList.add(erpMidUcnsDt.getUid());
                String uncsVerno = erpMidUcnsDt.getUcnsVerno();
                if (uncsVerno == null) {
                    uncsVerno = "";
                }
                keyMap.put(erpMidUcnsDt.getEndprdSeqno()+"|"+erpMidUcnsDt.getMtpckSeqno()+"|"+uncsVerno,"");
        }
            HashMap delPreIdMap = new HashMap();
            //根据中间表成品序号 + 料号序号 + 单耗版本串过滤，得到已存在的成品序号 + 料号序号 + 单耗版本信息
            Map<String, ErpPreUcnsDt> erpPreUcnsDtMap = new HashMap<>();
            Map params = new HashMap();
            params.put("keyList", keyMap.keySet().toArray());
            List<ErpPreUcnsDt> erpPreUcnsDtKeyList =  erpPreUcnsDtMapper.getDataByCond(params);

            for (ErpPreUcnsDt erpPreUcnsDt : erpPreUcnsDtKeyList){
                String uncsVerno = erpPreUcnsDt.getUcnsVerno();
                if (uncsVerno == null) {
                    uncsVerno = "";
                }
                delPreIdMap.put(erpPreUcnsDt.getUid(),erpPreUcnsDt.getUid());
                erpPreUcnsDtMap.put(erpPreUcnsDt.getEndprdSeqno() + "|" + erpPreUcnsDt.getMtpckSeqno() + "|" + uncsVerno,erpPreUcnsDt);
            }

            List <ErpPreUcnsDt>  addErpPreUcnsDtList = new ArrayList<ErpPreUcnsDt>();
            for (ErpMidUcnsDt erpMidUcnsDt : erpMidUcnsDtList){
                String modfMarkcd = erpMidUcnsDt.getModfMarkcd();
                String uncsVerno = erpMidUcnsDt.getUcnsVerno();
                if (uncsVerno == null) {
                    uncsVerno = "";
                }
                String key = erpMidUcnsDt.getEndprdSeqno()+"|"+erpMidUcnsDt.getMtpckSeqno()+"|"+uncsVerno;
                //修改标志为“新增”时，当企业原始单耗中存在中间表的单耗时，则报错
                if (modfMarkcd.equals("3") && (erpPreUcnsDtMap.containsKey(key))) {
                    _errorList.add("企业原始单耗中已存在成品序号为 " + erpMidUcnsDt.getEndprdSeqno() + " 料件序号为 " + erpMidUcnsDt.getMtpckSeqno() + " 单耗版本号为 " + uncsVerno + " 的数据，不允许新增！");
                }
                //修改标志为“修改”或“删除”时，预处理表中不存在中间表的料件或序号时，则报错
                if ((modfMarkcd.equals("1") || modfMarkcd.equals("2")) && !erpPreUcnsDtMap.containsKey(key)) {
                    _errorList.add("企业原始单耗中不存在成品序号为 " + erpMidUcnsDt.getEndprdSeqno() + " 料件序号为 " + erpMidUcnsDt.getMtpckSeqno() + " 单耗版本号为 " + uncsVerno + " 的数据，不允许修改或删除！");
                }
                //修改标志为“修改”或“删除”时，存在中间表的相同数据时，如果修改标志为“新增”且未生成备案，则报错
                //修改标志为“修改”或“删除”时，存在中间表的相同数据时，如果修改标志为“删除”且已生成备案，则报错
                if ((modfMarkcd.equals("1") || modfMarkcd.equals("2")) && erpPreUcnsDtMap.containsKey(key)) {
                    ErpPreUcnsDt erpPreUcnsDt  = (ErpPreUcnsDt) erpPreUcnsDtMap.get(key);

                    if (erpPreUcnsDt.getModfMarkcd().equals("3") && erpPreUcnsDt.getSuccessFlag().equals("0")) {
                        _errorList.add("成品序号为 " + erpMidUcnsDt.getEndprdSeqno() + " 料件序号为 " + erpMidUcnsDt.getMtpckSeqno() + " 单耗版本号为 " + uncsVerno + " 的数据未生成备案单耗，不允许修改或删除！");
                    }
                    if (erpPreUcnsDt.getModfMarkcd().equals("2") && erpPreUcnsDt.getSuccessFlag().equals("1")) {
                        _errorList.add("成品序号为 " + erpMidUcnsDt.getEndprdSeqno() + " 料件序号为 " + erpMidUcnsDt.getMtpckSeqno() + " 单耗版本号为 " + uncsVerno + " 的数据已变更删除，不允许再次变更！");
                    }
                }

                ErpPreUcnsDt erpPreUcnsDt = new ErpPreUcnsDt();
                BeanUtils.copyProperties(erpMidUcnsDt, erpPreUcnsDt);
                erpPreUcnsDt.setUid(UUIDGenerator.getUUID());
                erpPreUcnsDt.setSuccessFlag("0");
                SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
                erpPreUcnsDt.setCreateBy(sysUser.getUserName());
                erpPreUcnsDt.setCreateTime(sysUser.getCreateTime());
                erpPreUcnsDt.setInputCopNo(sysUser.getInputCopNo());
                erpPreUcnsDt.setInputEtpsSccd(sysUser.getCopGbCode());
                erpPreUcnsDt.setInputCopName(sysUser.getInputCopName());
                addErpPreUcnsDtList.add(erpPreUcnsDt);
            }

            if (_errorList.size()>0) {
                exportErrorLog("d:\\erp_data\\bomerror_gp.txt");
                result.put("code","0");
                result.put("message","导入失败，详见错误日志 D:\\erp_data\\bomerror_gp.txt");
                erpMidUcnsDtService.deleteBatchIds(idList);
            }
            else {
                //删除和中间表冲突数据
                if (delPreIdMap.size()>0) {
                    entityWrapper = new EntityWrapper();
                    entityWrapper.in("UID",delPreIdMap.keySet().toArray());
                    delete(entityWrapper);
                }

                Boolean flag = insertBatch(addErpPreUcnsDtList);
                if (!flag) {
                    result.put("code","0");
                    result.put("message","导入失败！");
                    return result.toJSONString();
                }
                else {
                    //删除中间表数据
                    flag = erpMidUcnsDtService.deleteBatchIds(idList);
                    if (flag) {
                        result.put("code","1");
                        result.put("message","导入成功");
                    }
                    else {
                        result.put("code","0");
                        result.put("message","删除中间表数据失败");
                    }
                }
            }
        } catch (Exception e) {
            result.put("code","0");
            result.put("message",e.getMessage());
            logger.error(e.getMessage());
        }
        return result.toJSONString();

    }

    /**
     * 将企业原始单耗数据写入账册单耗
     *
     *
     */
    public String insertEmsUcnsDataByIds(String ids, String emsId) throws JSONException {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String Str = httpClientUtil.executeByGET(CommonConstants.contextPath +"/ems/emsPutrecBsc/list/"+emsId+"/view");
        JSONObject jsonObject = (JSONObject) JSONObject.parse(Str);
        String code = jsonObject.get("code").toString();
        //读取成功才读序号和变更次数
        BigDecimal chgTmsCnt = null;
        String seqNo = "";
        if (code.equals("1")) {
            String data = jsonObject.get("data").toString();
            Gson gson = new Gson();
            EmsPutrecBsc head = gson.fromJson(data.toString(), new TypeToken<EmsPutrecBsc>(){}.getType());
            chgTmsCnt = head.getChgTmsCnt();
            seqNo = head.getSeqNo();
        }
        else {
            return "无法选取指定账册";
        }
        SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
        List<EmsCusUcnsDt> emsCusUcnsDtList = new ArrayList<EmsCusUcnsDt>();
        List idList = Arrays.asList(ids.split(","));
        List<ErpPreUcnsDt> erpPreUcnsDtList =  selectBatchIds(idList);
        for (ErpPreUcnsDt erpPreUcnsDt : erpPreUcnsDtList){
            EmsCusUcnsDt emsCusUcnsDt = new EmsCusUcnsDt();
            BeanUtils.copyProperties(erpPreUcnsDt,emsCusUcnsDt);
            emsCusUcnsDt.setUid(UUIDGenerator.getUUID());
            emsCusUcnsDt.setCreateTime(sysUser.getCreateTime());
            emsCusUcnsDt.setCreateBy(sysUser.getCreateBy());
            emsCusUcnsDt.setCreateName(sysUser.getCreateName());
            emsCusUcnsDt.setSeqNo(seqNo);
            emsCusUcnsDt.setChgTmsCnt(chgTmsCnt);
            emsCusUcnsDt.setUcnsDclStucd("2");//单耗申报状态
            emsCusUcnsDt.setBondMtpckPrpr(BigDecimal.valueOf(100));//保税料件比例
            emsCusUcnsDt.setModfMarkcd("3");//修改标记
            emsCusUcnsDtList.add(emsCusUcnsDt);
            //企业原始料件赋已备案
            erpPreUcnsDt.setSuccessFlag("1");
        }
        if(emsCusUcnsDtList.size()>0){
            httpClientUtil = new HttpClientUtil();
            String array= JSONArray.toJSONString(emsCusUcnsDtList);
            Map<String, String> map = new HashMap<String, String>();
            map.put("jasonEmsCusUcnsDts", array);
            try {
                Str = httpClientUtil.executeByPOST(CommonConstants.contextPath +"/ems/emsPutrecUcnsDt/list/insertBatchData",map);
                jsonObject = (JSONObject) JSONObject.parse(Str);
                code = jsonObject.get("code").toString();
                //写入成功才改标志
                if (code.equals("1")) {
                    insertOrUpdateBatch(erpPreUcnsDtList);
                    return "生成备案单耗成功！";
                } else {
                    return jsonObject.get("message").toString();
                }
            } catch (Exception e) {
                throw new JSONException(e.getMessage());
            }
        }
        return "";
    }
}