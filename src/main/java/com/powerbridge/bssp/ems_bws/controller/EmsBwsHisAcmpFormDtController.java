package com.powerbridge.bssp.ems_bws.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisAcmpFormDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 物流账册随附单证明细 前端控制器
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Controller
@RequestMapping("/ems_bws/emsBwsHisAcmpFormDt")
@CrossOrigin
public class EmsBwsHisAcmpFormDtController extends BaseController {
	 @Autowired
	    private IEmsBwsHisAcmpFormDtService emsBwsHisAcmpFormDtService;
	    /**
	     * 查询账册备案申请附件列表
	     *
	     * @return 账册备案申请附件列表
	     */
	    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
	    @ResponseBody
	    public AjaxResult selectEmsBwsHisAcmpFormDts(EmsBwsHisAcmpFormDt emsBwsHisAcmpFormDt) {
	        AjaxResult ajaxResult = null;
	        try {
	            Page page = getPage();  // 分页
	            String sort = getParameter("sort");
	            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
	            if (StringUtil.isNotEmpty(sort)) {
	                page.setAsc(sortOrder);
	                page.setOrderByField(sort);  // 排序
	            } else {
	                //默认按商品序号排序
	                page.setAsc(true);
	                page.setOrderByField("acmpFormSeqno");  // 排序
	            }
	            Page<EmsBwsHisAcmpFormDt> emsBwsHisAcmpFormDtPage = emsBwsHisAcmpFormDtService.selectEmsBwsHisAcmpFormDtList(page, emsBwsHisAcmpFormDt);
	            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsHisAcmpFormDtPage.getRecords(), page.getTotal());
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("selectEmsBwsHisAcmpFormDtList()--error", e);
	        }
	        return ajaxResult;
	    }
	    /**
	     * 根据账册编号查询账册备案申请附件列表
	     *
	     * @return 账册备案申请附件列表
	     */
	    @RequestMapping(value = "/list/selectByBopNo", method = {RequestMethod.POST, RequestMethod.GET})
	    @ResponseBody
	    public AjaxResult selectEmsBwsHisAcmpFormDtByBopNo(EmsBwsHisAcmpFormDt emsBwsHisAcmpFormDt) {
	        AjaxResult ajaxResult = null;
	        if(StringUtil.isEmpty(emsBwsHisAcmpFormDt.getBopNo())){
	            ajaxResult = result(MessageConstants.BSSP_STATUS_EMSNO_ERROR);
	            return  ajaxResult;
	        }
	        try {
	            Page page = getPage();  // 分页
	            String sort = getParameter("sort");
	            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
	            if (StringUtil.isNotEmpty(sort)) {
	                page.setAsc(sortOrder);
	                page.setOrderByField(sort);  // 排序
	            } else {
	                //默认按商品序号排序
	                page.setAsc(true);
	                page.setOrderByField("acmpFormSeqno");  // 排序
	            }
	            Page<EmsBwsHisAcmpFormDt>emsBwsHisAcmpFormDtPage = emsBwsHisAcmpFormDtService.selectEmsBwsHisAcmpFormDtList(page, emsBwsHisAcmpFormDt);
	            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsHisAcmpFormDtPage.getRecords(), page.getTotal());
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("selectEmsBwsHisAcmpFormDtByBopNo()--error", e);
	        }
	        return ajaxResult;
	    }

	    /**
	     * 根据id查询账册备案随附单证信息
	     * @param id
	     * @return
	     */
	    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
	    @ResponseBody
	    public AjaxResult selectEmsBwsHisAcmpFormDtsById(@PathVariable String id) {
	        AjaxResult ajaxResult;
	        try {
	        	EmsBwsHisAcmpFormDt emsBwsHisAcmpFormDt = emsBwsHisAcmpFormDtService.selectById(id);
	            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsHisAcmpFormDt);
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("selectEmsBwsHisAcmpFormDtsById()--err", e);
	        }
	        return ajaxResult;

	    }
	}

	

