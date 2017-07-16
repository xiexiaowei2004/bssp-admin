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
import com.powerbridge.bssp.ems.entity.EmsPutrecImg;
import com.powerbridge.bssp.erp.dao.ErpPreDtImgMapper;
import com.powerbridge.bssp.erp.entity.ErpMidDtImg;
import com.powerbridge.bssp.erp.entity.ErpPreDtImg;
import com.powerbridge.bssp.erp.service.IErpMidDtImgService;
import com.powerbridge.bssp.erp.service.IErpPreDtImgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * 项目名称：bssp-admin
 * 类名称：ErpPreDtImgServiceImpl
 * 类描述：料件预录入表 服务实现类
 * 创建人：lindapeng
 * 创建时间：2017/6/3 21:23
 * 修改人：
 * 修改时间：
 */
@Service("erpPreDtImgService")
public class ErpPreDtImgServiceImpl extends ErpBaseServiceImpl<ErpPreDtImgMapper, ErpPreDtImg> implements IErpPreDtImgService {

    @Autowired
    ErpPreDtImgMapper erpPreDtImgMapper;

    @Autowired
    private IErpMidDtImgService erpMidDtImgService;

    public Page<ErpPreDtImg> getListData(Page<ErpPreDtImg> page, ErpPreDtImg erpPreDtImg) {
        page.setRecords(erpPreDtImgMapper.getListData(page, erpPreDtImg));
        return page;
    }

    /**
     * 获取最大的商品序号
     *
     * @return 商品序号
     */
    @Override
    public BigDecimal getMaxGdsSeqno() {
        BigDecimal maxGdsSeqno = erpPreDtImgMapper.getMaxGdsSeqno();
        if (null == maxGdsSeqno) maxGdsSeqno = BigDecimal.valueOf(0.0);
        return maxGdsSeqno;
    }

    /**
     * 根据序号取成品信息
     *
     * @return 成品序号
     */
    public ErpPreDtImg getDataByGdsSeqno(String gdsSeqno) {
        ErpPreDtImg erpPreDtImg = erpPreDtImgMapper.getDataByGdsSeqno(gdsSeqno);
        return erpPreDtImg;
    }

