package com.powerbridge.bssp.erp.service;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by powerbridge on 2017/6/13.
 */
public class ExcelUtilService {
    public static String getCellValue(Cell cell,Boolean isFormat) {
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

    //将数据从excel读入list
    public static List getDataFromExcel(File file, Object bo, HashMap columnMap, HashSet stringSet) {
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
                            if (!columnMap.get(c).equals("")) {
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
    public static void moveFile(File file) throws Exception {
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

    //必填项检查
    public static List checkNull(Object bo, HashMap checkMap, List errorList, int row) {
        int i = 0;
        Map<String, String> fieldValMap = getFieldValueMap(bo);
        for (Map.Entry<String, String> entry : fieldValMap.entrySet()) {
            i++;
            if (checkMap.containsKey(entry.getKey()) && (entry.getValue()==null || entry.getValue()=="")) {
                errorList.add("第" + row + "行" + (String) checkMap.get(entry.getKey()) + "为空！");
            }
            System.out.println("[字段 "+i+"] ["+entry.getKey()+"]   ---   [" + entry.getValue()+"]");
        }
        return errorList;
    }

    /**
     * 取Bean的属性和值对应关系的MAP
     * @param bean
     * @return Map
     */
    public static Map<String, String> getFieldValueMap(Object bean) {
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
    public static void setFieldValue(Object bean, Map<String, String> valMap) {
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
    private static Date parseDate(String datestr) {
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
    private static String fmtDate(Date date) {
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
    private static boolean checkSetMet(Method[] methods, String fieldSetMet) {
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
    private static boolean checkGetMet(Method[] methods, String fieldGetMet) {
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
    private static String parGetName(String fieldName) {
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
    private static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "set" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
    }
}
