package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusInvestmode;
import com.powerbridge.bssp.cod_cus.service.ICodCusInvestmodeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



/**
 * 项目名称：bssp-admin
 * 类名称：CodCusInvestmodeController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Controller
@RequestMapping("/cod_cus/codCusInvestmode")
@CrossOrigin
public class CodCusInvestmodeController extends BaseController {
    @Autowired
    private ICodCusInvestmodeService codCusInvestmodeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusInvestmode:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusInvestmode(CodCusInvestmode codCusInvestmode) {
        if (codCusInvestmodeService.insert(codCusInvestmode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusInvestmode:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusInvestmodeService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusInvestmode:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusInvestmode>();
        entityWrapper.in("UNIT_CODE", ids);
        if (codCusInvestmodeService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusInvestmode:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusInvestmode(CodCusInvestmode codCusInvestmode) {
        if (codCusInvestmodeService.updateById(codCusInvestmode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusInvestmode:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusInvestmode codCusInvestmode = codCusInvestmodeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusInvestmode);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusInvestmode:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusInvestmode codCusInvestmode, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusInvestmode.getInvestModeCode() || "".equals(codCusInvestmode.getInvestModeCode().trim()))) {
            entityWrapper.like("INVEST_MODE_CODE", codCusInvestmode.getInvestModeCode());
        }
        if (!(null == codCusInvestmode.getInvestModeName() || "".equals(codCusInvestmode.getInvestModeName().trim()))) {
            entityWrapper.like("INVEST_MODE_NAME", codCusInvestmode.getInvestModeName());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusInvestmode> codCusInvestmodes = codCusInvestmodeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusInvestmodes.getRecords(), page.getTotal());
    }
}
