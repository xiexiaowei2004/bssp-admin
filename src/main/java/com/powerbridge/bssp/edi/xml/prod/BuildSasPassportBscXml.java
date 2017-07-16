package com.powerbridge.bssp.edi.xml.prod;

import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.xml.util.BeanUtil;
import com.powerbridge.bssp.edi.xml.util.XMLUtils;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @ClassName BuildSasVehicleBscXml
 * @Description 核放单
 * @author Simon.xie
 * @Date 2017年6月5日 下午7:12:18
 * @version 1.0.0
 */
public class BuildSasPassportBscXml {
	static final Log logger = LogFactory.getLog(BuildSasPassportBscXml.class);
	public void setBillData(HashMap<String,List> map, String docType,String bizType, Element dataInfoNode) {
		//创建BussinessData根节点
        Element bussinessDataNode= dataInfoNode.addElement("BussinessData");
        
        //创建EMS121根节点
        Element sAS121Node  = bussinessDataNode.addElement("SAS121");
        String dclTypecd = "";
        for (Entry<String, List> entry: map.entrySet()) {
            String  key = entry.getKey();
            List list = entry.getValue();
            if(key.equalsIgnoreCase("SAS_PASSPORT_BSC")){
            	List<SasPassportBsc> sasPassportBsc = (List<SasPassportBsc>) list;
				dclTypecd = buildTrHead(sasPassportBsc, docType,bizType, sAS121Node);
            }

			//正常申报
			if(SendMessageDeclareTypeEnum.SAS121_DCL_TYPECD_3.equals(dclTypecd) ){
				if(key.equalsIgnoreCase("SAS_PASSPORT_DT")){
					List<SasPassportDt> sasPassportDt = (List<SasPassportDt>) list;
					buildPassportDt(sasPassportDt, docType,bizType, sAS121Node);
				}

				if(key.equalsIgnoreCase("SAS_PASSPORT_RLT")){
					List<SasPassportRlt> sasPassportRlt = (List<SasPassportRlt>) list;
					buildPassportRlt(sasPassportRlt, docType,bizType, sAS121Node);
				}
			}

         }
	}
	
	
	/**
     * 
     * @Description 构建核放单关联单证表数据
     * @param list
     * @param docType
	 * @param bizType
     * @param sAS121Node
     */
	private void buildPassportRlt(List<SasPassportRlt> list, String docType,String bizType, Element sAS121Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_PASSPORT_RLT");
		if(list.size()>0) {
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建SasPassportRlt 根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = sAS121Node.addElement("SasPassportRlt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_PASSPORT_RLT", xmlNmae);

					try {
						SasPassportRlt sasPassportRlt = new SasPassportRlt();
						str = BeanUtil.getMethodReturnType(sasPassportRlt,beanMap.get(dbColumn));
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
     * @Description 构建核放单货物表数据
     * @param list
	  * @param docType
	  * @param bizType
     * @param sAS121Node
     */
	private void buildPassportDt(List<SasPassportDt> list, String docType,String bizType, Element sAS121Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_PASSPORT_DT");
		if(list.size()>0) {
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建SasPassportDt根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = sAS121Node.addElement("SasPassportDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_PASSPORT_DT", xmlNmae);

					try {
						SasPassportDt sasPassportDt = new SasPassportDt();
						str = BeanUtil.getMethodReturnType(sasPassportDt,beanMap.get(dbColumn));
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
     * @param sAS121Node
     */
	private String buildTrHead(List<SasPassportBsc> list, String docType,String bizType, Element sAS121Node) {
		String dclTypecd = "";
		String txt ="";
		String str ="";
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_PASSPORT_BSC");
		if(list.size()>0) {
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建SasPassportBsc根节点
			Element node = sAS121Node.addElement("SasPassportBsc");
			for(int  i= 0; i< list.size(); i ++){
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					//logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_PASSPORT_BSC", xmlNmae);

					try {
						SasPassportBsc sasPassportBsc = new SasPassportBsc();
						str = BeanUtil.getMethodReturnType(sasPassportBsc,beanMap.get(dbColumn));
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
