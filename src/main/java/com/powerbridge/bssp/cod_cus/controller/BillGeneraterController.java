package com.powerbridge.bssp.cod_cus.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.BillGenerater;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqInfo;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqList;
import com.powerbridge.bssp.cod_cus.service.IBillGeneraterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.ServletUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

/**
 * 
 * @ClassName BillGeneraterController
 * @Description 单据生成器
 * @author Simon.xie
 * @Date 2017年5月12日 上午10:55:35
 * @version 1.0.0
 */
@Controller
@RequestMapping("/bill")
@CrossOrigin
public class BillGeneraterController extends BaseController {
	private static ValidatorFactory factory;
	private static Validator validator;
	@Autowired
    private IBillGeneraterService billGeneraterService;
	
	private static BillGeneraterController billGeneraterController = null;

    public BillGeneraterController() {
    }
    
    /**
     * 取得PrimaryGenerater的单例实现
     *
     * @return
     */
    public static BillGeneraterController getInstance() {
        if (billGeneraterController == null) {
            synchronized (BillGeneraterController.class) {
                if (billGeneraterController == null) {
                	billGeneraterController = new BillGeneraterController();
                }
            }
        }
        return billGeneraterController;
    }
    
    /**
     * @Description 单据编号生成器
     * 单据编号生成规则表
     * 单据名称	          单据代码	           编号规则	                                                                                                                         对应字段名称
     * 物流帐册备案	B-帐册备案	企业预录入编号
     * 加工帐册备案	B-帐册备案	企业端生成，唯一，规则：关区代码4位+B+年份4位（YYMM）+10位流水号。	  企业预录入编号
     * 核注清单	    C-核注清单	企业端生成，唯一，规则：关区代码4位+C+年份4位（YYMM）+10位流水号	            企业内部清单编号
     * 申报表	        D—业务申请表	企业端生成，唯一，规则：关区代码4位+S+年份2位+11位流水号	                      申报表预录入编号
     * 出入库单	    E—出入库单	企业端生成，唯一，规则：关区代码4位+Z+年份2位+11位流水号	                      出入库单预录入编号
     * 核放单	        F—核放单	          企业端生成，唯一，规则：关区代码4位+X+年份2位+11位流水号	                      核放单预录入编号
     * 车辆信息备案	K—车辆备案	无编号，以及车牌号为唯一的单据编号	车牌号
//     * @param applyId 应用id
//     * @param customsCode 关区代码
//     * @param billCode  单据代码
     * @return
     */
//    @RequestMapping(value ="/list", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public synchronized AjaxResult  generaterNextNumber(String applyId,String customsCode,String billCode){
//    	AjaxResult ajaxResult = null;
//    	java.sql.Date billdate = new java.sql.Date(System.currentTimeMillis());
//    	
//    	if(StringUtil.isEmpty(applyId)){
//    		return ajaxResult = json(MessageConstants.BSSP_STATUS_APPLYID, applyId);
//    	}
//    	if(StringUtil.isEmpty(customsCode)){
//    		return ajaxResult = json(MessageConstants.BSSP_STATUS_CUSTOMSCODE, customsCode);
//    	}
//    	if(StringUtil.isEmpty(billCode)){
//    		return ajaxResult = json(MessageConstants.BSSP_STATUS_BILLCODE, billCode);
//    	}
//    	
//    	String yearMonth = isBillCodeType(billCode);
//    	int number = getBillMaxNumber(customsCode, billCode, yearMonth);
//    	
//    	int bit = 0 ;
//    	if(billCode.equals("B") || billCode.equals("C")){
//    		bit = 10;
//    	}else if(billCode.equals("S") || billCode.equals("Z") || billCode.equals("X")){
//    		bit = 11;
//    	} else {
//    		return ajaxResult = result(MessageConstants.BSSP_STATUS_BILLCODES);
//    	}
//    	
//    	String numbers = makeDocId(bit,number);
//    	StringBuffer Str = new StringBuffer();
//    	String id = Str.append(customsCode).append(billCode).append(yearMonth).append(numbers).toString();
//    	
//         try {
//        	BillGenerater billGenerater =new BillGenerater();
//         	billGenerater.setUid(UUIDGenerator.getUUID());		//uid
//         	billGenerater.setCustomsCode(customsCode);          //关区代码
//         	billGenerater.setBillCode(billCode);   				//单据代码
//         	billGenerater.setYearMonth(yearMonth);              //年月份
//         	billGenerater.setSerialNumber(number);              //流水号
//         	//调用者ip
//            String ip = ServletUtils.getIpAddr();
//         	billGenerater.setIp(ip);							//ip
//         	billGenerater.setBillNumber(id);                    //单据号
//         	billGenerater.setApplyId(applyId);                  //应用id
//         	billGenerater.setCreateTime(billdate);      
//         	
//         	Boolean fail = billGeneraterService.insert(billGenerater);
//             if (fail) {
//                 ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, id);
//             } else {
//                 ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
//             }
//         } catch (Exception e) {
//             ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
//             logger.error("addCodCusApply()--err", e);
//         }
//         return ajaxResult;
//    }
	private Map<String, String> getParameterMap() {

		Enumeration e = ServletUtils.getRequest().getParameterNames();
		Map<String, String> paramMap = new HashMap<>();
		while (e.hasMoreElements()) {
			String paraName = (String) e.nextElement();
			paramMap.put(paraName, getRequest().getParameter(paraName));
		}
		return paramMap;
	}

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
//     * @param applyId 应用id
//     * @param areaCode  场所代码
//     * @param docType  单据类型
//     * @param serverType 服务类型
     * @return
     */
    @RequestMapping(value ="/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public synchronized AjaxResult  generaterNextNumber(){
		Map<String, String> paramMap = this.getParameterMap();
//	paramMap.put("applyId", "001");
//	paramMap.put("areaCode", "4901");
//	paramMap.put("docType", "a");
//	paramMap.put("serverType", "c");
	AjaxResult ajaxResult = null;
	if(StringUtil.isEmpty(paramMap.get("applyId"))){
		return ajaxResult = json(MessageConstants.BSSP_STATUS_APPLYID, paramMap.get("applyId"));
	}
//	if(StringUtil.isEmpty(paramMap.get("areaCode"))){
//		return ajaxResult = json(MessageConstants.BSSP_STATUS_AREACODE, paramMap.get("areaCode"));
//	}
	if(StringUtil.isEmpty(paramMap.get("docType"))){
		return ajaxResult = json(MessageConstants.BSSP_STATUS_DOCTYPE, paramMap.get("docType"));
	}
	if(StringUtil.isEmpty(paramMap.get("serverType"))){
		return ajaxResult = json(MessageConstants.BSSP_STATUS_SERVERTYPE, paramMap.get("serverType"));
	}

	TreeMap<Integer, String> treeMap=new TreeMap<Integer, String>();
	String date = null;
	int number = 0;
	Boolean flat=false;
	String numbers =null;
	EdiSeqInfo ediSeqInfo = new EdiSeqInfo();
	//ediSeqInfo.setAreaCode(paramMap.get("areaCode"));
	ediSeqInfo.setDocType(paramMap.get("docType"));
	ediSeqInfo.setServerType(paramMap.get("serverType"));
	List<EdiSeqList> ediseqlists = billGeneraterService.selectEdiSeqList(ediSeqInfo);
	if(ediseqlists.size() >0){
		for(EdiSeqList ediseqlist : ediseqlists){
				//场所代码
			if(ediseqlist.getParamNo().equals("AREA_CODE")){
				treeMap.put(ediseqlist.getOrderNo(), paramMap.get("areaCode"));
			}else if(ediseqlist.getParamNo().equals("DOC_TYPE")){
				//单据类型
				treeMap.put(ediseqlist.getOrderNo(), paramMap.get("docType"));
			}else if(ediseqlist.getParamNo().equals("SERVER_TYPE")){
				//服务类型
				treeMap.put(ediseqlist.getOrderNo(), paramMap.get("serverType"));
			}else if(ediseqlist.getParamNo().equals("MASK")){
				//项目掩码
				//treeMap.put(ediseqlist.getOrderNo(), paramMap.get("mask"));
				treeMap.put(ediseqlist.getOrderNo(), ediseqlist.getMask());
			}else if(ediseqlist.getParamNo().equals("MODEL_NAME")){
				//模块名称
				//treeMap.put(ediseqlist.getOrderNo(), paramMap.get("modelName"));
				treeMap.put(ediseqlist.getOrderNo(), ediseqlist.getModelName());
			}else if(ediseqlist.getParamNo().equals("DATE_RULE")){
				//日期规则
				date = new SimpleDateFormat(ediseqlist.getDateRule()).format(new Date());
				treeMap.put(ediseqlist.getOrderNo(), date);
				number = getBillMaxNumberDate(paramMap.get("areaCode"), paramMap.get("docType"), paramMap.get("serverType"), date);
				flat = true;
			}else if(ediseqlist.getParamNo().equals("COUNTER_LENGTH")){
				//计数器位数
				if(!flat){
					number = getBillMaxNumber(paramMap.get("areaCode"), paramMap.get("docType"), paramMap.get("serverType"));
				}
				numbers = makeDocId(ediseqlist.getCounterLength(),number);
				treeMap.put(ediseqlist.getOrderNo(), numbers);
			} else {
				treeMap.put(ediseqlist.getOrderNo(), paramMap.get(ediseqlist.getParamNo()));
			}
		}
		StringBuffer Str = new StringBuffer();
		Iterator it = treeMap.keySet().iterator();  
		while (it.hasNext()) {  
            Str.append(treeMap.get(it.next()));
        }  
		ajaxResult = getBillList(paramMap, date, number, Str);
	} else {
		return ajaxResult = json(MessageConstants.BSSP_STATUS_PARAM, paramMap);
	}
	 return ajaxResult;
    }
    
