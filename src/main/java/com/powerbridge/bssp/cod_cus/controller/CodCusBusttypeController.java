package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusBusttype;
import com.powerbridge.bssp.cod_cus.service.ICodCusBusttypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusBusttypeController
 * 类描述： 企业类型控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusBusttype")
@CrossOrigin
public class CodCusBusttypeController extends BaseController {
    @Autowired
    private ICodCusBusttypeService codCusBusttypeService;

    private EntityWrapper getEntityWrapper(CodCusBusttype codCusBusttype) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusBusttype>();
        if (codCusBusttype.getTypeCo() != null && !"".equals(codCusBusttype.getTypeCo())) {
            entityWrapper.like("TYPE_CO", codCusBusttype.getTypeCo());
        }
        if (codCusBusttype.getTypeNa() != null && !"".equals(codCusBusttype.getTypeNa())) {
            entityWrapper.like("TYPE_NA", codCusBusttype.getTypeNa());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @Description：分页查询
     * @param codCusBusttype
     * @return
     * @throws
     */
    @RequiresPermissions("codCusBusttype:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusBusttypeList(
            CodCusBusttype codCusBusttype) {

        AjaxResult ajaxResult = null;
        try {

            EntityWrapper entityWrapper = getEntityWrapper(codCusBusttype); // 查询调用方法
            Page page = getPage(); // 分页
            Page<CodCusBusttype> codCusBusttypeList = codCusBusttypeService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBusttypeList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusBusttypeList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @Description：新增数据
     * @param codCusBusttype
     * @return
     * @throws
     */
    @RequiresPermissions("codCusBusttype:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusBusttype(
            CodCusBusttype codCusBusttype) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusBusttypeService.insert(codCusBusttype);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBusttype);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusBusttype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @Description：编辑页面
     * @param   id
     * @return
     * @throws
     */
    @RequiresPermissions("codCusBusttype:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusBusttype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusBusttype codCusBusttype = codCusBusttypeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBusttype);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusBusttype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @Description：修改数据
     * @param codCusBusttype
     * @return
     * @throws
     */
    @RequiresPermissions("codCusBusttype:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusBusttype(
            CodCusBusttype codCusBusttype) {

        AjaxResult ajaxResult = null;
        try {

            Boolean flag = codCusBusttypeService.updateById(codCusBusttype);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusBusttype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @Description：单个删除
     * @param   id
     * @return
     * @throws
     */
    @RequiresPermissions("codCusBusttype:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusBusttype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusBusttypeService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusBusttype()--err", e);
        }

        return ajaxResult;
    }

    /**
     * @Description：批量删除
     * @param
     * @return
     * @throws
     */
    @RequiresPermissions("codCusBusttype:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusBusttypeByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusBusttypeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusBusttypeByList()--err", e);
        }

        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 企业类型数据源
     */
    @RequestMapping(value = "/getDataSource", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getDataSource() {
        AjaxResult ajaxResult = null;
        try {
            List<CodCusBusttype> codCusBusttypes = codCusBusttypeService.selectBusttypeByList(); //企业类型下拉选择数据源
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBusttypes);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("getDataSource()--err", e);
        }
        return ajaxResult;
    }
}
