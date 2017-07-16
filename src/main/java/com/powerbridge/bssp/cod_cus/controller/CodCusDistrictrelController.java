package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusDistrictrel;
import com.powerbridge.bssp.cod_cus.service.ICodCusDistrictrelService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusDistrictrelController
 * 类描述： 国内地区控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusDistrictrel")
@CrossOrigin
public class CodCusDistrictrelController extends BaseController {
    @Autowired
    private ICodCusDistrictrelService codCusDistrictrelService;

    private EntityWrapper getEntityWrapper(CodCusDistrictrel codCusDistrictrel) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusDistrictrel>();
        if (codCusDistrictrel.getDistrictCode() != null && !"".equals(codCusDistrictrel.getDistrictCode())) {
            entityWrapper.like("DISTRICT_CODE", codCusDistrictrel.getDistrictCode());
        }
        if (codCusDistrictrel.getDistrictName() != null && !"".equals(codCusDistrictrel.getDistrictName())) {
            entityWrapper.like("DISTRICT_NAME", codCusDistrictrel.getDistrictName());
        }
        if (codCusDistrictrel.getDistrictType() != null && !"".equals(codCusDistrictrel.getDistrictType())) {
            entityWrapper.like("DISTRICT_TYPE", codCusDistrictrel.getDistrictType());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusDistrictrel
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusDistrictrel:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusDistrictrelList(
            CodCusDistrictrel codCusDistrictrel) {

        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusDistrictrel); // 查询调用方法
            Page page = getPage(); // 分页
            Page<CodCusDistrictrel> codCusDistrictrelList = codCusDistrictrelService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusDistrictrelList.getRecords(),
                    page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusDistrictrelList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusDistrictrel
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusDistrictrel:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusDistrictrel(
            CodCusDistrictrel codCusDistrictrel) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusDistrictrelService.insert(codCusDistrictrel);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusDistrictrel);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusDistrictrel()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusDistrictrel:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusDistrictrel(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusDistrictrel codCusDistrictrel = codCusDistrictrelService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusDistrictrel);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusDistrictrel()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusDistrictrel
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusDistrictrel:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusDistrictrel(
            CodCusDistrictrel codCusDistrictrel) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusDistrictrelService.updateById(codCusDistrictrel);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusDistrictrel()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusDistrictrel:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusDistrictrel(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusDistrictrelService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusDistrictrel()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusDistrictrel:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusDistrictrelByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusDistrictrelService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusDistrictrelByList()--err", e);
        }

        return ajaxResult;
    }
}
