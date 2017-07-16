package com.powerbridge.bssp.cod_biz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_biz.entity.CodBizBus;
import com.powerbridge.bssp.cod_biz.service.ICodBizBusService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodBizBusController
 * 类描述：业务参数表控制器
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日  上午09:59:00
 */
@Controller
@RequestMapping("/cod_biz/codBizBus")
@CrossOrigin
public class CodBizBusController extends BaseController {
    @Autowired
    private ICodBizBusService codBizBusService;

    private static ValidatorFactory factory;
    private static Validator validator;

    /**
     * @param codBizBus
     * @return AjaxResult
     * @throws
     * @Description: 进入业务参数管理页面:(列表查询)
     */
    @RequiresPermissions("codBizBus:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodBizBusList(CodBizBus codBizBus) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);
            } else {
                //默认按关区代码+场地代码+参数代码值从小到大顺排
                page.setAsc(true);
                page.setOrderByField("customsCode,areaCode,code");  // 排序
            }
            Page<CodBizBus> codBizBusList = codBizBusService.selectByCodBizBus(page, codBizBus);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codBizBusList.getRecords(), page.getTotal()); // 返回json数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodBizBusList()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codBizBus:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodBizBus(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodBizBus codBizBus = codBizBusService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codBizBus);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodBizBus()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codBizBus
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codBizBus:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodBizBus(CodBizBus codBizBus) {
        AjaxResult ajaxResult = null;
        AjaxResult validResult = check(codBizBus);
        if (!ObjectUtils.isEmpty(validResult)) {
            return validResult;
        }
        try {
            codBizBus.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            codBizBus.setUpdateBy(SingletonLoginUtils.getSystemUserName());
            Boolean flag = codBizBusService.updateById(codBizBus);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodBizBus()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：删除单条数据
     */
    @RequiresPermissions("codBizBus:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodBizBus(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codBizBusService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodBizBus()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codBizBus:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodBizBusByList(String idList) {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            Boolean flag = codBizBusService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodBizBusByList()--err", e);
        }

        return ajaxResult;
    }
}

