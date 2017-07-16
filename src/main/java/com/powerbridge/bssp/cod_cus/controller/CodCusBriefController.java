package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusBrief;
import com.powerbridge.bssp.cod_cus.service.ICodCusBriefService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodCusBriefController
 * 类描述：企业海关控制器
 * 创建人：haihuihuang
 * 创建时间：2017年4月29日 下午10:12:17
 */
@Controller
@RequestMapping("/cod_cus/codCusBrief")
@CrossOrigin
public class CodCusBriefController extends BaseController {
    @Autowired
    private ICodCusBriefService codCusBriefService;

    // 查询方法
    private EntityWrapper getEntityWrapper(CodCusBrief codCusBrief) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        EntityWrapper entityWrapper = new EntityWrapper<CodCusBrief>();
        if (codCusBrief.getRegCoCgac() != null && !"".equals(codCusBrief.getRegCoCgac())) {
            entityWrapper.like("REG_CO_CGAC", codCusBrief.getRegCoCgac());
        }
        if (codCusBrief.getEntName() != null && !"".equals(codCusBrief.getEntName())) {
            entityWrapper.like("ENT_NAME", codCusBrief.getEntName());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }

    /**
     * @param codCusBrief
     * @return AjaxResult
     * @throws
     * @Description: 进入企业海关管理页面:(列表查询)
     */
    @RequiresPermissions("codCusBrief:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusBriefList(CodCusBrief codCusBrief) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codCusBrief);  //查询调用方法
            Page page = getPage();  // 分页
            List<CodCusBrief> codCusBriefList = codCusBriefService.selectPage(page, entityWrapper).getRecords();
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBriefList, page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusBriefList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param codCusBrief
     * @return AjaxResult
     * @throws
     * @Description: 新增企业海关
     */
    @RequiresPermissions("codCusBrief:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusBrief(CodCusBrief codCusBrief) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusBriefService.insert(codCusBrief);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBrief);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusBrief()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codCusBrief:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusBrief(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodCusBrief codCusBrief = codCusBriefService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusBrief);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusBrief()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param codCusBrief
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codCusBrief:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusBrief(CodCusBrief codCusBrief) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusBriefService.updateById(codCusBrief);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusBrief()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("codCusBrief:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusBrief(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusBriefService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusBrief()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequiresPermissions("codCusBrief:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusBriefByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusBriefService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusBriefByList()--err", e);
        }
        return ajaxResult;
    }
}

