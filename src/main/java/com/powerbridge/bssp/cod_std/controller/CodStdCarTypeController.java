package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdCarType;
import com.powerbridge.bssp.cod_std.service.ICodStdCarTypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称：CodStdCarTypeController
 * 类描述：通关业务类型控制器
 * 创建人： zsl
 * 创建时间：2017年5月13日18:34:58
 */
@Controller
@RequestMapping("/cod_std/codStdCarType")
@CrossOrigin
public class CodStdCarTypeController extends BaseController {
    @Autowired
    private ICodStdCarTypeService codStdCarTypeService;

    private Gson gson = null;

    public CodStdCarTypeController() {
        gson = new Gson();
    }

    // 查询方法
    private EntityWrapper getEntityWrapper(CodStdCarType codStdCarType) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        EntityWrapper entityWrapper = new EntityWrapper<CodStdCarType>();
        if (codStdCarType.getCode() != null && !"".equals(codStdCarType.getCode())) {
            entityWrapper.like("CODE", codStdCarType.getCode());
        }
        if (codStdCarType.getName() != null && !"".equals(codStdCarType.getName())) {
            entityWrapper.like("NAME", codStdCarType.getName());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }

    /**
     * @param codStdCarType
     * @return AjaxResult
     * @throws
     * @Description: 进入车辆类型页面:(列表查询)showCodStdCarTypeList
     */
    @RequiresPermissions("codStdCarType:list:view")
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult showCodStdCarTypeList(HttpServletRequest request, HttpServletResponse response, CodStdCarType codStdCarType, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (StringUtils.isNotEmpty(codStdCarType.getCode())) {
            entityWrapper.like("CODE", codStdCarType.getCode());
        }

        if (StringUtils.isNotEmpty(codStdCarType.getName())) {
            entityWrapper.or().like("NAME", codStdCarType.getName());
        }

        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodStdCarType> codStdTransportTypes = codStdCarTypeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codStdTransportTypes.getRecords(), page.getTotal());
    }

    /**
     * @param codStdAreaCode
     * @return AjaxResult
     * @throws
     * @Description: 新增通关业务类型
     */
    @RequiresPermissions("codStdCarType:list:add")
    @RequestMapping(value = "/list/add")
    @ResponseBody
    public AjaxResult addCodStdCarType(CodStdCarType codStdCarType) {
        AjaxResult ajaxResult = null;
        try {
            codStdCarType.setUid(UUIDGenerator.getUUID());
            codStdCarType.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            codStdCarType.setCreateBy(SingletonLoginUtils.getSystemUserName());//创建人
            Boolean fail = codStdCarTypeService.insert(codStdCarType);
            if (fail) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdCarType);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodStdCarType()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codStdCarType:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodStdCarType(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodStdCarType codStdCarType = codStdCarTypeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdCarType);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodStdCarType()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param codStdCarType
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codStdCarType:list:edit")
    @RequestMapping("/list/update")
    @ResponseBody
    public AjaxResult updateCodStdCarType(CodStdCarType codStdCarType) {
        AjaxResult ajaxResult = null;
        try {
            codStdCarType.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            codStdCarType.setUpdateBy(SingletonLoginUtils.getSystemUserName());//修改人
            Boolean fail = codStdCarTypeService.updateById(codStdCarType);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updatecodStdCarType()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param codStdCarType
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("CodStdCarType:list:delete")
    @RequestMapping("/list/delete")
    @ResponseBody
    public AjaxResult deleteCodStdCarType() {
        AjaxResult ajaxResult = null;
        try {
            Boolean fail = codStdCarTypeService.deleteById(getParameter("id"));
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdCarType()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param model
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("CodStdCarType:list:delete")
    @RequestMapping("/list/deleteByList")
    @ResponseBody
    public AjaxResult deleteCodStdCarTypeList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean fail = codStdCarTypeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdCarTypeByList()--err", e);
        }

        return ajaxResult;
    }
}
  
    