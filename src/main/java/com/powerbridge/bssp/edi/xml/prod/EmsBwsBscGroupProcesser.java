package com.powerbridge.bssp.edi.xml.prod;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.listener.WebContextListener;
import com.powerbridge.bssp.common.util.DateUtils;
import com.powerbridge.bssp.common.util.RandomUtils;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.edi.xml.util.DomWrite;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsBscService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @ClassName EmsBwsBscGroupProcesser
 * @Description 物流账册报文生成
 * @author Simon.xie
 * @Date 2017年6月1日 下午10:28:11
 * @version 1.0.0
 */
public class EmsBwsBscGroupProcesser extends GroupProcesser {
	private WebApplicationContext wac = WebContextListener.getCurrentWebApplicationContext();   // 获取容器（上下文环境）
	//报文头表服务
	private IEmsBwsBscService emsBwsBscService;
	public EmsBwsBscGroupProcesser(){
        // 通过容器 获取service
		emsBwsBscService = (IEmsBwsBscService)wac.getBean("emsBwsBscService");
    }

	@Override
	public void execute(String docType,String bizType,String areaCode,String seqNo,BigDecimal chgTmsCnt) {
		//1.查询路由配置表
		EdiRoutingInfo ediRoutingInfo = getEdiRoutingInfo(docType, bizType, areaCode, seqNo);
		if(ediRoutingInfo != null) {
			//2.产生报文生成规则：bizType+申报类型代码+单据编号+日期+3位随机数
			EmsBwsBsc emsBwsBsc = getdclTypecd(seqNo);
			if(emsBwsBsc !=null){
				StringBuffer strBill = new StringBuffer();
				strBill.append(docType).append("_").append(emsBwsBsc.getDclTypecd()).append("_")
				.append(seqNo).append("_").append(DateUtils.getStringDate()).append("_")
				.append(RandomUtils.getFixLenthString(3));
				//组装数据到map
				HashMap<String,List> map = getAssembleSql(docType, bizType, seqNo,areaCode,chgTmsCnt);
				if(map.size()>0){
					//5.组装xml文件
					org.dom4j.Document document = createSendMessageXml(map,docType,bizType,seqNo, strBill.toString());
					//File fileName = new File(System.getProperty("user.dir")+ File.separator + strBill.toString()+ ".xml");
					//DomWrite.writeDocument(fileName.toString(),document);
					//7.校验xml                      
					File xsdFileName = new File(getXsdUrl()+File.separator+ "BWS101.xsd");
					ValidateXML validateXML = new ValidateXML();
					boolean flat = true;
					validateXML.validateXMLByXSD(document,xsdFileName.toString(),docType, bizType,areaCode,seqNo, strBill.toString());
					if(flat){
						//8.报文数据EDI_MESSAGE_DATA
						boolean ediMessageLog = savaEdiMessageData(docType, bizType, areaCode, seqNo, ediRoutingInfo, strBill, document);
						if(ediMessageLog){
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
	private EmsBwsBsc getdclTypecd(String seqNo) {
		EntityWrapper entityWrapper = new EntityWrapper<EmsBwsBsc>();
		entityWrapper.eq("SEQ_NO", seqNo);//单据编号
		return emsBwsBscService.selectOne(entityWrapper);
	}
	
	/**
     * 
     * @Description 创建物流账册报 XMl的文件
     * @param map 物流账册报
     * @param docType  单据类型
     * @param bizType 业务类型
     * @return
     */
    public Document createSendMessageXml(HashMap<String,List> map,final String docType,final String bizType,String seqNo,String strBill) {
		//List<EdiXmlMapDb> ediXmlInfos  = getEdiXmlInfo(docType,bizType);
		Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element root = document.addElement("Signature", SendMessageXmlEnum.SEND_MESSAGE_XSI_SIGNATURE_ONE);
        SendMessageHeadXml SendMessageHeadXml = new SendMessageHeadXml();
        Element dataInfoNode = SendMessageHeadXml.CreateSendSpecialSupervisionHeadXml(root,SendMessageGroupKindEnum.EMS_BWS_BSC_BIZTYPE,seqNo,strBill);
        BuildBwsBscXML buildBwsBscXML = new BuildBwsBscXML();
        buildBwsBscXML.setBillData(map, docType,bizType, dataInfoNode);
        document.setRootElement(root);
        return document;
    }
}
