package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusUnit;
import com.powerbridge.bssp.cod_cus.service.ICodCusUnitService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusUnitController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Controller
@RequestMapping("/cod_cus/codCusUnit")
@CrossOrigin
public class CodCusUnitController extends BaseController {
    @Autowired
    private ICodCusUnitService codCusUnitService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusUnit:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusUnit(CodCusUnit codCusUnit) {
        if (codCusUnitService.insert(codCusUnit)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusUnit:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusUnitService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusUnit:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusUnit>();
        entityWrapper.in("UNIT_CODE", ids);
        if (codCusUnitService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusUnit:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusUnit(CodCusUnit codCusUnit) {
        if (codCusUnitService.updateById(codCusUnit)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusUnit:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusUnit codCusUnit = codCusUnitService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusUnit);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusUnit:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusUnit codCusUnit, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusUnit.getUnitCode() || "".equals(codCusUnit.getUnitCode().trim()))) {
            entityWrapper.like("UNIT_CODE", codCusUnit.getUnitCode());
        }
        if (!(null == codCusUnit.getUnitName() || "".equals(codCusUnit.getUnitName().trim()))) {
            entityWrapper.like("UNIT_NAME", codCusUnit.getUnitName());
        }
        if (!(null == codCusUnit.getConvCode() || "".equals(codCusUnit.getConvCode().trim()))) {
            entityWrapper.like("CONV_CODE", codCusUnit.getConvCode());
        }
        if (!(null == codCusUnit.getConvRatio() || "".equals(codCusUnit.getConvRatio().trim()))) {
            entityWrapper.like("CONV_RATIO", codCusUnit.getConvRatio());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusUnit> codCusUnits = codCusUnitService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusUnits.getRecords(), page.getTotal());
    }
}
