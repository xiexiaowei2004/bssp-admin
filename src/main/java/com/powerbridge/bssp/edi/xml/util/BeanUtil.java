package com.powerbridge.bssp.edi.xml.util;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop.entity.CopEnt;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：BeanUtil
 * 类描述：报文回执处理Bean工具类
 * 创建人：danagao
 * 创建时间：2017/6/9 12:00
 *
 * @version 1.0
 */
public class BeanUtil {
	
	/**
     * 调用：Map map=BeanUtil.getXmlToBean(sasDclBsc,ediXmlMapDbService);
     * 其中参数sasDclBsc为实体类，ediXmlMapDbService为固定的service值，不要传其他值
     * 根据参数Object来，查询出map，object为实体类的名字
	 * key:就是xml的节点
	 * value：就是对应的实体类的字段
	 * 
	 */
	public static Map<String,String> getXmlToBean(Object obj,IService iservice){
		String nameTable=BeanUtil.getTableName(obj.getClass());//获取数据库表名sas_dcl_bsc
		//获取obj实体类的Map,key:数据库的属性名，value为实体类的名字
		Map<String,String> fieldbeanMap = BeanUtil.getBeanMap(obj);//{INPUT_COP_NAME=inputCopName, CHK_STATUS=chkStatus,...}
		Map<String,String> map= new Hashtable<String,String>();//新建map集合，存储数据
		//根据nameTable到字段关系表中查询
        EntityWrapper<EdiXmlMapDb> wrapper = new EntityWrapper<EdiXmlMapDb>();
        if (StringUtil.isNotEmpty(nameTable)) {
			wrapper.eq("DB_TABLE", nameTable);// 申报表预录入编号
		}
        List<EdiXmlMapDb> list = iservice.selectList(wrapper);
        for (EdiXmlMapDb ediXmlMapDb : list) {//XML_NAME  DB_COLUMN
            map.put(ediXmlMapDb.getXmlName(), fieldbeanMap.get(ediXmlMapDb.getDbColumn()));
        }
		
		return map ;
	}
	
	
	
    /**
     * 获取一个Bean中所有能和数据库字段匹配的属性名称的map
     */
    public static Map getBeanMap(Object beanObject) {
        Class clazz = beanObject.getClass();
        Map BeanMap = new HashMap<String, String>();

        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            String sqlFiledName = getField(f);
            String beanName = f.getName();
            if (StringUtil.isNotEmpty(sqlFiledName)) {
                BeanMap.put(sqlFiledName, beanName);
            }
        }
        return BeanMap;
    }

    /**
     * 判断Bean中是否有此属性并赋值
     * beanObject表名
     * propertyName字段名
     * propertyValue字段值
     */
 /*   public static void setProperties(Object beanObject, String propertyName, Object propertyValue) {
        // 判断属性是否存在并赋值
        if (BeanKit.propertyExists(beanObject, propertyName)) {
//            Object object = BeanKit.getProperty(beanObject, propertyName);
//            if (null == object) {
                setProperty(beanObject, propertyName, propertyValue,);
//            }
        }
    }
    */
    
    

    /**
    * 给bean中的某个属性赋值
    * */
    public static void setProperty(Object object, String propertyName, Object propertyValue) throws Exception {
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        String methodName = "set" + propertyName;

        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                Class[] methodParamTypes = getMethodParamTypes(object, method.getName());
                method.invoke(object, getCastValue(methodParamTypes[0], String.valueOf(propertyValue),method.getName()));
            }
        }
    }
    
  

    /**
     * 根据Bean中属性注解的数据库字段名称获取对应的属性名称
     */
    public static String getField(Field f) {
        boolean present = f.isAnnotationPresent(TableField.class);
        if (present) {
            //得到requestMapping注释
            TableField tableField = f.getAnnotation(TableField.class);
            if (tableField != null) {
                return tableField.value();
            }
        }
        return "";
    }


    /**
     * 根据Bean类名获取对应的表名称
     */
    public static String getTableName(Class clazz) {
        boolean present = clazz.isAnnotationPresent(TableName.class);
        if (present) {
            //得到requestMapping注释
            TableName tableName = (TableName)clazz.getAnnotation(TableName.class);
            if (tableName != null) {
                return tableName.value();
            }
        }
        return "";
    }
    
    public static Object selectOne(Object tableService, EntityWrapper entityWrapper) throws Exception {
        Class clazz = tableService.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().equals("selectOne")) {
                return method.invoke(tableService, entityWrapper);
            }
        }

        return null;
    }

    /**
     * 根据方法名称取得反射方法的参数类型(没有考虑同名重载方法使用时注意)
     * @param classInstance         类实例
     * @param methodName  方法名
     * @return
     * @throws ClassNotFoundException
     */
    public static Class[]  getMethodParamTypes(Object classInstance,
                                               String methodName) throws ClassNotFoundException{
        Class[] paramTypes = null;
        Method[]  methods = classInstance.getClass().getMethods();//全部方法
        for (int  i = 0;  i< methods.length; i++) {
            if(methodName.equals(methods[i].getName())){//和传入方法名匹配
                Class[] params = methods[i].getParameterTypes();
                paramTypes = new Class[ params.length] ;
                for (int j = 0; j < params.length; j++) {
                    paramTypes[j] = Class.forName(params[j].getName());
                }
                break;
            }
        }
        return paramTypes;
    }

    public static String getMethodReturnType(Object classInstance, String propertyName) {
        Class clzz = classInstance.getClass();
        String returnType = "";
        String methodName = "get" + propertyName;
        //获取该类的所有方法
        Method[] methods = clzz.getMethods();
        //遍历方法
        for (Method m : methods) {
            if (m.getName().equalsIgnoreCase(methodName)) {
                //获取返回类型
                Type type = m.getGenericReturnType();
                returnType = type.getTypeName();
            }
        }
        return returnType;
    }




    public static Object getCastValue(Class clazz, String value,String str) {
        String clazzName = clazz.getName();
        if (clazzName.equals("java.math.BigDecimal")) {
            return new BigDecimal(value);
        } else if (clazzName.equals("java.lang.Integer")) {
            return Integer.parseInt(value);
        } else if (clazzName.equals("java.lang.Double")) {
            return Double.parseDouble(value);
        }

        return value;
    }

    /**
     * 空值设定
     * @param str 字段类型
     * @param val 字段值
     * @return
     */
    public static String getNullTypeValue(String str,Object val) {
        if(str.equals("java.lang.Double") && val == null){
            return "0";
        } else if(str.equals("java.lang.Integer") && val.equals("null")){
            return "0";
        } else if(str.equals("java.math.BigDecimal") && val.equals("null")){
            return "0";
        } else if(str.equals("java.util.Date") && val == null){
            return DateUtil.now();
        } else if(str.equals("java.lang.String") && val.equals("null")){
            return "";
        } else if(str.equals("java.lang.Double") && val.equals("null")) {
            val=0.0;
        }
        return val.toString();
    }



/*

    public static void setBeanPropertiesByMap(Object beanObject, Map<String, Object> beanMap) {
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            BeanUtil.setProperties(beanObject, entry.getKey(), entry.getValue());
        }
    }

*/

   public static void main(String[] args) {


       try {
           CopEnt copEnt = new CopEnt();
           String str = getMethodReturnType(copEnt,"regFund");
           System.out.println(str);
       } catch (Exception e) {
           e.printStackTrace();
       }
//        System.out.println(copEnt.getRegFund());
    }
}
