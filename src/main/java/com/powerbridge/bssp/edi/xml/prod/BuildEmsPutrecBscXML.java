package com.powerbridge.bssp.edi.xml.prod;

import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.xml.util.BeanUtil;
import com.powerbridge.bssp.edi.xml.util.XMLUtils;
import com.powerbridge.bssp.ems.entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @ClassName BuildEmsPutrecBscXML
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Simon.xie
 * @Date 2017年5月25日 下午9:53:09
 * @version 1.0.0
 */
public class BuildEmsPutrecBscXML {
    static final Log logger = LogFactory.getLog(BuildEmsPutrecBscXML.class);
	
	public void setBillData(HashMap<String,List> map,String docType,String bizType, Element dataInfoNode) {
		//创建BussinessData根节点
        Element bussinessDataNode= dataInfoNode.addElement("BussinessData");
        
        //创建EMS101根节点
        Element eMS111Node  = bussinessDataNode.addElement("EMS111");
        for (Entry<String, List> entry: map.entrySet()) {
           String  key = entry.getKey();
           List list = entry.getValue();
           if(key.equalsIgnoreCase("EMS_PUTREC_BSC")){
        	   List<EmsPutrecBsc> emsPutrecBsc = (List<EmsPutrecBsc>) list;
        	   buildTrHead(emsPutrecBsc, docType,bizType, eMS111Node);
           }else if(key.equalsIgnoreCase("EMS_PUTREC_IMG")){
        	   List<EmsPutrecImg> emsPutrecImg = (List<EmsPutrecImg>) list;
        	   buildTrImg(emsPutrecImg, docType,bizType, eMS111Node);
           }else if(key.equalsIgnoreCase("EMS_PUTREC_EXG")){
        	   List<EmsPutrecExg> emsPutrecExg = (List<EmsPutrecExg>) list;
        	   buildTrExg(emsPutrecExg, docType,bizType, eMS111Node);
           }else if(key.equalsIgnoreCase("EMS_PUTREC_UCNS_DT")){
        	   List<EmsPutrecUcnsDt> emsPutrecUcnsDt = (List<EmsPutrecUcnsDt>) list;
        	   buildPutrecUcnsDt(emsPutrecUcnsDt, docType,bizType, eMS111Node);
           }else if(key.equalsIgnoreCase("EMS_PUTREC_ACMP_FORM_DT")){
        	   List<EmsPutrecAcmpFormDt> emsPutrecAcmpFormDt = (List<EmsPutrecAcmpFormDt>) list;
        	   buildTrFromDt(emsPutrecAcmpFormDt, docType,bizType, eMS111Node);
           }
        }
	}
    
