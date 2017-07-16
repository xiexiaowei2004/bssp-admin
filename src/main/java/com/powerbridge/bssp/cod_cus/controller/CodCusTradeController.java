package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusTrade;
import com.powerbridge.bssp.cod_cus.service.ICodCusTradeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusTradeController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/4 16:18
 * 修改人：willChen
 * 修改时间：2017/5/4 16:18
 */
@Controller
@RequestMapping("/cod_cus/codCusTrade")
@CrossOrigin
public class CodCusTradeController extends BaseController {
    @Autowired
    private ICodCusTradeService codCusTradeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusTrade:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insert(CodCusTrade codCusTrade) {
        if (codCusTradeService.insert(codCusTrade)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusTrade:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusTradeService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusTrade:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult delete(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusTrade>();
        entityWrapper.in("TRADE_MODE", ids);
        if (codCusTradeService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusTrade:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusTrade(CodCusTrade codCusTrade) {
        if (null == codCusTrade.getTradeMode()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (codCusTradeService.updateById(codCusTrade)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusTrade:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusTrade codCusTrade = codCusTradeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusTrade);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusTrade:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectList(CodCusTrade codCusTrade, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusTrade.getTradeMode() || "".equals(codCusTrade.getTradeMode().trim()))) {
            entityWrapper.like("TRADE_MODE", codCusTrade.getTradeMode());
        }
        if (!(null == codCusTrade.getFullTrade() || "".equals(codCusTrade.getFullTrade().trim()))) {
            entityWrapper.like("FULL_TRADE", codCusTrade.getFullTrade());
        }
        if (!(null == codCusTrade.getAbbrTrade() || "".equals(codCusTrade.getAbbrTrade().trim()))) {
            entityWrapper.like("ABBR_TRADE", codCusTrade.getAbbrTrade());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusTrade> codCusTrades = codCusTradeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusTrades.getRecords(), page.getTotal());
    }
}
