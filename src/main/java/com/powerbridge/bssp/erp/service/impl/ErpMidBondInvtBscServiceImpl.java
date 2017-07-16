package com.powerbridge.bssp.erp.service.impl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.powerbridge.bssp.common.constants.CommonConstants;
import com.powerbridge.bssp.common.util.HttpClientUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.cop.entity.CopEnt;
import com.powerbridge.bssp.ems.entity.EmsCusExg;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.erp.dao.ErpMidBondInvtBscMapper;
import com.powerbridge.bssp.erp.entity.ErpMidBondInvtBsc;
import com.powerbridge.bssp.erp.entity.ErpMidBondInvtDt;
import com.powerbridge.bssp.erp.service.IErpMidBondInvtBscService;
import com.powerbridge.bssp.erp.service.IErpMidBondInvtDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 核注清单表头-中间表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-19
 */
@Service("erpMidBondInvtBscService")
public class ErpMidBondInvtBscServiceImpl extends ErpBaseServiceImpl<ErpMidBondInvtBscMapper, ErpMidBondInvtBsc> implements IErpMidBondInvtBscService {
    @Autowired
    private IErpMidBondInvtDtService erpMidBondInvtDtService;
    //主表校验
    private HashMap getCheckNullMap_head() {
        HashMap map = new <String,String> HashMap();
        map.put("putrecNo","账册编号");
        map.put("etpsInnerInvtNo","清单企业内部编号");
        map.put("bizopEtpsno","经营单位编码");
        map.put("rcvgdEtpsno","收发货单位编码");
        map.put("dclEtpsno","申报单位编码");
        map.put("mtpckEndprdMarkcd","料件/成品标志");
        map.put("supvModecd","监管方式");
        map.put("trspModecd","运输方式");
        map.put("impexpMarkcd","进出口标志");
        map.put("impexpPortcd","进出口岸");
        map.put("dclPlcCuscd","申报地海关");
        map.put("natcd","启运运抵国别");
        map.put("bondInvtTypecd","清单类型");
        map.put("inputEtpsno","录入单位编码");
        map.put("dclcusFlag","报关标志");

        return map;
    }
    //从表校验
    private HashMap getCheckNullMap_dt() {
        HashMap map = new <String,String> HashMap();
        map.put("listGNo","商品序号");
        map.put("copGNo","商品料号");
        map.put("countryCode","产销国(地区)");
        map.put("decPrice","企业申报单价");
        map.put("decTotal","企业申报总价");
        map.put("curr","币制");
        map.put("qty","申报数量");
        map.put("qty1","法定数量");
        map.put("dutyMode","征免方式");
        map.put("useType","用途");
        return map;
    }
    private HashMap getColumnMap() {
        HashMap map = new <Integer,String> HashMap();
        map.put(0,"putrecNo"); //账册编号
        map.put(1,"etpsInnerInvtNo"); //清单企业内部编号
        map.put(2,"bizopEtpsno"); //经营单位编码
        map.put(3,"rcvgdEtpsno");
        map.put(4,"dclEtpsno");
        map.put(5,"mtpckEndprdMarkcd");
        map.put(6,"supvModecd");
        map.put(7,"trspModecd");
        map.put(8,"impexpMarkcd");
        map.put(9,"impexpPortcd");
        map.put(10,"dclPlcCuscd");
        map.put(11,"natcd");
        map.put(12,"bondInvtTypecd");
        map.put(13,"inputEtpsno");
        map.put(14,"dclcusFlag");
        map.put(15,"dclcusTypecd");
        map.put(16,"decTypecd");
        map.put(17,"listType");
        map.put(18,"rltInvtNo");
        map.put(19,"rltPutrecNo");
        map.put(20,"applyNo");
        map.put(21,"corrEntryDclEtpsno");
        map.put(22,"rltEntryBizopEtpsno");
        map.put(23,"rltEntryRcvgdEtpsno");
        map.put(24,"rltEntryDclEtpsno");
        map.put(25,"rltEntryNo"); //关联报关单编号
        map.put(26,"rmk");
        map.put(27,"listGNo");
        map.put(28,"copGNo");
        map.put(29,"countryCode");
        map.put(30,"decPrice");
        map.put(31,"decTotal");
        map.put(32,"curr");
        map.put(33,"qty");
        map.put(34,"qty1");
        map.put(35,"qty2");
        map.put(36,"factor1");
        map.put(37,"factor2");
        map.put(38,"factorWt");
        map.put(39,"grossWt");
        map.put(40,"netWt");
        map.put(41,"dutyMode");
        map.put(42,"useType");
        map.put(43,"entVersion");
        map.put(44,"entryGdsSeqno");
        map.put(45,"applyTbSeqno");
        map.put(46,"dtRmk");
        return map;
    }

