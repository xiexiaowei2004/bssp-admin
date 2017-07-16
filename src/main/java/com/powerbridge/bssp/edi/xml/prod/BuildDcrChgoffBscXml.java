package com.powerbridge.bssp.edi.xml.prod;

import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.dcr.entity.*;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.xml.util.BeanUtil;
import com.powerbridge.bssp.edi.xml.util.XMLUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @ClassName CreateSpringModelXML
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Simon.xie
 * @Date 2017年5月25日 下午9:53:09
 * @version 1.0.0
 */
public class BuildDcrChgoffBscXml {
	static final Log logger = LogFactory.getLog(BuildDcrChgoffBscXml.class);
	public void setBillData(HashMap<String,List> map, String docType,String bizType, Element dataInfoNode) {
		//创建BussinessData根节点
        Element bussinessDataNode= dataInfoNode.addElement("BussinessData");
        
        //创建EMS121根节点
        Element eMS121Node  = bussinessDataNode.addElement("EMS121");
        String chgoffTypecd = "";
        for (Entry<String, List> entry: map.entrySet()) {
            String  key = entry.getKey();
            List list = entry.getValue();
            if(key.equalsIgnoreCase("DCR_CHGOFF_BSC")){
         	   List<DcrChgoffBsc> dcrChgoffBsc = (List<DcrChgoffBsc>) list;
				chgoffTypecd = buildTrHead(dcrChgoffBsc, docType,bizType, eMS121Node);
            }

			//正常申报
			if(SendMessageDeclareTypeEnum.CHGOFF_TYPECD_1.equals(chgoffTypecd) ){
				if(key.equalsIgnoreCase("DCR_CHGOFF_INVT_LT")){
					List<DcrChgoffInvtLt> dcrChgoffInvtLt = (List<DcrChgoffInvtLt>) list;
					buildTrInvtLt(dcrChgoffInvtLt, docType,bizType, eMS121Node);
				}

				if(key.equalsIgnoreCase("DCR_CHGOFF_IMG")){
					List<DcrChgoffImg> dcrChgoffImg = (List<DcrChgoffImg>) list;
					buildTrImg(dcrChgoffImg, docType,bizType, eMS121Node);
				}

				if(key.equalsIgnoreCase("DCR_CHGOFF_EXG")){
					List<DcrChgoffExg> dcrChgoffExg = (List<DcrChgoffExg>) list;
					buildTrExg(dcrChgoffExg, docType,bizType, eMS121Node);
				}
			}

			//补充申报
			if(SendMessageDeclareTypeEnum.CHGOFF_TYPECD_2.equals(chgoffTypecd) ){
				if(key.equalsIgnoreCase("DCR_CHGOFF_INVT_LT")){
					List<DcrChgoffInvtLt> dcrChgoffInvtLt = (List<DcrChgoffInvtLt>) list;
					buildTrInvtLt(dcrChgoffInvtLt, docType,bizType, eMS121Node);
				}

				if(key.equalsIgnoreCase("DCR_ADJACC_ACMP_FORM_DT")){
					List<DcrAdjaccAcmpFormDt> dcrAdjaccAcmpFormDt = (List<DcrAdjaccAcmpFormDt>) list;
					buildTrFromDt(dcrAdjaccAcmpFormDt, docType,bizType, eMS121Node);
				}
			}
         }
	}
	
