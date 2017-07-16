package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdExchangeRate;
import com.powerbridge.bssp.cod_std.service.ICodStdExchangeRateService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclCusBsc;
import com.powerbridge.bssp.sas.entity.SasDclCusDt;
import com.powerbridge.bssp.sas.entity.SasStockBsc;
import com.powerbridge.bssp.sas.entity.SasStockDt;
import com.powerbridge.bssp.sas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：SasStockDtController
 * 类描述：出入库商品表 控制器
 * 创建人：xuzuotao
 * 创建时间：2017年6月1日  下午10:59:20
 */
@Controller
@RequestMapping("/sas/sasStockDt")
@CrossOrigin
public class SasStockDtController extends BaseController {
    @Autowired
    private ISasStockDtService sasStockDtService;

    @Autowired
    private ICodStdExchangeRateService codStdExchangeRateService;

    @Autowired
    private ISasStockBscService sasStockBscService;


    @Autowired
    private ISasDclCusDtService sasDclCusDtService;


    @Autowired
    private ISasDclCusBscService sasDclCusBscService;

    @Autowired
    private ISasStockCusDtService sasStockCusDtService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasStockDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasStockDtList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<SasStockDt> SasStockDtList = sasStockDtService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, SasStockDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showSasStockDtList()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param sasStockDt
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
//    @RequiresPermissions("sasStockDt:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasStockDt(SasStockDt sasStockDt) {
        double total = 0;
        AjaxResult ajaxResult = null;
        EntityWrapper<SasStockDt> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("SAS_STOCK_SEQNO", sasStockDt.getSasStockSeqno());
        entityWrapper.eq("SEQ_NO", sasStockDt.getSeqNo());
        SasStockDt s = sasStockDtService.selectOne(entityWrapper);
        if (s != null && !s.getUid().equals(sasStockDt.getUid())) {
            return result(MessageConstants.BSSP_STATUS_SAS_STOCK_SEQNO);
        }
        if (sasStockDt.getNetWt() != null && sasStockDt.getGrossWt() != null) {
            if (Integer.parseInt(sasStockDt.getNetWt().toString()) > Integer.parseInt(sasStockDt.getGrossWt().toString())) {
                return result(MessageConstants.BSSP_STATUS_ENDPRD_NET_WT);
            }
        }

