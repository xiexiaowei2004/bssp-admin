package com.powerbridge.bssp.inv.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.inv.entity.InvHisAcmpFormDt;
import com.powerbridge.bssp.inv.service.IInvHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@Controller
@RequestMapping("/inv/invHisAcmpFormDt")
@CrossOrigin
public class InvHisAcmpFormDtController extends BaseController {
    @Autowired
    IInvHisAcmpFormDtService invHisAcmpFormDtService;


    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrAdjaccAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvCusAcmpFormDtList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }
            Page<InvHisAcmpFormDt> invAcmpFormDtList = invHisAcmpFormDtService.selectByInvHisAcmpList(page,seqNo);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invAcmpFormDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrAdjaccAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }
}
