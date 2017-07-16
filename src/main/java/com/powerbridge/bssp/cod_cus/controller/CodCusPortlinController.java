package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusPortlin;
import com.powerbridge.bssp.cod_cus.service.ICodCusPortlinService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusPortlinController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/4 16:18
 * 修改人：willChen
 * 修改时间：2017/5/4 16:18
 */
@Controller
@RequestMapping("/cod_cus/codCusPortlin")
@CrossOrigin
public class CodCusPortlinController extends BaseController {
    @Autowired
    private ICodCusPortlinService codCusPortlinService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusPortlin:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusPortlin(CodCusPortlin codCusPortlin) {
        if (codCusPortlinService.insert(codCusPortlin)) {
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
    @RequiresPermissions("codCusPortlin:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusPortlinService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusPortlin:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusPortlin>();
        entityWrapper.in("PK_SEQ", ids);
        if (codCusPortlinService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusPortlin:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusPortlin(CodCusPortlin codCusPortlin) {
        if (null == codCusPortlin.getPortCode()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (codCusPortlinService.updateById(codCusPortlin)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusPortlin:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusPortlin codCusPortlin = codCusPortlinService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusPortlin);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusPortlin:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusPortlin codCusPortlin, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusPortlin.getPortCCod() || "".equals(codCusPortlin.getPortCCod().trim()))) {
            entityWrapper.like("PORT_C_COD", codCusPortlin.getPortCCod());
        }
        if (!(null == codCusPortlin.getPortECod() || "".equals(codCusPortlin.getPortECod().trim()))) {
            entityWrapper.like("PORT_E_COD", codCusPortlin.getPortECod());
        }
        if (!(null == codCusPortlin.getPortLine() || "".equals(codCusPortlin.getPortLine().trim()))) {
            entityWrapper.like("PORT_LINE", codCusPortlin.getPortLine());
        }
        if (!(null == codCusPortlin.getPortCount() || "".equals(codCusPortlin.getPortCount().trim()))) {
            entityWrapper.like("PORT_COUNT", codCusPortlin.getPortCount());
        }
        if (!(null == codCusPortlin.getPortCode() || "".equals(codCusPortlin.getPortCode().trim()))) {
            entityWrapper.like("PORT_CODE", codCusPortlin.getPortCode());
        }
        if (!(null == codCusPortlin.getQuarantine() || "".equals(codCusPortlin.getQuarantine().trim()))) {
            entityWrapper.like("QUARANTINE", codCusPortlin.getQuarantine());
        }
        if (!(null == codCusPortlin.getPkSeq() || "".equals(String.valueOf(codCusPortlin.getPkSeq()).trim()))) {
            entityWrapper.like("PK_SEQ", codCusPortlin.getPkSeq().toString());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusPortlin> codCusPortlins = codCusPortlinService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusPortlins.getRecords(), page.getTotal());
    }
}
