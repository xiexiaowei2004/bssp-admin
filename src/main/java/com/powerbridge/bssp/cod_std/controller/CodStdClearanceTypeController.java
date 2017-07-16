package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdClearanceType;
import com.powerbridge.bssp.cod_std.service.ICodStdClearanceTypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称：CodStdClearanceTypeController
 * 类描述：通关业务类型控制器
 * 创建人： xwq
 * 创建时间：2017年5月11日16:34:58
 */
@Controller
@RequestMapping("/cod_std/codStdClearanceType")
@CrossOrigin
public class CodStdClearanceTypeController extends BaseController {
    @Autowired
    private ICodStdClearanceTypeService codStdClearanceTypeService;

    private Gson gson = null;

    public CodStdClearanceTypeController() {
        gson = new Gson();
    }

    // 查询方法
    private EntityWrapper getEntityWrapper(CodStdClearanceType codStdClearanceType) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        EntityWrapper entityWrapper = new EntityWrapper<CodStdClearanceType>();
        if (codStdClearanceType.getCode() != null && !"".equals(codStdClearanceType.getCode())) {
            entityWrapper.like("CODE", codStdClearanceType.getCode());
        }
        if (codStdClearanceType.getName() != null && !"".equals(codStdClearanceType.getName())) {
            entityWrapper.like("NAME", codStdClearanceType.getName());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }

    /**
     * @param codStdClearanceType
     * @return AjaxResult
     * @throws
     * @Description: 进入通关业务类型页面:(列表查询)
     */
    @RequiresPermissions("codStdClearanceType:list:view")
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult showCodStdClearanceTypeList(CodStdClearanceType codStdClearanceType) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codStdClearanceType);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodStdClearanceType> codStdClearanceTypeList = codStdClearanceTypeService.selectPage(page, entityWrapper);
            //SimpleDateFormat.

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdClearanceTypeList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodStdClearanceTypeList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param codStdClearanceType
     * @return AjaxResult
     * @throws
     * @Description: 新增通关业务类型
     */
    @RequiresPermissions("codStdClearanceType:list:add")
    @RequestMapping(value = "/list/add")
    @ResponseBody
    public AjaxResult addCodStdClearanceType(CodStdClearanceType codStdClearanceType) {
        AjaxResult ajaxResult = null;
        try {
            codStdClearanceType.setUid(UUIDGenerator.getUUID());
            codStdClearanceType.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            codStdClearanceType.setCreateBy(SingletonLoginUtils.getSystemUserName());//创建人
            Boolean fail = codStdClearanceTypeService.insert(codStdClearanceType);
            if (fail) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdClearanceType);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodStdClearanceType()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codStdClearanceType:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodStdClearanceType(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodStdClearanceType codStdClearanceType = codStdClearanceTypeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdClearanceType);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodStdClearanceType()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param codStdClearanceType
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codStdClearanceType:list:edit")
    @RequestMapping("/list/update")
    @ResponseBody
    public AjaxResult updateCodStdClearanceType(CodStdClearanceType codStdClearanceType) {
        AjaxResult ajaxResult = null;
        try {
            codStdClearanceType.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            codStdClearanceType.setUpdateBy(SingletonLoginUtils.getSystemUserName());//修改人
            Boolean fail = codStdClearanceTypeService.updateById(codStdClearanceType);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updatecodStdClearanceType()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("CodStdClearanceType:list:delete")
    @RequestMapping("/list/delete")
    @ResponseBody
    public AjaxResult deleteCodStdClearanceType() {
        AjaxResult ajaxResult = null;
        try {
            Boolean fail = codStdClearanceTypeService.deleteById(getParameter("id"));
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdClearanceType()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("CodStdClearanceType:list:delete")
    @RequestMapping("/list/deleteByList")
    @ResponseBody
    public AjaxResult deleteCodStdClearanceTypeList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean fail = codStdClearanceTypeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdClearanceTypeByList()--err", e);
        }

        return ajaxResult;
    }
}
  
    