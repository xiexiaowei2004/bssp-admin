package com.powerbridge.bssp.dcr.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccCusAcmpFormDt;
import com.powerbridge.bssp.dcr.service.IDcrAdjaccCusAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 账册核销随附单证明细正式表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Controller
@RequestMapping("/dcr/dcrAdjaccCusAcmpFormDt")
@CrossOrigin
public class DcrAdjaccCusAcmpFormDtController extends BaseController {
    @Autowired
    private IDcrAdjaccCusAcmpFormDtService dcrAdjaccCusAcmpFormDtService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrAdjaccCusAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrAdjaccCusAcmpFormDtList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<DcrAdjaccCusAcmpFormDt> dcrAdjaccCusAcmpFormDtList = dcrAdjaccCusAcmpFormDtService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrAdjaccCusAcmpFormDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrAdjaccCusAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrAdjaccCusAcmpFormDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrAdjaccCusAcmpFormDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrAdjaccCusAcmpFormDt dcrAdjaccCusAcmpFormDt = dcrAdjaccCusAcmpFormDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrAdjaccCusAcmpFormDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrAdjaccCusAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }
}
