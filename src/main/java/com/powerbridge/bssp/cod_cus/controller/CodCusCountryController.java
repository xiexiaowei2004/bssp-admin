package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusCountry;
import com.powerbridge.bssp.cod_cus.service.ICodCusCountryService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusCountryController
 * 类描述： 国别地区控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusCountry")
@CrossOrigin
public class CodCusCountryController extends BaseController {
    @Autowired
    private ICodCusCountryService codCusCountryService;

    private EntityWrapper getEntityWrapper(CodCusCountry codCusCountry) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusCountry>();
        if (codCusCountry.getCountryCo() != null && !"".equals(codCusCountry.getCountryCo())) {
            entityWrapper.like("COUNTRY_CO", codCusCountry.getCountryCo());
        }
        if (codCusCountry.getCountryEn() != null && !"".equals(codCusCountry.getCountryEn())) {
            entityWrapper.like("COUNTRY_EN", codCusCountry.getCountryEn());
        }
        if (codCusCountry.getCountryNa() != null && !"".equals(codCusCountry.getCountryNa())) {
            entityWrapper.like("COUNTRY_NA", codCusCountry.getCountryNa());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusCountry
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusCountry:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusCountryList(CodCusCountry codCusCountry) {

        AjaxResult ajaxResult = null;
        try {

            EntityWrapper entityWrapper = getEntityWrapper(codCusCountry);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodCusCountry> codCusCountryList = codCusCountryService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCountryList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusCountryList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCountry
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusCountry:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusCountry(CodCusCountry codCusCountry) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCountryService.insert(codCusCountry);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCountry);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusCountry()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusCountry:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusCountry(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodCusCountry codCusCountry = codCusCountryService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusCountry);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusCountry()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusCountry
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusCountry:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusCountry(CodCusCountry codCusCountry) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCountryService.updateById(codCusCountry);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusCountry()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusCountry:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCountry(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusCountryService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCountry()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusCountry:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusCountryByList() {

        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusCountryService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusCountryByList()--err", e);
        }

        return ajaxResult;
    }
}
