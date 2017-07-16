package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusUserto;
import com.powerbridge.bssp.cod_cus.service.ICodCusUsertoService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusUsertoController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Controller
@RequestMapping("/cod_cus/codCusUserto")
@CrossOrigin
public class CodCusUsertoController extends BaseController {
    @Autowired
    private ICodCusUsertoService codCusUsertoService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusUserto:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusUserto(CodCusUserto codCusUserto) {
        if (codCusUsertoService.insert(codCusUserto)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusUserto:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusUsertoService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusUserto:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusUserto>();
        entityWrapper.in("USE_TO_COD", ids);
        if (codCusUsertoService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusUserto:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusUserto(CodCusUserto codCusUserto) {
        if (codCusUsertoService.updateById(codCusUserto)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusUserto:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusUserto codCusUserto = codCusUsertoService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusUserto);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusUserto:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusUserto codCusUserto, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusUserto.getUseToCod() || "".equals(codCusUserto.getUseToCod().trim()))) {
            entityWrapper.like("USE_TO_COD", codCusUserto.getUseToCod());
        }
        if (!(null == codCusUserto.getUseToNam() || "".equals(codCusUserto.getUseToNam().trim()))) {
            entityWrapper.like("USE_TO_NAM", codCusUserto.getUseToNam());
        }
        Page page = new com.baomidou.mybatisplus.plugins.Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusUserto> codCusUsertoPage = codCusUsertoService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusUsertoPage.getRecords(), page.getTotal());
    }
}
