package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusLevymode;
import com.powerbridge.bssp.cod_cus.service.ICodCusLevymodeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusLevymodeController
 * 类描述： 征免方式控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusLevymode")
@CrossOrigin
public class CodCusLevymodeController extends BaseController {
    @Autowired
    private ICodCusLevymodeService codCusLevymodeService;

    private EntityWrapper getEntityWrapper(CodCusLevymode codCusLevymode) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusLevymode>();
        if (codCusLevymode.getDutyMode() != null && !"".equals(codCusLevymode.getDutyMode())) {
            entityWrapper.like("DUTY_MODE", codCusLevymode.getDutyMode());
        }
        if (codCusLevymode.getDutySpec() != null && !"".equals(codCusLevymode.getDutySpec())) {
            entityWrapper.like("DUTY_SPEC", codCusLevymode.getDutySpec());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusLevymode
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusLevymode:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusLevymodeList(
            CodCusLevymode codCusLevymode) {

        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusLevymode); // 查询调用方法
            Page page = getPage(); // 分页
            Page<CodCusLevymode> codCusLevymodeList = codCusLevymodeService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusLevymodeList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusLevymodeList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusLevymode
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusLevymode:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusLevymode(
            CodCusLevymode codCusLevymode) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusLevymodeService.insert(codCusLevymode);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusLevymode);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusLevymode()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusLevymode:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusLevymode(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusLevymode codCusLevymode = codCusLevymodeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusLevymode);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusLevymode()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusLevymode
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusLevymode:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusLevymode(
            CodCusLevymode codCusLevymode) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusLevymodeService.updateById(codCusLevymode);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusLevymode()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusLevymode:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusLevymode(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusLevymodeService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusLevymode()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusLevymode:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusLevymodeByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusLevymodeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusLevymodeByList()--err", e);
        }

        return ajaxResult;
    }
}
