package com.powerbridge.bssp.common.constants;

/**
 * 
*    
* 项目名称：bssp Maven Webapp
* 类名称：GlobalConstants   
* 类描述：全局配置类  
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* 修改备注：   
* @version    
*
 */
public class GlobalConstants {
	
	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";
	
	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 获取Key加载信息
	 */
	public static boolean printKeyLoadMessage(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\r\n            ================================================================================================\r\n");
		stringBuilder.append("\r\n                                                欢迎使用 bssp 平台                  \r\n");
		stringBuilder.append("\r\n            ================================================================================================\r\n");
		System.out.println(stringBuilder.toString());
		return true;
	}
	

}
