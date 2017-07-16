package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusMftecode;
import com.powerbridge.bssp.cod_cus.service.ICodCusMftecodeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusMftecodeController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Controller
@RequestMapping("/cod_cus/codCusMftecode")
@CrossOrigin
public class CodCusMftecodeController extends BaseController {
    @Autowired
    private ICodCusMftecodeService codCusMftecodeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusMftecode:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusMftecode(CodCusMftecode codCusMftecode) {
        if (codCusMftecodeService.insert(codCusMftecode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusMftecode:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusMftecodeService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusMftecode:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusMftecode>();
        entityWrapper.in("MFTEC_CODE", ids);
        if (codCusMftecodeService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusMftecode:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusMftecode(CodCusMftecode codCusMftecode) {
        if (null == codCusMftecode.getMftecCode()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (codCusMftecodeService.updateById(codCusMftecode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusMftecode:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusMftecode codCusMftecode = codCusMftecodeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusMftecode);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusMftecode:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusMftecode codCusMftecode, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusMftecode.getMftecCode() || "".equals(codCusMftecode.getMftecCode().trim()))) {
            entityWrapper.like("MFTEC_CODE", codCusMftecode.getMftecCode());
        }
        if (!(null == codCusMftecode.getMftecName() || "".equals(codCusMftecode.getMftecName().trim()))) {
            entityWrapper.like("MFTEC_NAME", codCusMftecode.getMftecName());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusMftecode> codCusMftecodes = codCusMftecodeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusMftecodes.getRecords(), page.getTotal());
    }
}
