package com.powerbridge.bssp.common.constants;

import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop.entity.CopEnt;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import com.powerbridge.bssp.dcr.entity.DcrChgoffBsc;
import com.powerbridge.bssp.ems.entity.EmsPutrecBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.inv.entity.InvBsc;
import com.powerbridge.bssp.sas.entity.SasDclBsc;
import com.powerbridge.bssp.sas.entity.SasStockBsc;
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;

import java.util.HashMap;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：DoctypeConstants
 * 类描述：单据类别和业务类型赋值
 * 创建人：danagao
 * 创建时间：2017/5/26 19:21
 * <p>
 * <p>
 *  表名            docType
 *
 * cop_ent	        A0301                   企业备案
 * et_arcrp_bsc   	A0302	    EMS101      联网企业档案库  ETPS_PREENT_NO
 * ems_putrec_bsc 	A0401	    EMS111      加工帐册备案    ETPS_PREENT_NO
 * ems_bws_bsc	    A0402	    BWS101      物流账册        ETPS_PREENT_NO
 * ems_putrec_bsc	A0403	    EMS111      加贸账册        ETPS_PREENT_NO
 * sas_passport_bsc	A0503	    SAS121      核放单         SAS_PASSPORT_PREENT_NO
 * sas_vehicle_bsc	A0504	    SAS131      车辆备案 ETPS_PREENT_NO
 * inv_bsc	        A0601	    INV101      保税清单 ETPS_INNER_INVT_NO
 * inv_bsc	        A0602	    INV101      加贸核注清单 ETPS_INNER_INVT_NO
 * inv_bsc	        A0603	    INV101      物流核注清单 ETPS_INNER_INVT_NO
 * inv_bsc	        A0604	    INV101      集装加工核注清单 ETPS_INNER_INVT_NO
 * inv_bsc	        A0605	    INV101      集装物流核注清单 ETPS_INNER_INVT_NO
 * inv_bsc	        A0606	    INV101      简单加工核注清单 ETPS_INNER_INVT_NO
 * sas_dcl_bsc      A0501.01	SAS101      加工业务申请表 ETPS_PREENT_NO
 * sas_dcl_bsc      A0501.02	SAS101      物流业务申请表 ETPS_PREENT_NO
 * sas_dcl_bsc      A0501.03	SAS101      简单加工业务申请表 ETPS_PREENT_NO
 * sas_stock_bsc    A0502.01	SAS111      加工出入库单 ETPS_PREENT_NO
 * sas_stock_bsc    A0502.02	SAS111      物流出入库单 ETPS_PREENT_NO
 * dcr_chgoff_bsc	A0801	    EMS121      物流账册报核 ETPS_PREENT_NO
 * dcr_chgoff_bsc	A0802	    EMS121      加工账册报核 ETPS_PREENT_NO
 * dcr_chgoff_bsc	A0803	    EMS121      加贸账册报核 ETPS_PREENT_NO
 */

public class DocTypeConstants {

    private static HashMap<String, Object> docTypeMap = new HashMap<>();

    private static DocTypeConstants docTypeConstants = new DocTypeConstants();

