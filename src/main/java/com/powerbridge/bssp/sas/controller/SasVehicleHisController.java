package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasVehicleHis;
import com.powerbridge.bssp.sas.service.ISasVehicleHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：SasVehicleHisController
 * 类描述：车辆信息备案历史表控制器
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日  下午10:59:20
 */
@Controller
@RequestMapping("/sas/sasVehicleHis")
@CrossOrigin
public class SasVehicleHisController extends BaseController {
    @Autowired
    private ISasVehicleHisService sasVehicleHisService;


    /**
     * @param sasVehicleHis
     * @return AjaxResult
     * @throws
     * @Description: 进入车辆备案申请管理页面:(列表查询)
     */
    //@RequiresPermissions("sasVehicleHis:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showVehicleHisList(SasVehicleHis sasVehicleHis) {
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
            BsspUtil.filterCopEnt(sasVehicleHis, null); //过滤非本企业的数据
            Page<SasVehicleHis> vehicleHisList = sasVehicleHisService.selectByVehicleHis(page, sasVehicleHis);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, vehicleHisList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showVehicleHisList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 查阅页面
     */
    //@RequiresPermissions("sasVehicleHis:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editVehicleHis(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasVehicleHis sasVehicleHis = sasVehicleHisService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasVehicleHis);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editVehicleHis()--err", e);
        }
        return ajaxResult;
    }
}