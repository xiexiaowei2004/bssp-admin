package com.powerbridge.bssp.saspass.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportDt;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import com.powerbridge.bssp.saspass.service.ISasPassportBscService;
import com.powerbridge.bssp.saspass.service.ISasPassportDtService;
import com.powerbridge.bssp.saspass.service.ISasPassportRltService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/saspass/sasPassportDt")
public class SasPassportDtController extends BaseController {
	@Autowired
	private ISasPassportDtService sasPassportDtService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AjaxResult showSasPassportDtList(SasPassportDt entity){
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
			Page<SasPassportDt> pages = sasPassportDtService.selectBySasPassportDt(page, entity);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("showSasPassportDtList error", e);
		}
		return ajaxResult;
	}

	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
	public AjaxResult viewSasPassportDt(@PathVariable String id) {
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			SasPassportDt entity = sasPassportDtService.selectByUid(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity);
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("viewSasPassportDt error", e);
		}
		return ajaxResult;
	}

	/**
	 * 新增SasPassportBsc
	 * @param entity
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list/add",method = RequestMethod.POST)
	public AjaxResult saveSasPassportDt(@Valid SasPassportDt entity, BindingResult result){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		AjaxResult validResult = valid(result);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
		try {
			entity.setUid(UUIDGenerator.getUUID());
			Boolean flag = sasPassportDtService.insert(entity);
			if (flag) { ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity); }
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("saveSasPassportDt error: " + e);
		}
		return ajaxResult;
	}

	/**
	 * 修改SasPassportBsc
	 * @param entity
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list/update",method = RequestMethod.POST)
	public AjaxResult updateSasPassportDt(@Valid SasPassportDt entity, BindingResult result){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		AjaxResult validResult = valid(result);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
		try {
			Boolean flag = sasPassportDtService.updateById(entity);
			if (flag) { ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity); }
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("updateSasPassportDt error: " + e);
		}
		return ajaxResult;
	}

//	@RequiresPermissions("sasPassportBsc:list:delete")
	@RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
	public AjaxResult deleteSasPassportDtByList() {
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			//获取前台传输的主键集合
			String idList = getParameter("idList");
			Boolean flag = sasPassportDtService.deleteBatchIds(Arrays.asList(idList.split(",")));
			if (flag) { ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS); }
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("deleteSasPassportDtByList error", e);
		}
		return ajaxResult;
	}

/***********************************************************************************/



	/**
	 * @Description：根据ID子表查询 @param model @return @throws
	 */
	// @RequiresPermissions
	@RequestMapping(value = "/Dt/list/{id}/view", method = { RequestMethod.POST, RequestMethod.GET })
	 @ResponseBody
	public AjaxResult editSasPassportDt(@PathVariable String id) {
		AjaxResult ajaxResult = null;
		try {
			SasPassportDt sasPassportDt = sasPassportDtService.selectById(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasPassportDt);
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("editSasPassportDt()--err", e);
		}
		return ajaxResult;
	}

	/**
	 * @Description：字表数据的修改 @param model @return @throws
	 */
	// @RequiresPermissions("codCusBrief:list:edit")
	// @RequestMapping(value = "/list/update", method = {RequestMethod.POST,
	// RequestMethod.GET})
	// @ResponseBody
	public AjaxResult updateSasPassportDt(SasPassportDt sasPassportDt) {
		AjaxResult ajaxResult = null;
		try {
			Boolean flag = sasPassportDtService.updateById(sasPassportDt);
			if (flag) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
			} else {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			}
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("updateSasPassportDt()--err", e);
		}
		return ajaxResult;
	}

	/**
	 * @Description：子表新增方法 @param model @return @throws
	 */
	//@RequiresPermissions("codCusBrief:list:add")
	@RequestMapping(value = "/Dt/list/addDt", method = {RequestMethod.POST,
	 RequestMethod.GET})
	 @ResponseBody
	public AjaxResult addSasPassportDt(SasPassportDt sasPassportDt) {
		AjaxResult ajaxResult = null;
		Boolean flag;
		try {
			if(StringUtil.isEmpty( sasPassportDt.getUid())){
				sasPassportDt.setUid(UUIDGenerator.getUUID());
				flag = sasPassportDtService.insert(sasPassportDt);
			}else {
				flag = sasPassportDtService.updateById(sasPassportDt);
			}

			if (flag) {
				ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasPassportDt);
			} else {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			}
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("addSasPassportDt()--err", e);
		}
		return ajaxResult;
	}
	/*// @RequiresPermissions("sasPassportBsc:list:view")
	@RequestMapping(value = "/Dt/list", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public AjaxResult showSasPassportDtList(SasPassportDt sasPassportDt) {
		AjaxResult ajaxResult = null;
EntityWrapper entityWrapper = new EntityWrapper<SasPassportBsc>();
		entityWrapper.where("SEQ_NO={0}",sasPassportDt.getSeqNo());
		String sort = getParameter("sort");
		boolean sortOrder = getOrderSort(getParameter("sortOrder"));
		if (StringUtil.isNotEmpty(sort)) {
			entityWrapper.orderBy(sort, sortOrder);

		} else {
			entityWrapper.orderBy("UPDATE_TIME", false);
		}
		try {

			Page page = getPage();
			Page<SasPassportBsc> sasPassportBscList = sasPassportDtService.selectPage(page, entityWrapper);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasPassportBscList.getRecords(), page.getTotal());
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("showsasPassportDt()--error", e);
			// TODO: handle exception
		}
		return ajaxResult;
	}*/
	/*//@RequiresPermissions("sasPassportBsc:list:delete")
	@RequestMapping(value = "/Dt/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public AjaxResult deleteSasPassportDtByList() {
		AjaxResult ajaxResult = null;
		try {
			//获取前台传输的主键集合
			String uid = getParameter("idList");
			Boolean flag = sasPassportDtService.deleteById(uid);
			if (flag) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
			} else {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			}
		} catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
			logger.error("deleteCodCusBriefByList()--err", e);
		}
		return ajaxResult;
	}*/
}