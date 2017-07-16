package com.powerbridge.bssp.saspass.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportHisBsc;
import com.powerbridge.bssp.saspass.service.ISasPassportHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/saspass/sasPassportHisBsc")
public class SasPassportHisBscController extends BaseController {
	@Autowired
	private ISasPassportHisBscService sasPassportHisBscService;

	/**
	 * 分页查看
	 * @param sasPassportHisBsc
	 * @return
	 */
	// @RequiresPermissions("sasPassportBsc:list:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AjaxResult showSasPassportHisBscList(SasPassportHisBsc sasPassportHisBsc) {
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		Page page = getPage();
		String sort = getParameter("sort");
		boolean sortOrder = getOrderSort(getParameter("sortOrder"));
		if (StringUtil.isNotEmpty(sort)) {
			page.setAsc(sortOrder);
			page.setOrderByField(sort);
		} else {
			page.setAsc(true);
			page.setOrderByField("DCL_TIME");
		}
		try {
			BsspUtil.filterCopEnt(sasPassportHisBsc, null);
			Page<SasPassportBsc> pages = sasPassportHisBscService.selectBySasPassportHisBsc(page, sasPassportHisBsc);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("showSasPassportHisBscList()--error", e);
		}
		return ajaxResult;
	}

	/**
	 * 根据ID查询
	 * 获取中文字段名称
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
	public AjaxResult selectByUid(@PathVariable("id") String id){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			SasPassportHisBsc sasPassportHisBsc = sasPassportHisBscService.selectByUid(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasPassportHisBsc);
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("selectByUid error", e);
		}
		return ajaxResult;
	}
}