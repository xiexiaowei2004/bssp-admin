package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusBusitype;
import com.powerbridge.bssp.cod_cus.service.ICodCusBusitypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusBusitypeController
 * 类描述： 加工类别控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 *
 * @version
 */

/**
 * 项目名称： bssp Maven Webapp
 * 类名称：
 * 类描述：
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusBusitype")
@CrossOrigin
public class CodCusBusitypeController extends BaseController {
    @Autowired
    private ICodCusBusitypeService codCusBusitypeService;

    private EntityWrapper getEntityWrapper(CodCusBusitype codCusBusitype) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        EntityWrapper entityWrapper = new EntityWrapper<CodCusBusitype>();
        if (codCusBusitype.getBusiCode() != null && !"".equals(codCusBusitype.getBusiCode())) {
            entityWrapper.like("BUSI_CODE", codCusBusitype.getBusiCode());
        }
        if (codCusBusitype.getBusiName() != null && !"".equals(codCusBusitype.getBusiName())) {
            entityWrapper.like("BUSI_NAME", codCusBusitype.getBusiName());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusBusitype
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusBusitype:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusBusitypeList(CodCusBusitype codCusBusitype) {

        AjaxResult ajaxResult = null;
        try {

            Page page = getPage();
            EntityWrapper entityWrapper = getEntityWrapper(codCusBusitype);
            Page<CodCusBusitype> codCusBusitypeList = codCusBusitypeService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBusitypeList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusBusitypeList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusBusitype
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusBusitype:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusBusitype(CodCusBusitype codCusBusitype) {

        AjaxResult ajaxResult = null;
        try {

            Boolean flag = codCusBusitypeService.insert(codCusBusitype);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBusitype);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusBusitype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusBusitype:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusBusitype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusBusitype codCusBusitype = codCusBusitypeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBusitype);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusBusitype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusBusitype
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusBusitype:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusBusitype(CodCusBusitype codCusBusitype) {

        AjaxResult ajaxResult = null;
        try {

            Boolean flag = codCusBusitypeService.updateById(codCusBusitype);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusBusitype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusBusitype:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusBusitype(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusBusitypeService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusBusitype()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusBusitype:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusBusitypeByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusBusitypeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusBusitypeByList()--err", e);
        }

        return ajaxResult;
    }
}
