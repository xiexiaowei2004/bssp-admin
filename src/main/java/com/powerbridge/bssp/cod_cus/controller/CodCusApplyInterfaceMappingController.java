package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusApplyInterfaceMapping;
import com.powerbridge.bssp.cod_cus.service.ICodCusApplyInterfaceService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodCusApplyInterfaceMappingController
 * 类描述：应用接口控制器
 * 创建人：huanliu
 * 创建时间：2017年4月29日 下午10:12:17
 */
@Controller
@RequestMapping("/cod_cus/codCusApplyInterfaceMapping")
@CrossOrigin
public class CodCusApplyInterfaceMappingController extends BaseController {
    @Autowired
    private ICodCusApplyInterfaceService codCusApplyInterfaceService;

    // 查询方法
    private EntityWrapper getEntityWrapper(CodCusApplyInterfaceMapping codCusApplyInterfaceMapping) {
        String sort = getParameter("sort");//排序列名
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));//排序规则
        EntityWrapper entityWrapper = new EntityWrapper<CodCusApplyInterfaceMapping>();


        if (codCusApplyInterfaceMapping.getApplyId() != null && !"".equals(codCusApplyInterfaceMapping.getApplyId())) {
            entityWrapper.like("APPLY_ID", codCusApplyInterfaceMapping.getApplyId());
        }
        if (codCusApplyInterfaceMapping.getInterfaceInfo() != null && !"".equals(codCusApplyInterfaceMapping.getInterfaceInfo())) {
            entityWrapper.like("INTERFACE_INFO", codCusApplyInterfaceMapping.getInterfaceInfo().toString());
        }

        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }

    /**
     * @param codCusApplyInterfaceMapping
     * @return AjaxResult
     * @throws
     * @Description: 进入应用接口页面:(列表查询)
     */
    @RequiresPermissions("codCusApplyInterfaceMapping:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusApplyInterfaceMappingList(CodCusApplyInterfaceMapping codCusApplyInterfaceMapping) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusApplyInterfaceMapping);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodCusApplyInterfaceMapping> codCusApplyInterfaceMappingList = codCusApplyInterfaceService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusApplyInterfaceMappingList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusApplyInterfaceMappingList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param codCusApplyInterfaceMapping
     * @return AjaxResult
     * @throws
     * @Description: 新增应用接口
     */
    @RequiresPermissions("codCusApplyInterfaceMapping:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusApplyInterfaceMapping(CodCusApplyInterfaceMapping codCusApplyInterfaceMapping) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusApplyInterfaceService.insert(codCusApplyInterfaceMapping);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusApplyInterfaceMapping);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusApplyInterfaceMapping()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codCusApplyInterfaceMapping:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusApplyInterfaceMapping(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodCusApplyInterfaceMapping codCusApplyInterfaceMapping = codCusApplyInterfaceService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusApplyInterfaceMapping);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusApplyInterfaceMapping()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param codCusApplyInterfaceMapping
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codCusApplyInterfaceMapping:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusApplyInterfaceMapping(CodCusApplyInterfaceMapping codCusApplyInterfaceMapping) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusApplyInterfaceService.updateById(codCusApplyInterfaceMapping);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusApplyInterfaceMapping()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("codCusApplyInterfaceMapping:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusApplyInterfaceMapping(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusApplyInterfaceService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusApplyInterfaceMapping()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequiresPermissions("codCusApplyInterfaceMapping:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusApplyInterfaceMappingByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusApplyInterfaceService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusApplyInterfaceMappingByList()--err", e);
        }
        return ajaxResult;
    }
}

