package com.powerbridge.bssp.saspass.controller;

import java.math.BigDecimal;
import java.util.*;

import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;

import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.service.ISasPassportBscService;


/**
 * 项目名称： bssp Maven Webapp 类名称： SasPassportBscController 类描述： 核放单出入区控制器 创建人： LC
 * 创建时间： 2017年5月22日
 * 
 * @version
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/saspass/sasPassportBsc")
public class SasPassportBscController extends BaseController {
	@Autowired
	private ISasPassportBscService sasPassportBscService;

	/**
	 * 分页查看
	 * @param sasPassportBsc
	 * @return
	 */
	// @RequiresPermissions("sasPassportBsc:list:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AjaxResult showSasPassportBscList(SasPassportBsc sasPassportBsc) {
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
			BsspUtil.filterCopEnt(sasPassportBsc, null);
			Page<SasPassportBsc> pages = sasPassportBscService.selectBySasPassportBsc(page, sasPassportBsc);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("showSasPassportBscList()--error", e);
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
			SasPassportBsc sasPassportBsc = sasPassportBscService.selectByUid(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasPassportBsc);
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("selectByUid error", e);
		}
		return ajaxResult;
	}

	/**
	 * 新增SasPassportBsc
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/list/add",method = RequestMethod.POST)
	public AjaxResult saveSasPassportBsc(SasPassportBsc entity){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			entity.setUid(UUIDGenerator.getUUID());
			entity.setChgTmsCnt(0);//变更次数默认0
			BigDecimal totalWt = countTotalWt(entity);//获取总重
			entity.setTotaLWt(totalWt);
			DocTypeConstants.setDocType(entity,entity.getBusType());
			Boolean flag = sasPassportBscService.insert(entity);
			if (flag) { ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity); }
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("saveSasPassportBsc error: " + e);
		}
		return ajaxResult;
	}

	/**
	 * 修改SasPassportBsc
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/list/update",method = RequestMethod.POST)
	public AjaxResult updateSasPassportBsc(SasPassportBsc entity){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			BigDecimal totalWt = countTotalWt(entity);//获取总重
			entity.setTotaLWt(totalWt);
			Boolean flag = sasPassportBscService.updateById(entity);
			if (flag) { ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity); }
		}catch (Exception e){
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("updateSasPassportBsc error: " + e);
		}
		return ajaxResult;
	}

	/**
	 * 申报
	 * @param sasPassportBsc
	 * @param
	 * @return
	 */
	@RepeatSubmitValidation
	@RequestMapping(value = "/list/reports", method = {RequestMethod.POST, RequestMethod.GET})
	public AjaxResult submitSasPassportBsc(SasPassportBsc sasPassportBsc){
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		Boolean flag = false;
		AjaxResult validResult = check(sasPassportBsc);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
		BigDecimal totalWt = countTotalWt(sasPassportBsc);//获取总重
		sasPassportBsc.setTotaLWt(totalWt);
		sasPassportBsc.setRetChannel("");

		if (StringUtil.isEmpty(sasPassportBsc.getUid())){
			sasPassportBsc.setUid(UUIDGenerator.getUUID());
			sasPassportBsc.setChgTmsCnt(0);//变更次数默认0
			try {
				DocTypeConstants.setDocType(sasPassportBsc, sasPassportBsc.getBusType());
			} catch (Exception e) {
				ajaxResult = setErrorJson(e.getMessage());
				logger.error("submitSasPassportBsc error: " + e);
				return ajaxResult;
			}
			flag = sasPassportBscService.insert(sasPassportBsc);
		} else {
			flag = sasPassportBscService.updateById(sasPassportBsc);
		}
		if (flag){
			try{
				BsspUtil.checkStatusDeclare(sasPassportBsc, null, null,true);
				sasPassportBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_B);
				sasPassportBscService.updateById(sasPassportBsc);
				ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasPassportBsc);
			}catch (Exception e){
				ajaxResult = setErrorJson(e.getMessage());
				logger.error("submitSasPassportBsc error: " + e);
			}
		}
		return ajaxResult;
	}

	/**
	 * 批量删除
	 * @return
	 */
//	@RequiresPermissions("sasPassportBsc:list:delete")
	@RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
	public AjaxResult deleteSasPassportBscByList() {
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			//获取前台传输的主键集合
			String idList = getParameter("idList");
			Boolean flag = sasPassportBscService.deleteBatchSeqNos(Arrays.asList(idList.split(",")));
			if (flag) { ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS); }
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("deleteCodCusBriefByList error", e);
		}
		return ajaxResult;
	}

	/**
	 * 计算总重
	 * 总重 = 车架重 + 集装箱重 + 毛重 + 车自重
	 * @param entity
	 * @return
	 */
	private BigDecimal countTotalWt(SasPassportBsc entity){
		BigDecimal totalWt = BigDecimal.ZERO;
		if (entity.getVehicleFrameWt() != null){
			totalWt = totalWt.add(entity.getVehicleFrameWt());//车架重
		}
		if (entity.getTotalGrossWt() != null){
			totalWt = totalWt.add(entity.getTotalGrossWt());//毛重
		}
		if (entity.getContainerWt() != null){
			totalWt = totalWt.add(entity.getContainerWt());//集装箱重
		}
		if (entity.getVehicleWt() != null){
			totalWt =  totalWt.add(entity.getVehicleWt());//车自重
		}
		return totalWt;
	}
}