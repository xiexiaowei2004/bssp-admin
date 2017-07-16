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
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;
import com.powerbridge.bssp.ems.entity.EmsPutrecExg;
import com.powerbridge.bssp.erp.dao.ErpPreDtExgMapper;
import com.powerbridge.bssp.erp.entity.ErpMidDtExg;
import com.powerbridge.bssp.erp.entity.ErpPreDtExg;
import com.powerbridge.bssp.erp.service.IErpMidDtExgService;
import com.powerbridge.bssp.erp.service.IErpPreDtExgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * 项目名称：bssp-admin
 * 类名称：ErpPreDtExgServiceImpl
 * 类描述：成品预录入表 服务实现类
 * 创建人：lindapeng
 * 创建时间：2017/6/3 21:23
 * 修改人：
 * 修改时间：
 */
@Service("erpPreDtExgService")
public class ErpPreDtExgServiceImpl extends ErpBaseServiceImpl<ErpPreDtExgMapper, ErpPreDtExg> implements IErpPreDtExgService {
    @Autowired
    ErpPreDtExgMapper erpPreDtExgMapper;

    @Autowired
    private IErpMidDtExgService erpMidDtExgService;

    /**
     * 查询企业原始单耗列表数据
     *
     * @param page   分页
     * @param erpPreDtExg 企业原始成品
     * @return List<ErpPreDtExg>
     */
    public Page<ErpPreDtExg> getListData(Page<ErpPreDtExg> page, ErpPreDtExg erpPreDtExg) {
        page.setRecords(erpPreDtExgMapper.getListData(page, erpPreDtExg));
        return page;
    }

    /**
     * 获取最大的商品序号
     *
     * @return 商品序号
     */
    @Override
    public BigDecimal getMaxGdsSeqno() {
        BigDecimal maxGdsSeqno = erpPreDtExgMapper.getMaxGdsSeqno();
        if (null == maxGdsSeqno) maxGdsSeqno = BigDecimal.valueOf(0.0);
        return maxGdsSeqno;
    }

    /**
     * 根据序号取成品信息
     *
     * @return 成品序号
     */
    public ErpPreDtExg getDataByGdsSeqno(String gdsSeqno) {
        ErpPreDtExg erpPreDtExg = erpPreDtExgMapper.getDataByGdsSeqno(gdsSeqno);
        return erpPreDtExg;
    }