    private CopEnt getCopEnt(String tradeCode, String message, String fileName, int row) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String Str = httpClientUtil.executeByGET(CommonConstants.contextPath +"/cop/copEnt/list/getCopEnt?tradeCode="+tradeCode);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(Str);
        String code = jsonObject.get("code").toString();
        if (code.equals("1")) {
            Gson gson = new Gson();
            CopEnt copEnt = gson.fromJson(jsonObject.get("data").toString(), new TypeToken<CopEnt>(){}.getType());
            return copEnt;
        }
        else {
            _errorList.add(fileName + ": 第" + row + "行"+message + tradeCode + "不存在!");
            return null;
        }

    }

    @Override
    public Boolean dealExcelDoc(File fs) throws Exception{
        try {
            ErpMidBondInvtBsc erpMidBondInvtBsc = new ErpMidBondInvtBsc();
            HashMap columnMap = getColumnMap();
            HashMap checkMap_head = getCheckNullMap_head();
            HashMap checkMap_dt = getCheckNullMap_dt();
            //字符型在excel中如果是代码，会被识别成数值型
            HashSet<String> stringSet = new HashSet<String>();
            stringSet.add("putrecNo");
            stringSet.add("etpsInnerInvtNo");
            stringSet.add("bizopEtpsno");
            stringSet.add("rcvgdEtpsno");
            stringSet.add("dclEtpsno");
            stringSet.add("supvModecd");
            stringSet.add("trspModecd");
            stringSet.add("impexpMarkcd");
            stringSet.add("impexpPortcd");
            stringSet.add("dclPlcCuscd");
            stringSet.add("natcd");
            stringSet.add("inputEtpsno");
            stringSet.add("dclcusFlag");
            stringSet.add("dclcusTypecd");
            stringSet.add("bondInvtTypecd");
            stringSet.add("decTypecd");
            stringSet.add("countryCode");
            stringSet.add("curr");
            stringSet.add("dutyMode");
            stringSet.add("useType");
            stringSet.add("corrEntryDclEtpsno");
            stringSet.add("rltEntryBizopEtpsno");
            stringSet.add("rltEntryRcvgdEtpsno");
            stringSet.add("rltEntryDclEtpsno");
            stringSet.add("rltEntryNo");

            List columnList = getDataFromExcel(fs , erpMidBondInvtBsc, columnMap, stringSet);
            List headList = new ArrayList ();
            List dtList = new ArrayList ();
            String etpsInnerInvtNo_t = "";
            String etpsInnerInvtNo = "";
            String emsNo = "";
            String mtpckEndprdMarkcd = "";
            HashMap errMap = new HashMap();
            HashMap<String, String> etpsInnerInvtNoMap = new HashMap<String, String>();
            if (columnList!=null && columnList.size()>0) {
                int row = 1;
                for (Object bo: columnList) {
                    if ((((ErpMidBondInvtBsc) bo).getCopGNo()==null || ((ErpMidBondInvtBsc) bo).getCopGNo().equals("")) && ((ErpMidBondInvtBsc) bo).getListGNo()==null) {
                        break;
                    }
                    if (((ErpMidBondInvtBsc) bo).getEtpsInnerInvtNo()!=null && !((ErpMidBondInvtBsc) bo).getEtpsInnerInvtNo().equals("")) {
                        etpsInnerInvtNo = ((ErpMidBondInvtBsc) bo).getEtpsInnerInvtNo();
                        emsNo = ((ErpMidBondInvtBsc) bo).getPutrecNo();
                        mtpckEndprdMarkcd = ((ErpMidBondInvtBsc) bo).getMtpckEndprdMarkcd();

                        etpsInnerInvtNo_t = etpsInnerInvtNo_t + "," + etpsInnerInvtNo;

                        ((ErpMidBondInvtBsc) bo).setUid(UUIDGenerator.getUUID());
                        //企业内部编号是唯一主键
                        if (!etpsInnerInvtNoMap.containsKey(etpsInnerInvtNo)) {
                            etpsInnerInvtNoMap.put(etpsInnerInvtNo, etpsInnerInvtNo);
                            checkNull(bo,checkMap_head, row, fs.getName());
                            String bizopEtpsno = ((ErpMidBondInvtBsc)bo).getBizopEtpsno();
                            if (bizopEtpsno!=null && !bizopEtpsno.equals("")) {
                                CopEnt copEnt = getCopEnt(bizopEtpsno , "经营单位编码", fs.getName(), row);
                                if (copEnt != null) {
                                    ((ErpMidBondInvtBsc) bo).setBizopEtpsSccd(copEnt.getCopGbCode());
                                    ((ErpMidBondInvtBsc) bo).setBizopEtpsNm(copEnt.getEntName());
                                }
                            }
                            String rcvgdEtpsno = ((ErpMidBondInvtBsc)bo).getRcvgdEtpsno();
                            if (rcvgdEtpsno!=null && !rcvgdEtpsno.equals("")) {
                                CopEnt copEnt = getCopEnt(rcvgdEtpsno, "收发货单位编码", fs.getName(), row);
                                if (copEnt != null) {
                                    ((ErpMidBondInvtBsc) bo).setRvsngdEtpsSccd(copEnt.getCopGbCode());
                                    ((ErpMidBondInvtBsc) bo).setRcvgdEtpsNm(copEnt.getEntName());
                                }
                            }
                            String dclEtpsno = ((ErpMidBondInvtBsc)bo).getDclEtpsno();
                            if (dclEtpsno!=null && !dclEtpsno.equals("")) {
                                CopEnt copEnt = getCopEnt(dclEtpsno, "申报单位编码", fs.getName(), row);
                                if (copEnt != null) {
                                    ((ErpMidBondInvtBsc) bo).setDclEtpsSccd(copEnt.getCopGbCode());
                                    ((ErpMidBondInvtBsc) bo).setDclEtpsNm(copEnt.getEntName());
                                }
                            }
                            String inputCopNo = ((ErpMidBondInvtBsc)bo).getInputCopNo();
                            if (inputCopNo!=null && !inputCopNo.equals("")) {
                                CopEnt copEnt = getCopEnt(inputCopNo, "录入单位编码", fs.getName(), row);
                                if (copEnt != null) {
                                    ((ErpMidBondInvtBsc) bo).setInputEtpsSccd(copEnt.getCopGbCode());
                                    ((ErpMidBondInvtBsc) bo).setInputCopName(copEnt.getEntName());
                                }
                            }

                            String corrEntryDclEtpsno = ((ErpMidBondInvtBsc)bo).getCorrEntryDclEtpsno();
                            if (corrEntryDclEtpsno!=null && !corrEntryDclEtpsno.equals("")) {
                                CopEnt copEnt = getCopEnt(corrEntryDclEtpsno, "对应报关单申报单位编码", fs.getName(), row);
                                if (copEnt != null) {
                                    ((ErpMidBondInvtBsc) bo).setCorrEntryDclEtpsSccd(copEnt.getCopGbCode());
                                    ((ErpMidBondInvtBsc) bo).setCorrEntryDclEtpsNm(copEnt.getEntName());
                                }
                            }

                            String rltEntryBizopEtpsno = ((ErpMidBondInvtBsc)bo).getRltEntryBizopEtpsno();
                            if (rltEntryBizopEtpsno!=null && !rltEntryBizopEtpsno.equals("")) {
                                CopEnt copEnt = getCopEnt(rltEntryBizopEtpsno, "关联报关单经营单位编码", fs.getName(), row);
                                if (copEnt != null) {
                                    ((ErpMidBondInvtBsc) bo).setRltEntryBizopEtpsSccd(copEnt.getCopGbCode());
                                    ((ErpMidBondInvtBsc) bo).setRltEntryBizopEtpsNm(copEnt.getEntName());
                                }
                            }

                            String rltEntryRcvgdEtpsno = ((ErpMidBondInvtBsc)bo).getRltEntryRcvgdEtpsno();
                            if (rltEntryRcvgdEtpsno!=null && !rltEntryRcvgdEtpsno.equals("")) {
                                CopEnt copEnt = getCopEnt(rltEntryRcvgdEtpsno, "关联报关单收发货单位编码", fs.getName(), row);
                                if (copEnt != null) {
                                    ((ErpMidBondInvtBsc) bo).setRltEntryRvsngdEtpsSccd(copEnt.getCopGbCode());
                                    ((ErpMidBondInvtBsc) bo).setRltEntryRcvgdEtpsNm(copEnt.getEntName());
                                }
                            }

                            String rltEntryDclEtpsno = ((ErpMidBondInvtBsc)bo).getRltEntryDclEtpsno();
                            if (rltEntryDclEtpsno!=null && !rltEntryDclEtpsno.equals("")) {
                                CopEnt copEnt = getCopEnt(rltEntryDclEtpsno, "关联报关单申报单位编码", fs.getName(), row);
                                if (copEnt != null) {
                                    ((ErpMidBondInvtBsc) bo).setRltEntryDclEtpsSccd(copEnt.getCopGbCode());
                                    ((ErpMidBondInvtBsc) bo).setRltEntryDclEtpsNm(copEnt.getEntName());
                                }
                            }

                            if (((ErpMidBondInvtBsc)bo).getMtpckEndprdMarkcd().equals("料件")) {
                                ((ErpMidBondInvtBsc) bo).setMtpckEndprdMarkcd("I");
                            }
                            else if (((ErpMidBondInvtBsc)bo).getMtpckEndprdMarkcd().equals("成品")) {
                                ((ErpMidBondInvtBsc) bo).setMtpckEndprdMarkcd("E");
                            }
                            if (((ErpMidBondInvtBsc)bo).getImpexpMarkcd().equals("进口")) {
                                ((ErpMidBondInvtBsc) bo).setImpexpMarkcd("I");
                            }
                            else if (((ErpMidBondInvtBsc)bo).getImpexpMarkcd().equals("出口")) {
                                ((ErpMidBondInvtBsc) bo).setImpexpMarkcd("E");
                            }
                            headList.add((ErpMidBondInvtBsc) bo);
                        }
                        else {
                            _errorList.add("文档   " + fs.getName() + ": 企业内部编号" + etpsInnerInvtNo + "有重复!");
                        }
                    }
                    //表头有清单内部编号，才校验表体和新增表体
                    if (etpsInnerInvtNo!="" && emsNo!="" && mtpckEndprdMarkcd!="") {
                        //校验从表空
                        checkNull(bo,checkMap_dt, row, fs.getName());
                        ErpMidBondInvtDt erpMidBondInvtDt = new ErpMidBondInvtDt();
                        String copGNo = ((ErpMidBondInvtBsc) bo).getCopGNo();

                        if (copGNo!=null && !copGNo.equals("") && emsNo!=null && !emsNo.equals("")) {
                            //根据“商品料号”自动带入“备案序号、商品编码、商品名称、商品规格型号、申报计量单位、法定计量单位、法定第二计量单位”
                            HttpClientUtil httpClientUtil = new HttpClientUtil();
                            String Str = "";

                            //料件
                            if (mtpckEndprdMarkcd.equals("I")) {
                                EmsCusImg emsCusImg = new EmsCusImg();
                                emsCusImg.setEmsNo(emsNo);
                                emsCusImg.setGdsMtno(copGNo);
                                Str = httpClientUtil.executeByGET(CommonConstants.contextPath +"/ems/emsCusImg/list/selectImgsByEmsNo?emsNo="+emsNo+"&gdsMtno="+copGNo);
                            }
                            //成品
                            else {
                                EmsCusExg emsCusExg = new EmsCusExg();
                                emsCusExg.setEmsNo(emsNo);
                                emsCusExg.setGdsMtno(copGNo);
                                Str = httpClientUtil.executeByGET(CommonConstants.contextPath +"/ems/emsCusExg/list/selectExgsByEmsNo?emsNo="+emsNo+"&gdsMtno="+copGNo);
                            }
                            JSONObject jsonObject = (JSONObject) JSONObject.parse(Str);
                            String code = jsonObject.get("code").toString();
                            if (code.equals("1")) {
                                Gson gson = new Gson();
                                if (mtpckEndprdMarkcd.equals("I")) {
                                    EmsCusImg img = gson.fromJson(jsonObject.get("data").toString(), new TypeToken<EmsCusImg>(){}.getType());
                                    if (img != null) {
                                        erpMidBondInvtDt.setEmsGNo(img.getGdsSeqno());
                                        erpMidBondInvtDt.setgName(img.getGdsNm());
                                        erpMidBondInvtDt.setCodeTs(img.getGdecd());
                                        erpMidBondInvtDt.setgModel(img.getEndprdGdsSpcfModelDesc());
                                        erpMidBondInvtDt.setUnit(img.getDclUnitcd());
                                        erpMidBondInvtDt.setUnit1(img.getLawfUnitcd());
                                        erpMidBondInvtDt.setUnit2(img.getSecdLawfUnitcd());
                                    }
                                }
                                else {
                                    EmsCusExg exg = gson.fromJson(jsonObject.get("data").toString(), new TypeToken<EmsCusExg>(){}.getType());
                                    if (exg != null) {
                                        erpMidBondInvtDt.setEmsGNo(exg.getGdsSeqno());
                                        erpMidBondInvtDt.setgName(exg.getGdsNm());
                                        erpMidBondInvtDt.setCodeTs(exg.getGdecd());
                                        erpMidBondInvtDt.setgModel(exg.getEndprdGdsSpcfModelDesc());
                                        erpMidBondInvtDt.setUnit(exg.getDclUnitcd());
                                        erpMidBondInvtDt.setUnit1(exg.getLawfUnitcd());
                                        erpMidBondInvtDt.setUnit2(exg.getSecdLawfUnitcd());
                                    }
                                }

                            }
                            else {
                                //_errorList.add("文档   " + fs.getName() + "第" + row + "行 " + jsonObject.get("message").toString());
                                if (!errMap.containsKey("文档   " + fs.getName() + " " + jsonObject.get("message").toString())) {
                                    errMap.put("文档   " + fs.getName() + " " + jsonObject.get("message").toString(),"");
                                    _errorList.add("文档   " + fs.getName() + " " + jsonObject.get("message").toString());
                                }

                            }
                        }

                        if (_errorList.size() == 0) {
                            erpMidBondInvtDt.setUid(UUIDGenerator.getUUID());
                            erpMidBondInvtDt.setEtpsInnerInvtNo(etpsInnerInvtNo);
                            erpMidBondInvtDt.setListGNo(((ErpMidBondInvtBsc) bo).getListGNo());
                            erpMidBondInvtDt.setApplyTbSeqno(((ErpMidBondInvtBsc) bo).getApplyTbSeqno());
                            erpMidBondInvtDt.setCopGNo(((ErpMidBondInvtBsc) bo).getCopGNo());
                            erpMidBondInvtDt.setCountryCode(((ErpMidBondInvtBsc) bo).getCountryCode());
                            erpMidBondInvtDt.setCurr(((ErpMidBondInvtBsc) bo).getCurr());
                            erpMidBondInvtDt.setDecPrice(((ErpMidBondInvtBsc) bo).getDecPrice());
                            erpMidBondInvtDt.setDecTotal(((ErpMidBondInvtBsc) bo).getDecTotal());
                            erpMidBondInvtDt.setDutyMode(((ErpMidBondInvtBsc) bo).getDutyMode());
                            erpMidBondInvtDt.setEntryGdsSeqno(((ErpMidBondInvtBsc) bo).getEntryGdsSeqno());
                            erpMidBondInvtDt.setEntVersion(((ErpMidBondInvtBsc) bo).getEntVersion());
                            erpMidBondInvtDt.setFactor1(((ErpMidBondInvtBsc) bo).getFactor1());
                            erpMidBondInvtDt.setFactor2(((ErpMidBondInvtBsc) bo).getFactor1());
                            erpMidBondInvtDt.setFactorWt(((ErpMidBondInvtBsc) bo).getFactorWt());
                            erpMidBondInvtDt.setGrossWt(((ErpMidBondInvtBsc) bo).getGrossWt());
                            erpMidBondInvtDt.setNetWt(((ErpMidBondInvtBsc) bo).getNetWt());
                            erpMidBondInvtDt.setQty(((ErpMidBondInvtBsc) bo).getQty());
                            erpMidBondInvtDt.setQty1(((ErpMidBondInvtBsc) bo).getQty1());
                            erpMidBondInvtDt.setQty2(((ErpMidBondInvtBsc) bo).getQty2());
                            erpMidBondInvtDt.setUseType(((ErpMidBondInvtBsc) bo).getUseType());
                            erpMidBondInvtDt.setRmk(((ErpMidBondInvtBsc) bo).getDtRmk());

                            dtList.add(erpMidBondInvtDt);
                        }
                    }

                    row = row + 1;
                }
            }
            if (etpsInnerInvtNo_t.equals("")) {
                _errorList.add("文档   " + fs.getName() + ": 没有企业内部编号!");
            }

            if (_errorList.size() > 0) {
                //输出错误信息
                exportErrorLog(fs.getParent()+"error.txt");
                return false;
            }
            else {
                insertBatchErp(headList);
                erpMidBondInvtDtService.insertBatchErp(dtList);
                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
