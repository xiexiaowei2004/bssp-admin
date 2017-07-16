package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsCusAcmpFormDt;
import com.powerbridge.bssp.ems.entity.EmsCusBsc;
import com.powerbridge.bssp.ems.service.IEmsCusAcmpFormDtService;
import com.powerbridge.bssp.ems.service.IEmsCusBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 项目名称：bssp-admin
 * 类名称：EmsCusAcmpFormDtController
 * 类描述：账册备案申请附件接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：willChen
 * 修改时间：2017/5/17 9:49
 */
@Controller
@RequestMapping("/ems/emsCusAcmpFormDt")
@CrossOrigin
public class EmsCusAcmpFormDtController extends BaseController {
    @Autowired
    private IEmsCusAcmpFormDtService emsCusAcmpFormDtService;
    @Autowired
    private IEmsCusBscService emsCusBscService;

    /**
     * 查询账册备案申请附件列表
     *
     * @return 账册备案申请附件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusAcmpFormDts(EmsCusAcmpFormDt emsCusAcmpFormDt) {
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
                page.setOrderByField("acmpFormSeqno");  // 排序
            }
            Page<EmsCusAcmpFormDt> emsCusAcmpFormDtPage = emsCusAcmpFormDtService.selectEmsCusAcmpFormDtList(page, emsCusAcmpFormDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusAcmpFormDtPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }
    /**
     * 根据账册编号查询账册备案申请附件列表
     *
     * @return 账册备案申请附件列表
     */
    @RequestMapping(value = "/list/selectByEmsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusAcmpFormDtByEmsNo(EmsCusAcmpFormDt emsCusAcmpFormDt) {
        AjaxResult ajaxResult = null;
        try {
            //根据账册编号查找单据编号
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("EMS_NO", emsCusAcmpFormDt.getEmsNo());
            EmsCusBsc emsCusBsc = emsCusBscService.selectOne(entityWrapper);
            String seqNo = emsCusBsc.getSeqNo();
            if (StringUtil.isEmpty(seqNo)) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
                return ajaxResult;
            }
            emsCusAcmpFormDt.setSeqNo(seqNo);
            emsCusAcmpFormDt.setEmsNo("");
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按商品序号排序
                page.setAsc(true);
                page.setOrderByField("acmpFormSeqno");  // 排序
            }
            Page<EmsCusAcmpFormDt> emsCusAcmpFormDtPage = emsCusAcmpFormDtService.selectEmsCusAcmpFormDtList(page, emsCusAcmpFormDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusAcmpFormDtPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusAcmpFormDtByEmsNo()--error", e);
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
    public AjaxResult selectEmsCusAcmpFormDtsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsCusAcmpFormDt emsCusAcmpFormDt = emsCusAcmpFormDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusAcmpFormDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusAcmpFormDtsById()--err", e);
        }
        return ajaxResult;
    }
}
