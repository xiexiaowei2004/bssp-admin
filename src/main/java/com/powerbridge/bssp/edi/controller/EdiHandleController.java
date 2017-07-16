package com.powerbridge.bssp.edi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.edi.entity.EdiCirclationInfo;
import com.powerbridge.bssp.edi.entity.EdiCirclationLog;
import com.powerbridge.bssp.edi.service.IEdiCirclationInfoService;
import com.powerbridge.bssp.edi.service.IEdiCirclationLogService;
/**
 * 
 * @ClassName MessageHandle
 * @Description 报文发送
 * @author Simon.xie
 * @Date 2017年5月20日 下午5:43:36
 * @version 1.0.0
 */
@Controller
@RequestMapping("/sendMessage/messageHandle")
@CrossOrigin
@Transactional
public class EdiHandleController extends BaseController{
	
	private static ValidatorFactory factory;
	private static Validator validator;
	
	@Autowired
    private IEdiCirclationInfoService ediCirclationInfoService;
	
	@Autowired
	private IEdiCirclationLogService ediCirclationLogService;
	
	/**
	 * @Description 单据流转
	 * @param ediCirclationInfo
	 * @return
	 */
	@RequestMapping(value = "/list/add", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult addCirclation(EdiCirclationInfo ediCirclationInfo) {
		AjaxResult ajaxResult = null;
		try {
			factory = Validation.buildDefaultValidatorFactory();
	   	    validator = factory.getValidator();
		   	  Set<ConstraintViolation<EdiCirclationInfo>> set = validator.validate(ediCirclationInfo);
		      for (ConstraintViolation<EdiCirclationInfo> c : set) {
		          StringBuffer str = new StringBuffer();
		          return ajaxResult = setJson(0, str.append(c.getPropertyPath()).append(":").append(c.getMessage()).toString());
		      }
		    //保存单据流程
            Boolean ediCirclationInfoFlag = ediCirclationInfoService.insert(ediCirclationInfo);
            EdiCirclationLog ediCirclationLog = new EdiCirclationLog();
            //保存单据流程日志
            ediCirclationLog.setUid(UUIDGenerator.getUUID());
            ediCirclationLog.setDocType(ediCirclationInfo.getDocType());
            ediCirclationLog.setAreaCode(ediCirclationInfo.getAreaCode());
            ediCirclationLog.setBizType(ediCirclationInfo.getBizType());
            ediCirclationLog.setSeqNo(ediCirclationInfo.getSeqNo());
            ediCirclationLog.setChannel(ediCirclationInfo.getChannel());
            ediCirclationLog.setPosCode(ediCirclationInfo.getPosCode());
            ediCirclationLog.setOpUser(ediCirclationInfo.getOpUser());
            Date d = new Date();   
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
            ediCirclationLog.setOpDate(sdf.format(d));
            Boolean ediCirclationLogFlag = ediCirclationLogService.insert(ediCirclationLog);
            //更新单据状态（未实现）
            
            if (ediCirclationInfoFlag && ediCirclationLogFlag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediCirclationInfo);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCirclation()--err", e);
        }
		return ajaxResult;
	}	
}
