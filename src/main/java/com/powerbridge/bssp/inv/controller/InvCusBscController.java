package com.powerbridge.bssp.inv.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.inv.entity.*;
import com.powerbridge.bssp.inv.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@Controller
@RequestMapping("/inv/cus")
@CrossOrigin
public class InvCusBscController extends BaseController {
	@Autowired
    private IInvCusBscService invCusBscService;
    @Autowired
    private IInvCusDtService invCusDtService;
    @Autowired
    private IInvCusDtSimpleService invCusDtSimpleService;
    @Autowired
    private IInvCusDecBscService invCusDecBscService;
    @Autowired
    private IInvCusDecDtService invCusDecDtService;
	@Autowired
    private IInvCusAcmpFormDtService invCusAcmpFormDtService;

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 进入保税核注清单管理页面:(列表查询)
     */
//    @RequiresPermissions("invBsc:list:view")
    @RequestMapping(value = "/invCusBsc/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvCusBscList(InvCusBsc invCusBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            BsspUtil.filterCopEnt(invCusBsc, null);
            Page<InvCusBsc> invBscPage = invCusBscService.selectByInvCusBsc(page, invCusBsc);
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
    @RequestMapping(value = "/invCusDt/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvImgList(InvCusDt invCusDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            Page<InvCusDt> invImgPage = invCusDtService.selectByInvCusDt(page,invCusDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invImgPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvImgList()--error", e);
        }
        return ajaxResult;
    }


    @RequestMapping(value = "/invCusDtSimple/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvImgList(InvCusDtSimple invCusDtSimple) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            Page<InvCusDtSimple> invCusDtSimplePage = invCusDtSimpleService.selectByInvCusDtSimple(page,invCusDtSimple);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invCusDtSimplePage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvImgList()--error", e);
        }
        return ajaxResult;
    }



    @RequestMapping(value = "/invCusDeclare/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvExgList(InvCusDecDt invCusDecDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            // Page<InvExg> invExgPage = invExgService.selectPage(page, entityWrapper);
            Page<InvCusDecDt> invDeclareBillPage = invCusDecDtService.selectByCusDeclare(page,invCusDecDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invDeclareBillPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvExgList()--error", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/invCusBsc/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvCusBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvCusBsc invCusBsc = invCusBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invCusBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvBsc()--err", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/invCusBsc/list/getStockDt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getSasStockDt(String seqNoList) {
        AjaxResult ajaxResult = null;
        if (StringUtil.isNotEmpty(seqNoList)) {
            try {
                EntityWrapper<InvCusDt> entityWrapper = new EntityWrapper<InvCusDt>();
                entityWrapper.in("SEQ_NO", seqNoList);
                List<InvCusDt> list = invCusDtService.selectList(entityWrapper);
                if (list.size() > 0) {
                    ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, list);
                } else {
                    ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL,null);
                }
            } catch (Exception e) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL,null);
                logger.error("getSasStockDt()--err", e);
            }
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/invCusDt/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvCusDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvCusDt invCusDt = invCusDtService.selectById(id);
            // invImg.setGdsSeqno(invImgService.getMaxSeqno(invImg.getSeqNo()));; //获取最大商品序号
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invCusDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvImg()--err", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/invCusDtSimple/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvCusDtSimple(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvCusDtSimple invCusDtSimple = invCusDtSimpleService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invCusDtSimple);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvImg()--err", e);
        }
        return ajaxResult;
    }

}
