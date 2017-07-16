package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusCustomsrel;
import com.powerbridge.bssp.cod_cus.service.ICodCusCustomsrelService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusCustomsrelController
 * 类描述： 口岸海关代码控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 *
 * @version
 */

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusCustomsrelController
 * 类描述： 口岸海关代码控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusCustomsrel")
@CrossOrigin
public class CodCusCustomsrelController extends BaseController {
    @Autowired
    private ICodCusCustomsrelService codCusCustomsrelService;

    private EntityWrapper getEntityWrapper(CodCusCustomsrel codCusCustomsrel) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusCustomsrel>();
        if (codCusCustomsrel.getCustomsCode() != null && !"".equals(codCusCustomsrel.getCustomsCode())) {
            entityWrapper.like("CUSTOMS_CODE", codCusCustomsrel.getCustomsCode());
        }
        if (codCusCustomsrel.getCustomsName() != null && !"".equals(codCusCustomsrel.getCustomsName())) {
            entityWrapper.like("CUSTOMS_NAME", codCusCustomsrel.getCustomsName());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusCustomsrel
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusCustomsrel:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusCustomsrelList(
            CodCusCustomsrel codCusCustomsrel) {

        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusCustomsrel); // 查询调用方法
            Page page = getPage(); // 分页

            Page<CodCusCustomsrel> codCusCustomsrelList = codCusCustomsrelService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCustomsrelList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusCustomsrelList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCustomsrel
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusCustomsrel:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusCustomsrel(
            CodCusCustomsrel codCusCustomsrel) {

        AjaxResult ajaxResult = null;
        try {

            Boolean flag = codCusCustomsrelService.insert(codCusCustomsrel);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCustomsrel);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusCustomsrel()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusCustomsrel:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusCustomsrel(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusCustomsrel codCusCustomsrel = codCusCustomsrelService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCustomsrel);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusCustomsrel()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCustomsrel
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusCustomsrel:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusCustomsrel(
            CodCusCustomsrel codCusCustomsrel) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCustomsrelService.updateById(codCusCustomsrel);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusCustomsrel()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusCustomsrel:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCustomsrel(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCustomsrelService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCustomsrel()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusCustomsrel:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCustomsrelByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusCustomsrelService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCustomsrelByList()--err", e);
        }

        return ajaxResult;
    }
}
