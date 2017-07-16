package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclHisBsc;
import com.powerbridge.bssp.sas.service.ISasDclHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 业务申报历史表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Controller
@RequestMapping("/sas/sasDclHisBsc")
@CrossOrigin
public class SasDclHisBscController extends BaseController {
    @Autowired
    private ISasDclHisBscService sasDclHisBscService;

    /**
     * @param sasDclHisBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclBsc:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclBscList(SasDclHisBsc sasDclHisBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }else {
                page.setAsc(false);
                page.setOrderByField("decTime");
            }
            BsspUtil.filterCopEnt(sasDclHisBsc, null);
            Page<SasDclHisBsc> sasDclBscList = sasDclHisBscService.selectByList(page, sasDclHisBsc);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclBscList()--error", e);
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
    public AjaxResult editSasDclBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclHisBsc sasDclHisBsc = sasDclHisBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclHisBsc);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclBsc()--err", e);
        }
        return ajaxResult;
    }
}
