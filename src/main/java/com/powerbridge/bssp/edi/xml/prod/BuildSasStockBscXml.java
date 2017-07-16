package com.powerbridge.bssp.edi.xml.prod;


import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.xml.util.BeanUtil;
import com.powerbridge.bssp.edi.xml.util.XMLUtils;
import com.powerbridge.bssp.sas.entity.SasStockBsc;
import com.powerbridge.bssp.sas.entity.SasStockDt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @ClassName BuildSasStockBscXml
 * @Description 出入库单表
 * @author Simon.xie
 * @Date 2017年6月5日 下午7:12:18
 * @version 1.0.0
 */
public class BuildSasStockBscXml {
    static final Log logger = LogFactory.getLog(BuildSasStockBscXml.class);
	public void setBillData(HashMap<String,List> map, String docType,String bizType, Element dataInfoNode) {
		//创建BussinessData根节点
        Element bussinessDataNode = dataInfoNode.addElement("BussinessData");
        
        //创建SAS111根节点
        Element sAS111Node  = bussinessDataNode.addElement("SAS111");
        String dclTypecd = "";
        for (Entry<String, List> entry: map.entrySet()) {
            String  key = entry.getKey();
            List list = entry.getValue();
            if(key.equalsIgnoreCase("SAS_STOCK_BSC")){
         	  List<SasStockBsc> sasStockBsc = (List<SasStockBsc>) list;
         	  dclTypecd = buildStockTrHead(sasStockBsc, docType,bizType, sAS111Node);
            }
            //正常申报
            if(SendMessageDeclareTypeEnum.SAS111_DCL_TYPECD_1.equals(dclTypecd) ){
                if(key.equalsIgnoreCase("SAS_STOCK_DT")){
                    List<SasStockDt> sasStockDt = (List<SasStockDt>) list;
                    buildStockDtHead(sasStockDt, docType,bizType, sAS111Node);
                }
            }

         }
	}
	
	
	 /**
     * 
     * @Description 构建出入库商品表数据
     * @param list
     * @param docType
      * @param bizType
     * @param sAS111Node
     */
	private void buildStockDtHead(List<SasStockDt> list, String docType,String bizType, Element sAS111Node) {
        String txt ="";
        String str ="";
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_STOCK_DT");
        if(list.size()>0) {
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建成品 根节点
            for(int  i= 0; i< list.size(); i ++){
                Element node = sAS111Node.addElement("SasStockDt");
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    //logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_STOCK_DT", xmlNmae);

                    try {
                        SasStockDt sasStockDt = new SasStockDt();
                        str = BeanUtil.getMethodReturnType(sasStockDt,beanMap.get(dbColumn));
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

    
    /**
     * 
     * @Description 构建表头数据
     * @param list
     * @param docType
     * @param bizType
     * @param sAS111Node
     */
	private String buildStockTrHead(List<SasStockBsc> list, String docType,String bizType, Element sAS111Node) {
        String txt ="";
        String str ="";
        String dclTypecd = "";
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_STOCK_BSC");
        if(list.size()>0) {
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建SasVehicleBsc根节点
            Element node = sAS111Node.addElement("SasStockBsc");
            for(int  i= 0; i< list.size(); i ++){
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    System.out.println(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_STOCK_BSC", xmlNmae);

                    try {
                        SasStockBsc asStockBsc = new SasStockBsc();
                        str = BeanUtil.getMethodReturnType(asStockBsc,beanMap.get(dbColumn));
                        System.out.println(str);

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
