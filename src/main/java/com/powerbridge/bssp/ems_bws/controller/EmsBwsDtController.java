package com.powerbridge.bssp.ems_bws.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtArcrpAcmpFormDt;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsAcmpFormDt;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsAcmpFormDtService;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsDtService;

/**
 * <p>
 * 物流账册备案申请明细 前端控制器
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Controller
@RequestMapping("/ems_bws/emsBwsDt")
@CrossOrigin
public class EmsBwsDtController extends BaseController {
    @Autowired
    private IEmsBwsDtService emsBwsDtService;

    /**
     * 新增账册备案申请附件
     *
     * @param emsBwsDt 详细信息
     * @return
     */
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEmsBwsDt(EmsBwsDt emsBwsDt) {
        AjaxResult ajaxResult;
        try {
            //使用工具类生成id
            String uId = UUIDGenerator.getUUID();
            if (StringUtil.isEmpty(uId)) {
                //失败
                return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
            }
            emsBwsDt.setUid(uId);
            boolean flag = emsBwsDtService.insert(emsBwsDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsDt);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addEmsBwsDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id删除
     *
     * @param id uid主键
     * @return
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (emsBwsDtService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByEmsBwsDt(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<EmsBwsDt>();
        entityWrapper.in("UID", ids);
        if (emsBwsDtService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEmsBwsDt(EmsBwsDt emsBwsDt) {
        if (null == emsBwsDt.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (emsBwsDtService.updateById(emsBwsDt)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */

    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        EmsBwsDt emsBwsDt = emsBwsDtService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsDt);
    }

    /**
     * 查询账册备案申请附件列表
     *
     * @param emsBwsDt
     * @return 账册备案申请附件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsDts(EmsBwsDt emsBwsDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按商品序号排序
                page.setAsc(true);
                page.setOrderByField("gdsSeqno");  // 排序
            }
            Page<EmsBwsDt> emsBwsDtPage = emsBwsDtService.selectEmsBwsDtList(page, emsBwsDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsDtPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsDts()--error", e);
        }
        return ajaxResult;
    }
}

    