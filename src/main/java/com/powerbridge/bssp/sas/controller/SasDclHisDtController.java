package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclHisDt;
import com.powerbridge.bssp.sas.service.ISasDclHisDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 业务申报商品历史表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Controller
@RequestMapping("/sas/sasDclHisDt")
@CrossOrigin
public class SasDclHisDtController extends BaseController {

    @Autowired
    private ISasDclHisDtService sasDclHisDtService;

    /**
     * @param sasDclHisDt
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclDtList(SasDclHisDt sasDclHisDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {     // 默认申报序号升序
                page.setAsc(true);
                page.setOrderByField("sasDclSeqno");
            }
            Page<SasDclHisDt> sasDclHisDtPage = sasDclHisDtService.selectByList(page, sasDclHisDt);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclHisDtPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("sasDclDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasDclDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclHisDt sasDclHisDt = sasDclHisDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclHisDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclDt()--err", e);
        }
        return ajaxResult;
    }
}
