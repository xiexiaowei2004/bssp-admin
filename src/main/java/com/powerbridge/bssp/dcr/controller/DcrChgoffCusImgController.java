package com.powerbridge.bssp.dcr.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.dcr.entity.DcrChgoffCusImg;
import com.powerbridge.bssp.dcr.service.IDcrChgoffCusImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 账册报核料件正式表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
@Controller
@RequestMapping("/dcr/dcrChgoffCusImg")
@CrossOrigin
public class DcrChgoffCusImgController extends BaseController {

    @Autowired
    private IDcrChgoffCusImgService dcrChgoffCusImgService;
    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrChgoffCusImg:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrChgoffCusImgList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<DcrChgoffCusImg> dcrChgoffCusImgList = dcrChgoffCusImgService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffCusImgList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrChgoffCusImgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrChgoffCusImg:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrChgoffCusImg(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffCusImg dcrChgoffCusImg = dcrChgoffCusImgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffCusImg);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrChgoffCusImg()--err", e);
        }
        return ajaxResult;
    }

}