    /**
     * 从成品中间表取数据插入企业原始成品
     *
     * @return
     */
    @Override
    public String getMidExgData() throws JSONException {
        _errorList.clear();
        //服务调用
        //Logger logger = LoggerFactory.getLogger(this.getClass());
        JSONObject result = new JSONObject();
        EntityWrapper entityWrapper = new EntityWrapper();
        List<ErpMidDtExg> erpMidDtExgList = erpMidDtExgService.selectList(entityWrapper);
        if (erpMidDtExgList.size()==0) {
            result.put("code","0");
            result.put("message","中间表没有数据！");
            return result.toJSONString();
        }

        List <String> idList = new ArrayList<String>();
        HashMap delPreIdMap = new HashMap();
        List <BigDecimal> gdsSeqnoList = new ArrayList<BigDecimal>();
        List <String> gdsMtnoList = new ArrayList<String>();
        //获得中间表所有的数据，分别取得uid串和序号串及料号串
        for (ErpMidDtExg erpMidDtExg : erpMidDtExgList){
            idList.add(erpMidDtExg.getUid());
            gdsSeqnoList.add(erpMidDtExg.getGdsSeqno());
            gdsMtnoList.add(erpMidDtExg.getGdsMtno());
        }
        //根据中间表料号过滤，得到料号和实体的值对
        HashMap gdsMtnoMap = new HashMap();
        entityWrapper = new EntityWrapper();
        entityWrapper.in("GDS_MTNO",gdsMtnoList);
        List <ErpPreDtExg>  erpPreDtExgList = selectList(entityWrapper);
        for (ErpPreDtExg erpPreDtExg : erpPreDtExgList) {
            gdsMtnoMap.put(erpPreDtExg.getGdsMtno(),erpPreDtExg);
            delPreIdMap.put(erpPreDtExg.getUid(),erpPreDtExg.getUid());
        }
        //根据中间表序号过滤，得到序号和实体的值对
        HashMap gdsSeqnoMap = new HashMap();
        entityWrapper = new EntityWrapper();
        entityWrapper.in("GDS_SEQNO",gdsSeqnoList);
        erpPreDtExgList = selectList(entityWrapper);
        for (ErpPreDtExg erpPreDtExg : erpPreDtExgList) {
            gdsSeqnoMap.put(erpPreDtExg.getGdsSeqno(),erpPreDtExg);
            delPreIdMap.put(erpPreDtExg.getUid(),erpPreDtExg.getUid());
        }
        List <ErpPreDtExg>  addErpPreDtExgList = new ArrayList<ErpPreDtExg>();
        for (ErpMidDtExg erpMidDtExg : erpMidDtExgList){
            String modfMarkcd = erpMidDtExg.getModfMarkcd();
            //修改标志为“新增”时，当企业原始成品中存在中间表的成品或序号时，则报错
            if (modfMarkcd.equals("3") && (gdsMtnoMap.containsKey(erpMidDtExg.getGdsMtno()) || gdsSeqnoMap.containsKey(erpMidDtExg.getGdsSeqno()))) {
                if (gdsMtnoMap.containsKey(erpMidDtExg.getGdsMtno())) {
                    _errorList.add("企业原始成品已存在料号为" + erpMidDtExg.getGdsMtno() + "的成品，不允许新增！");
                }
                if (gdsSeqnoMap.containsKey(erpMidDtExg.getGdsSeqno())) {
                    _errorList.add("企业原始成品已存在序号为" + erpMidDtExg.getGdsSeqno() + "的成品，不允许新增！");
                }
            }
            //修改标志为“修改”或“删除”时，预处理表中不存在中间表的成品或序号时，则报错
            if ((modfMarkcd.equals("1") || modfMarkcd.equals("2")) && !gdsMtnoMap.containsKey(erpMidDtExg.getGdsMtno()) && !gdsSeqnoMap.containsKey(erpMidDtExg.getGdsSeqno())) {
                if (!gdsMtnoMap.containsKey(erpMidDtExg.getGdsMtno())) {
                    _errorList.add("企业原始成品不存在料号为" + erpMidDtExg.getGdsMtno() + "的成品，不允许修改或删除！");
                }
                if (!gdsSeqnoMap.containsKey(erpMidDtExg.getGdsSeqno())) {
                    _errorList.add("企业原始成品不存在序号为" + erpMidDtExg.getGdsSeqno() + "的成品，不允许修改或删除！");
                }
            }
            //修改标志为“修改”或“删除”时，存在中间表的成品或序号时，如果修改标志为“新增”且未生成备案，则报错
            //修改标志为“修改”或“删除”时，存在中间表的成品或序号时，如果修改标志为“删除”且已生成备案，则报错
            if ((modfMarkcd.equals("1") || modfMarkcd.equals("2")) && gdsMtnoMap.containsKey(erpMidDtExg.getGdsMtno())) {
                ErpPreDtExg erpPreDtExg = (ErpPreDtExg) gdsMtnoMap.get(erpMidDtExg.getGdsMtno());
                if (erpPreDtExg.getModfMarkcd().equals("3") && erpPreDtExg.getSuccessFlag().equals("0")) {
                    _errorList.add("料号为" + erpMidDtExg.getGdsMtno() + "的成品未生成备案成品，不允许修改或删除！");
                }
                if (erpPreDtExg.getModfMarkcd().equals("2") && erpPreDtExg.getSuccessFlag().equals("1")) {
                    _errorList.add("料号为" + erpMidDtExg.getGdsMtno() + "的成品已变更删除，不允许再次变更！");
                }
            }
            if ((modfMarkcd.equals("1") || modfMarkcd.equals("2")) && gdsSeqnoMap.containsKey(erpMidDtExg.getGdsSeqno())) {
                ErpPreDtExg erpPreDtExg = (ErpPreDtExg) gdsSeqnoMap.get(erpMidDtExg.getGdsSeqno());
                if (erpPreDtExg.getModfMarkcd().equals("3") && erpPreDtExg.getSuccessFlag().equals("0")) {
                    _errorList.add("序号为" + erpMidDtExg.getGdsSeqno() + "的成品未生成备案成品，不允许修改或删除！");
                }
                if (erpPreDtExg.getModfMarkcd().equals("2") && erpPreDtExg.getSuccessFlag().equals("1")) {
                    _errorList.add("序号为" + erpMidDtExg.getGdsSeqno() + "的成品已变更删除，不允许再次变更！");
                }
            }

            ErpPreDtExg erpPreDtExg = new ErpPreDtExg();
            BeanUtils.copyProperties(erpMidDtExg, erpPreDtExg);
            erpPreDtExg.setUid(UUIDGenerator.getUUID());
            erpPreDtExg.setSuccessFlag("0");
            SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
            erpPreDtExg.setCreateBy(sysUser.getUserName());
            erpPreDtExg.setCreateTime(sysUser.getCreateTime());
            erpPreDtExg.setInputCopNo(sysUser.getInputCopNo());
            erpPreDtExg.setInputEtpsSccd(sysUser.getCopGbCode());
            erpPreDtExg.setInputCopName(sysUser.getInputCopName());
            addErpPreDtExgList.add(erpPreDtExg);

        }

        if (_errorList.size()>0) {
            exportErrorLog("d:\\erp_data\\exgerror_gp.txt");
            result.put("code","0");
            result.put("message","导入失败，详见错误日志 D:\\erp_data\\exgerror_gp.txt");
            erpMidDtExgService.deleteBatchIds(idList);
        }
        else {
            //删除和中间表冲突数据
            if (delPreIdMap.size()>0) {
                entityWrapper = new EntityWrapper();
                entityWrapper.in("UID",delPreIdMap.keySet().toArray());
                delete(entityWrapper);
            }

            Boolean flag = insertBatch(addErpPreDtExgList);
            if (!flag) {
                result.put("code","0");
                result.put("message","导入失败！");
                return result.toJSONString();
            }
            else {
                //删除中间表数据
                flag = erpMidDtExgService.deleteBatchIds(idList);
                if (flag) {
                    result.put("code","1");
                    result.put("message","导入成功");
/*                    if (delPreIdMap.keySet().toArray().length >0 ) {
                        result.put("code","1");
                        result.put("message","已导入"+ addErpPreDtExgList.size()+"条数据，另外有" + delPreIdMap.keySet().toArray().length + "条数据与现有系统冲突，已自动删除！");
                    }
                    else {
                        result.put("code","1");
                        result.put("message","导入成功");
                    }*/
                }
                else {
                    result.put("code","0");
                    result.put("message","删除中间表数据失败");
                }
            }
        }
        return result.toJSONString();
    }

