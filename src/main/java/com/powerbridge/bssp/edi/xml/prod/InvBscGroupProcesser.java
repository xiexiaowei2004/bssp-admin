package com.powerbridge.bssp.edi.xml.prod;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.listener.WebContextListener;
import com.powerbridge.bssp.common.util.DateUtils;
import com.powerbridge.bssp.common.util.RandomUtils;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.edi.xml.util.DomWrite;
import com.powerbridge.bssp.inv.entity.InvBsc;
import com.powerbridge.bssp.inv.service.IInvBscService;
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
 * @ClassName InvBscJmGroupProcesser
 * @Description 加贸核注清单,保税清单报文生成器
 * @author Simon.xie
 * @Date 2017年6月1日 下午10:37:23
 * @version 1.0.0
 */
public class InvBscGroupProcesser extends GroupProcesser {
	private WebApplicationContext wac = WebContextListener.getCurrentWebApplicationContext();   // 获取容器（上下文环境）
	//报文头表服务
	private IInvBscService invBscService;
	public InvBscGroupProcesser(){
        // 通过容器 获取service
		invBscService = (IInvBscService)wac.getBean("invBscService");
    }
	
	@Override
	public void execute(String docType,String bizType,String areaCode,String seqNo,BigDecimal chgTmsCnt) {
		//1.查询路由配置表
		EdiRoutingInfo ediRoutingInfo = getEdiRoutingInfo(docType, bizType, areaCode, seqNo);
		if(ediRoutingInfo != null) {
			//2.产生报文生成规则：bizType+申报类型代码+单据编号+日期+3位随机数
			InvBsc invBsc = getdclTypecd(seqNo);
			if(invBsc !=null){
				StringBuffer strBill = new StringBuffer();
				strBill.append(docType).append("_").append(invBsc.getDeclareMark()).append("_")
				.append(seqNo).append("_").append(DateUtils.getStringDate()).append("_")
				.append(RandomUtils.getFixLenthString(3));
				//3.组装sql
				HashMap<String,List> map = getAssembleSql(docType, bizType, seqNo,areaCode,chgTmsCnt);
				if(map.size()>0){
					//5.组装xml文件
					org.dom4j.Document document = createSendMessageXml(map,docType,bizType,seqNo, strBill.toString());
					//File fileName = new File(System.getProperty("user.dir")+ File.separator + strBill.toString()+ ".xml");
					//DomWrite.writeDocument(fileName.toString(),document);
					//6.校验xml
					File xsdFileName = new File(getXsdUrl()+File.separator+ "INV101.xsd");
					ValidateXML validateXML = new ValidateXML();
					boolean flat = true;
					validateXML.validateXMLByXSD(document,xsdFileName.toString(),docType, bizType,areaCode,seqNo, strBill.toString());
					if(flat) {
						//7.报文数据EDI_MESSAGE_DATA
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
	private InvBsc getdclTypecd(String seqNo) {
		EntityWrapper entityWrapper = new EntityWrapper<InvBsc>();
		entityWrapper.eq("SEQ_NO", seqNo);//单据编号
		return invBscService.selectOne(entityWrapper);
	}
	
	/**
     * 
     * @Description 创建加贸核注清单,保税清单 XMl的文件
     * @param map 保税核注清单表 
     * @param docType  单据类型
     * @param bizType 业务类型
     * @return
     */
    public Document createSendMessageXml(HashMap<String,List> map,String docType,String bizType,String seqNo,String strBill) {
		//List<EdiXmlMapDb> ediXmlInfos  = getEdiXmlInfo(docType,bizType);
		Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element root = document.addElement("Signature", SendMessageXmlEnum.SEND_MESSAGE_XSI_SIGNATURE_ONE);
        SendMessageHeadXml SendMessageHeadXml = new SendMessageHeadXml();
        Element dataInfoNode = SendMessageHeadXml.CreateSendSpecialSupervisionHeadXml(root,SendMessageGroupKindEnum.INV_BSC_BS_BIZTYPE,seqNo, strBill.toString());
        BuildInvBscXML buildInvBscXML = new BuildInvBscXML();
        buildInvBscXML.setBillData(map, docType,bizType, dataInfoNode);
        document.setRootElement(root);
        return document;
    }

}
