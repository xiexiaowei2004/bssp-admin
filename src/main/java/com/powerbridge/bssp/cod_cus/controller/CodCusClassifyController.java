package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusClassify;
import com.powerbridge.bssp.cod_cus.service.ICodCusClassifyService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusClassifyController
 * 类描述： 商品归类表控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusClassify")
@CrossOrigin
public class CodCusClassifyController extends BaseController {
    @Autowired
    private ICodCusClassifyService codCusClassifyService;

    private EntityWrapper getEntityWrapper(CodCusClassify codCusClassify) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusClassify>();
        if (codCusClassify.getPkSeq() != null && !"".equals(codCusClassify.getPkSeq())) {
            entityWrapper.like("PK_SEQ", (codCusClassify.getPkSeq().toString()));
        }
        if (codCusClassify.getgName() != null && !"".equals(codCusClassify.getgName())) {
            entityWrapper.like("G_NAME", codCusClassify.getgName());
        }
        if (codCusClassify.getCodeT() != null && !"".equals(codCusClassify.getCodeT())) {
            entityWrapper.like("CODE_T", codCusClassify.getCodeT());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusClassify
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusClassify:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusClassifyList(
            CodCusClassify codCusClassify) {

        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusClassify); // 查询调用方法
            Page page = getPage(); // 分页

            Page<CodCusClassify> codCusClassifyList = codCusClassifyService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusClassifyList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusClassifyList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusClassify
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusClassify:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusClassify(
            CodCusClassify codCusClassify) {

        AjaxResult ajaxResult = null;
        try {

            Boolean flag = codCusClassifyService.insert(codCusClassify);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusClassify);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusClassify()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusClassify:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusClassify(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusClassify codCusClassify = codCusClassifyService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusClassify);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusClassify()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusClassify
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusClassify:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusClassify(
            CodCusClassify codCusClassify) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusClassifyService.updateById(codCusClassify);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusClassify()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusClassify:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusClassify(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusClassifyService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusClassify()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusClassify:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusClassifyByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusClassifyService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusClassifyByList()--err", e);
        }

        return ajaxResult;
    }
}
