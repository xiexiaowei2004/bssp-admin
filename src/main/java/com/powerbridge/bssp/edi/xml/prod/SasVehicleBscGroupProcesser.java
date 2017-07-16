package com.powerbridge.bssp.edi.xml.prod;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.listener.WebContextListener;
import com.powerbridge.bssp.common.util.DateUtils;
import com.powerbridge.bssp.common.util.RandomUtils;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.edi.xml.util.DomWrite;
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;
import com.powerbridge.bssp.sas.service.ISasVehicleBscService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @ClassName SasVehicleBscGroupProcesser
 * @Description 车辆备案报文生成器
 * @author Simon.xie
 * @Date 2017年6月1日 下午10:35:20
 * @version 1.0.0
 */
public class SasVehicleBscGroupProcesser extends GroupProcesser {
	private WebApplicationContext wac = WebContextListener.getCurrentWebApplicationContext();   // 获取容器（上下文环境）
	//报文头表服务
	private ISasVehicleBscService sasVehicleBscService;
	public SasVehicleBscGroupProcesser(){
        // 通过容器 获取service
		sasVehicleBscService = (ISasVehicleBscService)wac.getBean("sasVehicleBscService");
    }

	@Override
	public void execute(String docType,String bizType,String areaCode,String seqNo,BigDecimal chgTmsCnt) {
		//1.查询路由配置表
		EdiRoutingInfo ediRoutingInfo = getEdiRoutingInfo(docType, bizType, areaCode, seqNo);
		if(ediRoutingInfo != null) {
			//2.产生报文生成规则：bizType+申报类型代码+单据编号+日期+3位随机数
			SasVehicleBsc sasVehicleBsc = getdclTypecd(seqNo);
			if(sasVehicleBsc !=null){
				StringBuffer strBill = new StringBuffer();
				strBill.append(docType).append("_").append(sasVehicleBsc.getDclTypecd()).append("_")
				.append(seqNo).append("_").append(DateUtils.getStringDate()).append("_")
				.append(RandomUtils.getFixLenthString(3));
				//3.组装sql
				HashMap<String,List> map = getAssembleSql(docType, bizType, seqNo,areaCode,chgTmsCnt);
				if(map.size()>0){
					//5.组装xml文件
					org.dom4j.Document document = createSendMessageXml(map,docType,bizType, seqNo, strBill.toString());
					//File fileName = new File(System.getProperty("user.dir")+ File.separator + strBill.toString()+ ".xml");
					//DomWrite.writeDocument(fileName.toString(),document);

					//6.校验xml
					File xsdFileName = new File(getXsdSasUrl()+File.separator+ "SAS131.xsd");
					ValidateXML validateXML = new ValidateXML();
					boolean flat = true;
					validateXML.validateXMLByXSD(document,xsdFileName.toString(),docType, bizType,areaCode,seqNo, strBill.toString());
					if(flat) {
						//7.报文数据EDI_MESSAGE_DATA
						boolean ediMessage = savaEdiMessageData(docType, bizType, areaCode, seqNo, ediRoutingInfo, strBill, document);
						if(ediMessage){
							//7.删除单据队列表数据
							delEdiSendQueueById(docType, bizType, areaCode,seqNo);
							//8.保存单据队列表日志数据
							insetEdiSendLog(docType, bizType, areaCode, seqNo, TransTypeEnum.Y);
						}
					} else {
						UpdateEdiSendQueue(docType,bizType,seqNo,areaCode);
					}
				}
			} else {
				UpdateEdiSendQueue(docType,bizType,seqNo,areaCode);
				String msg ="请检查单据号是否正确！";
				inserEdiMessageLog(docType,bizType,areaCode,"",msg.getBytes(),"",TransTypeEnum.EDI_ASSEMBLE_ERROR,"");
			}
		}
	}

	/**
	 *
	 * @Description 获取申报类型代码字段
	 * @param seqNo
	 * @return
	 */
	private SasVehicleBsc getdclTypecd(String seqNo) {
		EntityWrapper entityWrapper = new EntityWrapper<SasVehicleBsc>();
		entityWrapper.eq("SEQ_NO", seqNo);//单据编号
		SasVehicleBsc sasVehicleBsc = sasVehicleBscService.selectOne(entityWrapper);
		return sasVehicleBsc;
	}

	/**
	 *
	 * @Description 创建车辆备案报文 XMl的文件
     * @param map 车辆备案报文
     * @param docType  单据类型
     * @param bizType 业务类型
     * @return
     */
    public Document createSendMessageXml(HashMap<String,List> map,final String docType,final String bizType, String seqNo, String strBill) {
		Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element root = document.addElement("Signature");
        root.addAttribute("xmlns:xsi", SendMessageXmlEnum.SEND_MESSAGE_XSI_SIGNATURE_TWO);
        SendMessageHeadXml SendMessageHeadXml = new SendMessageHeadXml();
        Element dataInfoNode = SendMessageHeadXml.CreateSendSpecialSupervisionHeadXml(root,SendMessageGroupKindEnum.SAS_VEHICLE_BSC_BIZTYPE, seqNo, strBill);
        BuildSasVehicleBscXml buildSasVehicleBscXml = new BuildSasVehicleBscXml();
        buildSasVehicleBscXml.setBillData(map, docType,bizType, dataInfoNode);
        document.setRootElement(root);
        return document;
    }

}
