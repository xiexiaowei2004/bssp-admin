package com.powerbridge.bssp.cod_biz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_biz.entity.CodBizRsk;
import com.powerbridge.bssp.cod_biz.service.ICodBizRskService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodBizRskController
 * 类描述：风险参数表控制器
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日  上午09:59:00
 */
@Controller
@RequestMapping("/cod_biz/codBizRsk")
@CrossOrigin
public class CodBizRskController extends BaseController {
    @Autowired
    private ICodBizRskService codBizRskService;

    private static ValidatorFactory factory;
    private static Validator validator;

    /**
     * @param codBizRsk
     * @return AjaxResult
     * @throws
     * @Description: 进入风险参数管理页面:(列表查询)
     */
    @RequiresPermissions("codBizRsk:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodBizRskList(CodBizRsk codBizRsk) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按关区代码+场地代码+参数代码值从小到大顺排
                page.setAsc(true);
                page.setOrderByField("customsCode,areaCode,code");  // 排序
            }
            Page<CodBizRsk> codBizRskList = codBizRskService.selectByCodBizRsk(page, codBizRsk);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codBizRskList.getRecords(), page.getTotal()); // 返回json数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodBizRskList()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codBizRsk:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodBizRsk(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodBizRsk CodBizRsk = codBizRskService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, CodBizRsk);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodBizRsk()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codBizRsk
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codBizRsk:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodBizRsk(CodBizRsk codBizRsk) {

        AjaxResult ajaxResult = null;
        AjaxResult validResult = check(codBizRsk);
        if (!ObjectUtils.isEmpty(validResult)) {
            return validResult;
        }
        try {
            codBizRsk.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            codBizRsk.setUpdateBy(SingletonLoginUtils.getSystemUserName());
            Boolean flag = codBizRskService.updateById(codBizRsk);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodBizRsk()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：删除单条数据
     */
    @RequiresPermissions("codBizRsk:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodBizRsk(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codBizRskService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodBizRsk()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codBizRsk:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodBizRskByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codBizRskService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodBizRskByList()--err", e);
        }

        return ajaxResult;
    }
}