	/**
     * 
     * @Description 构建账册报核清单数据
     * @param list
     * @param docType
	 * @param bizType
	 * @param eMS121Node
     */
	private void buildTrInvtLt(List<DcrChgoffInvtLt> list, String docType,String bizType, Element eMS121Node) {
		String txt ="";
		String str ="";
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "DCR_CHGOFF_INVT_LT");
		if(list.size()>0) {
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建ET_ARCRP_ACMP_FORM_DT根节点
			for(int  i= 0; i< list.size(); i ++){
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					Element node = eMS121Node.addElement("EmsChgoffInvtLt");
					//logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "DCR_CHGOFF_INVT_LT", xmlNmae);

					try {
						DcrChgoffInvtLt dcrChgoffInvtLt = new DcrChgoffInvtLt();
						str = BeanUtil.getMethodReturnType(dcrChgoffInvtLt,beanMap.get(dbColumn));
						//logger.info(str);

					} catch (Exception e) {
						e.printStackTrace();
					}

					txt = String.valueOf(BeanKit.getProperty(list.get(i),beanMap.get(dbColumn)));
					if(txt.equals("null")){
						txt = BeanUtil.getNullTypeValue(str,txt);
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
     * @Description 构建账册核销随附单证明细数据
     * @param list
	 * @param docType
	 * @param bizType
     * @param eMS121Node
     */
	private void buildTrFromDt(List<DcrAdjaccAcmpFormDt> list, String docType,String bizType, Element eMS121Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "DCR_ADJACC_ACMP_FORM_DT");
		if(list.size()>0) {
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建ET_EmsAdjaccAcmpFormDt根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = eMS121Node.addElement("EmsAdjaccAcmpFormDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "DCR_ADJACC_ACMP_FORM_DT", xmlNmae);

					try {
						DcrAdjaccAcmpFormDt dcrAdjaccAcmpFormDt = new DcrAdjaccAcmpFormDt();
						str = BeanUtil.getMethodReturnType(dcrAdjaccAcmpFormDt,beanMap.get(dbColumn));
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
     * @param eMS121Node
     */
	private void buildTrExg(List<DcrChgoffExg> list, String docType,String bizType, Element eMS121Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "DCR_CHGOFF_EXG");
		if(list.size()>0) {
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建账册报核成品 根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = eMS121Node.addElement("EmsChgoffDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "DCR_CHGOFF_EXG", xmlNmae);

					try {
						DcrChgoffExg dcrChgoffExg = new DcrChgoffExg();
						str = BeanUtil.getMethodReturnType(dcrChgoffExg,beanMap.get(dbColumn));
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
     * @param eMS121Node
     */
	private void buildTrImg(List<DcrChgoffImg> list, String docType,String bizType, Element eMS121Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "DCR_CHGOFF_IMG");
		if(list.size()>0) {
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建料件EmsChgoffDt根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = eMS121Node.addElement("EmsChgoffDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "DCR_CHGOFF_IMG", xmlNmae);

					try {
						DcrChgoffImg dcrChgoffImg = new DcrChgoffImg();
						str = BeanUtil.getMethodReturnType(dcrChgoffImg,beanMap.get(dbColumn));
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
     * @param eMS121Node
     */
	private String buildTrHead(List<DcrChgoffBsc> list, String docType,String bizType, Element eMS121Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "DCR_CHGOFF_BSC");
		String chgoffTypecd = "";
		String txt ="";
		String str ="";
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建EmsChgoffBsc根节点
			Element node = eMS121Node.addElement("EmsChgoffBsc");
			for(int  i= 0; i< list.size(); i ++) {
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "DCR_CHGOFF_BSC", xmlNmae);

					try {
						DcrChgoffBsc dcrChgoffBsc = new DcrChgoffBsc();
						str = BeanUtil.getMethodReturnType(dcrChgoffBsc, beanMap.get(dbColumn));
						logger.info(str);

					} catch (Exception e) {
						e.printStackTrace();
					}

					txt = String.valueOf(BeanKit.getProperty(list.get(i), beanMap.get(dbColumn)));
					if (txt.equals("null")) {
						txt = BeanUtil.getNullTypeValue(str, txt);
					}
					if (dbColumn.equals("COL3")) {
						//txt = DateUtil.now();
						txt = "2117-06-13 01:14:41";
					}
					if(txt.equals("")){
						node.addElement(xmlNmae);
					} else {
						node.addElement(xmlNmae).addText(txt);
					}
					if (xmlNmae.equals("dclTypecd")) {
						chgoffTypecd = txt;
					}
				}
			}
		}
        return chgoffTypecd;
	}

}
