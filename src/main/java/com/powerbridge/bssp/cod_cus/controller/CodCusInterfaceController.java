package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusInterface;
import com.powerbridge.bssp.cod_cus.service.ICodCusInterfaceService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;


/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodCusInterfaceController
 * 类描述：接口控制器
 * 创建人：huanliu
 * 创建时间：2017年4月29日 下午10:12:17
 */
@Controller
@RequestMapping("/cod_cus/codCusInterface")
@CrossOrigin
public class CodCusInterfaceController extends BaseController {
    @Autowired
    private ICodCusInterfaceService codCusInterfaceService;

    // 查询方法
    private EntityWrapper getEntityWrapper(CodCusInterface codCusInterface) {

    	
        String sort = getParameter("sort");//排序列名
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));//排序规则
        EntityWrapper entityWrapper = new EntityWrapper<CodCusInterface>();

        if (codCusInterface.getInterfaceId() != null && !"".equals(codCusInterface.getInterfaceId())) {
            entityWrapper.like("INTERFACE_ID", codCusInterface.getInterfaceId());
        }
        if (codCusInterface.getInterfaceName() != null && !"".equals(codCusInterface.getInterfaceName())) {
            entityWrapper.like("INTERFACE_NAME", codCusInterface.getInterfaceName());
        }
    
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }

        return entityWrapper;
    }

    /**
     * @param codCusInterface
     * @return AjaxResult
     * @throws
     * @Description: 进入接口页面:(列表查询)
     */
    @RequiresPermissions("codCusInterface:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusInterfaceList(CodCusInterface codCusInterface) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusInterface);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodCusInterface> codCusInterfaceList = codCusInterfaceService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusInterfaceList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusInterfaceList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 新增接口
     */
    @RequiresPermissions("codCusInterface:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusInterface(CodCusInterface codCusInterface) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusInterfaceService.insert(codCusInterface);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusInterface);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusInterface()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codCusInterface:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusInterface(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodCusInterface codCusInterface = codCusInterfaceService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusInterface);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusInterface()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param codCusInterface
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codCusInterface:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusInterface(CodCusInterface codCusInterface) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusInterfaceService.updateById(codCusInterface);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusInterface()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("codCusInterface:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusInterface(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusInterfaceService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusInterface()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequiresPermissions("codCusInterface:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusInterfaceByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusInterfaceService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusInterfaceByList()--err", e);
        }
        return ajaxResult;
    }
}