    /**
     * 将企业原始成品数据写入账册成品
     *
     *
     */
    public String insertEmsExgDataByIds(String ids, String emsId) throws JSONException {
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
        List<EmsPutrecExg> emsPutrecExgList = new ArrayList<EmsPutrecExg>();
        List idList = Arrays.asList(ids.split(","));
        List<ErpPreDtExg> erpPreDtExgList = selectBatchIds(idList);
        for (ErpPreDtExg erpPreDtExg : erpPreDtExgList) {
            EmsPutrecExg emsPutrecExg = new EmsPutrecExg();
            BeanUtils.copyProperties(erpPreDtExg, emsPutrecExg);
            emsPutrecExg.setUid(UUIDGenerator.getUUID());
            emsPutrecExg.setCreateTime(sysUser.getCreateTime());
            emsPutrecExg.setCreateBy(sysUser.getCreateBy());
            emsPutrecExg.setCreateName(sysUser.getCreateName());
            emsPutrecExg.setSeqNo(seqNo);
            emsPutrecExg.setChgTmsCnt(chgTmsCnt);
            emsPutrecExg.setEndprdGdsSpcfModelDesc(erpPreDtExg.getGdsSpcfModelDesc());
/*            emsPutrecExg.setModfMarkcd("3");
            emsPutrecExg.setEtpsExeMarkcd("1");*/
            emsPutrecExgList.add(emsPutrecExg);
            //企业原始成品赋已备案
            erpPreDtExg.setSuccessFlag("1");
        }
        if (emsPutrecExgList.size() > 0) {
            httpClientUtil = new HttpClientUtil();
            String array = JSONArray.toJSONString(emsPutrecExgList);
            Map<String, String> map = new HashMap<String, String>();
            map.put("jasonEmsPutrecExgs", array);
            try {
                Str = httpClientUtil.executeByPOST(CommonConstants.contextPath + "/ems/emsPutrecExg/list/insertBatchData", map);
                jsonObject = (JSONObject) JSONObject.parse(Str);
                code = jsonObject.get("code").toString();
                //写入成功才改标志
                if (code.equals("1")) {
                    insertOrUpdateBatch(erpPreDtExgList);
                    return "生成备案成品成功！";
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