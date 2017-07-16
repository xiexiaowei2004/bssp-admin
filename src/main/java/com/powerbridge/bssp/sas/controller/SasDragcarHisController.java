package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDragcarHis;
import com.powerbridge.bssp.sas.service.ISasDragcarHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 扡卡历史表 前端控制器
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-05
 */
@Controller
@RequestMapping("/sas/sasDragcarHis")
@CrossOrigin
public class SasDragcarHisController extends BaseController {
    @Autowired
    private ISasDragcarHisService sasDragcarHisService;
    /**
     * 根据条件查询
     */

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectSasDragcarHiss(SasDragcarHis sasDragcarHis) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按录入日期倒排序
                page.setAsc(false);
                page.setOrderByField("DEC_TIME");  // 排序
            }
            BsspUtil.filterCopEnt(sasDragcarHis, null);
            Page<SasDragcarHis> sasDragcarHisPage = sasDragcarHisService.selectSasDragcarHisList(page, sasDragcarHis);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDragcarHisPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectSasDragcarHiss()--error", e);
        }
        return ajaxResult;
    }
    /**
     * 根据id查询
     */

    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        SasDragcarHis sasDragcarHis = sasDragcarHisService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, sasDragcarHis);
    }
}
