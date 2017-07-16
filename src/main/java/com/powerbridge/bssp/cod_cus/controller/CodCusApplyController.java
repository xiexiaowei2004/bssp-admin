package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusApply;
import com.powerbridge.bssp.cod_cus.service.ICodCusApplyService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodCusApplyController
 * 类描述：应用控制器
 * 创建人：huanliu
 * 创建时间：2017年4月29日 下午10:12:17
 */
@Controller
@RequestMapping("/cod_cus/codCusApply")
@CrossOrigin
public class CodCusApplyController extends BaseController {
    @Autowired
    private ICodCusApplyService codCusApplyService;

    // 查询方法
    private EntityWrapper getEntityWrapper(CodCusApply codCusApply) {

        String sort = getParameter("sort");//排序列名
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));//排序规则
        EntityWrapper entityWrapper = new EntityWrapper<CodCusApply>();

        if (codCusApply.getApplyId() != null && !"".equals(codCusApply.getApplyId())) {
            entityWrapper.like("APPLY_ID", codCusApply.getApplyId());
        }
        if (codCusApply.getApplyName() != null && !"".equals(codCusApply.getApplyName())) {
            entityWrapper.like("APPLY_NAME", codCusApply.getApplyName());
        }
    
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }


    /**
     * @param codCusApply
     * @return AjaxResult
     * @throws
     * @Description: 进入应用页面:(列表查询)
     */
    @RequiresPermissions("codCusApply:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusApplyList(CodCusApply codCusApply) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusApply);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodCusApply> codCusApplyList = codCusApplyService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusApplyList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusApplyList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param codCusApply
     * @return AjaxResult
     * @throws
     * @Description: 新增应用
     */
    @RequiresPermissions("codCusApply:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusApply(CodCusApply codCusApply) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusApplyService.insert(codCusApply);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusApply);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusApply()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codCusApply:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusApply(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodCusApply codCusApply = codCusApplyService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusApply);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusApply()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param codCusApply
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codCusApply:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusApply(CodCusApply codCusApply) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusApplyService.updateById(codCusApply);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusApply()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("codCusApply:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusApply(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusApplyService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusApply()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequiresPermissions("codCusApply:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusApplyByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusApplyService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusApplyByList()--err", e);
        }
        return ajaxResult;
    }
}

