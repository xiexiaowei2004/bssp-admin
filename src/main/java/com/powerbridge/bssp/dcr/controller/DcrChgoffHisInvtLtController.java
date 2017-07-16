package com.powerbridge.bssp.dcr.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.dcr.entity.DcrChgoffHisInvtLt;
import com.powerbridge.bssp.dcr.service.IDcrChgoffHisInvtLtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 账册报核清单历史表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Controller
@RequestMapping("/dcr/dcrChgoffHisInvtLt")
@CrossOrigin
public class DcrChgoffHisInvtLtController extends BaseController {
    @Autowired
    private IDcrChgoffHisInvtLtService dcrChgoffHisInvtLtService;

    /**
     * @param dcrChgoffHisInvtLt
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrChgoffHisInvtLt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrChgoffHisInvtLtList(DcrChgoffHisInvtLt dcrChgoffHisInvtLt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<DcrChgoffHisInvtLt> dcrChgoffHisInvtLtList = dcrChgoffHisInvtLtService.selectByList(page, dcrChgoffHisInvtLt);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffHisInvtLtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrChgoffHisInvtLtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrChgoffHisInvtLt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrChgoffHisInvtLt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffHisInvtLt dcrChgoffHisInvtLt = dcrChgoffHisInvtLtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffHisInvtLt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrChgoffHisInvtLt()--err", e);
        }
        return ajaxResult;
    }
}
