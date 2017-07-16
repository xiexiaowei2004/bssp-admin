package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusTrans;
import com.powerbridge.bssp.cod_cus.service.ICodCusTransService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusTransController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/4 16:18
 * 修改人：willChen
 * 修改时间：2017/5/4 16:18
 */
@Controller
@RequestMapping("/cod_cus/codCusTrans")
@CrossOrigin
public class CodCusTransController extends BaseController {
    @Autowired
    private ICodCusTransService codCusTransService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusTrans:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusTrans(CodCusTrans codCusTrans) {
        if (codCusTransService.insert(codCusTrans)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusTrans:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusTransService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusTrans:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusTrans>();
        entityWrapper.in("TRANS_MODE", ids);
        if (codCusTransService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusTrans:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusTrans(CodCusTrans codCusTrans) {
        if (codCusTransService.updateById(codCusTrans)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusTrans:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusTrans codCusTrans = codCusTransService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusTrans);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusTrans:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusTrans codCusTrans, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusTrans.getTransSpec() || "".equals(codCusTrans.getTransSpec().trim()))) {
            entityWrapper.like("TRANS_SPEC", codCusTrans.getTransSpec());
        }
        if (!(null == codCusTrans.getTransMode() || "".equals(codCusTrans.getTransMode().trim()))) {
            entityWrapper.like("TRANS_MODE", codCusTrans.getTransMode());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusTrans> codCusTranss = codCusTransService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusTranss.getRecords(), page.getTotal());
    }
}
