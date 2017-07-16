package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusCustomsfec;
import com.powerbridge.bssp.cod_cus.service.ICodCusCustomsfecService;
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
 * 类名称： CodCusCustomsfecController
 * 类描述： 关区代码控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusCustomsfec")
@CrossOrigin
public class CodCusCustomsfecController extends BaseController {
    @Autowired
    private ICodCusCustomsfecService codCusCustomsfecService;

    private EntityWrapper getEntityWrapper(CodCusCustomsfec codCusCustomsfec) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusCustomsfec>();
        if (codCusCustomsfec.getCustomsCode() != null && !"".equals(codCusCustomsfec.getCustomsCode())) {
            entityWrapper.like("CUSTOMS_CODE", codCusCustomsfec.getCustomsCode());
        }
        if (codCusCustomsfec.getCustomsName() != null && !"".equals(codCusCustomsfec.getCustomsName())) {
            entityWrapper.like("CUSTOMS_NAME", codCusCustomsfec.getCustomsName());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusCustomsfec
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusCustomsfec:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusCustomsfecList(
            CodCusCustomsfec codCusCustomsfec) {

        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusCustomsfec); // 查询调用方法
            Page page = getPage(); // 分页
            Page<CodCusCustomsfec> codCusCustomsfecList = codCusCustomsfecService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCustomsfecList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusCustomsfecList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCustomsfec
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusCustomsfec:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusCustomsfec(
            CodCusCustomsfec codCusCustomsfec) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCustomsfecService.insert(codCusCustomsfec);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCustomsfec);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusCustomsfec()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusCustomsfec:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusCustomsfec(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusCustomsfec codCusCustomsfec = codCusCustomsfecService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCustomsfec);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusCustomsfec()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCustomsfec
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusCustomsfec:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusCustomsfec(
            CodCusCustomsfec codCusCustomsfec) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCustomsfecService.updateById(codCusCustomsfec);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusCustomsfec()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusCustomsfec:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCustomsfec(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCustomsfecService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCustomsfec()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusCustomsfec:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCustomsfecByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusCustomsfecService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCustomsfecByList()--err", e);
        }

        return ajaxResult;
    }

}
