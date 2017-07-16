package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.service.ISasFixedCardHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.sas.entity.SasFixedCardHis;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luohongsu
 * @since 2017-07-05
 */
@Controller
@RequestMapping("/sas/sasFixedCardHis")
@CrossOrigin
public class SasFixedCardHisController extends BaseController {
    @Autowired
    private ISasFixedCardHisService sasFixedCardHisService;

    /**
     * @param sasFixedCardHis
     * @return AjaxResult
     * @throws
     * @Description: 进入长期卡备案申请管理页面:(列表查询)
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showFixedCardHisList(SasFixedCardHis sasFixedCardHis) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按录入日期倒排序
                page.setAsc(false);
                page.setOrderByField("decTime");  // 排序
            }
            BsspUtil.filterCopEnt(sasFixedCardHis,null); //过滤非本企业的数据s
            Page<SasFixedCardHis> FixedCardBscList = sasFixedCardHisService.selectByFixedCardHis(page, sasFixedCardHis);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, FixedCardBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showFixedCardBscList()--error", e);
        }
        return ajaxResult;
    }



    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    //@RequiresPermissions("sasVehicleBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editVehicleBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasFixedCardHis sasFixedCardHis = sasFixedCardHisService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasFixedCardHis);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editVehicleBsc()--err", e);
        }
        return ajaxResult;
    }
}
