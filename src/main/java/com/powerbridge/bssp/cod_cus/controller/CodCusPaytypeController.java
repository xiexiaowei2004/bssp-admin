package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusPaytype;
import com.powerbridge.bssp.cod_cus.service.ICodCusPaytypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusPaytypeController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/4 16:18
 * 修改人：willChen
 * 修改时间：2017/5/4 16:18
 */
@Controller
@RequestMapping("/cod_cus/codCusPaytype")
@CrossOrigin
public class CodCusPaytypeController extends BaseController {
    @Autowired
    private ICodCusPaytypeService codCusPaytypeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusPaytype:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusPaytype(CodCusPaytype codCusPaytype) {
        if (codCusPaytypeService.insert(codCusPaytype)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusPaytype:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusPaytypeService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusPaytype:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusPaytype>();
        entityWrapper.in("PAY_TYPE_CO", ids);
        if (codCusPaytypeService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusPaytype:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusPaytype(CodCusPaytype codCusPaytype) {
        if (null == codCusPaytype.getPayTypeCo()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (codCusPaytypeService.updateById(codCusPaytype)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusPaytype:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusPaytype codCusPaytype = codCusPaytypeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusPaytype);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusPaytype:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusPaytype codCusPaytype, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusPaytype.getPayTypeCo() || "".equals(codCusPaytype.getPayTypeCo().trim()))) {
            entityWrapper.like("PAY_TYPE_CO", codCusPaytype.getPayTypeCo());
        }
        if (!(null == codCusPaytype.getPayTypeNa() || "".equals(codCusPaytype.getPayTypeNa().trim()))) {
            entityWrapper.like("PAY_TYPE_NA", codCusPaytype.getPayTypeNa());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusPaytype> codCusPaytypes = codCusPaytypeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusPaytypes.getRecords(), page.getTotal());
    }
}
