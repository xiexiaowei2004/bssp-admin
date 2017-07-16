package com.powerbridge.bssp.edi.xml.prod;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.listener.WebContextListener;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.*;
import com.powerbridge.bssp.edi.service.*;
import org.dom4j.io.XMLWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @ClassName GroupProcesser
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Simon.xie
 * @Date 2017年6月2日 下午10:13:56
 * @version 1.0.0
 */
public abstract class GroupProcesser implements IGroupProcesser {
	
	private WebApplicationContext wac = WebContextListener.getCurrentWebApplicationContext();   // 获取容器（上下文环境）
	//报文头表服务
	private IEdiMessageHeadService ediMessageHeadService;
	//报文数据服务
	private IEdiMessageDataService ediMessageDataService;
	//字段关系服务
	private IEdiXmlMapDbService ediXmlMapDbService;
	//单据队列表服务
	private IEdiSendQueueService ediSendQueueService;
	//路由配置服务 
	private IEdiRoutingInfoService ediRoutingInfoService;
	//单据队列表日志服务
	private IEdiSendLogService ediSendLogService;
	//报文日志服务
	private IEdiMessageLogService ediMessageLogService;
	//
	private IEdiBusinessConfigService ediBusinessConfigService;
	
	public GroupProcesser(){
        // 通过容器 获取service
		ediMessageHeadService = (IEdiMessageHeadService)wac.getBean("ediMessageHeadService");
		ediMessageDataService = (IEdiMessageDataService)wac.getBean("ediMessageDataService");
		ediXmlMapDbService = (IEdiXmlMapDbService) wac.getBean("ediXmlMapDbService");
		ediSendQueueService = (IEdiSendQueueService) wac.getBean("ediSendQueueService");
		ediRoutingInfoService = (IEdiRoutingInfoService)wac.getBean("ediRoutingInfoService");
		ediSendLogService = (IEdiSendLogService) wac.getBean("ediSendLogService");
		ediMessageLogService = (IEdiMessageLogService)wac.getBean("ediMessageLogService");
		ediBusinessConfigService = (IEdiBusinessConfigService)wac.getBean("ediBusinessConfigService");
    }

