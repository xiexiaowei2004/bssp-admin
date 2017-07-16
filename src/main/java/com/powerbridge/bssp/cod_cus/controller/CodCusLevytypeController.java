package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusLevytype;
import com.powerbridge.bssp.cod_cus.service.ICodCusLevytypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusLevytypeController
 * 类描述： 征免性质控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusLevytype")
@CrossOrigin
public class CodCusLevytypeController extends BaseController {
    @Autowired
    private ICodCusLevytypeService codCusLevytypeService;

    private EntityWrapper getEntityWrapper(CodCusLevytype codCusLevytype) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusLevytype>();
        if (codCusLevytype.getCutMode() != null && !"".equals(codCusLevytype.getCutMode())) {
            entityWrapper.like("CUT_MODE", codCusLevytype.getCutMode());
        }
        if (codCusLevytype.getAbbrCut() != null && !"".equals(codCusLevytype.getAbbrCut())) {
            entityWrapper.like("ABBR_CUT", codCusLevytype.getAbbrCut());
        }
        if (codCusLevytype.getFullCut() != null && !"".equals(codCusLevytype.getFullCut())) {
            entityWrapper.like("FULL_CUT", codCusLevytype.getFullCut());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusLevytype
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusLevytype:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusLevytypeList(
            CodCusLevytype codCusLevytype) {

        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusLevytype); // 查询调用方法
            Page page = getPage(); // 分页
            Page<CodCusLevytype> codCusLevytypeList = codCusLevytypeService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusLevytypeList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusLevytypeList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusLevytype
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusLevytype:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusLevytype(
            CodCusLevytype codCusLevytype) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusLevytypeService.insert(codCusLevytype);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusLevytype);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusLevytype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusLevytype:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusLevytype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusLevytype codCusLevytype = codCusLevytypeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusLevytype);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusLevytype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusLevytype
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusLevytype:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusLevytype(
            CodCusLevytype codCusLevytype) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusLevytypeService.updateById(codCusLevytype);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusLevytype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusLevytype:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusLevytype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusLevytypeService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusLevytype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusLevytype:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusLevytypeByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusLevytypeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusLevytypeByList()--err", e);
        }

        return ajaxResult;
    }
}
