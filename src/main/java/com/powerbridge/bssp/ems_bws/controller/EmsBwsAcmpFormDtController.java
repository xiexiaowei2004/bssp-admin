package com.powerbridge.bssp.ems_bws.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtArcrpAcmpFormDt;

import com.powerbridge.bssp.ems_bws.entity.EmsBwsAcmpFormDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsAcmpFormDtService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;

/**
 * 项目名称：bssp-admin
 * 类名称emsBwsAcmpFormDtController
 * 类描述：账册备案申请附件接口实现

 */
@Controller
@RequestMapping("/ems_bws/emsBwsAcmpFormDt")
@CrossOrigin
public class EmsBwsAcmpFormDtController extends BaseController {

    //获取当前时间
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @Autowired
    private IEmsBwsAcmpFormDtService emsBwsAcmpFormDtService;

    /**
     * 新增账册备案申请附件
     * @param emsBwsAcmpFormDt 详细信息
     * @return
     */
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEmsBwsAcmpFormDt(EmsBwsAcmpFormDt emsBwsAcmpFormDt) {
    	AjaxResult ajaxResult;
        //TODO:需要添加验证,保证该账单下只有三个附件
    	try {
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        if (StringUtil.isEmpty(uId)) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        emsBwsAcmpFormDt.setUid(uId);
        boolean flag = emsBwsAcmpFormDtService.insert(emsBwsAcmpFormDt);
        if (flag) {
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsAcmpFormDt);
        } else {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        }
    } catch (Exception e) {
        ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        logger.error("addEmsBwsAcmpFormDt()--err", e);
    }
    return ajaxResult;
}

    /**
     * 根据id删除账册备案申请附件
     *
     * @param id uid主键
     * @return
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
            if (emsBwsAcmpFormDtService.deleteById(id)) {
                //成功
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
            return result(MessageConstants.BSSP_STATUS_FAIL);
        }
    /**
     * 根据条件删除
     */
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByEmsBwsAcmpFormDt() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = emsBwsAcmpFormDtService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteEmsBwsBscByList()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 根据id修改
     */
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
   public AjaxResult updateEmsBwsAcmpFormDt(EmsBwsAcmpFormDt emsBwsAcmpFormDt) {
        if (null == emsBwsAcmpFormDt.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (emsBwsAcmpFormDtService.updateById(emsBwsAcmpFormDt)) {
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
    	EmsBwsAcmpFormDt emsBwsAcmpFormDt = emsBwsAcmpFormDtService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsAcmpFormDt);
    }

    /**
     * 查询账册备案申请附件列表
     *
     * @param emsBwsAcmpFormDt
     * @return 账册备案申请附件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsAcmpFormDts(EmsBwsAcmpFormDt emsBwsAcmpFormDt) {
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
                 page.setOrderByField("acmpFormSeqno");  // 排序
             }
             Page<EmsBwsAcmpFormDt> emsBwsAcmpFormDtPage = emsBwsAcmpFormDtService.selectEmsBwsAcmpFormDtList(page, emsBwsAcmpFormDt);
             ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsAcmpFormDtPage.getRecords(), page.getTotal());
         } catch (Exception e) {
             ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
             logger.error("selectEmsBwsAcmpFormDtList()--error", e);
         }
         return ajaxResult;
    }
}