    private DocTypeConstants() {
        //企业备案
        docTypeMap.put(CopEnt.class.getName(), new Doctype("A0301", ""));

        //联网企业档案库
        docTypeMap.put(EtArcrpBsc.class.getName(), new Doctype("A0302", "EMS101"));

        //加工帐册备案
        docTypeMap.put(EmsPutrecBsc.class.getName() + "_jg", new Doctype("A0401", "EMS111"));

        //物流账册
        docTypeMap.put(EmsBwsBsc.class.getName(), new Doctype("A0402", "BWS101"));

        //加贸账册
        docTypeMap.put(EmsPutrecBsc.class.getName() + "_jm", new Doctype("A0403", "EMS111"));

        //核放单
        docTypeMap.put(SasPassportBsc.class.getName(), new Doctype("A0503", "SAS121"));

        //简单加工核放单
        docTypeMap.put(SasPassportBsc.class.getName() + "_simple", new Doctype("A0503.02", "SAS121"));

        //外发加工核放单
        docTypeMap.put(SasPassportBsc.class.getName() + "_outward", new Doctype("A0503.03", "SAS121"));

        //临时业务核放单
        docTypeMap.put(SasPassportBsc.class.getName() + "_temporary", new Doctype("A0503.04", "SAS121"));

        //车辆备案
        docTypeMap.put(SasVehicleBsc.class.getName(), new Doctype("A0504", "SAS131"));

        //保税清单(加工核注清单)
        docTypeMap.put(InvBsc.class.getName() + "_bs", new Doctype("A0601", "INV101"));

        //加贸核注清单
        docTypeMap.put(InvBsc.class.getName() + "_jm", new Doctype("A0602", "INV101"));

        //物流核注清单
        docTypeMap.put(InvBsc.class.getName() + "_wl", new Doctype("A0603", "INV101"));

        //集报加工核注清单
        docTypeMap.put(InvBsc.class.getName() + "_jbbs", new Doctype("A0604", "INV101"));

        //集报物流核注清单
        docTypeMap.put(InvBsc.class.getName() + "_jbwl", new Doctype("A0605", "INV101"));

        //简单加工核注清单
        docTypeMap.put(InvBsc.class.getName() + "_jdjg", new Doctype("A0606", "INV101"));

        //加工业务申请表
        docTypeMap.put(SasDclBsc.class.getName()+"_jg", new Doctype("A0501.01", "SAS101"));

        //物流业务申请表
        docTypeMap.put(SasDclBsc.class.getName()+"_wl", new Doctype("A0501.02", "SAS101"));

        //简单加工业务申请表
        docTypeMap.put(SasDclBsc.class.getName()+"_jdjg", new Doctype("A0501.03", "SAS101"));

        //外发加工申请表
        docTypeMap.put(SasDclBsc.class.getName()+"_wf", new Doctype("A0501.04", "SAS101"));

        //临时出入区申请表
        docTypeMap.put(SasDclBsc.class.getName()+"_ls", new Doctype("A0501.05", "SAS101"));

        //加工出入库单
        docTypeMap.put(SasStockBsc.class.getName()+"_jg", new Doctype("A0502.01", "SAS111"));

        //物流出入库单
        docTypeMap.put(SasStockBsc.class.getName()+"_wl", new Doctype("A0502.02", "SAS111"));

        //外发加工出入库单
        docTypeMap.put(SasStockBsc.class.getName()+"_wfjg", new Doctype("A0502.03", "SAS111"));

        //临时出入区出入库单
        docTypeMap.put(SasStockBsc.class.getName()+"_ls", new Doctype("A0502.04", "SAS111"));

        //物流账册报核
        docTypeMap.put(DcrChgoffBsc.class.getName() + "_wl", new Doctype("A0801", "EMS121"));

        //加工账册报核
        docTypeMap.put(DcrChgoffBsc.class.getName() + "_jg", new Doctype("A0802", "EMS121"));

        //加贸账册报核
        docTypeMap.put(DcrChgoffBsc.class.getName() + "_jm", new Doctype("A0803", "EMS121"));
    }


    //统一的调用方法,将实例和标识符传人,填充固定分类
    public static void setDocType(Object objectBean, String flag) throws Exception {

        String className = objectBean.getClass().getName();
        if (!StringUtil.isEmpty(flag)) {
            className += "_" + flag;
        }
        //
        if (!docTypeMap.containsKey(className)) {
            throw new Exception("未定义的分类:" + className);
        }

        if (BeanKit.propertyExists(objectBean, "docType")) {
            BeanKit.setProperty(objectBean, "docType", ((Doctype) docTypeMap.get(className)).getDocType());
        }
        if (BeanKit.propertyExists(objectBean, "bizType")) {
            BeanKit.setProperty(objectBean, "bizType", ((Doctype) docTypeMap.get(className)).getBizType());
        }

    }


    class Doctype {
        String docType;
        String bizType;

        public String getDocType() {
            return docType;
        }

        public void setDocType(String docType) {
            this.docType = docType;
        }

        public String getBizType() {
            return bizType;
        }

        public void setBizType(String bizType) {
            this.bizType = bizType;
        }

        public Doctype(String docType, String bizType) {
            this.docType = docType;
            this.bizType = bizType;
        }
    }

    public static void main(String[] args) {

        try {

            //单据新增
            EtArcrpBsc etArcrpBsc = new EtArcrpBsc();
            DocTypeConstants.setDocType(etArcrpBsc, null);


            System.out.println(etArcrpBsc.getDocType());
            System.out.println(etArcrpBsc.getBizType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
