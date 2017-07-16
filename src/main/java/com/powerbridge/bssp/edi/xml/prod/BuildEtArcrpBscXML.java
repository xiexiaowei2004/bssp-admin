package com.powerbridge.bssp.edi.xml.prod;

import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.cop_et.entity.EtArcrpAcmpFormDt;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import com.powerbridge.bssp.cop_et.entity.EtArcrpExg;
import com.powerbridge.bssp.cop_et.entity.EtArcrpImg;
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
public class BuildEtArcrpBscXML {
	static final Log logger = LogFactory.getLog(BuildEtArcrpBscXML.class);
	public void setBillData(HashMap<String,List> map,String docType,String bizType, Element dataInfoNode) {
		//创建BussinessData根节点
        Element bussinessDataNode= dataInfoNode.addElement("BussinessData");
        
        //创建EMS101根节点
        Element eMS101Node  = bussinessDataNode.addElement("EMS101");
        for (Entry<String, List> entry: map.entrySet()) {
            String  key = entry.getKey();
            List list = entry.getValue();
            if(key.equalsIgnoreCase("ET_ARCRP_BSC")){
         	   List<EtArcrpBsc> etArcrpBsc = (List<EtArcrpBsc>) list;
         	   buildTrHead(etArcrpBsc, docType,bizType, eMS101Node);
            }else if(key.equalsIgnoreCase("ET_ARCRP_IMG")){
         	   List<EtArcrpImg> etArcrpImg = (List<EtArcrpImg>) list;
         	   buildTrImg(etArcrpImg, docType,bizType, eMS101Node);
            }else if(key.equalsIgnoreCase("ET_ARCRP_EXG")){
         	   List<EtArcrpExg> etArcrpExg = (List<EtArcrpExg>) list;
         	   buildTrExg(etArcrpExg, docType,bizType, eMS101Node);
            }else if(key.equalsIgnoreCase("ET_ARCRP_ACMP_FORM_DT")){
         	   List<EtArcrpAcmpFormDt> etArcrpAcmpFormDt = (List<EtArcrpAcmpFormDt>) list;
         	   buildTrFromDt(etArcrpAcmpFormDt, docType,bizType, eMS101Node);
            }
         }
	}
    
    /**
     * 
     * @Description 构建报文体随附单证明细数据
	 * @param list
     * @param docType
	 * @param bizType
     * @param eMS101Node
     */
	private void buildTrFromDt(List<EtArcrpAcmpFormDt> list,String docType,String bizType, Element eMS101Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "ET_ARCRP_ACMP_FORM_DT");
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建ET_ARCRP_ACMP_FORM_DT根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = eMS101Node.addElement("EtArcrpAcmpFormDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "ET_ARCRP_ACMP_FORM_DT", xmlNmae);

					try {
						EtArcrpAcmpFormDt etArcrpAcmpFormDt = new EtArcrpAcmpFormDt();
						str = BeanUtil.getMethodReturnType(etArcrpAcmpFormDt,beanMap.get(dbColumn));
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
					//日期转换
					/*try {
						if(!txt.equals("")){
							Boolean flat = DateUtil.StringCheckDateTime(txt);
							if(flat){
								txt = DateUtil.DateString3formatString(txt);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}*/
					node.addElement(xmlNmae).addText(txt);
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
     * @param eMS101Node
     */
	private void buildTrExg(List<EtArcrpExg> list, String docType,String bizType, Element eMS101Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "ET_ARCRP_EXG");
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建成品 根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = eMS101Node.addElement("EtArcrpDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "ET_ARCRP_EXG", xmlNmae);

					try {
						EtArcrpExg etArcrpExg = new EtArcrpExg();
						str = BeanUtil.getMethodReturnType(etArcrpExg,beanMap.get(dbColumn));
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
					//日期转换
					/*try {
						if(!txt.equals("")){
							Boolean flat = DateUtil.StringCheckDateTime(txt);
							if(flat){
								txt = DateUtil.DateString3formatString(txt);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}*/
					node.addElement(xmlNmae).addText(txt);
				}
			}
		}
	}
    
    /**
     * 
     * @Description 构建报文体料件数据
     * @param list
     * @param docType
	 *  @param bizType
     * @param eMS101Node
     */
	private void buildTrImg(List<EtArcrpImg> list, String docType,String bizType, Element eMS101Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "ET_ARCRP_IMG");
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建料件ET_ARCRP_DT根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = eMS101Node.addElement("EtArcrpDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "ET_ARCRP_IMG", xmlNmae);

					try {
						EtArcrpImg etArcrpImg = new EtArcrpImg();
						str = BeanUtil.getMethodReturnType(etArcrpImg,beanMap.get(dbColumn));
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
     * @param eMS101Node
     */
	private void buildTrHead(List<EtArcrpBsc> list, String docType,String bizType, Element eMS101Node) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "ET_ARCRP_BSC");
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建ET_ARCRP_BSC根节点
			Element node = eMS101Node.addElement("EtArcrpBsc");
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					//logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "ET_ARCRP_BSC", xmlNmae);

					try {
						EtArcrpBsc etArcrpBsc = new EtArcrpBsc();
						str = BeanUtil.getMethodReturnType(etArcrpBsc,beanMap.get(dbColumn));
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
