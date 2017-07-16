package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsHisBsc;
import com.powerbridge.bssp.ems.entity.EmsHisUcnsDt;
import com.powerbridge.bssp.ems.entity.EmsPutrecUcnsDt;
import com.powerbridge.bssp.ems.service.IEmsHisBscService;
import com.powerbridge.bssp.ems.service.IEmsHisUcnsDtService;
import com.powerbridge.bssp.ems.service.IEmsPutrecUcnsDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsHisUcnsDtController
 * 类描述：账册备案正式表单损耗接口实现
 * 创建人：jokylao
 * 创建时间：2017/7/5
 */
@Controller
@RequestMapping("/ems/emsHisUcnsDt")
@CrossOrigin
public class EmsHisUcnsDtController extends BaseController {
    @Autowired
    private IEmsHisUcnsDtService emsHisUcnsDtService;

    /**
     * 根据id查询账册备案正式表单损耗信息
     *
     * @param id uid主键
     * @return 账册备案正式表单损耗信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisUcnsDtsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsHisUcnsDt emsPutrecImg = emsHisUcnsDtService.selectByUid(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisUcnsDtsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案正式表单损耗列表
     *
     * @return 账册备案正式表单损耗列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisUcnsDts(EmsHisUcnsDt emsHisUcnsDt) {
        AjaxResult ajaxResult = null;
        if(StringUtil.isEmpty(emsHisUcnsDt.getSeqNo())){
            ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
            return  ajaxResult;
        }
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按序号排序
                page.setAsc(true);
                page.setOrderByField("ucnsSeqno");  // 排序
            }
            Page<EmsHisUcnsDt> emsPutrecImgPage = emsHisUcnsDtService.selectEmsHisUcnsDtList(page, emsHisUcnsDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisUcnsDtList()--error", e);
        }
        return ajaxResult;
    }
}
