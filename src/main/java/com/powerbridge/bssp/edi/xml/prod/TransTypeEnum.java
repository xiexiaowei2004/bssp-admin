package com.powerbridge.bssp.edi.xml.prod;
/**
 * 
 * @ClassName TransTypeEnum
 * @Description 动作状态
 * @author Simon.xie
 * @Date 2017年6月4日 下午4:21:46
 * @version 1.0.0
 */
public class TransTypeEnum {
	//S：发送 
	public static final String S = "S";
	//R：接收
	public static final String R = "R";
	//生成
	public static final String C = "C";
	//处理
	public static final String D = "D";
	//已处理
	public static final String Y = "Y";
	//未处理
	public static final String N = "N";
	//已失败
	public static final String ONE = "1";
	//XML版本号
	public static final String CLASSVER = "1.0.0";

	public static final String EDI_ASSEMBLE_ERROR ="组装XML文件失败！";

	public static final String EDI_ASSEMBLE_SUCCESS ="组装XML文件失败！";

	public static final String EDI_XML_ERROR ="检验XML文件失败！";

	public static final String EDI_XML_SUCCESS ="检验XML文件通过！";




}
