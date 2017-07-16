package com.powerbridge.bssp.erp.service.impl;

import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.erp.dao.ErpMidDtImgMapper;
import com.powerbridge.bssp.erp.entity.ErpMidDtImg;
import com.powerbridge.bssp.erp.service.IErpMidDtImgService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：ErpMidDtImgServiceImpl
 * 类描述：企业中间表料件服务实现
 * 创建人：lindapeng
 * 创建时间：2017/6/2 21:23
 * 修改人：
 * 修改时间：
 */
@Service("erpMidDtImgService")
public class ErpMidDtImgServiceImpl extends ErpBaseServiceImpl<ErpMidDtImgMapper, ErpMidDtImg> implements IErpMidDtImgService {

    @Override
    public Boolean dealExcelDoc(File fs) throws Exception{
        try {
            ErpMidDtImg erpMidDtImg = new ErpMidDtImg();
            HashMap columnMap = getColumnMap();
            HashMap checkMap = getCheckNullMap();
            HashSet<String> stringSet = new HashSet<String>();
            stringSet.add("natcd");
            stringSet.add("dclCurrcd");
            stringSet.add("adjmtrMarkcd");
            stringSet.add("lvyrlfModecd");
            stringSet.add("bondedFlag");
            stringSet.add("modfMarkcd");

            String fileName = fs.getName();
            List columnList = getDataFromExcel(fs , erpMidDtImg, columnMap, stringSet);
            if (columnList!=null && columnList.size()>0) {
                int row = 1;
                for (Object bo: columnList) {
                    if (((ErpMidDtImg)bo).getGdsSeqno()==null && (((ErpMidDtImg)bo).getGdsMtno()==null || ((ErpMidDtImg)bo).getGdsMtno().equals(""))) {
                        break;
                    }
                    checkNull(bo,checkMap, row, fileName);
                    if (((ErpMidDtImg)bo).getAdjmtrMarkcd().equals("主料")) {
                        ((ErpMidDtImg) bo).setAdjmtrMarkcd("1");
                    }
                    else if (((ErpMidDtImg)bo).getAdjmtrMarkcd().equals("非主料")) {
                        ((ErpMidDtImg) bo).setAdjmtrMarkcd("2");
                    }
                    if (((ErpMidDtImg)bo).getAdjmtrMarkcd().equals("是") || ((ErpMidDtImg)bo).getAdjmtrMarkcd().equals("保税")) {
                        ((ErpMidDtImg) bo).setAdjmtrMarkcd("1");
                    }
                    else if (((ErpMidDtImg)bo).getAdjmtrMarkcd().equals("是") || ((ErpMidDtImg)bo).getAdjmtrMarkcd().equals("非保税")) {
                        ((ErpMidDtImg) bo).setAdjmtrMarkcd("0");
                    }
                    if (((ErpMidDtImg)bo).getModfMarkcd().equals("新增")) {
                        ((ErpMidDtImg) bo).setModfMarkcd("3");
                    }
                    if (((ErpMidDtImg)bo).getModfMarkcd().equals("删除")) {
                        ((ErpMidDtImg) bo).setModfMarkcd("2");
                    }
                    if (((ErpMidDtImg)bo).getModfMarkcd().equals("修改")) {
                        ((ErpMidDtImg) bo).setModfMarkcd("1");
                    }
                    if (!((ErpMidDtImg)bo).getModfMarkcd().equals("1") && !((ErpMidDtImg)bo).getModfMarkcd().equals("2") && !((ErpMidDtImg)bo).getModfMarkcd().equals("3")) {
                        _errorList.add(fileName + ": 第" + row + "行修改标记必须为'1','2','3'或'新增','修改','删除'！");
                    }
                    row = row + 1;
                    ((ErpMidDtImg) bo).setUid(UUIDGenerator.getUUID());
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
        map.put("adjmtrMarkcd","主料标志");
        map.put("entUnitcd","企业单位");
        map.put("bondedFlag","是否保税");
        return map;
    }

    private HashMap getColumnMap() {
        HashMap map = new <Integer,String> HashMap();
        map.put(0,"gdsMtno");
        map.put(1,"gdsSeqno");
        map.put(2,"modfMarkcd");
        map.put(3,"adjmtrMarkcd");
        map.put(4,"gdecd");
        map.put(5,"gdsNm");
        map.put(6,"gdsSpcfModelDesc");
        map.put(7,"engGdsNm");
        map.put(8,"engGdsSpcfModelDesc");
        map.put(9,"entUnitcd");
        map.put(10,"dclUnitcd");
        map.put(11,"lawfUnitcd");
        map.put(12,"secdLawfUnitcd");
        map.put(13,"natcd");
        map.put(14,"dclUprcAmt");
        map.put(15,"dclCurrcd");
        map.put(16,"dclUprcAmtRmb");
        map.put(17,"entSfVal");
        map.put(18,"fstSfVal");
        map.put(19,"secdSfVal");
        map.put(20,"wtSfVal");
        map.put(21,"lvyrlfModecd");
        map.put(22,"bondedFlag");
        map.put(23,"rmk");

        return map;
    }
}
