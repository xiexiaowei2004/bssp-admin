package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusCotype;
import com.powerbridge.bssp.cod_cus.service.ICodCusCotypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusCotypeController
 * 类描述： 企业性质控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusCotype")
@CrossOrigin
public class CodCusCotypeController extends BaseController {
    @Autowired
    private ICodCusCotypeService codCusCotypeService;

    private EntityWrapper getEntityWrapper(CodCusCotype codCusCotype) {
        String sort = getParameter("sort");

        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusCotype>();
        if (codCusCotype.getCoOwner() != null && !"".equals(codCusCotype.getCoOwner())) {
            entityWrapper.like("CO_OWNER", codCusCotype.getCoOwner());
        }
        if (codCusCotype.getCoOwnersh() != null && !"".equals(codCusCotype.getCoOwnersh())) {
            entityWrapper.like("CO_OWNERSH", codCusCotype.getCoOwnersh());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }

    /**
     * @param codCusCotype
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusCotype:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusCotypeList(CodCusCotype codCusCotype) {

        AjaxResult ajaxResult = null;
        try {


            EntityWrapper entityWrapper = getEntityWrapper(codCusCotype);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodCusCotype> codCusCotypeList = codCusCotypeService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCotypeList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusCotypeList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCotype
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusCotype:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusCotype(CodCusCotype codCusCotype) {

        AjaxResult ajaxResult = null;
        try {

            Boolean flag = codCusCotypeService.insert(codCusCotype);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCotype);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusCotype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusCotype:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusCotype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusCotype codCusCotype = codCusCotypeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCotype);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusCotype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCotype
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusCotype:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusCotype(CodCusCotype codCusCotype) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCotypeService.updateById(codCusCotype);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusCotype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusCotype:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCotype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCotypeService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCotype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusCotype:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCotypeByList() {

        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusCotypeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCotypeByList()--err", e);
        }

        return ajaxResult;
    }
}
