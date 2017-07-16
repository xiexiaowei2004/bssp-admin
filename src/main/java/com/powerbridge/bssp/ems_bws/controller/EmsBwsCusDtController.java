package com.powerbridge.bssp.ems_bws.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsCusBscService;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsCusDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 物流账册正式表明细 前端控制器
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Controller
@RequestMapping("/ems_bws/emsBwsCusDt")
@CrossOrigin
public class EmsBwsCusDtController extends BaseController {
    @Autowired
    private IEmsBwsCusDtService emsBwsCusDtService;
    @Autowired
    private IEmsBwsCusBscService emsBwsCusBscService;

    /**
     * 查询账册备案申请附件列表
     *
     * @return 账册备案申请附件列表
//     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsCusDts(EmsBwsCusDt emsBwsCusDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按商品序号排序
                page.setAsc(true);
                page.setOrderByField("gdsSeqno");  // 排序
            }
            Page<EmsBwsCusDt> emsBwsCusDtPage = emsBwsCusDtService.selectEmsBwsCusDtList(page, emsBwsCusDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsCusDtPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsCusDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id查询账册备案随附单证信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsCusDtsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsBwsCusDt emsBwsCusDt = emsBwsCusDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsCusDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsCusDtsById()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 查询账册备案正式表明细信息列表
     *
     * @return 账册备案正式表明细信息列表
     */
    @RequestMapping(value = "/list/selectByBwsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsDtByBwsNo(EmsBwsCusDt emsBwsCusDt) {
        AjaxResult ajaxResult = null;
        if (StringUtil.isEmpty(emsBwsCusDt.getBwsNo())) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_EMSNO_ERROR);
            return ajaxResult;
        }
        try {
            //根据账册编号查找单据编号
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("BWS_NO", emsBwsCusDt.getBwsNo());
            EmsBwsCusBsc emsBwsCusBsc = emsBwsCusBscService.selectOne(entityWrapper);
            String seqNo = emsBwsCusBsc.getSeqNo();
            if (StringUtil.isEmpty(seqNo)) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
                return ajaxResult;
            }
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按商品序号排序
                page.setAsc(true);
                page.setOrderByField("gdsSeqno");  // 排序
            }
            Page<EmsBwsCusDt> emsBwsCusDtPage = emsBwsCusDtService.selectEmsBwsCusDtList(page, emsBwsCusDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsCusDtPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsDtByBwsNo()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 获取最大的商品序号
     * @param emsBwsCusDt
     * @return 商品序号
     */
    @RequestMapping(value = "/list/selectMaxGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectMaxGdsSeqno(EmsBwsCusDt emsBwsCusDt){
        AjaxResult ajaxResult = null;
        BigDecimal maxGdsSeqno= BigDecimal.valueOf(1);
        try{
            EntityWrapper<EmsBwsCusDt> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("BWS_NO", emsBwsCusDt.getBwsNo());
            entityWrapper.orderBy("GDS_SEQNO",false);
            List<EmsBwsCusDt> emsBwsCusDtByList=emsBwsCusDtService.selectList(entityWrapper);
            if (!emsBwsCusDtByList.isEmpty()){
                maxGdsSeqno = emsBwsCusDtByList.get(0).getGdsSeqno().add(new BigDecimal(1));
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,maxGdsSeqno);
        } catch (Exception e) {
            ajaxResult = setErrorJson("获取商品序号失败");
            logger.error("selectMaxGdsSeqno()--error", e);
        }
        return ajaxResult;
    }
}
