package com.powerbridge.bssp.erp.service.impl;

import com.baomidou.mybatisplus.enums.SqlMethod;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by powerbridge on 2017/6/14.
 */
public class ErpBaseServiceImpl<M extends BaseMapper<T>, T> extends BaseServiceImpl<M, T> {
    List _errorList = new ArrayList();

    /**
     * 批量更新数据
     *
     *
     */
    public void insertBatchErp(List<org.apache.poi.ss.formula.functions.T> entityList) throws Exception{
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = entityList.size();
            String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
            for (int i = 0; i < size; i++) {
                batchSqlSession.insert(sqlStatement, entityList.get(i));
                if (i >= 1 && i % 30 == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     * 将指定目录下excel文档数据导入中间表
     *
     *
     */
    @Transactional
    public void importExcel(String path, String errorPath) {
        _errorList.clear();
        File errorFile = null;
        try {
            // 取子目录
            //String path = "d:\\erp_data\\img";
            _errorList.clear();
            File file = new File(path);
            if (!file.isDirectory()) {
                throw new Exception("不存在" + path + "目录！");
            }
            File fs1[] = file.listFiles();
            File fs[] = new File[fs1.length];
            if (fs1.length > 0) {
                int i = 0;
                for (int j = 0; fs1 != null && j < fs1.length; j++ ) {
                    if (!fs1[j].isDirectory() && (fs1[j].getName().substring((int) fs1[j].getName().length() - 4).equals(".xls") || fs1[j].getName().substring((int) fs1[j].getName().length() - 5).equals(".xlsx"))) {
                        fs[i] = fs1[j];
                        i++;
                    }
                }
                if (i > 0) {
                    for (int j=0; j<i; j++) {
                        if (fs[j]!=null) {
                            errorFile = fs[j];
                            if (dealExcelDoc(fs[j])) {
                                moveFileToBak(fs[j]);
                            }
                            else {
                                moveFileToError(fs[j]);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
            _errorList.add(e.getMessage());
            if (_errorList.size()>0) {
                if (errorFile!=null) {
                    try {
                        moveFileToError(errorFile);
                    } catch (Exception e1) {
                        _errorList.add(e.getMessage());
                    }
                }
                exportErrorLog(errorPath+"error.txt");
            }

        }

    }

    /**
     * 处理excel文档(一般是提供给子类处理各自逻辑)
     *
     *
     */
    public Boolean dealExcelDoc(File fs) throws Exception{
        return true;
    }

    private String getCellValue(Cell cell, Boolean isFormat) {
        String cellValue = "";
        if (null != cell)
        {
            // 以下是判断数据的类型
            switch (cell.getCellType())
            {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    if (isFormat) {
                        DecimalFormat df = new DecimalFormat("0");
                        cellValue = df.format(cell.getNumericCellValue());
                    }
                    else {
                        cellValue = cell.getNumericCellValue() + "";
                    }
                    return cellValue;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    return cellValue;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    return cellValue;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula() + "";
                    return cellValue;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    return cellValue;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    return cellValue;
                default:
                    cellValue = "未知类型";
            }
        }
        return cellValue;
    }

    /**
     * @param: file 要导入的文件
     * @param: bo 要导入的bean类型
     * @param: columnMap excel的列和字段对映射集合
     * @param: stringSet 字符型在excel中如果是代码，会被识别成数值型
     * 将数据从excel读入list
     * */
    public List getDataFromExcel(File file, Object bo, HashMap columnMap, HashSet stringSet) throws Exception{
        FileInputStream inputStream = null;
        List<Object> columnList = new ArrayList<Object>();
        try {
            inputStream = new FileInputStream(file);
            Workbook workBook = WorkbookFactory.create(inputStream);
            Sheet sheet = workBook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            //至少一行数据才处理
            if (totalRows>1) {
                //读第一行读出总列数
                int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
                for (int r = 1; r < totalRows; r++) {
                    Row row = sheet.getRow(r);
                    Map<String, String> valMap = new HashMap<String, String>();
                    for (int c = 0; c < totalCells; c++) {
                        Cell cell = row.getCell(c);
                        //根据指定列将数据以字段：值方式读入map
                        if (columnMap.containsKey(c) && !columnMap.get(c).equals("")) {
                            String fieldName = (String) columnMap.get(c);
                            String cellValue = "";
                            //如果出现字符型字段，但是在excel中被识别成数字，则要格式化
                            if (stringSet.contains(fieldName)) {
                                cellValue = getCellValue(cell,true);
                            }
                            else {
                                cellValue = getCellValue(cell,false);
                            }
                            valMap.put(fieldName,cellValue);
                        }
                    }
                    Class beanClass = Class.forName(bo.getClass().getName());
                    Object boNew = beanClass.newInstance();
                    setFieldValue(boNew, valMap);
                    columnList.add(boNew);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return columnList;
    }

    /**
     * @param: file 文件
     * 将文件移入备份目录
     * */
    public void moveFileToBak(File file) throws Exception {
        String path = file.getParent();
        File filed = new File(path + "\\bak");
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!filed.exists()) {
            filed.mkdir();
        }
        // 备份回执文件
        File bakFile = new File(path + "\\bak\\" + file.getName());
        String bakFileName = path + "\\bak\\" + file.getName();
        File file1 = new File(path + "\\" + file.getName());
        if (bakFile.exists() && bakFile.isFile()) {
            // delete the old file if exits
            bakFile.delete();
            bakFile = new File(path + "\\bak\\" + file.getName());
        }

        if (!file1.renameTo(bakFile)) {
            throw new Exception("Can not move file[" + file.getAbsolutePath() + "] to " + bakFile.getAbsolutePath());
        }

    }

    /**
     * @param: file 文件
     * 将文件移入备份目录
     * */
    public void moveFileToError(File file) throws Exception {
        String path = file.getParent();
        File filed = new File(path + "\\error");
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!filed.exists()) {
            filed.mkdir();
        }
        // 备份回执文件
        File errorFile = new File(path + "\\error\\" + file.getName());
        File file1 = new File(path + "\\" + file.getName());
        if (errorFile.exists() && errorFile.isFile()) {
            // delete the old file if exits
            errorFile.delete();
            errorFile = new File(path + "\\error\\" + file.getName());
        }

        if (!file1.renameTo(errorFile)) {
            throw new Exception("Can not move file[" + file.getAbsolutePath() + "] to " + errorFile.getAbsolutePath());
        }

    }

    /**
     * @param: bo 要检查的 bean对象
     * @param: checkMap 需要检查非空的字段
     * @param: row 检查行
     * @param: fileName 提示文件名
     * 必填项检查
     * */
    public void checkNull(Object bo, HashMap checkMap, int row, String fileName) {
        int i = 0;
        if (fileName!=null && !fileName.equals("")) {
            fileName = "文档   " + fileName + ": ";
        }
        Map<String, String> fieldValMap = getFieldValueMap(bo);
        for (Map.Entry<String, String> entry : fieldValMap.entrySet()) {
            i++;
            if (checkMap.containsKey(entry.getKey()) && (entry.getValue()==null || entry.getValue()=="")) {
                _errorList.add(fileName + "第" + row + "行" + (String) checkMap.get(entry.getKey()) + "为空！");
            }
            //System.out.println("[字段 "+i+"] ["+entry.getKey()+"]   ---   [" + entry.getValue()+"]");
        }
    }

    /**
     * @param: errorList 错误提示
     * 输出错误信息
     * */
    public void exportErrorLog(String errorPath) {
        try {
            OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(errorPath), "gb18030");
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH) + 1;
            String smonth = month + "";
            if (month < 10) {
                smonth = "0" + month;
            }
            int day = now.get(Calendar.DAY_OF_MONTH);
            String sday = day + "";
            if (day < 10) {
                sday = "0" + day;
            }
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int minute = now.get(Calendar.MINUTE);
            int second = now.get(Calendar.SECOND);

            String time = year + "/" + month + "/" + sday + " " +hour + ":" +minute + ":" + second;
            fileWriter.write(time+"错误日志：\r\n");
            for (int j=0; j<_errorList.size(); j++) {
                fileWriter.write((String) _errorList.get(j));
                fileWriter.write("\r\n");
            }
            fileWriter.write("\r\n");
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取Bean的属性和值对应关系的MAP
     * @param bean
     * @return Map
     */
    private Map<String, String> getFieldValueMap(Object bean) {
        Class<?> cls = bean.getClass();
        Map<String, String> valueMap = new HashMap<String, String>();
        // 取出bean里的所有方法
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            try {
                String fieldType = field.getType().getSimpleName();
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
                String result = null;
                if ("Date".equals(fieldType)) {
                    result = fmtDate((Date) fieldVal);
                } else {
                    if (null != fieldVal) {
                        result = String.valueOf(fieldVal);
                    }
                }
                valueMap.put(field.getName(), result);
            } catch (Exception e) {
                continue;
            }
        }
        return valueMap;

    }

    /**
     * set属性的值到Bean
     * @param bean
     * @param valMap
     */
    private void setFieldValue(Object bean, Map<String, String> valMap) {
        Class<?> cls = bean.getClass();
        // 取出bean里的所有方法
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            try {
                String fieldSetName = parSetName(field.getName());
                if (!checkSetMet(methods, fieldSetName)) {
                    continue;
                }
                Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
                String value = valMap.get(field.getName());
                if (null != value && !"".equals(value)) {
                    String fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {
                        fieldSetMet.invoke(bean, value);
                    } else if ("Date".equals(fieldType)) {
                        Date temp = parseDate(value);
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
                        Integer intval = Integer.parseInt(value);
                        fieldSetMet.invoke(bean, intval);
                    } else if ("Long".equalsIgnoreCase(fieldType)) {
                        Long temp = Long.parseLong(value);
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Double".equalsIgnoreCase(fieldType)) {
                        Double temp = Double.parseDouble(value);
                        fieldSetMet.invoke(bean, temp);
                    } else if ("BigDecimal".equalsIgnoreCase(fieldType)) {
                        BigDecimal temp = BigDecimal.valueOf(Double.parseDouble(value));
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                        Boolean temp = Boolean.parseBoolean(value);
                        fieldSetMet.invoke(bean, temp);
                    } else {
                        System.out.println("not supper type" + fieldType);
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }

    }

    /**
     * 格式化string为Date
     * @param datestr
     * @return date
     */
    private Date parseDate(String datestr) {
        if (null == datestr || "".equals(datestr)) {
            return null;
        }
        try {
            String fmtstr = null;
            if (datestr.indexOf(':') > 0) {
                fmtstr = "yyyy-MM-dd HH:mm:ss";
            } else {
                fmtstr = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
            return sdf.parse(datestr);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 日期转化为String
     * @param date
     * @return date string
     */
    private String fmtDate(Date date) {
        if (null == date) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否存在某属性的 set方法
     * @param methods
     * @param fieldSetMet
     * @return boolean
     */
    private boolean checkSetMet(Method[] methods, String fieldSetMet) {
        for (Method met : methods) {
            if (fieldSetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }
    /**
     * 判断是否存在某属性的 get方法
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    private boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 拼接某属性的 get方法
     * @param fieldName
     * @return String
     */
    private String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "get" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
    }
    /**
     * 拼接在某属性的 set方法
     * @param fieldName
     * @return String
     */
    private String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "set" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
    }


}
