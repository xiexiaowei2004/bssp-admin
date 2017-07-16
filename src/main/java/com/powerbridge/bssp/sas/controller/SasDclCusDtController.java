package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclCusDt;
import com.powerbridge.bssp.sas.entity.SasDclDt;
import com.powerbridge.bssp.sas.service.ISasDclCusDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 业务申报商品正式表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Controller
@RequestMapping("/sas/sasDclCusDt")
@CrossOrigin
public class SasDclCusDtController extends BaseController {

    @Autowired
    private ISasDclCusDtService sasDclCusDtService;

    /**
     * @param sasDclCusDt
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclDtList(SasDclCusDt sasDclCusDt) {
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
            Page<SasDclCusDt> sasDclCusDtPage = sasDclCusDtService.selectByList(page, sasDclCusDt);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclCusDtPage.getRecords(), page.getTotal()); // 格式要返回的数据
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
            SasDclCusDt sasDclCusDt = sasDclCusDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclCusDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclDt()--err", e);
        }
        return ajaxResult;
    }
}
