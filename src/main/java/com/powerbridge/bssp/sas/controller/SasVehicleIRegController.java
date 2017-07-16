package com.powerbridge.bssp.sas.controller;

import java.util.Arrays;

import javax.validation.Valid;

import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.sas.entity.SasVehicleIReg;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.service.ISasVehicleIRegService;

/**
 * @ClassName: SasVehicleIRegController
 * @Description:  车辆入区登记控制器
 * 	1.selectByPage()   分页及条件查询	    GET  /sas/vehicleIReg/list
 * 	2.selectById()     id查询				GET  /sas/vehicleIReg/list/{id}/view
 * 	3.save()		      新增保存				POST /sas/vehicleIReg/list/add
 * 	4.update()		      修改保存				POST /sas/vehicleIReg/list/update
 * 	5.deleteById()	   id删除				POST /sas/vehicleIReg/list/{id}/delete
 * 	6.deleteBatchIds() 批量删除				POST /sas/vehicleIReg/list/deleteByList
 * @author: songke  
 * @createTime: 2017年5月20日 下午4:01:36 
 * @version: v1.0
 */
@Controller
@CrossOrigin
@RequestMapping("/sas/vehicleIReg")
@ResponseBody
public class SasVehicleIRegController extends BaseController {

	@Autowired
	private ISasVehicleIRegService sasVehicleIRegService;
	
	private Logger logger = LoggerFactory.getLogger(SasVehicleIRegController.class);
	
	/**
	 * @Title: selectByPage
	 * @Description: 分页及条件查询 
	 * @param: @param sasVehicleIReg
	 * @param: @return 
	 * @return: AjaxResult
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public AjaxResult selectByPage(SasVehicleIReg sasVehicleIReg){
		//设置默认的返回结果为失败
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		Page page = getPage();
		String sort = getParameter("sort");
		boolean sortOrder = getOrderSort(getParameter("sortOrder"));
		if (StringUtil.isNotEmpty(sort)) {
			page.setAsc(sortOrder);
			page.setOrderByField(sort);
		} else {
			page.setAsc(true);
			page.setOrderByField("I_REG_DATE");
		}
		try {
			BsspUtil.filterCopEnt(sasVehicleIReg, null);
			Page<SasPassportBsc> pages = sasVehicleIRegService.selectBySasVehicleIReg(page, sasVehicleIReg);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
		} catch (Exception e) {
			ajaxResult = setErrorJson(e.getMessage());
			logger.error("selectByPage()--error", e);
		}
		return ajaxResult;
	}

	/**
	 * @Title: selectById
	 * @Description: 根据id查询 
	 * @param: @param id
	 * @param: @return 
	 * @return: AjaxResult
	 */
	@RequestMapping(value="/list/{id}/view",method={RequestMethod.POST, RequestMethod.GET})
	public AjaxResult selectById(@PathVariable String id){
		//设置默认的返回结果为失败
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			SasVehicleIReg entity = sasVehicleIRegService.selectById(id);
			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity);
		} catch (Exception e) {
			logger.error("selectById error: " + e);
		}
		return ajaxResult;
	}
	
	/**
	 * @Title: save
	 * @Description: 新增保存
	 * @param: @param entity
	 * @param: @return 
	 * @return: AjaxResult
	 */
	@RequestMapping(value="/list/add",method=RequestMethod.POST)
	public AjaxResult save(@Valid SasVehicleIReg entity, BindingResult result){
		//设置默认的返回结果为失败
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		AjaxResult validResult = valid(result);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
		try {
			entity.setUid(UUIDGenerator.getUUID());
			Boolean flag = sasVehicleIRegService.insert(entity);
			
			if (flag) { ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entity); }
		} catch (Exception e) {
			logger.error("save error: " + e);
		}
		return ajaxResult;
	}

	/**
	 * @Title: update
	 * @Description: 修改保存 
	 * @param: @param entity
	 * @param: @return 
	 * @return: AjaxResult
	 */
	@RequestMapping(value="/list/update",method=RequestMethod.POST)
	public AjaxResult update(@Valid SasVehicleIReg entity, BindingResult result){
		//设置默认的返回结果为失败
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		AjaxResult validResult = valid(result);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
		try {
			Boolean flag = sasVehicleIRegService.updateById(entity);
			
			if(flag){ ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS); }
		} catch (Exception e) {
			logger.error("update error: " + e);
		}
		return ajaxResult;
	}
	
	/**
	 * @Title: deleteById
	 * @Description:  根据id删除
	 * @param: @param id
	 * @param: @return 
	 * @return: AjaxResult
	 */
	@RequestMapping(value="/list/{id}/delete",method=RequestMethod.POST)
	public AjaxResult deleteById(@PathVariable String id){
		//设置默认的返回结果为失败
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			Boolean flag = sasVehicleIRegService.deleteById(id);
			
			if (flag) { ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS); }
		} catch (Exception e) {
			logger.error("deleteById error: " + e);
		}
		return ajaxResult;
	}
	
	/**
	 * @Title: deleteBatchIds
	 * @Description: 根据id集合批量删除 
	 * @param: @param idList
	 * @param: @return 
	 * @return: AjaxResult
	 */
	@RequestMapping(value="/list/deleteByList",method=RequestMethod.POST)
	public AjaxResult deleteBatchIds(String idList){
		//设置默认的返回结果为失败
		AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		try {
			Boolean flag = sasVehicleIRegService.deleteBatchIds(Arrays.asList(idList.split(",")));
			
			if (flag) { ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS); }
		} catch (Exception e) {
			logger.error("deleteBatchIds error: " + e);
		}
		return ajaxResult;
	}
	
}
  
    