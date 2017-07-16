package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsHisExg;
import com.powerbridge.bssp.ems.service.IEmsHisExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsHisExgController
 * 类描述：账册备案历史表成品接口实现
 * 创建人：jokylao
 * 创建时间：2017/7/5
 */
@Controller
@RequestMapping("/ems/emsHisExg")
@CrossOrigin
public class EmsHisExgController extends BaseController {
    @Autowired
    private IEmsHisExgService emsHisExgService;

    /**
     * 根据id查询账册备案正式表成品信息
     *
     * @param id uid主键
     * @return 账册备案正式表成品信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisExgsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsHisExg emsPutrecExg = emsHisExgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecExg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisExgsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案正式表成品列表
     *
     * @return 账册备案正式表成品列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisExgs(EmsHisExg emsHisExg) {
        AjaxResult ajaxResult = null;
        if (StringUtil.isEmpty(emsHisExg.getSeqNo())) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
            return ajaxResult;
        }
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
            Page<EmsHisExg> emsPutrecExgPage = emsHisExgService.selectEmsHisExgList(page, emsHisExg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecExgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisExgList()--error", e);
        }
        return ajaxResult;
    }
}