        ajaxResult = check(sasStockDt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        EntityWrapper<SasStockBsc> entity = new EntityWrapper<>();
        entity.eq("SEQ_NO", sasStockDt.getSeqNo());
        SasStockBsc sasStockBsc = sasStockBscService.selectOne(entity);

        try {
            double sasStockDtNum = sasStockDtService.selectSum(sasStockBsc.getSasDclNo(), sasStockDt.getSasDclSeqno());
            double sasStockCusDtNum = sasStockCusDtService.selectSum(sasStockBsc.getSasDclNo(), sasStockDt.getSasDclSeqno());
            double num = sasStockDtNum + sasStockCusDtNum;
            double dclQty = sasStockDt.getDclQty() == null ? 0 : sasStockDt.getDclQty().doubleValue();
            total = num + dclQty;
            //获取申报表中申报商品的数量
            /*EntityWrapper entityWrapper1 = new EntityWrapper();
            entityWrapper1.eq("SAS_DCL_NO", sasStockBsc.getSasDclNo());
            SasDclCusBsc sasDclCusBsc = sasDclCusBscService.selectOne(entityWrapper1);
            EntityWrapper entityWrapper2 = new EntityWrapper();
            entityWrapper2.eq("SEQ_NO", sasDclCusBsc.getSeqNo());
            entityWrapper2.eq("SAS_DCL_SEQNO",sasStockDt.getSasDclSeqno());
            entityWrapper2.setSqlSelect("SUM(DCL_QTY) AS DCLQTY");
            SasDclCusDt sasDclCusDt = sasDclCusDtService.selectOne(entityWrapper2);
            double dclQtyTotal=0;
            if(sasDclCusDt!=null){
                dclQtyTotal= sasDclCusDt.getDclQty() == null ? 0 : sasDclCusDt.getDclQty().doubleValue();
            }*/
            double dclQtyTotal = sasStockDt.getTotal() == null ? 0 : sasStockDt.getTotal().doubleValue();
            if (total > dclQtyTotal) {
                return result(MessageConstants.BSSP_STATUS_SAS_STOCK_DtTotal);
            }

            sasStockDt.setUid(UUIDGenerator.getUUID());
            Boolean flag = sasStockDtService.insert(sasStockDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockDt);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addSasStockDt()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param dclCurrcd
     * @return AjaxResult
     * @throws
     * @Description: 获取汇率
     */
//    @RequiresPermissions("sasStockDt:list:add")
    @RequestMapping(value = "/list/getRate", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getRate(String dclCurrcd) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper<CodStdExchangeRate> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("CODE", dclCurrcd);
            CodStdExchangeRate codStdExchangeRate = codStdExchangeRateService.selectOne(entityWrapper);
            if (codStdExchangeRate != null) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdExchangeRate);
            } else {
                ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, codStdExchangeRate);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("getRate()-error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("sasStockDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasStockDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasStockDt sasStockDt = sasStockDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editSasStockDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasStockDt
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("sasStockDt:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasStockDt(SasStockDt sasStockDt) {
        double total = 0;
        AjaxResult ajaxResult = null;
        EntityWrapper<SasStockDt> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("SEQ_NO", sasStockDt.getSeqNo());
        entityWrapper.eq("SAS_STOCK_SEQNO", sasStockDt.getSasStockSeqno());
        SasStockDt s = sasStockDtService.selectOne(entityWrapper);
        if (s != null && !s.getUid().equals(sasStockDt.getUid())) {
            return result(MessageConstants.BSSP_STATUS_SAS_STOCK_SEQNO);
        }
        ajaxResult = check(sasStockDt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        EntityWrapper<SasStockBsc> entity = new EntityWrapper<>();
        entity.eq("SEQ_NO", sasStockDt.getSeqNo());
        SasStockBsc sasStockBsc = sasStockBscService.selectOne(entity);

        EntityWrapper<SasDclCusBsc> wrapper = new EntityWrapper<>();
        wrapper.eq("SAS_DCL_NO", sasStockBsc.getSasDclNo());
        SasDclCusBsc sasDclCusBsc = sasDclCusBscService.selectOne(wrapper);

        EntityWrapper<SasDclCusDt> entityWrapper2 = new EntityWrapper<>();
        entityWrapper2.eq("SEQ_NO", sasDclCusBsc.getSeqNo());
        entityWrapper2.eq("SAS_DCL_SEQNO", sasStockDt.getSasDclSeqno());
        entityWrapper2.setSqlSelect("SUM(DCL_QTY) AS DCLQTY");
        SasDclCusDt sasDclCusDt = sasDclCusDtService.selectOne(entityWrapper2);
        double dclQtyTotal = 0;
        if (sasDclCusDt != null) {
            dclQtyTotal = sasDclCusDt.getDclQty() == null ? 0 : sasDclCusDt.getDclQty().doubleValue();
        }
        SasStockDt dt = sasStockDtService.selectById(sasStockDt.getUid());
        try {
            double sasStockCusDtNum = sasStockCusDtService.selectSum(sasStockBsc.getSasDclNo(), sasStockDt.getSasDclSeqno());
            double num = sasStockDtService.selectSum(sasStockBsc.getSasDclNo(), sasStockDt.getSasDclSeqno()) - dt.getDclQty().doubleValue() + sasStockCusDtNum;
            total = num + sasStockDt.getDclQty().doubleValue();
            if (total > dclQtyTotal) {
                return result(MessageConstants.BSSP_STATUS_SAS_STOCK_DtTotal);
            }
            Boolean flag = sasStockDtService.updateById(sasStockDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockDt);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateSasStockDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("sasStockDt:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasStockDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            boolean flag = sasStockDtService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteSasStockDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("sasStockDt:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasStockDtByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = sasStockDtService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteSasStockDtByList()--err", e);
        }
        return ajaxResult;
    }
}
