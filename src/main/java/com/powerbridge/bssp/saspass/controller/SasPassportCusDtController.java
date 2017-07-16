package com.powerbridge.bssp.saspass.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.saspass.entity.SasPassportCusDt;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import com.powerbridge.bssp.saspass.service.ISasPassportCusDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/saspass/sasPassportCusDt")
public class SasPassportCusDtController extends BaseController {
	@Autowired
	private ISasPassportCusDtService sasPassportCusDtService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AjaxResult showSasPassportCusDtList(SasPassportCusDt entity){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		Page page = getPage();
		String sort = getParameter("sort");
		boolean sortOrder = getOrderSort(getParameter("sortOrder"));
		if (StringUtil.isNotEmpty(sort)) {
			page.setAsc(sortOrder);
			page.setOrderByField(sort);  // 排序
		} else {
			page.setAsc(true);
			page.setOrderByField("CREATE_TIME"); //默认按最后变更日期倒排序
		}
		try {
			BsspUtil.filterCopEnt(entity, null);
			Page<SasPassportDt> pages = sasPassportCusDtService.selectBySasPassportCusDt(page, entity);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("showSasPassportCusDtList error", e);
		}
		return ajaxResult;
	}

	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
	public AjaxResult viewSasPassportCusDt(@PathVariable String id) {
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			SasPassportCusDt entity = sasPassportCusDtService.selectByUid(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity);
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("viewSasPassportCusDt error", e);
		}
		return ajaxResult;
	}
}