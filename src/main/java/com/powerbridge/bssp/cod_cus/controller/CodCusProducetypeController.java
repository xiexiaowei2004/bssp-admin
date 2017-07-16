package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusProducetype;
import com.powerbridge.bssp.cod_cus.service.ICodCusProducetypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusProducetypeController
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/4 16:18
 * 修改人：willChen
 * 修改时间：2017/5/4 16:18
 */
@Controller
@RequestMapping("/cod_cus/codCusProducetype")
@CrossOrigin
public class CodCusProducetypeController extends BaseController {
    @Autowired
    private ICodCusProducetypeService codCusProducetypeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codCusProducetype:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusProducetype(CodCusProducetype codCusProducetype) {
        if (codCusProducetypeService.insert(codCusProducetype)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codCusProducetype:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (codCusProducetypeService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("codCusProducetype:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodCusProducetype>();
        entityWrapper.in("PRODUCT_TYPE_CODE", ids);
        if (codCusProducetypeService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codCusProducetype:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusProducetype(CodCusProducetype codCusProducetype) {
        if (null == codCusProducetype.getProductTypeCode()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (codCusProducetypeService.updateById(codCusProducetype)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codCusProducetype:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        CodCusProducetype codCusProducetype = codCusProducetypeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusProducetype);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codCusProducetype:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(CodCusProducetype codCusProducetype, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (!(null == codCusProducetype.getProductTypeCode() || "".equals(codCusProducetype.getProductTypeCode().trim()))) {
            entityWrapper.like("PRODUCT_TYPE_CODE", codCusProducetype.getProductTypeCode());
        }
        if (!(null == codCusProducetype.getProductTypeName() || "".equals(codCusProducetype.getProductTypeName().trim()))) {
            entityWrapper.like("PRODUCT_TYPE_NAME", codCusProducetype.getProductTypeName());
        }
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodCusProducetype> codCusProducetypes = codCusProducetypeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codCusProducetypes.getRecords(), page.getTotal());
    }
}
