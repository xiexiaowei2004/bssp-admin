package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasStockCus;
import com.powerbridge.bssp.sas.entity.SasStockCusDt;
import com.powerbridge.bssp.sas.service.ISasStockCusDtService;
import com.powerbridge.bssp.sas.service.ISasStockCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：SasStockCusController
 * 类描述：出入库单正式表 控制器
 * 创建人：xuzuotao
 * 创建时间：2017年6月1日  下午10:59:20
 */
@Controller
@RequestMapping("/sas/sasStockCus")
@CrossOrigin
public class SasStockCusController extends BaseController {
    @Autowired
    private ISasStockCusService sasStockCusService;

    @Autowired
    private ISasStockCusDtService sasStockCusDtService;
    /**
     * @param sasStockCus
     * @return AjaxResult
     * @throws
     * @Description: 出入库单正式表管理页面:(列表查询)
     */
    //@RequiresPermissions("SasStockCus:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasStockCusList(SasStockCus sasStockCus) {
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
            BsspUtil.filterCopEnt(sasStockCus, null); //过滤非本企业的数据
            Page<SasStockCus> sasStockCusList = sasStockCusService.selectBySasStockCus(page, sasStockCus);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockCusList.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showSasStockCusList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("SasStockCus:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasStockCus(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasStockCus sasStockCus = sasStockCusService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockCus);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editSasStockCus()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param seqNoList
     * @return AjaxResult
     * @throws
     * @Description: 根据单据编号获取所有商品信息
     */
    @RequestMapping(value = "/list/getStockCusDt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getSasStockCusDt(String seqNoList) {
        AjaxResult ajaxResult = null;
        if (StringUtil.isNotEmpty(seqNoList)) {
            try {
                EntityWrapper<SasStockCusDt> entityWrapper = new EntityWrapper<>();
                entityWrapper.in("SEQ_NO", seqNoList);
                List<SasStockCusDt> sasStockCusDtList = sasStockCusDtService.selectList(entityWrapper);
                if (sasStockCusDtList.size() > 0) {
                    ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockCusDtList,sasStockCusDtList.size());
                } else {
                    ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, null);
                }
            } catch (Exception e) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, null);
                logger.error("getSasStockCusDt()--err", e);
            }
        }
        return ajaxResult;
    }
}