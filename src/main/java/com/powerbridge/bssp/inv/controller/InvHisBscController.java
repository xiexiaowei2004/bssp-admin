package com.powerbridge.bssp.inv.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.inv.entity.*;
import com.powerbridge.bssp.inv.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@Controller
@RequestMapping("/inv/his")
@CrossOrigin
public class InvHisBscController extends BaseController {
    @Autowired
    IInvHisBscService invHisBscService;
    @Autowired
    IInvHisDtService invHisDtService;
    @Autowired
    IInvHisDtSimpleService invHisDtSimpleService;
    @Autowired
    IInvHisDecBscService invHisDecBscService;
    @Autowired
    IInvHisDecDtService invHisDecDtService;
    @Autowired
    IInvHisAcmpFormDtService invHisAcmpFormDtService;

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 进入保税核注清单管理页面:(列表查询)
     */
//    @RequiresPermissions("invBsc:list:view")
    @RequestMapping(value = "/invHisBsc/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvCusBscList(InvHisBsc invHisBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            BsspUtil.filterCopEnt(invHisBsc, null);
            Page<InvCusBsc> invBscPage = invHisBscService.selectByInvHisBsc(page, invHisBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invBscPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvBscList()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 进入保税核注清单料件管理页面:(列表查询)
     */
//    @RequiresPermissions("invImg:list:view")
    @RequestMapping(value = "/invHisDt/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvImgList(InvHisDt invHisDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            Page<InvCusDt> invImgPage = invHisDtService.selectByInvHisDt(page,invHisDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invImgPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvImgList()--error", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/invHisDtSimple/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvImgList(InvHisDtSimple invHisDtSimple) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            Page<InvHisDtSimple> invHisDtSimplePage = invHisDtSimpleService.selectByInvHisDtSimple(page,invHisDtSimple);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invHisDtSimplePage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvImgList()--error", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/invHisDeclare/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvHisDeclareList(InvHisDecDt invHisDecDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            // Page<InvExg> invExgPage = invExgService.selectPage(page, entityWrapper);
            Page<InvHisDecDt> invDeclareBillPage = invHisDecDtService.selectByHisDeclare(page,invHisDecDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invDeclareBillPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvExgList()--error", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/invHisBsc/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvCusBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvHisBsc invHisBsc = invHisBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invHisBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvBsc()--err", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/invHisDt/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvHisDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvHisDt invHisDt = invHisDtService.selectById(id);
            // invImg.setGdsSeqno(invImgService.getMaxSeqno(invImg.getSeqNo()));; //获取最大商品序号
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invHisDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvImg()--err", e);
        }
        return ajaxResult;
    }


    @RequestMapping(value = "/invHisDtSimple/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvHisDtSimple(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvHisDtSimple invHisDtSimple = invHisDtSimpleService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invHisDtSimple);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvImg()--err", e);
        }
        return ajaxResult;
    }
}
