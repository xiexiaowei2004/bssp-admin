package com.powerbridge.bssp.saspass.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportCusBsc;
import com.powerbridge.bssp.saspass.service.ISasPassportCusBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/saspass/sasPassportCusBsc")
public class SasPassportCusBscController extends BaseController {
	@Autowired
	private ISasPassportCusBscService sasPassportCusBscService;

	/**
	 * 分页查看
	 * @param sasPassportCusBsc
	 * @return
	 */
	// @RequiresPermissions("sasPassportBsc:list:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AjaxResult showSasPassportCusBscList(SasPassportCusBsc sasPassportCusBsc) {
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
			BsspUtil.filterCopEnt(sasPassportCusBsc, null);
			Page<SasPassportBsc> pages = sasPassportCusBscService.selectBySasPassportCusBsc(page, sasPassportCusBsc);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("showSasPassportCusBscList()--error", e);
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
			SasPassportCusBsc sasPassportCusBsc = sasPassportCusBscService.selectByUid(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasPassportCusBsc);
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("selectByUid error", e);
		}
		return ajaxResult;
	}
}