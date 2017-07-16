package com.powerbridge.bssp.dcr.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.dcr.entity.DcrChgoffHisBsc;
import com.powerbridge.bssp.dcr.service.IDcrChgoffHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 账册报核历史表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Controller
@RequestMapping("/dcr/dcrChgoffHisBsc")
@CrossOrigin
public class DcrChgoffHisBscController extends BaseController {


    @Autowired
    private IDcrChgoffHisBscService dcrChgoffHisBscService;

    /**
     * @param dcrChgoffHisBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrChgoffHisBsc:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrChgoffHisBscList(DcrChgoffHisBsc dcrChgoffHisBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            BsspUtil.filterCopEnt(dcrChgoffHisBsc,null);   // 过滤企业数据
            Page<DcrChgoffHisBsc> dcrChgoffHisBscList = dcrChgoffHisBscService.selectByList(page, dcrChgoffHisBsc);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffHisBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrChgoffHisBscList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrChgoffHisBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrChgoffHisBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffHisBsc dcrChgoffHisBsc = dcrChgoffHisBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffHisBsc);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrChgoffHisBsc()--err", e);
        }
        return ajaxResult;
    }
}
