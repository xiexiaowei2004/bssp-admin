package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusDisttype;
import com.powerbridge.bssp.cod_cus.service.ICodCusDisttypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusDisttypeController
 * 类描述： 地区类别控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusDisttype")
@CrossOrigin
public class CodCusDisttypeController extends BaseController {
    @Autowired
    private ICodCusDisttypeService codCusDisttypeService;

    private EntityWrapper getEntityWrapper(CodCusDisttype codCusDisttype) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusDisttype>();
        if (codCusDisttype.getDistrictT() != null && !"".equals(codCusDisttype.getDistrictT())) {
            entityWrapper.like("DISTRICT_T", codCusDisttype.getDistrictT());
        }
        if (codCusDisttype.getDistType() != null && !"".equals(codCusDisttype.getDistType())) {
            entityWrapper.like("DIST_TYPE", codCusDisttype.getDistType());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusDisttype
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusDisttype:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusDisttypeList(
            CodCusDisttype codCusDisttype) {

        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusDisttype); // 查询调用方法
            Page page = getPage(); // 分页
            Page<CodCusDisttype> codCusDisttypeList = codCusDisttypeService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusDisttypeList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusDisttypeList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusDisttype
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusDisttype:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusDisttype(
            CodCusDisttype codCusDisttype) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusDisttypeService.insert(codCusDisttype);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusDisttype);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusDisttype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusDisttype:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusDisttype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusDisttype codCusDisttype = codCusDisttypeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusDisttype);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusDisttype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusDisttype
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusDisttype:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusDisttype(
            CodCusDisttype codCusDisttype) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusDisttypeService.updateById(codCusDisttype);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusDisttype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusDisttype:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusDisttype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusDisttypeService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusDisttype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusDisttype:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusDisttypeByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusDisttypeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusDisttypeByList()--err", e);
        }

        return ajaxResult;
    }
}