    /**
     * 
     * @Description 生成单据编号
     * @param paramMap 
     * @param date 日期
     * @param number 序列号
     * @param Str  单据编号
     * @return
     */
	private AjaxResult getBillList(Map<String, String> paramMap, String date, int number, StringBuffer Str) {
		AjaxResult ajaxResult = null;
		java.sql.Date billdate = new java.sql.Date(System.currentTimeMillis());
		BillGenerater billGenerater =new BillGenerater();
		billGenerater.setUid(UUIDGenerator.getUUID());		      //uid
		billGenerater.setApplyId(paramMap.get("applyId"));       //应用id
		billGenerater.setAreaCode(paramMap.get("areaCode"));     //场所代码
		billGenerater.setDocType(paramMap.get("docType"));       //单据类型
		billGenerater.setServerType(paramMap.get("serverType")); //服务类型
		billGenerater.setModelName(paramMap.get("modelName"));   //模块名称
		billGenerater.setYearMonth(date);					     //日期      
		billGenerater.setMask(paramMap.get("mask"));             //项目掩码
		billGenerater.setSerialNumber(number);                   //序号
		billGenerater.setSeqNo(Str.toString());                  //单据号
		//调用者ip
        String ip = ServletUtils.getIpAddr();
   	    billGenerater.setIp(ip);							//ip
   	    factory = Validation.buildDefaultValidatorFactory();
   	    validator = factory.getValidator();
	   	  Set<ConstraintViolation<BillGenerater>> set = validator.validate(billGenerater);
	      for (ConstraintViolation<BillGenerater> c : set) {
	          StringBuffer str = new StringBuffer();
	          return ajaxResult = setJson(0, str.append(c.getPropertyPath()).append(":").append(c.getMessage()).toString());
	      }
	      Boolean fail = billGeneraterService.insert(billGenerater);
          if (fail) {
              ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, Str);
          } else {
              ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
          }
		return ajaxResult;
	}
    /**
     * 
     * @Description 获取单据类型最大序列号
//     * @param customsCode
//     * @param billCode
//     * @param yearMonth
     * @return
     */
	private int getBillMaxNumber(String areaCode, String docType, String serverType) {
		Map<String, String> paramMap=new HashMap<String, String>();
    	paramMap.put("areaCode", areaCode);
    	paramMap.put("docType", docType);
    	paramMap.put("serverType", serverType);
    	int number  = 0;
    	BillGenerater beans = billGeneraterService.selectNumber(paramMap);
    	if(beans == null){
    		number = 1;
    	} else {
    		number= beans.getSerialNumber()+1;
    	}
		return number;
	}
	
	/**
     * 
     * @Description 获取单据类型最大序列号
     * @param areaCode
     * @param docType
     * @param serverType
     * @param date
     * @return
     */
	private int getBillMaxNumberDate(String areaCode, String docType, String serverType,String date) {
		Map<String, String> paramMap=new HashMap<String, String>();
    	paramMap.put("areaCode", areaCode);
    	paramMap.put("docType", docType);
    	paramMap.put("serverType", serverType);
    	paramMap.put("yearMonth", date);
    	int number  = 0;
    	BillGenerater beans = billGeneraterService.selectNumberDate(paramMap);
    	if(beans == null){
    		number = 1;
    	} else {
    		number= beans.getSerialNumber()+1;
    	}
		return number;
	}
    
    
    /**
     * 
     * @Description 判断单据代码类型
     * @param billCode 单据代码
     * @return
     */
	private String isBillCodeType(String billCode) {
		String yearMonth = "";
    	if(billCode.equals("B") || billCode.equals("C")){
    		yearMonth = getYears();
    	}else if(billCode.equals("S") || billCode.equals("Z") || billCode.equals("X")){
    		yearMonth = getYear();
    	} else {
    		return null;
    	}
		return yearMonth;
	}
    

    
    /**
     * 
     * @Description 传入固定长度和数字，返回数值
     * @param idLength 长度
     * @param oid  数字值
     * @return
     */
    public static String makeDocId(int idLength, int oid) {
        String docId = "";
        if (oid == 0) {
            oid = 1;
        }
        Integer ID1 = new Integer(oid);
        String ID = ID1.toString();
        int l = ID.length();
        for (int i = 0; i < idLength - l; i++) {
            docId += "0";
        }
        docId += ID;
        return docId;
    }
    
    /**
     * @Description 返回2位年日期
     * YYMM：表示：YY年份的后二位。
     * @return
     */
    public String getYear(){
    	return new SimpleDateFormat("yy",Locale.CHINESE).format(Calendar.getInstance().getTime());
    }
    
    /**
     * @Description 返回4位年月
     * YYMM：表示：YY年份的后二位，MM月份。
     * @return
     */
    public String getYears(){
    	SimpleDateFormat df = new SimpleDateFormat("yyMM");
    	Date dd  = Calendar.getInstance().getTime();
    	return df.format(dd);
    }
}
