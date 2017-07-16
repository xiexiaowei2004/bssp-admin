package com.powerbridge.bssp.erp.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.powerbridge.bssp.common.constants.CommonConstants;
import com.powerbridge.bssp.common.util.HttpClientUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.json.JSONException;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.ems.entity.EmsCusExg;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.erp.dao.ErpPreBondInvtDtMapper;
import com.powerbridge.bssp.erp.entity.ErpPreBondInvtDt;
import com.powerbridge.bssp.erp.service.IErpPreBondInvtDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 核注清单表体-预处理 服务实现类
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-19
 */
@Service("erpPreBondInvtDtService")
public class ErpPreBondInvtDtServiceImpl extends ErpBaseServiceImpl<ErpPreBondInvtDtMapper, ErpPreBondInvtDt> implements IErpPreBondInvtDtService {
    @Autowired
    ErpPreBondInvtDtMapper erpPreBondInvtDtMapper;

    /**
     * 查询企业原始清单列表数据
     *
     * @param page   分页
     * @param erpPreBondInvtDt 企业原始清单商品
     * @return List<ErpPreBondInvtDt>
     */
    public Page<ErpPreBondInvtDt> getListData(Page<ErpPreBondInvtDt> page, ErpPreBondInvtDt erpPreBondInvtDt) {
        page.setRecords(erpPreBondInvtDtMapper.getListData(page, erpPreBondInvtDt));
        return page;
    }

    /**
     * 根据账册料件或成品生成清单商品数据
     *
     * @param id 账册料件或成品id
     * @param mtpckEndprdMarkcd 进出口标志
     * @param etpsInnerInvtNo 清单企业内部编号
     * @return List<ErpPreBondInvtBsc>
     */
    public String addPreBondInvtDtByEmsList(String id, String mtpckEndprdMarkcd, String etpsInnerInvtNo) throws JSONException {
        JSONObject obj=new JSONObject();
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String Str = "";
        //料件
        if (mtpckEndprdMarkcd.equals("I")) {
            Str = httpClientUtil.executeByGET(CommonConstants.contextPath +"/ems/emsCusImg/list/"+id+"/view");
        }
        //成品
        else {
            Str = httpClientUtil.executeByGET(CommonConstants.contextPath +"/ems/emsCusExg/list/"+id+"/view");
        }
        JSONObject jsonObject = (JSONObject) JSONObject.parse(Str);
        String code = jsonObject.get("code").toString();
        if (code.equals("1")) {
            String data = jsonObject.get("data").toString();
            Gson gson = new Gson();
            ErpPreBondInvtDt erpPreBondInvtDt = new ErpPreBondInvtDt();
            erpPreBondInvtDt.setUid(UUIDGenerator.getUUID());
            erpPreBondInvtDt.setEtpsInnerInvtNo(etpsInnerInvtNo);

            BigDecimal maxListGno = erpPreBondInvtDtMapper.getMaxListGNo(etpsInnerInvtNo);
            if (maxListGno == null) {
                maxListGno = new BigDecimal(0);
            }
            maxListGno = maxListGno.add(new BigDecimal(1));
            erpPreBondInvtDt.setListGNo(maxListGno);
            //料件
            if (mtpckEndprdMarkcd.equals("I")) {
                EmsCusImg img = gson.fromJson(data.toString(), new TypeToken<EmsCusImg>(){}.getType());
                erpPreBondInvtDt.setCodeTs(img.getGdecd());
                erpPreBondInvtDt.setCopGNo(img.getGdsMtno());
                erpPreBondInvtDt.setCurr(img.getDclCurrcd());
                erpPreBondInvtDt.setDecPrice(img.getDclUprcAmt());
                erpPreBondInvtDt.setQty(img.getDclQty());
                erpPreBondInvtDt.setDutyMode(img.getLvyrlfModecd());
                erpPreBondInvtDt.setEmsGNo(img.getGdsSeqno());
                erpPreBondInvtDt.setgModel(img.getEndprdGdsSpcfModelDesc());
                erpPreBondInvtDt.setUnit(img.getDclUnitcd());
                erpPreBondInvtDt.setUnit1(img.getLawfUnitcd());
                erpPreBondInvtDt.setUnit2(img.getSecdLawfUnitcd());
                erpPreBondInvtDt.setgName(img.getGdsNm());
            }
            //成品
            else {
                EmsCusExg exg = gson.fromJson(data.toString(), new TypeToken<EmsCusExg>(){}.getType());
                erpPreBondInvtDt.setCodeTs(exg.getGdecd());
                erpPreBondInvtDt.setCopGNo(exg.getGdsMtno());
                erpPreBondInvtDt.setCurr(exg.getDclCurrcd());
                erpPreBondInvtDt.setDecPrice(exg.getDclUprcAmt());
                erpPreBondInvtDt.setQty(exg.getDclQty());
                erpPreBondInvtDt.setDutyMode(exg.getLvyrlfModecd());
                erpPreBondInvtDt.setEmsGNo(exg.getGdsSeqno());
                erpPreBondInvtDt.setgModel(exg.getEndprdGdsSpcfModelDesc());
                erpPreBondInvtDt.setUnit(exg.getDclUnitcd());
                erpPreBondInvtDt.setUnit1(exg.getLawfUnitcd());
                erpPreBondInvtDt.setUnit2(exg.getSecdLawfUnitcd());
                erpPreBondInvtDt.setgName(exg.getGdsNm());
            }

            Boolean flag = insert(erpPreBondInvtDt);
            if (flag) {
                obj.put("code","1");
                obj.put("message","生成清单商品成功");
                obj.put("data", JSONObject.toJSON(erpPreBondInvtDt));
            }
            else {
                obj.put("code","0");
                obj.put("message","生成清单商品失败");
            }
        }
        else {
            obj.put("code","0");
            obj.put("message","获取账册料件失败");
        }
        return obj.toJSONString();
    }
}
