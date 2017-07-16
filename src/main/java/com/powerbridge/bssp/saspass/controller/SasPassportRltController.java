package com.powerbridge.bssp.saspass.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import com.powerbridge.bssp.saspass.service.ISasPassportRltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称： bssp Maven Webapp 类名称： SasPassportBscController 类描述： 核放单出入区控制器 创建人： LC
 * 创建时间： 2017年5月22日
 * 
 * @version
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/saspass/sasPassportRlt")
public class SasPassportRltController extends BaseController {
	@Autowired
	private ISasPassportRltService sasPassportRltService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AjaxResult showSasPassportRltList(SasPassportRlt entity){
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
			Page<SasPassportRlt> pages = sasPassportRltService.selectBySasPassportRlt(page,entity);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("showSasPassportRltList error", e);
		}
		return ajaxResult;
	}

	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list/{id}/view", method = RequestMethod.POST)
	public AjaxResult viewSasPassportRlt(@PathVariable String id) {
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			SasPassportRlt entity = sasPassportRltService.selectById(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity);
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("viewSasPassportRlt error", e);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "/list/addByList", method = RequestMethod.POST)
	public AjaxResult saveByList(@RequestBody SasPassportBsc sasPass){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);

		try {
			Boolean flag = sasPassportRltService.batchAndCascadeInsert(sasPass);
			if (flag) { ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasPass); }
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("saveByList error: " + e);
		}
		return ajaxResult;
	}

	/**
	 * 新增SasPassportRlt
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/list/add",method = RequestMethod.POST)
	public AjaxResult saveSasPassportRlt(SasPassportRlt entity){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			entity.setUid(UUIDGenerator.getUUID());
			Boolean flag = sasPassportRltService.insert(entity);
			if (flag) { ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity); }
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("saveSasPassportRlt error: " + e);
		}
		return ajaxResult;
	}

	/**
	 * 修改SasPassportRlt
	 * @param entity
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list/update",method = RequestMethod.POST)
	public AjaxResult updateSasPassportRlt(@Valid SasPassportRlt entity, BindingResult result){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		AjaxResult validResult = valid(result);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
		try {
			Boolean flag = sasPassportRltService.updateById(entity);
			if (flag) { ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity); }
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("updateSasPassportRlt error: " + e);
		}
		return ajaxResult;
	}

	/*@RequiresPermissions("sasPassportBsc:list:delete")*/
	@RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
	public AjaxResult deleteSasPassportRltByList() {
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			//获取前台传输的主键集合
			String idList = getParameter("idList");
			Boolean flag = sasPassportRltService.deleteBatchIds(Arrays.asList(idList.split(",")));
			if (flag) { ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS); }
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("deleteSasPassportRltByList error", e);
		}
		return ajaxResult;
	}

	/**
	 * 根据rltNo查询关联表数据
	 * @param rltNos
	 * @return 集合的size
	 */
	@RequestMapping(value = "/list/checkByRltNo",method = RequestMethod.GET)
	public AjaxResult checkByRltNo(@RequestParam(required = false) String rltNos){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try{
			List<SasPassportRlt> list = sasPassportRltService.selectByRltNos(Arrays.asList(rltNos.split(",")));
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, list.size());
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("checkByRltNo error", e);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "/list/checkCurrentRlt",method = RequestMethod.GET)
	public AjaxResult checkCurrentRltBySeqNoAndRltNos(String seqNo,String rltNos){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try{
			List<SasPassportRlt> list = sasPassportRltService.selectByRltNosAndSeqNo(Arrays.asList(rltNos.split(",")),seqNo);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, list.size());
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("checkCurrentRltBySeqNoAndRltNos error", e);
		}
		return ajaxResult;
	}
}