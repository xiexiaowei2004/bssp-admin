package com.powerbridge.bssp.edi.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDragcarBsc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.edi.entity.EdiRoutingInfo;
import com.powerbridge.bssp.edi.service.IEdiRoutingInfoService;
@Controller
@RequestMapping("/edi/ediRoutingInfo")
@CrossOrigin
public class EdiRoutingInfoController extends BaseController{
	@Autowired
	private IEdiRoutingInfoService ediRoutingInfoService;
	

	
	private EntityWrapper getEntityWrapper(EdiRoutingInfo ediRoutingInfo) {
		
		String sort = getParameter("sort");
		boolean sortOrder = getOrderSort(getParameter("sortOrder"));
		EntityWrapper entityWrapper = new EntityWrapper<EdiRoutingInfo>();
		if (ediRoutingInfo.getAreaCode() != null && !"".equals(ediRoutingInfo.getAreaCode())) {
			entityWrapper.like("AREA_CODE", ediRoutingInfo.getAreaCode());
		}
		if (ediRoutingInfo.getDocType() != null && !"".equals(ediRoutingInfo.getDocType())) {
			entityWrapper.like("DOC_TYPE",ediRoutingInfo.getDocType());
		}
		if (ediRoutingInfo.getBizType() != null && !"".equals(ediRoutingInfo.getBizType())) {
			entityWrapper.like("BIZ_TYPE", ediRoutingInfo.getBizType());
		}
		if (ediRoutingInfo.getStatus() != null && !"".equals(ediRoutingInfo.getStatus())) {
			entityWrapper.like("STATUS", ediRoutingInfo.getStatus());
		}
		if (ediRoutingInfo.getProname() != null && !"".equals(ediRoutingInfo.getProname())) {
			entityWrapper.like("PRONAME", ediRoutingInfo.getProname());
		}
		if (sort != null && !"".equals(sort)) {
			entityWrapper.orderBy(sort, sortOrder);
		}
		
		return entityWrapper;
	}
	/**
	 * @Description：分页查询
	 * @param ediRoutingInfo
	 * @return
	 * @throws
	 */
	/**
	 * 根据条件查询
	 */

	@RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxResult selectEdiRoutingInfos(EdiRoutingInfo ediRoutingInfo) {
		AjaxResult ajaxResult = null;
		try {
			Page page = getPage();  // 分页
			String sort = getParameter("sort");
			boolean sortOrder = getOrderSort(getParameter("sortOrder"));
			if (StringUtil.isNotEmpty(sort)) {
				page.setAsc(sortOrder);
				page.setOrderByField(sort);  // 排序
			} else {
				//默认按监管场所倒排序
				page.setAsc(false);
				page.setOrderByField("AREA_CODE");  // 排序
			}
			BsspUtil.filterCopEnt(ediRoutingInfo, null);
			Page<EdiRoutingInfo> ediRoutingInfoPage = ediRoutingInfoService.selectEdiRoutingInfoList(page, ediRoutingInfo);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediRoutingInfoPage.getRecords(), page.getTotal());
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("selectEdiRoutingInfos()--error", e);
		}
		return ajaxResult;
	}
	

	/**
	 * @Description：新增数据
	 * @param ediRoutingInfo
	 * @return
	 * @throws
	 */
//	@RequiresPermissions("ediRoutingInfo:list:add")
	@RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxResult addEdiRoutingInfo(EdiRoutingInfo ediRoutingInfo) {
		AjaxResult ajaxResult = null;
		//使用生成器生成id
		ediRoutingInfo.setUid(UUIDGenerator.getUUID());
		try {
			Boolean fail = ediRoutingInfoService.insert(ediRoutingInfo);
			if (fail) {
				ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediRoutingInfo);
			} else {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			}
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("addEdiRoutingInfo()--err", e);
		}

		return ajaxResult;
	}
	

	/**
	 * @Description：编辑页面
	 * @param id
	 * @return
	 * @throws
	 */
//	@RequiresPermissions("ediRoutingInfo:list:view")
	@RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxResult editEdiRoutingInfo(@PathVariable String id) {
		AjaxResult ajaxResult = null;
		try {
			EdiRoutingInfo ediRoutingInfo = ediRoutingInfoService.selectById(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediRoutingInfo);
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("editEdiRoutingInfo()--err", e);
		}

		return ajaxResult;
	}
	/**
	 * @Description：修改数据
	 * @param ediRoutingInfo
	 * @return
	 * @throws
	 */
//	@RequiresPermissions("ediRoutingInfo:list:edit")
	 @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxResult updateEdiRoutingInfo(EdiRoutingInfo ediRoutingInfo) {

		AjaxResult ajaxResult = null;
		try {

			Boolean fail = ediRoutingInfoService.updateById(ediRoutingInfo);
			if (fail) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
			} else {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			}
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("updateEdiRoutingInfo()--err", e);
		}

		return ajaxResult;
	}
	 /**
		 * @Description：单个删除
		 * @param id
		 * @return
		 * @throws
		 */
//		@RequiresPermissions("ediRoutingInfo:list:delete")
		@RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
		@ResponseBody
		public AjaxResult deleteEdiRoutingInfo(@PathVariable String id) {

			AjaxResult ajaxResult = null;
			try {
				Boolean fail = ediRoutingInfoService.deleteById(id);
				if (fail) {
					ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
				} else {
					ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				}
			} catch (Exception e) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				logger.error("deleteEdiRoutingInfo()--err", e);
			}

			return ajaxResult;
		}


		/**
		 * @Description：批量删除
		 * @param
		 * @return
		 * @throws
		 */
		//@RequiresPermissions("ediRoutingInfo:list:delete")
		@RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
		@ResponseBody
		public AjaxResult deleteEdiRoutingInfoByList() {

			AjaxResult ajaxResult = null;
			try {
				// 获取前台传输的主键集合
				String idList = getParameter("idList");
				Boolean fail = ediRoutingInfoService.deleteBatchIds(Arrays.asList(idList.split(",")));
				if (fail) {
					ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
				} else {
					ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				}
			} catch (Exception e) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				logger.error("deleteEdiRoutingInfoByList()--err", e);
			}

			return ajaxResult;
		}
	}


