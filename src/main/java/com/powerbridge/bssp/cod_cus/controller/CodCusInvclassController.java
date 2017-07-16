package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusInvclass;
import com.powerbridge.bssp.cod_cus.service.ICodCusInvclassService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusInvclassController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Controller
@RequestMapping("/cod_cus/codCusInvclass")
@CrossOrigin
public class CodCusInvclassController extends BaseController {
    @Autowired
    private ICodCusInvclassService codCusInvclassService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusInvclass:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insert(CodCusInvclass codCusInvclass) {
        if (codCusInvclassService.insert(codCusInvclass)) {
            //成功
            return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusInvclass);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusInvclass:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusInvclassService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusInvclass:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusInvclass>();
        entityWrapper.in("INV_CODE", ids);
        if (codCusInvclassService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusInvclass:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusInvclass(CodCusInvclass codCusInvclass) {
        if (codCusInvclassService.updateById(codCusInvclass)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusInvclass:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusInvclass codCusInvclass = codCusInvclassService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusInvclass);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusInvclass:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusInvclass codCusInvclass, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusInvclass.getInvCode() || "".equals(codCusInvclass.getInvCode().trim()))) {
            entityWrapper.like("INV_CODE", codCusInvclass.getInvCode());
        }
        if (!(null == codCusInvclass.getInvName() || "".equals(codCusInvclass.getInvName().trim()))) {
            entityWrapper.like("INV_NAME", codCusInvclass.getInvName());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusInvclass> codCusInvclassPage = codCusInvclassService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusInvclassPage.getRecords(), page.getTotal());
    }
}
