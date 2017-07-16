package com.powerbridge.bssp.edi.xml.prod;

/**
 * 
 * @ClassName SendMessageFactory
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Simon.xie
 * @Date 2017年6月4日 下午4:20:26
 * @version 1.0.0
 */
public class SendMessageFactory {
   public static IGroupProcesser createGroupProcesser(final String docType,final String bizType) {
	    IGroupProcesser groupProcesser = null;
	   switch (bizType) {
		   case SendMessageGroupKindEnum.ET_ARCRP_BSC_BIZTYPE:  //EMS101
			   System.out.println("----------------------企业档案库--------------------");
			   // 企业档案库报文处理方式
			   groupProcesser = new EtArcrpBscGroupProcesser();
			   break;
		   case SendMessageGroupKindEnum.EMS_PUTREC_BSC_BIZTYPE://EMS111
			   System.out.println("----------------------加工帐册备案--------------------");
			   // 加工帐册备案,加贸账册
			   groupProcesser = new EmsPutrecBscGroupProcesser();
			   break;
		   case SendMessageGroupKindEnum.EMS_BWS_BSC_BIZTYPE: //BWS101
			   System.out.println("----------------------物流账册--------------------");
			   // 物流账册
			   groupProcesser = new EmsBwsBscGroupProcesser();
			   break;
		   case SendMessageGroupKindEnum.SAS_PASSPORT_BSC_BIZTYPE: //SAS121
			   System.out.println("----------------------核放单--------------------");
			   groupProcesser = new SasPassportBscGroupProcesser();
			   break;
		   case SendMessageGroupKindEnum.INV_BSC_BS_BIZTYPE:   //inv101
			   System.out.println("----------------------保税清单--------------------");
			   //保税清单
			   groupProcesser = new InvBscGroupProcesser();
			   break;
		   case SendMessageGroupKindEnum.DCR_CHGOFF_BSC_BIZTYPE:  //EMS121
			   System.out.println("----------------------账册报核--------------------");
			   // 加贸账册报核,加工账册报核,物流账册报核
			   groupProcesser = new DcrChgoffBscGroupProcesser();
			   break;
		   case SendMessageGroupKindEnum.SAS_VEHICLE_BSC_BIZTYPE:   ////SAS131
			   System.out.println("----------------------车辆备案--------------------");
			   // 车辆备案
			   groupProcesser = new SasVehicleBscGroupProcesser();
			   break;
		   case SendMessageGroupKindEnum.SAS_DCL_BSC_BIZTYPE:   ////SAS101
			   System.out.println("----------------------加工业务申请表,物流业务申请表--------------------");
			   groupProcesser = new SasDclBscGroupProcesser();
			   break;
		   case SendMessageGroupKindEnum.SAS_STOCK_BSC_BIZTYPE:   ////SAS111
			   System.out.println("----------------------加工出入库单,物流出入库单--------------------");
			   groupProcesser = new SasStockBscGroupProcesser();
			   break;
	   }




	    /*if(SendMessageGroupKindEnum.ET_ARCRP_BSC_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.ET_ARCRP_BSC_BIZTYPE.equals(bizType)) {
			// 企业档案库报文处理方式
			groupProcesser = new EtArcrpBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.EMS_PUTREC_BSC_JG_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.EMS_PUTREC_BSC_JG_BIZTYPE.equals(bizType)) {
			// 加工帐册备案
	    	groupProcesser = new EmsPutrecBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.EMS_BWS_BSC_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.EMS_BWS_BSC_BIZTYPE.equals(bizType)) {
			// 物流账册
	    	groupProcesser = new EmsBwsBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.EMS_PUTREC_BSC_JM_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.EMS_PUTREC_BSC_JM_BIZTYPE.equals(bizType)) {
			// 加贸账册
	    	groupProcesser = new EmsPutrecBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.SAS_PASSPORT_BSC_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.SAS_PASSPORT_BSC_BIZTYPE.equals(bizType)) {
			// 核放单
	    	groupProcesser = new SasPassportBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.SAS_VEHICLE_BSC_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.SAS_VEHICLE_BSC_BIZTYPE.equals(bizType)) {
			// 车辆备案
	    	groupProcesser = new SasVehicleBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.INV_BSC_BS_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.INV_BSC_BS_BIZTYPE.equals(bizType)) {
			//保税清单
	    	groupProcesser = new InvBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.INV_BSC_JM_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.INV_BSC_JM_BIZTYPE.equals(bizType)) {
			// 加贸核注清单
	    	groupProcesser = new InvBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.SAS_DCL_BSC_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.SAS_DCL_BSC_BIZTYPE.equals(bizType)) {
			// 业务申请表
	    	groupProcesser = new SasDclBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.SAS_STOCK_BSC_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.SAS_STOCK_BSC_BIZTYPE.equals(bizType)) {
			// 出入库单
	    	groupProcesser = new SasStockBscGroupProcesser();
	    	
	    }else if(SendMessageGroupKindEnum.DCR_CHGOFF_BSC_WL_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.DCR_CHGOFF_BSC_WL_BIZTYPE.equals(bizType)) {
			// 物流账册报核
	    	groupProcesser = new DcrChgoffBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.DCR_CHGOFF_BSC_JG_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.DCR_CHGOFF_BSC_JG_BIZTYPE.equals(bizType)) {
			// 加工账册报核
	    	groupProcesser = new DcrChgoffBscGroupProcesser();
	    }else if(SendMessageGroupKindEnum.DCR_CHGOFF_BSC_JM_DOCTYPE.equals(docType) && SendMessageGroupKindEnum.DCR_CHGOFF_BSC_JM_BIZTYPE.equals(bizType)) {
			// 加贸账册报核
	    	groupProcesser = new DcrChgoffBscGroupProcesser();
		} */
	return groupProcesser;
 }

}
