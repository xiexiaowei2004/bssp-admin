package com.powerbridge.bssp.erp.service.impl;

import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.erp.dao.ErpMidUcnsDtMapper;
import com.powerbridge.bssp.erp.entity.ErpMidUcnsDt;
import com.powerbridge.bssp.erp.service.IErpMidUcnsDtService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：ErpMidDtImgServiceImpl
 * 类描述：企业中间表单损耗服务实现
 * 创建人：lindapeng
 * 创建时间：2017/6/2 21:23
 * 修改人：
 * 修改时间：
 */
@Service("erpMidUcnsDtService")
public class ErpMidUcnsDtServiceImpl extends ErpBaseServiceImpl<ErpMidUcnsDtMapper, ErpMidUcnsDt> implements IErpMidUcnsDtService {
    @Override
    public Boolean dealExcelDoc(File fs)  throws Exception{
        try {
            ErpMidUcnsDt erpMidUcnsDt = new ErpMidUcnsDt();
            HashMap columnMap = getColumnMap();
            HashMap checkMap = getCheckNullMap();
            HashSet<String> stringSet = new HashSet<String>();
            stringSet.add("natcd");
            stringSet.add("dclCurrcd");
            stringSet.add("lvyrlfModecd");
            stringSet.add("bondedFlag");
            stringSet.add("modfMarkcd");
            stringSet.add("ucnsDclStucd");
            String fileName = fs.getName();
            List columnList = getDataFromExcel(fs , erpMidUcnsDt, columnMap, stringSet);
            if (columnList!=null && columnList.size()>0) {
                int row = 1;
                for (Object bo: columnList) {
                    if ((((ErpMidUcnsDt) bo).getUcnsVerno()==null || ((ErpMidUcnsDt) bo).getUcnsVerno().equals("")) && ((ErpMidUcnsDt) bo).getEndprdSeqno()==null && ((ErpMidUcnsDt) bo).getMtpckSeqno()==null) {
                        break;
                    }
                    if (((ErpMidUcnsDt) bo).getTgblLossRate() == null) {
                        ((ErpMidUcnsDt) bo).setTgblLossRate(new BigDecimal(0));
                    }
                    if (((ErpMidUcnsDt) bo).getIntgbLossRate() == null) {
                        ((ErpMidUcnsDt) bo).setIntgbLossRate(new BigDecimal(0));
                    }
                    checkNull(bo,checkMap, row, fileName);
                    if (((ErpMidUcnsDt)bo).getModfMarkcd().equals("新增")) {
                        ((ErpMidUcnsDt) bo).setModfMarkcd("3");
                    }
                    if (((ErpMidUcnsDt)bo).getModfMarkcd().equals("删除")) {
                        ((ErpMidUcnsDt) bo).setModfMarkcd("2");
                    }
                    if (((ErpMidUcnsDt)bo).getModfMarkcd().equals("修改")) {
                        ((ErpMidUcnsDt) bo).setModfMarkcd("1");
                    }
                    if (((ErpMidUcnsDt)bo).getUcnsDclStucd().equals("未申报")) {
                        ((ErpMidUcnsDt) bo).setUcnsDclStucd("1");
                    }
                    if (((ErpMidUcnsDt)bo).getUcnsDclStucd().equals("已申报")) {
                        ((ErpMidUcnsDt) bo).setUcnsDclStucd("2");
                    }
                    if (((ErpMidUcnsDt)bo).getUcnsDclStucd().equals("已确定")) {
                        ((ErpMidUcnsDt) bo).setUcnsDclStucd("3");
                    }
                    if (!((ErpMidUcnsDt)bo).getUcnsDclStucd().equals("1") && !((ErpMidUcnsDt)bo).getUcnsDclStucd().equals("2") && !((ErpMidUcnsDt)bo).getUcnsDclStucd().equals("3")) {
                        _errorList.add(fileName + ": 第" + row + "行修改标记必须为'1','2','3'或'未申报','已申报','已确定'！");
                    }
                    //校验单耗和净耗必须大于0
                    if (((ErpMidUcnsDt) bo).getUcnsQty() == null || ((ErpMidUcnsDt) bo).getUcnsQty().compareTo(new BigDecimal(0))<=0) {
                        _errorList.add(fileName + ": 第" + row + "行单耗必须大于0！");
                    }
                    if (((ErpMidUcnsDt) bo).getNetUseupQty() == null || ((ErpMidUcnsDt) bo).getNetUseupQty().compareTo(new BigDecimal(0))<=0) {
                        _errorList.add(fileName + ": 第" + row + "行净耗必须大于0！");
                    }
                    //保税料件比例必须大于0、小于等于100，为空则导入100.
                    if (((ErpMidUcnsDt) bo).getBondMtpckPrpr() == null) {
                        ((ErpMidUcnsDt) bo).setBondMtpckPrpr(new BigDecimal(100));
                    }
                    else {
                       if (((ErpMidUcnsDt) bo).getBondMtpckPrpr().compareTo(new BigDecimal(0))<=0 || ((ErpMidUcnsDt) bo).getBondMtpckPrpr().compareTo(new BigDecimal(0))>100) {
                           _errorList.add(fileName + ": 第" + row + "行保税料件比例必须大于0并且小于等于100！");
                        }
                    }
                    row = row + 1;
                    ((ErpMidUcnsDt) bo).setUid(UUIDGenerator.getUUID());
                }
            }

            if (_errorList.size() > 0) {
                //输出错误信息
                exportErrorLog(fs.getParent()+"error.txt");
                return false;
            }
            else {
                insertBatchErp(columnList);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    private HashMap getCheckNullMap() {
        HashMap map = new <String,String> HashMap();
        map.put("endprdSeqno","成品序号");
        map.put("mtpckSeqno","料件序号");
        map.put("netUseupQty","净耗");
        map.put("tgblLossRate","有形损耗率");
        map.put("intgbLossRate","无形损耗率");
        map.put("ucnsDclStucd","单耗申报状态");
        map.put("modfMarkcd","修改标记");
        return map;
    }

    private HashMap getColumnMap() {
        HashMap map = new <Integer,String> HashMap();
        map.put(0,"endprdSeqno");
        map.put(1,"ucnsVerno");
        map.put(6,"mtpckSeqno");
        map.put(11,"ucnsQty");
        map.put(12,"netUseupQty");
        map.put(13,"tgblLossRate");
        map.put(14,"intgbLossRate");
        map.put(15,"ucnsDclStucd");
        map.put(16,"bondMtpckPrpr");
        map.put(17,"modfMarkcd");
        map.put(18,"rmk");
        return map;
    }
}
