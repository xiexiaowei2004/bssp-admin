package com.powerbridge.bssp.edi.xml.prod;

import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.xml.util.BeanUtil;
import com.powerbridge.bssp.edi.xml.util.XMLUtils;
import com.powerbridge.bssp.inv.entity.InvAcmpFormDt;
import com.powerbridge.bssp.inv.entity.InvBsc;
import com.powerbridge.bssp.inv.entity.InvDt;
import com.powerbridge.bssp.inv.entity.InvExg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @ClassName BuildInvBscXML
 * @Description 加贸核注清单,保税清单
 * @author Simon.xie
 * @Date 2017年5月25日 下午9:53:09
 * @version 1.0.0
 */
public class BuildInvBscXML {
	static final Log logger = LogFactory.getLog(BuildSasVehicleBscXml.class);
	public void setBillData(HashMap<String,List> map, String docType,String bizType, Element dataInfoNode) {
		//创建BussinessData根节点
        Element bussinessDataNode= dataInfoNode.addElement("BussinessData");
        
        //创建EMS101根节点
        Element iNV101  = bussinessDataNode.addElement("INV101");
        for (Entry<String, List> entry: map.entrySet()) {
            String  key = entry.getKey();
            List list = entry.getValue();
            if(key.equalsIgnoreCase("INV_BSC")){
         	   List<InvBsc> invBsc = (List<InvBsc>) list;
         	   buildInvBscHead(invBsc, docType,bizType, iNV101);
            }else if(key.equalsIgnoreCase("INV_IMG")){
         	   List<InvDt> invImg = (List<InvDt>) list;
         	   buildInvImg(invImg, docType,bizType, iNV101);
            }else if(key.equalsIgnoreCase("INV_EXG")){
         	   List<InvExg> invExg = (List<InvExg>) list;
         	   buildInvExg(invExg, docType,bizType, iNV101);
            }else if(key.equalsIgnoreCase("INV_ACMP_FORM_DT")){
         	   List<InvAcmpFormDt> invAcmpFormDt = (List<InvAcmpFormDt>) list;
         	   buildInvAcmpFromDt(invAcmpFormDt, docType,bizType, iNV101);
            }
         }
	}
    
    /**
     * 
     * @Description 构建报文体随附单证明细数据
     * @param docType
	 * @param bizType
     * @param iNV101
     */
	private void buildInvAcmpFromDt(List<InvAcmpFormDt> list, String docType,String bizType, Element iNV101) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "INV_EXG");
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建ET_ARCRP_ACMP_FORM_DT根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = iNV101.addElement("BondInvtAcmpFormDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "ET_ARCRP_ACMP_FORM_DT", xmlNmae);

					try {
						InvAcmpFormDt invAcmpFormDt = new InvAcmpFormDt();
						str = BeanUtil.getMethodReturnType(invAcmpFormDt,beanMap.get(dbColumn));
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
	 * @param bizType
     * @param iNV101
     */
	private void buildInvExg(List<InvExg> list, String docType,String bizType, Element iNV101) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "INV_EXG");
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建料件BondInvtDt根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = iNV101.addElement("BondInvtDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "INV_EXG", xmlNmae);

					try {
						InvExg invExg = new InvExg();
						str = BeanUtil.getMethodReturnType(invExg,beanMap.get(dbColumn));
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
	 * @param bizType
     * @param iNV101
     */
	private void buildInvImg(List<InvDt> list, String docType,String bizType, Element iNV101) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "INV_IMG");
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建料件ET_ARCRP_DT根节点
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				Element node = iNV101.addElement("BondInvtDt");
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "INV_IMG", xmlNmae);

					try {
						InvDt invImg = new InvDt();
						str = BeanUtil.getMethodReturnType(invImg,beanMap.get(dbColumn));
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
     * @param iNV101
     */
	private void buildInvBscHead(List<InvBsc> list, String docType,String bizType, Element iNV101) {
		EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
		List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "INV_BSC");
		if(list.size() > 0){
			Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
			//创建BondInvtBsc根节点
			Element node = iNV101.addElement("BondInvtBsc");
			String txt ="";
			String str ="";
			for(int  i= 0; i< list.size(); i ++){
				for (int j = 0; j < ediXmlMapDbs.size(); j++) {
					//logger.info(ediXmlMapDbs.get(j).getXmlName());
					String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
					//xmlNmae
					String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "INV_BSC", xmlNmae);

					try {
						InvBsc invBsc = new InvBsc();
						str = BeanUtil.getMethodReturnType(invBsc,beanMap.get(dbColumn));
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
