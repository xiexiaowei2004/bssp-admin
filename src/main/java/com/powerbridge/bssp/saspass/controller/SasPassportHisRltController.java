package com.powerbridge.bssp.saspass.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.saspass.entity.SasPassportHisRlt;
import com.powerbridge.bssp.saspass.service.ISasPassportHisRltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/saspass/sasPassportHisRlt")
public class SasPassportHisRltController extends BaseController {
	@Autowired
	private ISasPassportHisRltService sasPassportHisRltService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AjaxResult showSasPassportHisRltList(SasPassportHisRlt entity){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);

		Page page = getPage();
		String sort = getParameter("sort");
		boolean sortOrder = getOrderSort(getParameter("sortOrder"));
		if (StringUtil.isNotEmpty(sort)){
			page.setAsc(sortOrder);
			page.setOrderByField(sort);
		} else {
			page.setAsc(true);
			page.setOrderByField("A.CREATE_TIME");
		}
		try {
			BsspUtil.filterCopEnt(entity, null);
			Page<SasPassportHisRlt> pages = sasPassportHisRltService.selectBySasPassportHisRlt(page,entity);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("showSasPassportHisRltList error", e);
		}
		return ajaxResult;
	}

	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list/{id}/view", method = RequestMethod.POST)
	public AjaxResult viewSasPassportHisRlt(@PathVariable String id) {
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			SasPassportHisRlt entity = sasPassportHisRltService.selectById(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity);
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("viewSasPassportRlt error", e);
		}
		return ajaxResult;
	}
}