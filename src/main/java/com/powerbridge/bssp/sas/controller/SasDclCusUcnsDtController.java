package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclCusUcnsDt;
import com.powerbridge.bssp.sas.service.ISasDclCusUcnsDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 业务申报单耗正式表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Controller
@RequestMapping("/sas/sasDclCusUcnsDt")
@CrossOrigin
public class SasDclCusUcnsDtController extends BaseController {

    @Autowired
    private ISasDclCusUcnsDtService sasDclCusUcnsDtService;
    /**
     * @param sasDclCusUcnsDt
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclCusUcnsDtList(SasDclCusUcnsDt sasDclCusUcnsDt) {
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
            }
            Page<SasDclCusUcnsDt> sasDclCusUcnsDtList = sasDclCusUcnsDtService.selectByList(page, sasDclCusUcnsDt);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclCusUcnsDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclCusUcnsDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("sasDclBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasDclCusUcnsDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclCusUcnsDt sasDclCusUcnsDt = sasDclCusUcnsDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclCusUcnsDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclCusUcnsDt()--err", e);
        }
        return ajaxResult;
    }
}