    /**
     * 
     * @Description 构建报文体随附单证明细数据
     * @param list
     * @param docType
     * @param bizType
     * @param eMS111Node
     */
	private void buildTrFromDt(List<EmsPutrecAcmpFormDt> list, String docType,String bizType, Element eMS111Node) {
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "EMS_PUTREC_ACMP_FORM_DT");
        if(list.size() > 0){
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建EmsPutrecAcmpFormDt根节点
            String txt ="";
            String str ="";
            for(int  i= 0; i< list.size(); i ++){
                Element node = eMS111Node.addElement("EmsPutrecAcmpFormDt");
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "EMS_PUTREC_ACMP_FORM_DT", xmlNmae);

                    try {
                        EmsPutrecAcmpFormDt emsPutrecAcmpFormDt = new EmsPutrecAcmpFormDt();
                        str = BeanUtil.getMethodReturnType(emsPutrecAcmpFormDt,beanMap.get(dbColumn));
                        logger.info(str);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    txt = String.valueOf(BeanKit.getProperty(list.get(i),beanMap.get(dbColumn)));
                    if(txt.equals("null")){
                        txt = BeanUtil.getNullTypeValue(str,txt);
                    }
                    if(dbColumn.equals("COL3")){
                        //txt = DateUtil.now();
                        txt ="2117-06-13 01:14:41";
                    }
                    if(txt.equals("")){
                        node.addElement(xmlNmae);
                    } else {
                        node.addElement(xmlNmae).addText(txt);
                    }
                }
            }
        }

	}
	
	 /**
     * 
     * @Description 构建报文体随附单证明细数据
      * @param list
     * @param docType
      * @param bizType
     * @param eMS111Node
     */
	private void buildPutrecUcnsDt(List<EmsPutrecUcnsDt> list, String docType,String bizType, Element eMS111Node) {
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "EMS_PUTREC_UCNS_DT");
        if(list.size() > 0){
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建EmsPutrecUcnsDt根节点
            String txt ="";
            String str ="";
            for(int  i= 0; i< list.size(); i ++){
                Element node = eMS111Node.addElement("EmsPutrecUcnsDt");
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "EMS_PUTREC_UCNS_DT", xmlNmae);

                    try {
                        EmsPutrecUcnsDt emsPutrecUcnsDt = new EmsPutrecUcnsDt();
                        str = BeanUtil.getMethodReturnType(emsPutrecUcnsDt,beanMap.get(dbColumn));
                        logger.info(str);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    txt = String.valueOf(BeanKit.getProperty(list.get(i),beanMap.get(dbColumn)));
                    if(txt.equals("null")){
                        txt = BeanUtil.getNullTypeValue(str,txt);
                    }
                    if(dbColumn.equals("COL3")){
                        //txt = DateUtil.now();
                        txt ="2117-06-13 01:14:41";
                    }
                    if(txt.equals("")){
                        node.addElement(xmlNmae);
                    } else {
                        node.addElement(xmlNmae).addText(txt);
                    }
                }
            }
        }
	}
    /**
     * 
     * @Description 构建报文体成品数据
     * @param list
     * @param docType
     * @param bizType
     * @param eMS111Node
     */
	private void buildTrExg(List<EmsPutrecExg> list, String docType,String bizType, Element eMS111Node) {
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "EMS_PUTREC_EXG");
        if(list.size() > 0){
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建成品 根节点
            String txt ="";
            String str ="";
            for(int  i= 0; i< list.size(); i ++){
                Element node = eMS111Node.addElement("EmsPutrecDt");
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "EMS_PUTREC_EXG", xmlNmae);

                    try {
                        EmsPutrecExg emsPutrecExg = new EmsPutrecExg();
                        str = BeanUtil.getMethodReturnType(emsPutrecExg,beanMap.get(dbColumn));
                        logger.info(str);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    txt = String.valueOf(BeanKit.getProperty(list.get(i),beanMap.get(dbColumn)));
                    if(txt.equals("null")){
                        txt = BeanUtil.getNullTypeValue(str,txt);
                    }
                    if(dbColumn.equals("COL3")){
                        //txt = DateUtil.now();
                        txt ="2117-06-13 01:14:41";
                    }
                    if(txt.equals("")){
                        node.addElement(xmlNmae);
                    } else {
                        node.addElement(xmlNmae).addText(txt);
                    }
                }
            }
        }
	}
    
    /**
     * 
     * @Description 构建报文体料件数据
     * @param list
     * @param docType
     * @param bizType
     * @param eMS111Node
     */
	private void buildTrImg(List<EmsPutrecImg> list, String docType,String bizType, Element eMS111Node) {
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "EMS_PUTREC_IMG");
        if(list.size() > 0){
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建料件 根节点
            String txt ="";
            String str ="";
            for(int  i= 0; i< list.size(); i ++){
                Element node = eMS111Node.addElement("EmsPutrecDt");
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "EMS_PUTREC_IMG", xmlNmae);

                    try {
                        EmsPutrecImg emsPutrecImg = new EmsPutrecImg();
                        str = BeanUtil.getMethodReturnType(emsPutrecImg,beanMap.get(dbColumn));
                        logger.info(str);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    txt = String.valueOf(BeanKit.getProperty(list.get(i),beanMap.get(dbColumn)));
                    if(txt.equals("null")){
                        txt = BeanUtil.getNullTypeValue(str,txt);
                    }
                    if(dbColumn.equals("COL3")){
                        //txt = DateUtil.now();
                        txt ="2117-06-13 01:14:41";
                    }
                    if(txt.equals("")){
                        node.addElement(xmlNmae);
                    } else {
                        node.addElement(xmlNmae).addText(txt);
                    }
                }
            }
        }
	}
    
    /**
     * 
     * @Description 构建表头数据
     * @param list
     * @param docType
     * @param bizType
     * @param eMS111Node
     */
	private void buildTrHead(List<EmsPutrecBsc> list, String docType,String bizType, Element eMS111Node) {
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "EMS_PUTREC_BSC");
        if(list.size() > 0){
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建ET_ARCRP_BSC根节点
            Element node = eMS111Node.addElement("EmsPutrecBsc");
            String txt ="";
            String str ="";
            for(int  i= 0; i< list.size(); i ++){
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    //logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "EMS_PUTREC_BSC", xmlNmae);

                    try {
                        EmsPutrecBsc emsPutrecBsc = new EmsPutrecBsc();
                        str = BeanUtil.getMethodReturnType(emsPutrecBsc,beanMap.get(dbColumn));
                        //logger.info(str);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    txt = String.valueOf(BeanKit.getProperty(list.get(i),beanMap.get(dbColumn)));
                    if(txt.equals("null")){
                        txt = BeanUtil.getNullTypeValue(str,txt);
                    }
                    if(dbColumn.equals("COL3")){
                        //txt = DateUtil.now();
                        txt ="2117-06-13 01:14:41";
                    }
                    if(txt.equals("")){
                        node.addElement(xmlNmae);
                    } else {
                        node.addElement(xmlNmae).addText(txt);
                    }
                }
            }
        }
	}
}
