package com.powerbridge.bssp.common.constants;

import com.powerbridge.bssp.common.util.PropertiesUtils;

/**
 * 系统常量
 * @author simon.xie
 *
 */
public class CommonConstants {  
	
	/**获取properties配置文件属性 */
	private static final String CONSTANTSPROPERTIES = "properties/constants.properties";
	//用static修饰的代码块表示静态代码块，当Java虚拟机（JVM）加载类时，就会执行该代码块
	static {
		PropertiesUtils.readProperties(CONSTANTSPROPERTIES);
	}
	
	/**项目路径*/
	private static final String CONTEXTPATH = "contextPath";
	public static final String contextPath = PropertiesUtils.getProperty(CONTEXTPATH);

	/**单据编号服务项目路径*/
	private static final String BILLCONTEXTPATH = "billContextPath";
	public static final String billContextPath = PropertiesUtils.getProperty(BILLCONTEXTPATH);
}
