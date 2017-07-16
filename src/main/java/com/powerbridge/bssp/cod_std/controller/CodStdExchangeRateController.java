package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdExchangeRate;
import com.powerbridge.bssp.cod_std.service.ICodStdExchangeRateService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodStdExchangeRateController
 * 类描述： 汇率维护控制器
 * 创建人： LC
 * 创建时间： 2017年5月13日
 */
@Controller
@RequestMapping("/cod_std/codStdExchangeRate")
@CrossOrigin
public class CodStdExchangeRateController extends BaseController {

    @Autowired
    private ICodStdExchangeRateService codStdExchangeRateService;
    private Gson gson = null;

    public CodStdExchangeRateController() {
        gson = new Gson();
    }


    private EntityWrapper getEntityWrapper(CodStdExchangeRate codStdExchangeRate) {

        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        EntityWrapper entityWrapper = new EntityWrapper<CodStdExchangeRate>();
        if (codStdExchangeRate.getCode() != null && !"".equals(codStdExchangeRate.getCode())) {
            entityWrapper.like("CODE", codStdExchangeRate.getCode());
        }
        if (codStdExchangeRate.getName() != null && !"".equals(codStdExchangeRate.getName())) {
            entityWrapper.like("NAME", codStdExchangeRate.getName());
        }
        if (codStdExchangeRate.geteName() != null && !"".equals(codStdExchangeRate.geteName())) {
            entityWrapper.like("E_NAME", codStdExchangeRate.geteName());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codStdExchangeRate:list:view")
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult showCodStdExchangeRateList(CodStdExchangeRate codStdExchangeRate) {

        AjaxResult ajaxResult = null;

        try {

            Page page = getPage();
            EntityWrapper entityWrapper = getEntityWrapper(codStdExchangeRate);
            Page<CodStdExchangeRate> codStdExchangeRateList = codStdExchangeRateService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdExchangeRateList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodStdExchangeRateList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codStdExchangeRate:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodStdExchangeRate(CodStdExchangeRate codStdExchangeRate) {
        System.out.println(codStdExchangeRate.getUid());
        AjaxResult ajaxResult = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //使用生成器生成id
        String uid = UUIDGenerator.getUUID();
        if ("".equals(uid) && null == uid) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        codStdExchangeRate.setUid(uid);
        codStdExchangeRate.setCreateBy(SingletonLoginUtils.getSystemUserName());
        codStdExchangeRate.setCreateTime(timestamp);

        try {

            Boolean fail = codStdExchangeRateService.insert(codStdExchangeRate);
            if (fail) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdExchangeRate);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodStdExchangeRate()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codStdExchangeRate:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodStdExchangeRate(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodStdExchangeRate codStdExchangeRate = codStdExchangeRateService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdExchangeRate);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodStdExchangeRate()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codStdExchangeRate:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodStdExchangeRate(CodStdExchangeRate codStdExchangeRate) {

        AjaxResult ajaxResult = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        codStdExchangeRate.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        codStdExchangeRate.setUpdateTime(timestamp);
        try {

            Boolean fail = codStdExchangeRateService.updateById(codStdExchangeRate);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodStdExchangeRate()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codStdExchangeRate:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdExchangeRate(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean fail = codStdExchangeRateService.deleteById(id);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdExchangeRate()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codStdExchangeRate:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdExchangeRateByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean fail = codStdExchangeRateService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdExchangeRateByList()--err", e);
        }

        return ajaxResult;
    }
}


