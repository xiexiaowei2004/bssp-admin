package com.powerbridge.bssp.inv.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.inv.entity.InvAcmpFormDt;
import com.powerbridge.bssp.inv.service.IInvAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 保税核注清单随附单证明细表 前端控制器
 * </p>
 *
 * @author zsl
 * @since 2017-06-20
 */
@Controller
@RequestMapping("/inv/invAcmpFormDt")
@CrossOrigin
public class InvAcmpFormDtController extends BaseController {
    @Autowired
    private IInvAcmpFormDtService invAcmpFormDtService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrAdjaccAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvAcmpFormDtList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<InvAcmpFormDt> invAcmpFormDtList = invAcmpFormDtService.selectByInvAcmpList(page,seqNo);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invAcmpFormDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrAdjaccAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param invAcmpFormDt
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
//    @RequiresPermissions("invAcmpFormDt:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addDcrAdjaccAcmpFormDt(InvAcmpFormDt invAcmpFormDt) {
        AjaxResult ajaxResult = null;
      /*  ajaxResult = check(invAcmpFormDt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }*/
        try {
            invAcmpFormDt.setUid(UUIDGenerator.getUUID());
            Boolean flag = invAcmpFormDtService.insert(invAcmpFormDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invAcmpFormDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("invAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrAdjaccAcmpFormDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvAcmpFormDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvAcmpFormDt invAcmpFormDt = invAcmpFormDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invAcmpFormDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editInvAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param invAcmpFormDt
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("dcrAdjaccAcmpFormDt:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateInvAcmpFormDt(InvAcmpFormDt invAcmpFormDt) {
        AjaxResult ajaxResult = null;
     /*   ajaxResult = check(dcrAdjaccAcmpFormDt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }*/
        try {
            Boolean flag = invAcmpFormDtService.updateById(invAcmpFormDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invAcmpFormDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateInvAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 删除操作
     */
//    @RequiresPermissions("dcrAdjaccAcmpFormDt:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteInvAcmpFormDtByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = invAcmpFormDtService.deleteById(idList);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteInvAcmpFormDtByList()--err", e);
        }
        return ajaxResult;
    }


    @RequestMapping(value = "/list/getAcmpFormSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getAcmpFormSeqno(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            int acmpFormSeqno = 1;
            EntityWrapper<InvAcmpFormDt> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.orderBy("ACMP_FORM_SEQNO", false);
            List<InvAcmpFormDt> invAcmpFormDtList = invAcmpFormDtService.selectList(entityWrapper);
            if (!invAcmpFormDtList.isEmpty()) {
                acmpFormSeqno = invAcmpFormDtList.get(0).getAcmpFormSeqno() + 1;
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, acmpFormSeqno);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getAcmpFormSeqno()--err", e);
        }
        return ajaxResult;
    }

    private String checkEndprdSeqno(String seqNo) {
        String errorInfo = "";

        EntityWrapper<InvAcmpFormDt> entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("COUNT(ACMP_FORM_SEQNO) AS ACMPFORMSEQNO");
        entityWrapper.eq("SEQ_NO", seqNo);
        entityWrapper.groupBy("ACMP_FORM_SEQNO");
        entityWrapper.having("COUNT(ACMP_FORM_SEQNO)>1");
        List<InvAcmpFormDt> dcrAdjaccAcmpFormDts = invAcmpFormDtService.selectList(entityWrapper);
        if (!dcrAdjaccAcmpFormDts.isEmpty()) {
            errorInfo = "窗口中存在随附单证序号重复的记录，请检查";
        }
        return errorInfo;
    }
}
