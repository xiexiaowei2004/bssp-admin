package com.powerbridge.bssp.ems_bws.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisBsc;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author jokylao
 * @since 2017-07-06
 */

@Controller
@RequestMapping("/ems_bws/emsBwsHisBsc")
@CrossOrigin
public class EmsBwsHisBscController extends BaseController {
    @Autowired
    private IEmsBwsHisBscService emsBwsHisBscService;
    /**
     * 根据id查询账册备案申请信息
     *
     * @param id 主键id
     * @return 账册备案申请信息
     */
    //@RequiresPermissions("emsBwsHisBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsHisBscsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsBwsHisBsc emsBwsHisBsc = emsBwsHisBscService.selectById(id);
            if (StringUtil.isNotEmpty(emsBwsHisBsc.getSeqNo())) {
                EntityWrapper entityWrapper = new EntityWrapper();
                entityWrapper.eq("SEQ_NO", emsBwsHisBsc.getSeqNo());
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsHisBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsHisBscsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请列表
     */
    //@RequiresPermissions("emsBwsHisBsc:list")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsHisBscs(EmsBwsHisBsc emsBwsHisBsc) {
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
            BsspUtil.filterCopEnt(emsBwsHisBsc, null);
            Page<EmsBwsHisBsc> emsHisBscPage = emsBwsHisBscService.selectEmsBwsHisBscList(page, emsBwsHisBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsHisBscPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsHisBscs()--error", e);
        }
        return ajaxResult;
    }
}