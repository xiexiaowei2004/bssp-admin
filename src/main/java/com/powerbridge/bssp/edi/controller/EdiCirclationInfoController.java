package com.powerbridge.bssp.edi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.edi.entity.EdiCirclationInfo;
import com.powerbridge.bssp.edi.service.IEdiCirclationInfoService;
@Controller
@RequestMapping("/edi/ediCirclationInfo")
@CrossOrigin
public class EdiCirclationInfoController extends BaseController {
	@Autowired
	private IEdiCirclationInfoService ediCirclationInfoService;
	

	
	private EntityWrapper getEntityWrapper(EdiCirclationInfo ediCirclationInfo) {
		
		String sort = getParameter("sort");
		boolean sortOrder = getOrderSort(getParameter("sortOrder"));
		EntityWrapper entityWrapper = new EntityWrapper<EdiCirclationInfo>();
		if (ediCirclationInfo.getAreaCode() != null && !"".equals(ediCirclationInfo.getAreaCode())) {
			entityWrapper.like("AREA_CODE", ediCirclationInfo.getAreaCode());
		}
		if (ediCirclationInfo.getDocType() != null && !"".equals(ediCirclationInfo.getDocType())) {
			entityWrapper.like("DOC_TYPE",ediCirclationInfo.getDocType());
		}
		if (ediCirclationInfo.getBizType() != null && !"".equals(ediCirclationInfo.getBizType())) {
			entityWrapper.like("BIZ_TYPE", ediCirclationInfo.getBizType());
		}
		if (ediCirclationInfo.getStatus() != null && !"".equals(ediCirclationInfo.getStatus())) {
			entityWrapper.like("STATUS", ediCirclationInfo.getStatus());
		}

		if (sort != null && !"".equals(sort)) {
			entityWrapper.orderBy(sort, sortOrder);
		}
		
		return entityWrapper;
	}
	/**
	 * @Description：分页查询
	 * @param model
	 * @return
	 * @throws
	 */
	//@RequiresPermissions("ediCirclationInfo:list:view")
	@RequestMapping("/list")
	@ResponseBody
	public AjaxResult showEdiCirclationInfoList(EdiCirclationInfo ediCirclationInfo) {

		AjaxResult ajaxResult = null;
		
		try {

			Page page = getPage();
			EntityWrapper entityWrapper = getEntityWrapper(ediCirclationInfo);
			Page<EdiCirclationInfo> ediCirclationInfoList = ediCirclationInfoService.selectPage(page, entityWrapper);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediCirclationInfoList.getRecords(), page.getTotal()); // 格式要返回的数据
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("showEdiCirclationInfoList()--error", e);
		}

		return ajaxResult;
	}
	

	/**
	 * @Description：新增数据
	 * @param model
	 * @return
	 * @throws
	 */
/*//	@RequiresPermissions("ediCirclationInfo:list:add")
	@RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxResult addEdiCirclationInfo(EdiCirclationInfo ediCirclationInfo) {
		AjaxResult ajaxResult = null;
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//使用生成器生成id
		        String uid = UUIDGenerator.getUUID();
		        if("".equals(uid) && null==uid){
		            //失败
		            return json(MessageConstants.BSSP_STATUS_FAIL,"id生成失败");
		        }
		        ediCirclationInfo.setUid(uid);
		        ediCirclationInfo.setCreateBy(String.valueOf(SingletonLoginUtils.getSystemUserId()));
		        ediCirclationInfo.setCreateName(SingletonLoginUtils.getSystemUserName());
		       ediCirclationInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		try {

			Boolean fail = ediCirclationInfoService.insert(ediCirclationInfo);
			if (fail) {
				ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediCirclationInfo);
			} else {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			}
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("addEdiCirclationInfo()--err", e);
		}

		return ajaxResult;
	}
	

	*//**
	 * @Description：编辑页面
	 * @param model
	 * @return
	 * @throws
	 *//*
//	@RequiresPermissions("ediCirclationInfo:list:view")
	@RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxResult editEdiCirclationInfo(@PathVariable String id) {
		AjaxResult ajaxResult = null;
		try {
			EdiCirclationInfo ediCirclationInfo = ediCirclationInfoService.selectById(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediCirclationInfo);
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("editEdiCirclationInfo()--err", e);
		}

		return ajaxResult;
	}
	*//**
	 * @Description：修改数据
	 * @param model
	 * @return
	 * @throws
	 *//*
//	@RequiresPermissions("ediCirclationInfo:list:edit")
	 @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxResult updateEdiCirclationInfo(EdiCirclationInfo ediCirclationInfo) {

		AjaxResult ajaxResult = null;
	    ediCirclationInfo.setUpdateBy(String.valueOf(SingletonLoginUtils.getSystemUserId()));
	    ediCirclationInfo.setUpdateBy(SingletonLoginUtils.getSystemUserName());
	    ediCirclationInfo.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		try {

			Boolean fail = ediCirclationInfoService.updateById(ediCirclationInfo);
			if (fail) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
			} else {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			}
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("updateEdiCirclationInfo()--err", e);
		}

		return ajaxResult;
	}
	 *//**
		 * @Description：单个删除
		 * @param model
		 * @return
		 * @throws
		 *//*
//		@RequiresPermissions("ediCirclationInfo:list:delete")
		@RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
		@ResponseBody
		public AjaxResult deleteEdiCirclationInfo(@PathVariable String id) {

			AjaxResult ajaxResult = null;
			try {
				Boolean fail = ediCirclationInfoService.deleteById(id);
				if (fail) {
					ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
				} else {
					ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				}
			} catch (Exception e) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				logger.error("deleteEdiCirclationInfo()--err", e);
			}

			return ajaxResult;
		}


		*//**
		 * @Description：批量删除
		 * @param model
		 * @return
		 * @throws
		 *//*
		//@RequiresPermissions("ediCirclationInfo:list:delete")
		@RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
		@ResponseBody
		public AjaxResult deleteEdiCirclationInfoByList() {

			AjaxResult ajaxResult = null;
			try {
				// 获取前台传输的主键集合
				String idList = getParameter("idList");
				Boolean fail = ediCirclationInfoService.deleteBatchIds(Arrays.asList(idList.split(",")));
				if (fail) {
					ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
				} else {
					ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				}
			} catch (Exception e) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				logger.error("deleteEdiCirclationInfoByList()--err", e);
			}

			return ajaxResult;
		}*/
}
