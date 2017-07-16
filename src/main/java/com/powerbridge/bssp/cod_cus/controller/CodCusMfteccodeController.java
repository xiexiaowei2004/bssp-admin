package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusMfteccode;
import com.powerbridge.bssp.cod_cus.service.ICodCusMfteccodeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusMfteccodeController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/4 16:18
 * 修改人：willChen
 * 修改时间：2017/5/4 16:18
 */
@Controller
@RequestMapping("/cod_cus/codCusMfteccode")
@CrossOrigin
public class CodCusMfteccodeController extends BaseController {
    @Autowired
    private ICodCusMfteccodeService codCusMfteccodeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusMfteccode:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusMfteccode(CodCusMfteccode codCusMfteccode) {
        if (codCusMfteccodeService.insert(codCusMfteccode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        } else {
            //失败
            return result(MessageConstants.BSSP_STATUS_FAIL);
        }
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusMfteccode:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusMfteccodeService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusMfteccode:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusMfteccode>();
        entityWrapper.in("MFTEC_CODE", ids);
        if (codCusMfteccodeService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusMfteccode:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusMfteccode(CodCusMfteccode codCusMfteccode) {
        if (null == codCusMfteccode.getMftecCode()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (codCusMfteccodeService.updateById(codCusMfteccode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusMfteccode codCusMfteccode = codCusMfteccodeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusMfteccode);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusMfteccode:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusMfteccode codCusMfteccode, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusMfteccode.getMftecCode() || "".equals(codCusMfteccode.getMftecCode().trim()))) {
            entityWrapper.like("MFTEC_CODE", codCusMfteccode.getMftecCode());
        }
        if (!(null == codCusMfteccode.getMftecName() || "".equals(codCusMfteccode.getMftecName().trim()))) {
            entityWrapper.like("MFTEC_NAME", codCusMfteccode.getMftecName());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusMfteccode> codCusMfteccodes = codCusMfteccodeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusMfteccodes.getRecords(), page.getTotal());
    }
}
