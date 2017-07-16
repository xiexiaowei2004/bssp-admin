package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusPaymode;
import com.powerbridge.bssp.cod_cus.service.ICodCusPaymodeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusPaymodeController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/4 16:18
 * 修改人：willChen
 * 修改时间：2017/5/4 16:18
 */
@Controller
@RequestMapping("/cod_cus/codCusPaymode")
@CrossOrigin
public class CodCusPaymodeController extends BaseController {
    @Autowired
    private ICodCusPaymodeService codCusPaymodeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusPaymode:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusPaymode(CodCusPaymode codCusPaymode) {
        if (codCusPaymodeService.insert(codCusPaymode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusPaymode:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusPaymodeService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusPaymode:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusPaymode>();
        entityWrapper.in("PAY_MODE_CODE", ids);
        if (codCusPaymodeService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusPaymode:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusPaymode(CodCusPaymode codCusPaymode) {
        if (null == codCusPaymode.getPayModeCode()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (codCusPaymodeService.updateById(codCusPaymode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusPaymode:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusPaymode codCusPaymode = codCusPaymodeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusPaymode);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusPaymode:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusPaymode codCusPaymode, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusPaymode.getPayModeCode() || "".equals(codCusPaymode.getPayModeCode().trim()))) {
            entityWrapper.like("PAY_MODE_CODE", codCusPaymode.getPayModeCode());
        }
        if (!(null == codCusPaymode.getPayModeName() || "".equals(codCusPaymode.getPayModeName().trim()))) {
            entityWrapper.like("PAY_MODE_NAME", codCusPaymode.getPayModeName());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusPaymode> codCusPaymodes = codCusPaymodeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusPaymodes.getRecords(), page.getTotal());
    }
}
