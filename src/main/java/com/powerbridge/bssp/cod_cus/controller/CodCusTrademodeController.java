package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusTrademode;
import com.powerbridge.bssp.cod_cus.service.ICodCusTrademodeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusTrademodeController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/4 16:18
 * 修改人：willChen
 * 修改时间：2017/5/4 16:18
 */
@Controller
@RequestMapping("/cod_cus/codCusTrademode")
@CrossOrigin
public class CodCusTrademodeController extends BaseController {
    @Autowired
    private ICodCusTrademodeService codCusTrademodeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusTrademode:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusTrademode(CodCusTrademode codCusTrademode) {
        if (codCusTrademodeService.insert(codCusTrademode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusTrademode:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusTrademodeService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusTrademode:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusTrademode>();
        entityWrapper.in("PK_SEQ", ids);
        if (codCusTrademodeService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusTrademode:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusTrademode(CodCusTrademode codCusTrademode) {
        if (codCusTrademodeService.updateById(codCusTrademode)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusTrademode:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusTrademode codCusTrademode = codCusTrademodeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusTrademode);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusTrademode:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusTrademode codCusTrademode, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusTrademode.getPkSeq() || "".equals(codCusTrademode.getPkSeq().trim()))) {
            entityWrapper.like("PK_SEQ", codCusTrademode.getPkSeq());
        }
        if (!(null == codCusTrademode.getImControl() || "".equals(codCusTrademode.getImControl().trim()))) {
            entityWrapper.like("IM_CONTROL", codCusTrademode.getImControl());
        }
        if (!(null == codCusTrademode.getExControl() || "".equals(codCusTrademode.getExControl().trim()))) {
            entityWrapper.like("EX_CONTROL", codCusTrademode.getExControl());
        }
        if (!(null == codCusTrademode.getDistrictT() || "".equals(codCusTrademode.getDistrictT().trim()))) {
            entityWrapper.like("DISTRICT_T", codCusTrademode.getDistrictT());
        }
        if (!(null == codCusTrademode.getBasicIm() || "".equals(codCusTrademode.getBasicIm().trim()))) {
            entityWrapper.like("BASIC_IM", codCusTrademode.getBasicIm());
        }
        if (!(null == codCusTrademode.getBasicEx() || "".equals(codCusTrademode.getBasicEx().trim()))) {
            entityWrapper.like("BASIC_EX", codCusTrademode.getBasicEx());
        }
        if (!(null == codCusTrademode.getTradeMode() || "".equals(codCusTrademode.getTradeMode().trim()))) {
            entityWrapper.like("TRADE_MODE", codCusTrademode.getTradeMode());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusTrademode> codCusTrademodes = codCusTrademodeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusTrademodes.getRecords(), page.getTotal());
    }
}
