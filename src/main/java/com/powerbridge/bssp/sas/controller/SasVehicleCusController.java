package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasVehicleCus;
import com.powerbridge.bssp.sas.service.ISasVehicleCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：SasVehicleCusController
 * 类描述：车辆信息备案申请控制器
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日  下午10:59:20
 */
@Controller
@RequestMapping("/sas/sasVehicleCus")
@CrossOrigin
public class SasVehicleCusController extends BaseController {
    @Autowired
    private ISasVehicleCusService sasVehicleCusService;


    /**
     * @param sasVehicleCus
     * @return AjaxResult
     * @throws
     * @Description: 进入车辆备案正式表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasVehicleCus:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showVehicleCusList(SasVehicleCus sasVehicleCus) {
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
            BsspUtil.filterCopEnt(sasVehicleCus, null); //过滤非本企业的数据
            Page<SasVehicleCus> vehicleCusList = sasVehicleCusService.selectByVehicleCus(page, sasVehicleCus);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, vehicleCusList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showVehicleCusList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 查阅页面
     */
    //@RequiresPermissions("sasVehicleCus:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editVehicleCus(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasVehicleCus sasVehicleCus = sasVehicleCusService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasVehicleCus);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editVehicleCus()--err", e);
        }
        return ajaxResult;
    }
}