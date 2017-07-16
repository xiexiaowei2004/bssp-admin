package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdCodes;
import com.powerbridge.bssp.cod_std.service.ICodStdCodesService;
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
 * 类名称： CodStdCodesController
 * 类描述： 数字字典维护
 * 创建人： LC
 * 创建时间： 2017年5月13日
 * 修改人: haihuihuang
 * 修改时间: 2017年5月18日
 */
@Controller
@RequestMapping("/cod_std/codStdCodes")
@CrossOrigin
public class CodStdCodesController extends BaseController {
    @Autowired
    private ICodStdCodesService codStdCodesService;

    private EntityWrapper getEntityWrapper(CodStdCodes codStdCodes) {

        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        EntityWrapper entityWrapper = new EntityWrapper<CodStdCodes>();
        if (codStdCodes.getAttrValue() != null && !"".equals(codStdCodes.getAttrValue())) {
            entityWrapper.like("ATTR_VALUE", codStdCodes.getAttrValue());
        }
        if (codStdCodes.getAttrName() != null && !"".equals(codStdCodes.getAttrName())) {
            entityWrapper.like("ATTR_NAME", codStdCodes.getAttrName());
        }
        if (codStdCodes.getDictionaryValue() != null && !"".equals(codStdCodes.getDictionaryValue())) {
            entityWrapper.like("DICTIONARY_VALUE", codStdCodes.getDictionaryValue());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        } else {
            entityWrapper.orderBy("DICTIONARY_VALUE DESC,ORDER_NO", true);
        }
        return entityWrapper;
    }


    /**
     * @param codStdCodes
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codStdCodes:list:view")
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult showCodStdCodesList(CodStdCodes codStdCodes) {

        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();
            EntityWrapper entityWrapper = getEntityWrapper(codStdCodes);
            Page<CodStdCodes> codStdCodesList = codStdCodesService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdCodesList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodStdCodesList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codStdCodes
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codStdCodes:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodStdCodes(CodStdCodes codStdCodes) {
        System.out.println(codStdCodes.getUid());
        AjaxResult ajaxResult = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //使用生成器生成id
        String uid = UUIDGenerator.getUUID();
        if ("".equals(uid) && null == uid) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        codStdCodes.setUid(uid);
        codStdCodes.setCreateBy(SingletonLoginUtils.getSystemUserName());
        codStdCodes.setCreateTime(timestamp);

        try {

            Boolean fail = codStdCodesService.insert(codStdCodes);
            if (fail) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdCodes);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodStdCodes()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codStdCodes:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodStdCodes(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodStdCodes codStdCodes = codStdCodesService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdCodes);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodStdCodes()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codStdCodes
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codStdCodes:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodStdCodes(CodStdCodes codStdCodes) {

        AjaxResult ajaxResult = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        codStdCodes.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        codStdCodes.setUpdateTime(timestamp);
        try {

            Boolean fail = codStdCodesService.updateById(codStdCodes);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodStdCodes()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codStdCodes:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdCodes(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean fail = codStdCodesService.deleteById(id);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdCodes()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codStdCodes:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdCodesByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean fail = codStdCodesService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdCodesByList()--err", e);
        }

        return ajaxResult;
    }
}
