package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdContaParam;
import com.powerbridge.bssp.cod_std.service.ICodStdContaParamService;
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
 * 类名称： CodStdContaParamController
 * 类描述： 集装箱参数控制器
 * 创建人： LC
 * 创建时间： 2017年5月12日
 */
@Controller
@RequestMapping("/cod_std/codStdContaParam")
@CrossOrigin
public class CodStdContaParamController extends BaseController {
    @Autowired
    private ICodStdContaParamService codStdContaParamService;
    private Gson gson = null;

    public CodStdContaParamController() {
        gson = new Gson();
    }


    private EntityWrapper getEntityWrapper(CodStdContaParam codStdContaParam) {

        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        EntityWrapper entityWrapper = new EntityWrapper<CodStdContaParam>();
        if (codStdContaParam.getCode() != null && !"".equals(codStdContaParam.getCode())) {
            entityWrapper.like("CODE", codStdContaParam.getCode());
        }
        if (codStdContaParam.getName() != null && !"".equals(codStdContaParam.getName())) {
            entityWrapper.like("NAME", codStdContaParam.getName());
        }
        if (codStdContaParam.getModel() != null && !"".equals(codStdContaParam.getModel())) {
            entityWrapper.like("NAME", codStdContaParam.getModel());
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
    @RequiresPermissions("codStdContaParam:list:view")
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult showCodStdContaParamList(CodStdContaParam codStdContaParam) {

        AjaxResult ajaxResult = null;

        try {

            Page page = getPage();
            EntityWrapper entityWrapper = getEntityWrapper(codStdContaParam);
            Page<CodStdContaParam> codStdContaParamList = codStdContaParamService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdContaParamList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodStdContaParamList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codStdContaParam:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodStdContaParam(CodStdContaParam codStdContaParam) {

        AjaxResult ajaxResult = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //使用生成器生成id
        String uid = UUIDGenerator.getUUID();
        if ("".equals(uid) && null == uid) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        codStdContaParam.setUid(uid);
        codStdContaParam.setCreateBy(SingletonLoginUtils.getSystemUserName());
        codStdContaParam.setCreateTime(timestamp);

        try {

            Boolean fail = codStdContaParamService.insert(codStdContaParam);
            if (fail) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdContaParam);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodStdContaParam()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codStdContaParam:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodStdContaParam(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodStdContaParam codStdContaParam = codStdContaParamService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdContaParam);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodStdContaParam()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codStdContaParam:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodStdContaParam(CodStdContaParam codStdContaParam) {

        AjaxResult ajaxResult = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        codStdContaParam.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        codStdContaParam.setUpdateTime(timestamp);
        try {

            Boolean fail = codStdContaParamService.updateById(codStdContaParam);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodStdContaParam()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codStdContaParam:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdContaParam(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean fail = codStdContaParamService.deleteById(id);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdContaParam()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codStdContaParam:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdContaParamByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean fail = codStdContaParamService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdContaParamByList()--err", e);
        }

        return ajaxResult;
    }
}


