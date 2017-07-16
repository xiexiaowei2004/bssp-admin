package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusCurr;
import com.powerbridge.bssp.cod_cus.service.ICodCusCurrService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusCurrController
 * 类描述： 币制代码控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusCurr")
@CrossOrigin
public class CodCusCurrController extends BaseController {
    @Autowired
    private ICodCusCurrService codCusCurrService;

    private EntityWrapper getEntityWrapper(CodCusCurr codCusCurr) {

        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusCurr>();
        if (codCusCurr.getCurrCode() != null && !"".equals(codCusCurr.getCurrCode())) {
            entityWrapper.like("CURR_CODE", codCusCurr.getCurrCode());
        }
        if (codCusCurr.getCurrSymb() != null && !"".equals(codCusCurr.getCurrSymb())) {
            entityWrapper.like("CURR_SYMB", codCusCurr.getCurrSymb());
        }
        if (codCusCurr.getCurrName() != null && !"".equals(codCusCurr.getCurrName())) {
            entityWrapper.like("CURR_NAME", codCusCurr.getCurrName());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusCurr
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusCurr:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusCurrList(CodCusCurr codCusCurr) {

        AjaxResult ajaxResult = null;
        try {

            EntityWrapper entityWrapper = getEntityWrapper(codCusCurr);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodCusCurr> codCusCurrList = codCusCurrService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCurrList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusCurrList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCurr
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusCurr:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusCurr(CodCusCurr codCusCurr) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCurrService.insert(codCusCurr);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCurr);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusCurr()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusCurr:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusCurr(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusCurr codCusCurr = codCusCurrService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCurr);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusCurr()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCurr
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusCurr:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusCurr(CodCusCurr codCusCurr) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCurrService.updateById(codCusCurr);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusCurr()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusCurr:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCurr(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCurrService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCurr()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusCurr:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCurrByList() {

        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusCurrService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCurrByList()--err", e);
        }

        return ajaxResult;
    }
}
