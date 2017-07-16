package com.powerbridge.bssp.edi.xml.prod;

import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.xml.util.BeanUtil;
import com.powerbridge.bssp.edi.xml.util.XMLUtils;
import com.powerbridge.bssp.sas.entity.SasDclAcmpFormDt;
import com.powerbridge.bssp.sas.entity.SasDclBsc;
import com.powerbridge.bssp.sas.entity.SasDclDt;
import com.powerbridge.bssp.sas.entity.SasDclUcnsDt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @ClassName BuildSasDclBscXml
 * @Description 业务申报
 * @author Simon.xie
 * @Date 2017年6月5日 下午7:12:18
 * @version 1.0.0
 */
public class BuildSasDclBscXml {
    static final Log logger = LogFactory.getLog(BuildSasVehicleBscXml.class);
	public void setBillData(HashMap<String,List> map,String docType,String bizType, Element dataInfoNode) {
		//创建BussinessData根节点
        Element bussinessDataNode= dataInfoNode.addElement("BussinessData");
        
        //创建SAS101根节点
        Element sAS101Node  = bussinessDataNode.addElement("SAS101");
        String dclTypecd="";
        for (Entry<String, List> entry: map.entrySet()) {
            String  key = entry.getKey();
            List list = entry.getValue();
            if(key.equalsIgnoreCase("SAS_DCL_BSC")){
         	   List<SasDclBsc> sasDclBsc = (List<SasDclBsc>) list;
                dclTypecd = buildBscHead(sasDclBsc,docType,bizType, sAS101Node);
            }

            //备案，变更
            if(SendMessageDeclareTypeEnum.SAS101_DCL_TYPECD_1.equals(dclTypecd) || SendMessageDeclareTypeEnum.SAS101_DCL_TYPECD_2.equals(dclTypecd)){
                if(key.equalsIgnoreCase("SAS_DCL_DT")){
                    List<SasDclDt> sasDclDt = (List<SasDclDt>) list;
                    buildDtHead(sasDclDt,docType,bizType, sAS101Node);
                }

                if(key.equalsIgnoreCase("SAS_DCL_UCNS_DT")){
                    List<SasDclUcnsDt> SasDclUcnsDt = (List<SasDclUcnsDt>) list;
                    buildUcnsDtHead(SasDclUcnsDt,docType,bizType, sAS101Node);
                }
                if(key.equalsIgnoreCase("SAS_DCL_ACMP_FORM_DT")){
                    List<SasDclAcmpFormDt> sasDclAcmpFormDt = (List<SasDclAcmpFormDt>) list;
                    buildFormDtHead(sasDclAcmpFormDt,docType,bizType, sAS101Node);
                }
            }
         }
	}
	
	/**
     * 
     * @Description 构建业务申报随附单证数据
     * @param list 
     * @param docType
     * @param bizType
     * @param sAS101Node
     */
	private void buildFormDtHead(List<SasDclAcmpFormDt> list,String docType,String bizType, Element sAS101Node) {
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_DCL_ACMP_FORM_DT");
        if(list.size() > 0){
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建SasDclUcnsDt根节点
            String txt ="";
            String str ="";
            for(int  i= 0; i< list.size(); i ++){
                Element node = sAS101Node.addElement("SasDclAcmpFormDt");
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_DCL_ACMP_FORM_DT", xmlNmae);

                    try {
                        SasDclAcmpFormDt sasDclAcmpFormDt = new SasDclAcmpFormDt();
                        str = BeanUtil.getMethodReturnType(sasDclAcmpFormDt,beanMap.get(dbColumn));
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
     * @Description 构建业务申报单耗数据
     * @param list 
     * @param docType
     * @param bizType
     * @param sAS101Node
     */
	private void buildUcnsDtHead(List<SasDclUcnsDt> list,String docType,String bizType, Element sAS101Node) {
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_DCL_UCNS_DT");
        if(list.size() > 0){
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建SasDclUcnsDt根节点
            String txt ="";
            String str ="";
            for(int  i= 0; i< list.size(); i ++){
                Element node = sAS101Node.addElement("SasDclUcnsDt");
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_DCL_UCNS_DT", xmlNmae);

                    try {
                        SasDclUcnsDt sasDclUcnsDt = new SasDclUcnsDt();
                        str = BeanUtil.getMethodReturnType(sasDclUcnsDt,beanMap.get(dbColumn));
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
        /*else {
            Element node = sAS101Node.addElement("SasDclUcnsDt");
            for(int j = 0;j < ediXmlMapDbs.size(); j ++){
                node.addElement(ediXmlMapDbs.get(j).getXmlName());
            }
        }*/
	}
	
	/**
     * 
     * @Description 构建业务申报商品数据
     * @param list 
     * @param docType
     * @param bizType
     * @param sAS101Node
     */
	private void buildDtHead(List<SasDclDt> list,String docType,String bizType, Element sAS101Node) {
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_DCL_DT");
        if(list.size() > 0){
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建SasDclDt根节点
            String txt ="";
            String str ="";
            for(int  i= 0; i< list.size(); i ++){
                Element node = sAS101Node.addElement("SasDclDt");
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_DCL_DT", xmlNmae);

                    try {
                        SasDclDt sasDclDt = new SasDclDt();
                        str = BeanUtil.getMethodReturnType(sasDclDt,beanMap.get(dbColumn));
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
     * @param sAS101Node
     */
	private String buildBscHead(List<SasDclBsc> list,String docType,String bizType, Element sAS101Node) {
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_DCL_BSC");
        String dclTypecd="";
        if(list.size() > 0){
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建SasDclBsc根节点
            String txt ="";
            String str ="";
            Element node = sAS101Node.addElement("SasDclBsc");
            for(int  i= 0; i< list.size(); i ++){
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    //logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_DCL_BSC", xmlNmae);

                    try {
                        SasDclBsc sasDclBsc = new SasDclBsc();
                        str = BeanUtil.getMethodReturnType(sasDclBsc,beanMap.get(dbColumn));
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