    /**
     * 从料件中间表取数据插入企业原始料件
     *
     * @return
     */
    @Override
    public String getMidImgData() throws JSONException {
        _errorList.clear();
        //服务调用
        //Logger logger = LoggerFactory.getLogger(this.getClass());
        JSONObject result = new JSONObject();
        EntityWrapper entityWrapper = new EntityWrapper();
        List<ErpMidDtImg> erpMidDtImgList = erpMidDtImgService.selectList(entityWrapper);
        if (erpMidDtImgList.size()==0) {
            result.put("code","0");
            result.put("message","中间表没有数据！");
            return result.toJSONString();
        }

        List <String> idList = new ArrayList<String>();
        HashMap delPreIdMap = new HashMap();
        List <BigDecimal> gdsSeqnoList = new ArrayList<BigDecimal>();
        List <String> gdsMtnoList = new ArrayList<String>();
        //获得中间表所有的数据，分别取得uid串和序号串及料号串
        for (ErpMidDtImg erpMidDtImg : erpMidDtImgList){
            idList.add(erpMidDtImg.getUid());
            gdsSeqnoList.add(erpMidDtImg.getGdsSeqno());
            gdsMtnoList.add(erpMidDtImg.getGdsMtno());
        }
        //根据中间表料号过滤，得到料号和实体的值对
        HashMap gdsMtnoMap = new HashMap();
        entityWrapper = new EntityWrapper();
        entityWrapper.in("GDS_MTNO",gdsMtnoList);
        List <ErpPreDtImg>  erpPreDtImgList = selectList(entityWrapper);
        for (ErpPreDtImg erpPreDtImg : erpPreDtImgList) {
            gdsMtnoMap.put(erpPreDtImg.getGdsMtno(),erpPreDtImg);
            delPreIdMap.put(erpPreDtImg.getUid(),erpPreDtImg.getUid());
        }
        //根据中间表序号过滤，得到序号和实体的值对
        HashMap gdsSeqnoMap = new HashMap();
        entityWrapper = new EntityWrapper();
        entityWrapper.in("GDS_SEQNO",gdsSeqnoList);
        erpPreDtImgList = selectList(entityWrapper);
        for (ErpPreDtImg erpPreDtImg : erpPreDtImgList) {
            gdsSeqnoMap.put(erpPreDtImg.getGdsSeqno(),erpPreDtImg);
            delPreIdMap.put(erpPreDtImg.getUid(),erpPreDtImg.getUid());
        }
        List <ErpPreDtImg>  addErpPreDtImgList = new ArrayList<ErpPreDtImg>();
        for (ErpMidDtImg erpMidDtImg : erpMidDtImgList){
             String modfMarkcd = erpMidDtImg.getModfMarkcd();
             //修改标志为“新增”时，当企业原始料件中存在中间表的料件或序号时，则报错
             if (modfMarkcd.equals("3") && (gdsMtnoMap.containsKey(erpMidDtImg.getGdsMtno()) || gdsSeqnoMap.containsKey(erpMidDtImg.getGdsSeqno()))) {
                 if (gdsMtnoMap.containsKey(erpMidDtImg.getGdsMtno())) {
                     _errorList.add("企业原始料件已存在料号为" + erpMidDtImg.getGdsMtno() + "的料件，不允许新增！");
                 }
                 if (gdsSeqnoMap.containsKey(erpMidDtImg.getGdsSeqno())) {
                     _errorList.add("企业原始料件已存在序号为" + erpMidDtImg.getGdsSeqno() + "的料件，不允许新增！");
                 }
             }
             //修改标志为“修改”或“删除”时，预处理表中不存在中间表的料件或序号时，则报错
            if ((modfMarkcd.equals("1") || modfMarkcd.equals("2")) && !gdsMtnoMap.containsKey(erpMidDtImg.getGdsMtno()) && !gdsSeqnoMap.containsKey(erpMidDtImg.getGdsSeqno())) {
                if (!gdsMtnoMap.containsKey(erpMidDtImg.getGdsMtno())) {
                    _errorList.add("企业原始料件不存在料号为" + erpMidDtImg.getGdsMtno() + "的料件，不允许修改或删除！");
                }
                if (!gdsSeqnoMap.containsKey(erpMidDtImg.getGdsSeqno())) {
                    _errorList.add("企业原始料件不存在序号为" + erpMidDtImg.getGdsSeqno() + "的料件，不允许修改或删除！");
                }
            }
            //修改标志为“修改”或“删除”时，存在中间表的料件或序号时，如果修改标志为“新增”且未生成备案，则报错
            //修改标志为“修改”或“删除”时，存在中间表的料件或序号时，如果修改标志为“删除”且已生成备案，则报错
            if ((modfMarkcd.equals("1") || modfMarkcd.equals("2")) && gdsMtnoMap.containsKey(erpMidDtImg.getGdsMtno())) {
                ErpPreDtImg erpPreDtImg = (ErpPreDtImg) gdsMtnoMap.get(erpMidDtImg.getGdsMtno());
                if (erpPreDtImg.getModfMarkcd().equals("3") && erpPreDtImg.getSuccessFlag().equals("0")) {
                    _errorList.add("料号为" + erpMidDtImg.getGdsMtno() + "的料件未生成备案料件，不允许修改或删除！");
                }
                if (erpPreDtImg.getModfMarkcd().equals("2") && erpPreDtImg.getSuccessFlag().equals("1")) {
                    _errorList.add("料号为" + erpMidDtImg.getGdsMtno() + "的料件已变更删除，不允许再次变更！");
                }
            }
            if ((modfMarkcd.equals("1") || modfMarkcd.equals("2")) && gdsSeqnoMap.containsKey(erpMidDtImg.getGdsSeqno())) {
                ErpPreDtImg erpPreDtImg = (ErpPreDtImg) gdsSeqnoMap.get(erpMidDtImg.getGdsSeqno());
                if (erpPreDtImg.getModfMarkcd().equals("3") && erpPreDtImg.getSuccessFlag().equals("0")) {
                    _errorList.add("序号为" + erpMidDtImg.getGdsSeqno() + "的料件未生成备案料件，不允许修改或删除！");
                }
                if (erpPreDtImg.getModfMarkcd().equals("2") && erpPreDtImg.getSuccessFlag().equals("1")) {
                    _errorList.add("序号为" + erpMidDtImg.getGdsSeqno() + "的料件已变更删除，不允许再次变更！");
                }
            }

            ErpPreDtImg erpPreDtImg = new ErpPreDtImg();
            BeanUtils.copyProperties(erpMidDtImg, erpPreDtImg);
            erpPreDtImg.setUid(UUIDGenerator.getUUID());
            erpPreDtImg.setSuccessFlag("0");
            SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
            erpPreDtImg.setCreateBy(sysUser.getUserName());
            erpPreDtImg.setCreateTime(sysUser.getCreateTime());
            erpPreDtImg.setInputCopNo(sysUser.getInputCopNo());
            erpPreDtImg.setInputEtpsSccd(sysUser.getCopGbCode());
            erpPreDtImg.setInputCopName(sysUser.getInputCopName());
            addErpPreDtImgList.add(erpPreDtImg);

        }

        if (_errorList.size()>0) {
            exportErrorLog("d:\\erp_data\\imgerror_gp.txt");
            result.put("code","0");
            result.put("message","导入失败，详见错误日志 D:\\erp_data\\imgerror_gp.txt");
            erpMidDtImgService.deleteBatchIds(idList);
        }
        else {
            //删除和中间表冲突数据
            if (delPreIdMap.size()>0) {
                entityWrapper = new EntityWrapper();
                entityWrapper.in("UID",delPreIdMap.keySet().toArray());
                delete(entityWrapper);
            }

            Boolean flag = insertBatch(addErpPreDtImgList);
            if (!flag) {
                result.put("code","0");
                result.put("message","导入失败！");
                return result.toJSONString();
            }
            else {
                //删除中间表数据
                flag = erpMidDtImgService.deleteBatchIds(idList);
                if (flag) {
                    result.put("code","1");
                    result.put("message","导入成功");
/*                    if (delPreIdMap.keySet().toArray().length >0 ) {
                        result.put("code","1");
                        result.put("message","已导入"+ addErpPreDtImgList.size()+"条数据，另外有" + delPreIdMap.keySet().toArray().length + "条数据与现有系统冲突，已自动删除！");
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
     * 将企业原始料件数据写入账册料件
     *
     *
     */
    public String insertEmsImgDataByIds(String ids, String emsId) throws JSONException {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        //String Str = httpClientUtil.executeByGET(CommonConstants.contextPath +"/ems/emsPutrecBsc/list?UID="+emsId);
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
        List<EmsPutrecImg> emsPutrecImgList = new ArrayList<EmsPutrecImg>();
        List idList = Arrays.asList(ids.split(","));
        List<ErpPreDtImg> erpPreDtImgList =  selectBatchIds(idList);
        for (ErpPreDtImg erpPreDtImg : erpPreDtImgList){
            EmsPutrecImg emsPutrecImg = new EmsPutrecImg();
            BeanUtils.copyProperties(erpPreDtImg,emsPutrecImg);
            emsPutrecImg.setUid(UUIDGenerator.getUUID());
            emsPutrecImg.setCreateTime(sysUser.getCreateTime());
            emsPutrecImg.setCreateBy(sysUser.getCreateBy());
            emsPutrecImg.setCreateName(sysUser.getCreateName());
            emsPutrecImg.setSeqNo(seqNo);
            emsPutrecImg.setChgTmsCnt(chgTmsCnt);
            emsPutrecImg.setEndprdGdsSpcfModelDesc(erpPreDtImg.getGdsSpcfModelDesc());
/*            emsPutrecImg.setModfMarkcd("3");
            emsPutrecImg.setEtpsExeMarkcd("1");*/
            emsPutrecImgList.add(emsPutrecImg);
            //企业原始料件赋已备案
            erpPreDtImg.setSuccessFlag("1");
        }
        if(emsPutrecImgList.size()>0){
            String array= JSONArray.toJSONString(emsPutrecImgList);
            Map<String, String> map = new HashMap<String, String>();
            map.put("jasonEmsPutrecImgs", array);
            try {
                Str = httpClientUtil.executeByPOST(CommonConstants.contextPath +"/ems/emsPutrecImg/list/insertBatchData",map);
                jsonObject = (JSONObject) JSONObject.parse(Str);
                code = jsonObject.get("code").toString();
                //写入成功才改标志
                if (code.equals("1")) {
                    insertOrUpdateBatch(erpPreDtImgList);
                    return "生成备案料件成功！";
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