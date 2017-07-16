package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsHisBsc;
import com.powerbridge.bssp.ems.service.IEmsHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
 * 项目名称：bssp-admin
 * 类名称：EmsHisBscController
 * 类描述：账册备案申请接口实现
 * 创建人：jokylao
 * 创建时间：2017/7/4 21:23
 */
@Controller
@RequestMapping("/ems/emsHisBsc")
@CrossOrigin
public class EmsHisBscController extends BaseController {
    @Autowired
    private IEmsHisBscService emsHisBscService;

    /**
     * 根据id查询账册备案申请信息
     *
     * @param id 主键id
     * @return 账册备案申请信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisBscsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsHisBsc emsHisBsc = emsHisBscService.selectById(id);
            if (StringUtil.isNotEmpty(emsHisBsc.getSeqNo())) {
                EntityWrapper entityWrapper = new EntityWrapper();
                entityWrapper.eq("SEQ_NO", emsHisBsc.getSeqNo());
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsHisBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisBscsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请列表
     */
    //@RequiresPermissions("emsHisBsc:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisBscs(EmsHisBsc emsHisBsc) {
        if(StringUtil.isEmpty(emsHisBsc.getEmsTypecd())){
            return result(MessageConstants.BSSP_STATUS_FAIL);
        }
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
            BsspUtil.filterCopEnt(emsHisBsc, null);
            Page<EmsHisBsc> emsHisBscPage = emsHisBscService.selectEmsHisBscList(page, emsHisBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsHisBscPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisBscList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请列表
     */
    //@RequiresPermissions("emsHisBsc:list:view")
    @RequestMapping(value = "/list/selectByEmsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisBscByEmsNo(EmsHisBsc emsHisBscParam) {
        AjaxResult ajaxResult;
        try {

            if (StringUtil.isEmpty(emsHisBscParam.getEmsNo())) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_EMSNO_ERROR);
                return ajaxResult;
            }
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("EMS_NO", emsHisBscParam.getEmsNo());
            entityWrapper.ge("FINISH_VALID_DATE", DateUtil.today());
            if (!StringUtil.isEmpty(emsHisBscParam.getEmsTypecd())){
                entityWrapper.eq("EMS_TYPECD",emsHisBscParam.getEmsTypecd());
            }
            EmsHisBsc emsHisBsc = emsHisBscService.selectOne(entityWrapper);
            if (emsHisBsc != null) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsHisBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisBscByEmsNo()--err", e);
        }
        return ajaxResult;
    }
}
