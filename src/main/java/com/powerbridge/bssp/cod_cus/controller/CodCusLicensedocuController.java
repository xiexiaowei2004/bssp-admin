package com.powerbridge.bssp.cod_cus.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusLicensedocu;
import com.powerbridge.bssp.cod_cus.service.ICodCusLicensedocuService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusLicensedocuController
 * 类描述： 许可证件控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusLicensedocu")
@CrossOrigin
public class CodCusLicensedocuController extends BaseController {
    @Autowired
    private ICodCusLicensedocuService codCusLicensedocuService;

    private EntityWrapper getEntityWrapper(CodCusLicensedocu codCusLicensedocu) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusLicensedocu>();
        if (codCusLicensedocu.getDocuCode() != null && !"".equals(codCusLicensedocu.getDocuCode())) {
            entityWrapper.like("DOCU_CODE", codCusLicensedocu.getDocuCode());
        }
        if (codCusLicensedocu.getDocuName() != null && !"".equals(codCusLicensedocu.getDocuName())) {
            entityWrapper.like("DOCU_NAME", codCusLicensedocu.getDocuName());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusLicensedocu
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusLicensedocu:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusLicensedocuList(
            CodCusLicensedocu codCusLicensedocu) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusLicensedocu); // 查询调用方法
            Page page = getPage(); // 分页
            Page<CodCusLicensedocu> codCusLicensedocuList = codCusLicensedocuService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusLicensedocuList.getRecords(),
                    page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusLicensedocuList()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param codCusLicensedocu
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusLicensedocu:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusLicensedocu(
            CodCusLicensedocu codCusLicensedocu) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusLicensedocuService.insert(codCusLicensedocu);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusLicensedocu);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusLicensedocu()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusLicensedocu:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusLicensedocu(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodCusLicensedocu codCusLicensedocu = codCusLicensedocuService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusLicensedocu);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusLicensedocu()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusLicensedocu
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusLicensedocu:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusLicensedocu(
            CodCusLicensedocu codCusLicensedocu) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusLicensedocuService.updateById(codCusLicensedocu);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusLicensedocu()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusLicensedocu:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusLicensedocu(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusLicensedocuService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusLicensedocu()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusLicensedocu:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusLicensedocuByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusLicensedocuService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusLicensedocuByList()--err", e);
        }

        return ajaxResult;
    }
}
