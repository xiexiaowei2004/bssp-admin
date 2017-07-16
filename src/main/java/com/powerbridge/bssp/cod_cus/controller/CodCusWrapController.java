package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusWrap;
import com.powerbridge.bssp.cod_cus.service.ICodCusWrapService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusWrapController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Controller
@RequestMapping("/cod_cus/codCusWrap")
@CrossOrigin
public class CodCusWrapController extends BaseController {
    @Autowired
    private ICodCusWrapService codCusWrapService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusWrap:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusWrap(CodCusWrap codCusWrap) {
        if (codCusWrapService.insert(codCusWrap)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusWrap:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusWrapService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusWrap:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusWrap>();
        entityWrapper.in("WRAP_TYPE", ids);
        if (codCusWrapService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusWrap:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusWrap(CodCusWrap codCusWrap) {
        if (codCusWrapService.updateById(codCusWrap)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusWrap:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusWrap codCusWrap = codCusWrapService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusWrap);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusWrap:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusWrap codCusWrap, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusWrap.getLicenseducoCode() || "".equals(codCusWrap.getLicenseducoCode().trim()))) {
            entityWrapper.like("LICENSEDUCO_CODE", codCusWrap.getLicenseducoCode());
        }
        if (!(null == codCusWrap.getWrapType() || "".equals(codCusWrap.getWrapType().trim()))) {
            entityWrapper.like("WRAP_TYPE", codCusWrap.getWrapType());
        }
        if (!(null == codCusWrap.getWrapName() || "".equals(codCusWrap.getWrapName().trim()))) {
            entityWrapper.like("WRAP_NAME", codCusWrap.getWrapName());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusWrap> codCusWraps = codCusWrapService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusWraps.getRecords(), page.getTotal());
    }
}
