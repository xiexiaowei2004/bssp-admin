package com.powerbridge.bssp.edi.xml.prod;

import java.time.LocalDateTime;

import org.dom4j.Element;

import com.powerbridge.bssp.edi.entity.EdiMessageHead;
/**
 * 
 * @ClassName SendMessageHeadXml
 * @Description 组装发送报文头xml
 * @author Simon.xie
 * @Date 2017年6月4日 下午4:37:17
 * @version 1.0.0
 */
public class SendMessageHeadXml {
	/**
     * 
     * @Description 组装发送报文头xml-基础
     * @param ediMessageHead 报文头表
     * @param root 
     * @return
     */
    public Element CreateSendBeasHeadXml(Element root,String messageType,String seqNo,String strBill) {
		// 创建SignedInfo根节点
        Element signedInfoNode = root.addElement("SignedInfo");
        Element canonicalizationMethod = signedInfoNode.addElement("CanonicalizationMethod");
        canonicalizationMethod.addAttribute("Algorithm", SendMessageXmlEnum.SEND_MESSAGE_XSI_SIGNATURE_ONE);
        //canonicalizationMethod.addAttribute("Algorithm", "");
        Element signatureMethod = signedInfoNode.addElement("SignatureMethod");
        signatureMethod.addAttribute("Algorithm", SendMessageXmlEnum.SEND_MESSAGE_XSI_ALGORITHM_TWO);
        //signatureMethod.addAttribute("Algorithm", "");
        Element reference = signedInfoNode.addElement("Reference");
        Element algorithmNode = reference.addAttribute("URI", "String");
        Element digestMethod  = algorithmNode.addElement("DigestMethod");
        digestMethod.addAttribute("Algorithm", SendMessageXmlEnum.SEND_MESSAGE_XSI_ALGORITHM_THREE);
        //digestMethod.addAttribute("Algorithm", "");
        algorithmNode.addElement("DigestValue").addText("");
        
        //创建SignatureValue根节点
        root.addElement("SignatureValue").addText("");
        //创建SignatureValue根节点
        Element keyInfoNode = root.addElement("KeyInfo");
        //动态加载值
        keyInfoNode.addElement("KeyName").addText("aa");
        
      //创建Object根节点
        Element objectNode = root.addElement("Object");
        Element packageNode  = objectNode.addElement("Package");
        Element envelopInfo  = packageNode.addElement("EnvelopInfo");
        envelopInfo.addElement("version").addText(TransTypeEnum.CLASSVER);      //报文头
        envelopInfo.addElement("business_id").addText(seqNo);
        envelopInfo.addElement("message_id").addText(strBill);
        envelopInfo.addElement("file_name").addText(strBill+".xml");
        envelopInfo.addElement("message_type").addText(messageType);
        envelopInfo.addElement("sender_id").addText("1234");
        envelopInfo.addElement("receiver_id").addText("1234");
        envelopInfo.addElement("send_time").addText(LocalDateTime.now()+"");
        
        //创建DataInfo根节点
        Element dataInfoNode  = packageNode.addElement("DataInfo");
        Element pocketInfoNode= dataInfoNode.addElement("PocketInfo");
        pocketInfoNode.addElement("pocket_id");
        pocketInfoNode.addElement("total_pocket_qty").addText("1");
        pocketInfoNode.addElement("cur_pocket_no").addText(1+"");
        //pocketInfoNode.addElement("is_unstructured").addText("");
		return dataInfoNode;
	}
    
    /**
     * 
     * @Description 组装发送报文头xml-基础
     * @param ediMessageHead 报文头表
     * @param root 
     * @return
     */
    public Element CreateSendSpecialSupervisionHeadXml(Element root,String messageType,String seqNo,String strBill) {
		// 创建SignedInfo根节点
        Element signedInfoNode = root.addElement("SignedInfo");
        Element canonicalizationMethod = signedInfoNode.addElement("CanonicalizationMethod");
        canonicalizationMethod.addAttribute("Algorithm", SendMessageXmlEnum.SEND_MESSAGE_XSI_ALGORITHM_ONE);
        Element signatureMethod = signedInfoNode.addElement("SignatureMethod");
        signatureMethod.addAttribute("Algorithm", SendMessageXmlEnum.SEND_MESSAGE_XSI_ALGORITHM_TWO);
        Element reference = signedInfoNode.addElement("Reference");
        Element algorithmNode = reference.addAttribute("URI", "String");
        Element digestMethod  = algorithmNode.addElement("DigestMethod");
        digestMethod.addAttribute("Algorithm", SendMessageXmlEnum.SEND_MESSAGE_XSI_ALGORITHM_THREE);
        algorithmNode.addElement("DigestValue").addText("");
        
        //创建SignatureValue根节点
        root.addElement("SignatureValue").addText("");
        //创建SignatureValue根节点
        Element keyInfoNode = root.addElement("KeyInfo");
        //动态加载值
        keyInfoNode.addElement("KeyName").addText("aa");
        
      //创建Object根节点
        Element objectNode = root.addElement("Object");
        Element packageNode  = objectNode.addElement("Package");
        Element envelopInfo  = packageNode.addElement("EnvelopInfo");
        envelopInfo.addElement("version").addText(TransTypeEnum.CLASSVER);      //报文头
        envelopInfo.addElement("business_id").addText(seqNo);
        envelopInfo.addElement("message_id").addText(strBill);
        envelopInfo.addElement("file_name").addText(strBill+".xml");
        envelopInfo.addElement("message_type").addText(messageType);
        envelopInfo.addElement("sender_id").addText("1234");
        envelopInfo.addElement("receiver_id").addText("1234");
        envelopInfo.addElement("send_time").addText(LocalDateTime.now()+"");
        
        //创建DataInfo根节点
        Element dataInfoNode  = packageNode.addElement("DataInfo");
        Element pocketInfoNode= dataInfoNode.addElement("PocketInfo");
        pocketInfoNode.addElement("pocket_id");
        pocketInfoNode.addElement("total_pocket_qty").addText(1+"");
        pocketInfoNode.addElement("cur_pocket_no").addText(1+"");
		return dataInfoNode;
	}
}
