package com.powerbridge.bssp.dcr.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccHisAcmpFormDt;
import com.powerbridge.bssp.dcr.service.IDcrAdjaccHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 账册核销随附单证明细历史表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Controller
@RequestMapping("/dcr/dcrAdjaccHisAcmpFormDt")
@CrossOrigin
public class DcrAdjaccHisAcmpFormDtController extends BaseController {
    @Autowired
    private IDcrAdjaccHisAcmpFormDtService dcrAdjaccHisAcmpFormDtService;

    /**
     * @param dcrAdjaccHisAcmpFormDt
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrAdjaccHisAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrAdjaccHisAcmpFormDtList(DcrAdjaccHisAcmpFormDt dcrAdjaccHisAcmpFormDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<DcrAdjaccHisAcmpFormDt> dcrAdjaccHisAcmpFormDtList = dcrAdjaccHisAcmpFormDtService.selectByList(page, dcrAdjaccHisAcmpFormDt);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrAdjaccHisAcmpFormDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrAdjaccHisAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrAdjaccHisAcmpFormDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrAdjaccHisAcmpFormDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrAdjaccHisAcmpFormDt dcrAdjaccHisAcmpFormDt = dcrAdjaccHisAcmpFormDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrAdjaccHisAcmpFormDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrAdjaccHisAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }
}
