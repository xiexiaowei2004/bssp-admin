package com.powerbridge.bssp.dcr.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.dcr.entity.DcrChgoffCusInvtLt;
import com.powerbridge.bssp.dcr.service.IDcrChgoffCusInvtLtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 账册报核清单正式表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Controller
@RequestMapping("/dcr/dcrChgoffCusInvtLt")
@CrossOrigin
public class DcrChgoffCusInvtLtController extends BaseController {
    @Autowired
    private IDcrChgoffCusInvtLtService dcrChgoffCusInvtLtService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrChgoffCusInvtLt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrChgoffCusInvtLtList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<DcrChgoffCusInvtLt> dcrChgoffCusInvtLtList = dcrChgoffCusInvtLtService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffCusInvtLtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrChgoffCusInvtLtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrChgoffCusInvtLt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrChgoffCusInvtLt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffCusInvtLt dcrChgoffCusInvtLt = dcrChgoffCusInvtLtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffCusInvtLt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrChgoffCusInvtLt()--err", e);
        }
        return ajaxResult;
    }
}
