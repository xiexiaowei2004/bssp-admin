package com.powerbridge.bssp.approval.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.approval.entity.AppSysWorklog;
import com.powerbridge.bssp.approval.service.IAppSysWorklogService;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：CopComController
 * 类描述：审批流程控制器
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日  上午09:59:00
 */
@Controller
@RequestMapping("/approval/appSysWorklog")
@CrossOrigin
public class AppSysWorklogController extends BaseController {

    @Autowired
    private IAppSysWorklogService appSysWorklogService;

    /**
     * @param
     * @return EntityWrapper
     * @throws
     * @Description: 查询方法:(分页)
     */
    private EntityWrapper getEntityWrapper() {
        String docNo = getParameter("docNo");
        EntityWrapper entityWrapper = new EntityWrapper<AppSysWorklog>();
        if (StringUtil.isNotEmpty(docNo)) {
            entityWrapper.eq("DOC_NO", docNo);  // 通过单据编号查询
        }
        entityWrapper.orderBy("PROC_DATE", false);  // 处理时间 倒序
        return entityWrapper;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 经营资料列表页面:(列表查询)
     */
//    @RequiresPermissions("copBuiness:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showAppSysWorklogList() {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper();  //查询调用方法
            List<AppSysWorklog> appSysWorklogList = appSysWorklogService.selectList(entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, appSysWorklogList); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showAppSysWorklogList()--error", e);
        }
        return ajaxResult;
    }

}