	/**
	 * @return
	 * @Description 获取xsd路径
	 */
	public static File getXsdUrl() {
		File cfgFile = null;
		try {
			cfgFile = ResourceUtils.getFile("classpath:XSD" +File.separator+"EMS");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cfgFile;
	}

	/**
	 * @return
	 * @Description 获取xsd路径
	 */
	public static File getXsdSasUrl() {
		File cfgFile = null;
		try {
			cfgFile = ResourceUtils.getFile("classpath:XSD" +File.separator+"SAS");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cfgFile;
	}

	/**
	 * @return
	 * @Description 获取发送报文xml路径
	 */
	public static File getSendMessageXmlUrl() {
		File cfgFile = null;
		try {
			cfgFile = ResourceUtils.getFile("classpath:sendMessageXml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cfgFile;
	}
	
	public abstract void execute(String docType,String bizType,String areaCode,String seqNo,BigDecimal chgTmsCnt);

	public EdiMessageHead savaEdiMessageHead(final String docType, final String bizType, final String areaCode,
			final String seqNo, StringBuffer strBill) {
		EdiMessageHead ediMessageHead = new EdiMessageHead();
		ediMessageHead.setUid(UUIDGenerator.getUUID());
		ediMessageHead.setSerialNo(seqNo);
		ediMessageHead.setAreaCode(areaCode);                     //监管场所
		ediMessageHead.setDocType(docType);
		ediMessageHead.setBizType(bizType);
		ediMessageHead.setClassVer(TransTypeEnum.CLASSVER);       //报文版本号
		ediMessageHead.setClassName("XML");                       //报文类型
		//ediMessageHead.setAppStepId(appStepId);                 //环节编码(新增：A 修改：E 删除：D)
		ediMessageHead.setSeqNo(seqNo);							  //单据编号
		ediMessageHead.setFileName(strBill.toString());           //报文名称
		ediMessageHead.setStatus(TransTypeEnum.Y);
		ediMessageHeadService.insert(ediMessageHead);
		return ediMessageHead;
	}

	/**
	 *
	 * @Description 保存报文数据
	 * @param docType 监管场所
	 * @param bizType 单据类型
	 * @param areaCode 业务类型
	 * @param seqNo  单据编号
	 * @param ediRoutingInfo
	 * @param strBill
	 * @param document
	 */
	public boolean savaEdiMessageData(final String docType, final String bizType, final String areaCode,
			final String seqNo, EdiRoutingInfo ediRoutingInfo, StringBuffer strBill, org.dom4j.Document document) {
		EdiMessageData ediMessageData = new EdiMessageData();
		String messageDataUid= UUIDGenerator.getUUID();
		ediMessageData.setUid(messageDataUid);
		ediMessageData.setSerialNo(seqNo);                                     //编号
		ediMessageData.setDocType(docType);                                 //单据类型
		ediMessageData.setAreaCode(areaCode);                               //监管场所
		ediMessageData.setBizType(bizType);        							//业务类型
		ediMessageData.setFileName(strBill.toString());          			//报文名称
		ediMessageData.setTransType(TransTypeEnum.S);       				//传输类型   S：发送 R：接收
		ediMessageData.setTransMode(ediRoutingInfo.getTransMode());      	//传输方式
	/*	try {
			ediMessageData.setBigData(document.asXML().getBytes("UTF-8"));             //大数据
			System.out.println("-------------------"+document.asXML());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		XMLWriter xmlWriter = null;
		try {
			xmlWriter = new XMLWriter(byteOut);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			xmlWriter.write(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] data = byteOut.toByteArray();

		ediMessageData.setBigData(data);             //大数据
		ediMessageData.setProcessingTime(DateUtil.now());  					//处理时间
		ediMessageData.setStatus(TransTypeEnum.N);							//处理标识
		boolean ediMessageDataFlat = ediMessageDataService.insert(ediMessageData);
		if(ediMessageDataFlat){
			inserEdiMessageLog(docType,bizType,areaCode,strBill.toString(),data,"",TransTypeEnum.EDI_ASSEMBLE_SUCCESS,messageDataUid);
		} else{
			inserEdiMessageLog(docType,bizType,areaCode,strBill.toString(),data,"",TransTypeEnum.EDI_ASSEMBLE_ERROR,messageDataUid);
		}
		return ediMessageDataFlat;
	}

	/**
	 *
	 * @Description sql拼接
	 * @param docType 监管场所
	 * @param bizType 单据类型
	 * @param areaCode 业务类型
	 * @param seqNo  单据编号
	 */
	public HashMap<String,List> getAssembleSql(String docType,String bizType,String seqNo,String areaCode,BigDecimal chgTmsCnt) {
		HashMap<String, List> map = new LinkedHashMap<String, List>();
		EntityWrapper entityWrapper = new EntityWrapper<EdiBusinessConfig>();
		entityWrapper.eq("DOC_TYPE", docType);//单据类型
		entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
		entityWrapper.eq("Status", "Y");//处理标识
		entityWrapper.orderBy("ORDER_NO");
		List<EdiBusinessConfig> ediBusinessConfigList = ediBusinessConfigService.selectList(entityWrapper);
		if(ediBusinessConfigList.size() >0){
			for(EdiBusinessConfig list:ediBusinessConfigList){
				seleteEntity(seqNo, map, list.getTableName(), list.getBizkey(), list.getTableService(),chgTmsCnt);
	        }
		}
		if(map.size()<1){
			UpdateEdiSendQueue(docType,bizType,seqNo,areaCode);
			String msg ="请检查是否配置edi_business_config表数据！";
			inserEdiMessageLog(docType,bizType,areaCode,"",msg.getBytes(),"",TransTypeEnum.ONE,"");
		}
		return map;
	}

	/**
	 *
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param seqNo
	 * @param map
	 * @param bizTable
	 * @param bizKey
	 * @param bizTableService
	 * @param chgTmsCnt 变更次数
	 */
	public void seleteEntity(String seqNo, Map<String, List> map, String bizTable, String bizKey,
			String bizTableService,BigDecimal chgTmsCnt) {
		EntityWrapper entityWrapper = new EntityWrapper<>();
		entityWrapper.eq(bizKey, seqNo);//处理标识
		entityWrapper.eq("CHG_TMS_CNT", chgTmsCnt);
		Object tableService = wac.getBean(bizTableService);
		Class clazz = tableService.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		try {
		    for (Method method : methods) {
		        if (method.getName().equals("selectList")) {
		        	List list = (List) method.invoke(tableService,entityWrapper);
		            map.put(bizTable, list);
		            break;
		        }
		    }
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		} catch (InvocationTargetException e) {
		    e.printStackTrace();
		}
	}

	/**
	 *
	 * @Description 获取表关系配置xml
	 * @return
	 */
	private Document getXmlFile() {
		File file = new File("C:/Users/Simon.xie/Desktop/BusinessConfig.xml");
		Document document = null;
		try {
			document = (Document) Jsoup.parse(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 *
	 * @Description 根据条件，删除单据队列表数据
	 * @param docType 监管场所
	 * @param bizType 单据类型
	 * @param areaCode 业务类型
	 */
	public void delEdiSendQueueById(String docType, String bizType, String areaCode, String seqNo) {
		EntityWrapper entityWrapper = new EntityWrapper<EdiSendQueue>();
		entityWrapper.eq("AREA_CODE", areaCode);//监管场所
		entityWrapper.eq("DOC_TYPE", docType);//单据类型
		entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
		entityWrapper.eq("SEQ_NO", seqNo);   //单据编号
		ediSendQueueService.delete(entityWrapper);
	}

	/**
	 *
	 * @Description 查询路由配置表
	 * @param docType 监管场所
	 * @param bizType 单据类型
	 * @param areaCode 业务类型
	 * @return
	 */
	public EdiRoutingInfo getEdiRoutingInfo(final String docType, final String bizType, String areaCode) {
		EntityWrapper entityWrapper = new EntityWrapper<EdiRoutingInfo>();
		entityWrapper.eq("AREA_CODE", areaCode);//监管场所
		entityWrapper.eq("DOC_TYPE", docType);//单据类型
		entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
		entityWrapper.eq("status", TransTypeEnum.Y);//启用标识
		return ediRoutingInfoService.selectOne(entityWrapper);
	}

	/**
	 *
	 * @Description 查询路由配置表
	 * @param docType 监管场所
	 * @param bizType 单据类型
	 * @param areaCode 业务类型
	 * @return
	 */
	public EdiRoutingInfo getEdiRoutingInfo(final String docType, final String bizType, final String areaCode,
			final String seqNo) {
		EntityWrapper entityWrapper = new EntityWrapper<EdiRoutingInfo>();
		//entityWrapper.eq("AREA_CODE", areaCode);//监管场所
		entityWrapper.eq("DOC_TYPE", docType);//单据类型
		entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
		entityWrapper.eq("status", TransTypeEnum.Y);//启用标识
		EdiRoutingInfo ediRoutingInfo = ediRoutingInfoService.selectOne(entityWrapper);
		if(ediRoutingInfo == null) {
			String msg = "请检查路由配置表edi_routing_info";
			String Status=TransTypeEnum.ONE;
			byte[] bytes = null;
			try {
				bytes = msg.getBytes("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			inserEdiMessageLog(docType,bizType,areaCode,"",bytes,"",TransTypeEnum.EDI_ASSEMBLE_ERROR,"");
			UpdateEdiSendQueue(docType,bizType,seqNo,areaCode);
		}
		return ediRoutingInfo;
	}

	/**
	 *
	 * @Description 保存单据队列表日志数据
	 * @param docType 监管场所
	 * @param bizType 单据类型
	 * @param areaCode 业务类型
	 * @param seqNo 单据编号
	 */
	public void insetEdiSendLog(final String docType, final String bizType, final String areaCode,
			final String seqNo,String Status) {
		EdiSendLog ediSendLog = new EdiSendLog();
		ediSendLog.setUid(UUIDGenerator.getUUID());
		ediSendLog.setAreaCode(areaCode);
		ediSendLog.setBizType(bizType);
		ediSendLog.setDocType(docType);
		ediSendLog.setSeqNo(seqNo);
		ediSendLog.setStatus(Status);
		//ediSendLog.setFailNote(bytes);
		ediSendLogService.insert(ediSendLog);
	}

	/**
	 *
     * @Description 根据单据类型，业务类型，表名，字段名查询对应报文字段名
     * @param docType   单据类型
     * @param bizType  业务类型
     * @return
	 */
	public List<EdiXmlMapDb> getEdiXmlInfo(final String docType, final String bizType) {
		Map<String,String> map = new HashMap<String,String>();
    	map.put("docType", docType);
    	map.put("bizType", bizType);
		return ediXmlMapDbService.getEdiXmlMapDb(map);
	}

	/**
	 *
	 * @Description 保存报文日志
	 * @param docType
	 * @param bizType
	 * @param areaCode
	 * @param data
	 * @param fileName
	 * @param ediMessageDataFlat
	 */
	/*public void insertEdiMessageLog(final String docType, final String bizType, final String areaCode,
									   byte[] data, String fileName, boolean ediMessageDataFlat,String messageDataUid,byte[] checkInfo) {
		EdiMessageLog ediMessageLog = new EdiMessageLog();
		ediMessageLog.setUid(UUIDGenerator.getUUID());
		ediMessageLog.setAreaCode(areaCode);
		ediMessageLog.setBizType(bizType);
		ediMessageLog.setDocType(docType);
		ediMessageLog.setFileName(fileName);
		ediMessageLog.setProcessingLog(data);
		ediMessageLog.setMessageUid(messageDataUid);
		ediMessageLog.setCheckInfo(checkInfo);
		ediMessageLog.setTransType(TransTypeEnum.S);
		ediMessageLog.setMessageUid(messageDataUid);
		if(ediMessageDataFlat){
			ediMessageLog.setStatus(TransTypeEnum.N);
		} else{
			ediMessageLog.setStatus(TransTypeEnum.ONE);
		}
	}*/

	/**
	 * @Description 更新队列表
	 * @param seqNo
	 */
	public void UpdateEdiSendQueue(String docType,String bizType,String seqNo,String areaCode) {
		EntityWrapper entityWrapper = new EntityWrapper<EdiSendQueue>();
		entityWrapper.eq("AREA_CODE", areaCode);//处理标识
		entityWrapper.eq("DOC_TYPE", docType);//处理标识
		entityWrapper.eq("BIZ_TYPE", bizType);//处理标识
		entityWrapper.eq("SEQ_NO", seqNo);//处理标识
		EdiSendQueue ediSendQueue  = new EdiSendQueue();
		ediSendQueue.setStatus(TransTypeEnum.ONE);
		ediSendQueueService.update(ediSendQueue,entityWrapper);
	}

	public void inserEdiMessageLog(String docType, String bizType, String areaCode,String fileName,byte[] msg,String checkInfo,String status,String messageDataUid) {
		EdiMessageLog ediMessageLog = new EdiMessageLog();
		ediMessageLog.setUid(UUIDGenerator.getUUID());
		ediMessageLog.setAreaCode(areaCode);
		ediMessageLog.setBizType(bizType);
		ediMessageLog.setDocType(docType);
		ediMessageLog.setFileName(fileName);
		ediMessageLog.setProcessingTime(DateUtil.now());
		ediMessageLog.setProcessingLog(msg);
		ediMessageLog.setCheckInfo(checkInfo.getBytes());
		ediMessageLog.setStatus(status);
		ediMessageLog.setTransType(TransTypeEnum.S);
		ediMessageLog.setMessageUid(messageDataUid);
		ediMessageLogService.insert(ediMessageLog);
	}
}
