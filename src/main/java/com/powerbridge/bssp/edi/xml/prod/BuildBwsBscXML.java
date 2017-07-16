package com.powerbridge.bssp.edi.xml.prod;

import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.xml.util.BeanUtil;
import com.powerbridge.bssp.edi.xml.util.XMLUtils;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsAcmpFormDt;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsDt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @ClassName BuildBwsBscXML
 * @Description 物流账册
 * @author Simon.xie
 * @Date 2017年5月25日 下午9:53:09
 * @version 1.0.0
 */
public class BuildBwsBscXML {
	static final Log logger = LogFactory.getLog(BuildDcrChgoffBscXml.class);
	public void setBillData(HashMap<String,List> map,String docType,String bizType, Element dataInfoNode) {
		//创建BussinessData根节点
        Element bussinessDataNode= dataInfoNode.addElement("BussinessData");
        
        //创建BWS101根节点
        Element bWS101Node  = bussinessDataNode.addElement("BWS101");
		String dclTypecd="";
        for (Entry<String, List> entry: map.entrySet()) {
            String  key = entry.getKey();
            List list = entry.getValue();
            if(key.equalsIgnoreCase("EMS_BWS_BSC")){
         	   List<EmsBwsBsc> emsBwsBsc = (List<EmsBwsBsc>) list;
				dclTypecd = buildBwsBscHead(emsBwsBsc, docType,bizType, bWS101Node);
            }

			//备案
			//if(SendMessageDeclareTypeEnum.BWS101_DCL_TYPECD_1.equals(dclTypecd) ){
				if(key.equalsIgnoreCase("EMS_BWS_ACMP_FORM_DT")){
					List<EmsBwsAcmpFormDt> emsBwsAcmpFormDt = (List<EmsBwsAcmpFormDt>) list;
					buildTrFromDt(emsBwsAcmpFormDt, docType,bizType, bWS101Node);
				}
			//}

			//变更
			//if(SendMessageDeclareTypeEnum.BWS101_DCL_TYPECD_2.equals(dclTypecd)){
				if(key.equalsIgnoreCase("EMS_BWS_DT")){
					List<EmsBwsDt> emsBwsDt = (List<EmsBwsDt>) list;
					buildBwsDtImg(emsBwsDt, docType,bizType, bWS101Node);
				}

				if(key.equalsIgnoreCase("EMS_BWS_ACMP_FORM_DT")){
					List<EmsBwsAcmpFormDt> emsBwsAcmpFormDt = (List<EmsBwsAcmpFormDt>) list;
					buildTrFromDt(emsBwsAcmpFormDt, docType,bizType, bWS101Node);
				}
			//}
         }
	}
    
    /**
     * 
     * @Description 构建报文体随附单证明细数据
     * @param list
     * @param docType
	 * @param bizType
	 * @param bWS101Node
     */
	private void buildTrFromDt(List<EmsBwsAcmpFormDt> list, String docType,String bizType, Element bWS101Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "EMS_BWS_ACMP_FORM_DT");
		if(list.size()>0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建PreBwsPutrecAcmpFormDt根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = bWS101Node.addElement("PreBwsPutrecAcmpFormDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "EMS_BWS_ACMP_FORM_DT", xmlNmae);

					try {
						EmsBwsAcmpFormDt emsBwsAcmpFormDt = new EmsBwsAcmpFormDt();
						str = BeanUtil.getMethodReturnType(emsBwsAcmpFormDt,beanMap.get(dbColumn));
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
     * @param bWS101Node
     */
	private void buildBwsDtImg(List<EmsBwsDt> list, String docType,String bizType, Element bWS101Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "EMS_BWS_DT");
		if(list.size()>0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建料件BwsDt根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = bWS101Node.addElement("BwsDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "EMS_BWS_DT", xmlNmae);

					try {
						EmsBwsDt emsBwsDt = new EmsBwsDt();
						str = BeanUtil.getMethodReturnType(emsBwsDt,beanMap.get(dbColumn));
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
     * @param bWS101Node
     */
	private String buildBwsBscHead(List<EmsBwsBsc> list, String docType,String bizType, Element bWS101Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "EMS_BWS_BSC");
		String dclTypecd = "";
		String txt ="";
		String str ="";
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建BwsBsc根节点
			Element node = bWS101Node.addElement("BwsBsc");
			for(int  i= 0; i< list.size(); i ++){
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					//logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "EMS_BWS_BSC", xmlNmae);

					try {
						EmsBwsBsc emsBwsBsc = new EmsBwsBsc();
						str = BeanUtil.getMethodReturnType(emsBwsBsc,beanMap.get(dbColumn));
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
					if(xmlNmae.equals("dclTypecd")){
						dclTypecd = txt;
					}
				}
			}
		}
        return dclTypecd;
	}

}
