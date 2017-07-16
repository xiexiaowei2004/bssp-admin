package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.base.entity.SystemLog;
import com.powerbridge.bssp.base.service.ISystemLogService;
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
 * 类名称：systemLogController
 * 类描述：日志控制器
 * 创建人：huanliu
 * 创建时间：2017年4月29日 下午10:12:17
 */
@Controller
@RequestMapping("/cod_cus/systemLog")
@CrossOrigin
public class SystemLogController extends BaseController {
    @Autowired
    private ISystemLogService systemLogService;

    // 查询方法
    private EntityWrapper getEntityWrapper(SystemLog systemLog) {

        String sort = getParameter("sort");//排序列名
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));//排序规则
        EntityWrapper entityWrapper = new EntityWrapper<SystemLog>();


        if (systemLog.getId() != null && !"".equals(systemLog.getId())) {
            entityWrapper.like("ID", systemLog.getId());
        }
        if (systemLog.getApplyId() != null && !"".equals(systemLog.getApplyId())) {
            entityWrapper.like("APPLY_ID", systemLog.getApplyId());
        }

        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }

    /**
     * @param systemLog
     * @return AjaxResult
     * @throws
     * @Description: 进入日志页面:(列表查询)
     */
    @RequiresPermissions("systemLog:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showsystemLogList(SystemLog systemLog) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(systemLog);  //查询调用方法
            Page page = getPage();  // 分页
            Page<SystemLog> systemLogList = systemLogService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, systemLogList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showsystemLogList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param systemLog
     * @return AjaxResult
     * @throws
     * @Description: 新增日志
     */
    @RequiresPermissions("systemLog:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addsystemLog(SystemLog systemLog) {
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());//获取当前时间（mysl：timestamp类型）
        systemLog.setReponesData(nousedate.toString());
        systemLog.setData(nousedate);
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = systemLogService.insert(systemLog);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, systemLog);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addsystemLog()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("systemLog:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editsystemLog(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SystemLog systemLog = systemLogService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, systemLog);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editsystemLog()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param systemLog
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("systemLog:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updatesystemLog(SystemLog systemLog) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = systemLogService.updateById(systemLog);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updatesystemLog()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("systemLog:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deletesystemLog(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = systemLogService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deletesystemLog()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequiresPermissions("systemLog:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deletesystemLogByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = systemLogService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deletesystemLogByList()--err", e);
        }
        return ajaxResult;
    }
}

