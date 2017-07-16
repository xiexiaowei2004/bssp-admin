package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclHisAcmpFormDt;
import com.powerbridge.bssp.sas.service.ISasDclHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 业务申报随附单证历史表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Controller
@RequestMapping("/sas/sasDclHisAcmpFormDt")
@CrossOrigin
public class SasDclHisAcmpFormDtController extends BaseController {
    @Autowired
    private ISasDclHisAcmpFormDtService sasDclHisAcmpFormDtService;

    /**
     * @param sasDclHisAcmpFormDt
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclAcmpFormDtList(SasDclHisAcmpFormDt sasDclHisAcmpFormDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<SasDclHisAcmpFormDt> sasDclHisAcmpFormDtPage = sasDclHisAcmpFormDtService.selectByList(page, sasDclHisAcmpFormDt);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclHisAcmpFormDtPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("sasDclAcmpFormDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasDclAcmpFormDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclHisAcmpFormDt sasDclHisAcmpFormDt = sasDclHisAcmpFormDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclHisAcmpFormDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }
}
