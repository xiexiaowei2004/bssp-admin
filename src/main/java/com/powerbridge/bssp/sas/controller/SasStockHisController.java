package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasStockHis;
import com.powerbridge.bssp.sas.service.ISasStockHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：SasStockHisController
 * 类描述：出入库单历史记录表 控制器
 * 创建人：xuzuotao
 * 创建时间：2017年6月1日  下午10:59:20
 */
@Controller
@RequestMapping("/sas/sasStockHis")
@CrossOrigin
public class SasStockHisController extends BaseController {
    @Autowired
    private ISasStockHisService sasStockHisService;

    /**
     * @param sasStockHis
     * @return AjaxResult
     * @throws
     * @Description: 出入库单历史记录表页面:(列表查询)
     */
    //@RequiresPermissions("SasStockHis:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasStockHisList(SasStockHis sasStockHis) {
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
            BsspUtil.filterCopEnt(sasStockHis, null); //过滤非本企业的数据
            Page<SasStockHis> sasStockHisList = sasStockHisService.selectBySasStockHis(page, sasStockHis);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockHisList.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showSasStockHisList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("SasStockHis:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasStockHis(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasStockHis sasStockHis = sasStockHisService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockHis);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editSasStockHis()--err", e);
        }
        return ajaxResult;
    }

}