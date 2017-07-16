package com.powerbridge.bssp.edi.xml.prod;


import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.xml.util.BeanUtil;
import com.powerbridge.bssp.edi.xml.util.XMLUtils;
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;
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
 * @Description 车辆信息备案
 * @author Simon.xie
 * @Date 2017年6月5日 下午7:12:18
 * @version 1.0.0
 */
public class BuildSasVehicleBscXml {
    static final Log logger = LogFactory.getLog(BuildSasVehicleBscXml.class);
	public void setBillData(HashMap<String,List> map, String docType,String bizType, Element dataInfoNode) {
		//创建BussinessData根节点
        Element bussinessDataNode= dataInfoNode.addElement("BussinessData");
        
        //创建EMS121根节点
        Element sAS131Node  = bussinessDataNode.addElement("SAS131");
        for (Entry<String, List> entry: map.entrySet()) {
            String  key = entry.getKey();
            List list = entry.getValue();
            if(key.equalsIgnoreCase("SAS_VEHICLE_BSC")){
         	   List<SasVehicleBsc> sasVehicleBsc = (List<SasVehicleBsc>) list;
         	  buildTrHead(sasVehicleBsc, docType,bizType, sAS131Node);
            }
         }
	}

    /**
     * 
     * @Description 构建表头数据
     * @param list
     * @param docType
     * @param bizType
     * @param sAS131Node
     */
	private void buildTrHead(List<SasVehicleBsc> list, String docType,String bizType, Element sAS131Node) {
        String txt ="";
        String str ="";
        EdiXmlInfoData ediXmlInfoData = new EdiXmlInfoData();
        List<EdiXmlMapDb> ediXmlMapDbs = ediXmlInfoData.getEdiXmlInfo(docType, bizType, "SAS_VEHICLE_BSC");
        if(list.size()>0) {
            Map<String,String> beanMap = BeanUtil.getBeanMap(list.get(0));
            //创建SasVehicleBsc根节点
            Element node = sAS131Node.addElement("SasVehicleBsc");
            for(int  i= 0; i< list.size(); i ++){
                for (int j = 0; j < ediXmlMapDbs.size(); j++) {
                    //logger.info(ediXmlMapDbs.get(j).getXmlName());
                    String xmlNmae = ediXmlMapDbs.get(j).getXmlName();
                    //xmlNmae
                    String dbColumn = XMLUtils.getDbName(ediXmlMapDbs, "SAS_VEHICLE_BSC", xmlNmae);

                    try {
                        SasVehicleBsc sasVehicleBsc = new SasVehicleBsc();
                        str = BeanUtil.getMethodReturnType(sasVehicleBsc,beanMap.get(dbColumn));
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
