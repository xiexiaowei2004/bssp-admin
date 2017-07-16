package com.powerbridge.bssp.edi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.edi.entity.EdiCirclationLog;
import com.powerbridge.bssp.edi.service.IEdiCirclationLogService;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称： EdiCirclationLogController
 * 类描述：单据流转日志
 * 创建人： xwq
 * 创建时间：2017年5月19日09:42:06
 *@version
 */
@Controller
@RequestMapping("/edi/ediCirclationLog")
@CrossOrigin
public class EdiCirclationLogController extends BaseController{
	 @Autowired
	 private IEdiCirclationLogService ediCirclationLogService;
	 
	 private Gson gson = null;
	 
	 public EdiCirclationLogController(){
		 gson=new Gson();
	 }
	 
	  // 查询方法
	    private EntityWrapper getEntityWrapper(EdiCirclationLog ediCirclationLog) {
	        String sort = getParameter("sort");
	        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
	        EntityWrapper entityWrapper = new EntityWrapper<EdiCirclationLog>();
	        if (ediCirclationLog.getAreaCode() != null && !"".equals(ediCirclationLog.getAreaCode())) {
	            entityWrapper.like("AREA_CODE", ediCirclationLog.getAreaCode());
	        }
	        if (ediCirclationLog.getDocType() != null && !"".equals(ediCirclationLog.getDocType())) {
	            entityWrapper.like("DOC_TYPE", ediCirclationLog.getDocType());
	        }
	        if (ediCirclationLog.getBizType() != null && !"".equals(ediCirclationLog.getBizType())) {
	            entityWrapper.like("BIZ_TYPE", ediCirclationLog.getBizType());
	        }
	        
	        if (sort != null && !"".equals(sort)) {
	            entityWrapper.orderBy(sort, sortOrder);  // 排序
	        }
	        return entityWrapper;
	    }

	    /**
	     * @param ediCirclationLog
	     * @return AjaxResult
	     * @throws
	     * @Description: 进入字段关系页面:(列表查询)
	     */
//	    @RequiresPermissions("ediCirclationLog:list:view")
	    @RequestMapping("/list")
	    @ResponseBody
	    public AjaxResult showEdiCirclationLog(EdiCirclationLog ediCirclationLog) {
	        AjaxResult ajaxResult = null;
	        try {
	            EntityWrapper entityWrapper = getEntityWrapper(ediCirclationLog);  //查询调用方法
	            Page page = getPage();  // 分页
	            Page<EdiCirclationLog> ediCirclationLogList = ediCirclationLogService.selectPage(page, entityWrapper);
	    		ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediCirclationLogList.getRecords(), page.getTotal()); // 格式要返回的数据
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("showEdiCirclationLog()--error", e);
	        }
	        return ajaxResult;
	    }
	    

}
  
    