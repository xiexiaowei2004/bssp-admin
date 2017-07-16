package com.powerbridge.bssp.erp.service.impl;

import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.erp.dao.ErpMidDtExgMapper;
import com.powerbridge.bssp.erp.entity.ErpMidDtExg;
import com.powerbridge.bssp.erp.service.IErpMidDtExgService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * 项目名称：bssp-admin
 * 类名称：ErpMidDtExgServiceImpl
 * 类描述：企业中间表成品服务实现
 * 创建人：lindapeng
 * 创建时间：2017/6/2 21:23
 * 修改人：
 * 修改时间：
 */
@Service("erpMidDtExgService")
public class ErpMidDtExgServiceImpl extends ErpBaseServiceImpl<ErpMidDtExgMapper, ErpMidDtExg> implements IErpMidDtExgService {
    @Override
    public Boolean dealExcelDoc(File fs) throws Exception{
        try {
            ErpMidDtExg erpMidDtExg = new ErpMidDtExg();
            HashMap columnMap = getColumnMap();
            HashMap checkMap = getCheckNullMap();
            HashSet<String> stringSet = new HashSet<String>();
            stringSet.add("natcd");
            stringSet.add("dclCurrcd");
            stringSet.add("lvyrlfModecd");
            stringSet.add("bondedFlag");
            stringSet.add("modfMarkcd");

            String fileName = fs.getName();
            List columnList = getDataFromExcel(fs , erpMidDtExg, columnMap, stringSet);
            if (columnList!=null && columnList.size()>0) {
                int row = 1;
                for (Object bo: columnList) {
                    if (((ErpMidDtExg)bo).getGdsSeqno()==null && (((ErpMidDtExg)bo).getGdsMtno()==null || ((ErpMidDtExg)bo).getGdsMtno().equals(""))) {
                        break;
                    }
                    checkNull(bo,checkMap,row, fileName);
                    if (((ErpMidDtExg)bo).getModfMarkcd().equals("新增")) {
                        ((ErpMidDtExg) bo).setModfMarkcd("3");
                    }
                    if (((ErpMidDtExg)bo).getModfMarkcd().equals("删除")) {
                        ((ErpMidDtExg) bo).setModfMarkcd("2");
                    }
                    if (((ErpMidDtExg)bo).getModfMarkcd().equals("修改")) {
                        ((ErpMidDtExg) bo).setModfMarkcd("1");
                    }
                    if (!((ErpMidDtExg)bo).getModfMarkcd().equals("1") && !((ErpMidDtExg)bo).getModfMarkcd().equals("2") && !((ErpMidDtExg)bo).getModfMarkcd().equals("3")) {
                        _errorList.add(fileName + ": 第" + row + "行修改标记必须为'1','2','3'或'新增','修改','删除'！");
                    }
                    row = row + 1;
                    ((ErpMidDtExg) bo).setUid(UUIDGenerator.getUUID());
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
        map.put("gdsMtno","商品料号");
        map.put("gdsSeqno","商品序号");
        map.put("modfMarkcd","修改标记");
        map.put("entUnitcd","企业单位");
        map.put("bondedFlag","是否保税");
        return map;
    }

    private HashMap getColumnMap() {
        HashMap map = new <Integer,String> HashMap();
        map.put(0,"gdsMtno");
        map.put(1,"gdsSeqno");
        map.put(2,"modfMarkcd");
        map.put(3,"gdecd");
        map.put(4,"gdsNm");
        map.put(5,"gdsSpcfModelDesc");
        map.put(6,"engGdsNm");
        map.put(7,"engGdsSpcfModelDesc");
        map.put(8,"entUnitcd");
        map.put(9,"dclUnitcd");
        map.put(10,"lawfUnitcd");
        map.put(11,"secdLawfUnitcd");
        map.put(12,"natcd");
        map.put(13,"dclUprcAmt");
        map.put(14,"dclCurrcd");
        map.put(15,"dclUprcAmtRmb");
        map.put(16,"entSfVal");
        map.put(17,"fstSfVal");
        map.put(18,"secdSfVal");
        map.put(19,"wtSfVal");
        map.put(20,"lvyrlfModecd");
        map.put(21,"bondedFlag");
        map.put(22,"rmk");

        return map;
    }

}
