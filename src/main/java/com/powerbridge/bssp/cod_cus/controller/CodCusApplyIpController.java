package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusApplyIp;
import com.powerbridge.bssp.cod_cus.service.ICodCusApplyIpService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodCusApplyIpController
 * 类描述：应用IP控制器
 * 创建人：huanliu
 * 创建时间：2017年4月29日 下午10:12:17
 */
@Controller
@RequestMapping("/cod_cus/codCusApplyIp")
@CrossOrigin
public class CodCusApplyIpController extends BaseController {
    @Autowired
    private ICodCusApplyIpService codCusApplyIpService;

    // 查询方法
    private EntityWrapper getEntityWrapper(CodCusApplyIp codCusApplyIp) {

        String sort = getParameter("sort");//排序列名
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));//排序规则
        EntityWrapper entityWrapper = new EntityWrapper<CodCusApplyIp>();
        if (codCusApplyIp.getApplyId() != null && !"".equals(codCusApplyIp.getApplyId())) {
            entityWrapper.like("APPLY_ID", codCusApplyIp.getApplyId());
        }
        if (codCusApplyIp.getApplyIp() != null && !"".equals(codCusApplyIp.getApplyIp())) {
            entityWrapper.like("APPLY_IP", codCusApplyIp.getApplyIp());
        }
       
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }

    /**
     * @param codCusApplyIp
     * @return AjaxResult
     * @throws
     * @Description: 进入应用IP管理页面:(列表查询)

     */
    @RequiresPermissions("codCusApplyIp:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusApplyIpList(CodCusApplyIp codCusApplyIp) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusApplyIp);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodCusApplyIp> codCusApplyIpList = codCusApplyIpService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusApplyIpList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusApplyIpList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param codCusApplyIp
     * @return AjaxResult
     * @throws
     * @Description: 新增应用IP
     */
    @RequiresPermissions("codCusApplyIp:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusApplyIp(CodCusApplyIp codCusApplyIp) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusApplyIpService.insert(codCusApplyIp);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusApplyIp);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusApplyIp()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codCusApplyIp:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusApplyIp(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodCusApplyIp codCusApplyIp = codCusApplyIpService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusApplyIp);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusApplyIp()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param codCusApplyIp
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codCusApplyIp:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusApplyIp(CodCusApplyIp codCusApplyIp) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusApplyIpService.updateById(codCusApplyIp);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusApplyIp()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("codCusApplyIp:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusApplyIp(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusApplyIpService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusApplyIp()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequiresPermissions("codCusApplyIp:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusApplyIpByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusApplyIpService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusApplyIpByList()--err", e);
        }
        return ajaxResult;
    }
}

