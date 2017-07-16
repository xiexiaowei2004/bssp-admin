package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsHisAcmpFormDt;
import com.powerbridge.bssp.ems.service.IEmsHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 项目名称：bssp-admin
 * 类名称：EmsHisAcmpFormDtController
 * 类描述：账册历史表附件接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：willChen
 * 修改时间：2017/5/17 9:49
 */
@Controller
@RequestMapping("/ems/EmsHisAcmpFormDt")
@CrossOrigin
public class EmsHisAcmpFormDtController extends BaseController {
    @Autowired
    private IEmsHisAcmpFormDtService emsHisAcmpFormDtService;

    /**
     * 查询账册历史表附件列表
     *
     * @return 账册历史表附件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisAcmpFormDts(EmsHisAcmpFormDt EmsHisAcmpFormDt) {
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
            Page<EmsHisAcmpFormDt> EmsHisAcmpFormDtPage = emsHisAcmpFormDtService.selectEmsHisAcmpFormDtList(page, EmsHisAcmpFormDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, EmsHisAcmpFormDtPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }
    /**
     * 根据id查询账册历史表随附单证信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisAcmpFormDtsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsHisAcmpFormDt EmsHisAcmpFormDt = emsHisAcmpFormDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, EmsHisAcmpFormDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisAcmpFormDtsById()--err", e);
        }
        return ajaxResult;
    }
}
