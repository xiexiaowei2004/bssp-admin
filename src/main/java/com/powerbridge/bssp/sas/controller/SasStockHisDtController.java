package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasStockHisDt;
import com.powerbridge.bssp.sas.service.ISasStockHisDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：SasStockHisDtController
 * 类描述：出入库商品历史记录表 控制器
 * 创建人：xuzuotao
 * 创建时间：2017年6月1日  下午10:59:20
 */
@Controller
@RequestMapping("/sas/sasStockHisDt")
@CrossOrigin
public class SasStockHisDtController extends BaseController {
    @Autowired
    private ISasStockHisDtService sasStockHisDtService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("SasStockHisDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasStockHisDtList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<SasStockHisDt> SasStockHisDtList = sasStockHisDtService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, SasStockHisDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showSasStockHisDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("SasStockHisDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasStockHisDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasStockHisDt SasStockHisDt = sasStockHisDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, SasStockHisDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editSasStockHisDt()--err", e);
        }
        return ajaxResult;
    }

}
