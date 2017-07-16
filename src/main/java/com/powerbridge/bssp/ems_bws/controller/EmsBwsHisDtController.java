package com.powerbridge.bssp.ems_bws.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsHisDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 物流账册正式表明细 前端控制器
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Controller
@RequestMapping("/ems_bws/emsBwsHisDt")
@CrossOrigin
public class EmsBwsHisDtController extends BaseController {
    @Autowired
    private IEmsBwsHisDtService emsBwsHisDtService;

    /**
     * 查询账册备案申请附件列表
     *
     * @return 账册备案申请附件列表
//     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsHisDts(EmsBwsHisDt emsBwsHisDt) {
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
            Page<EmsBwsHisDt> emsBwsHisDtPage = emsBwsHisDtService.selectEmsBwsHisDtList(page, emsBwsHisDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsHisDtPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsHisDtList()--error", e);
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
    public AjaxResult selectEmsBwsHisDtsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsBwsHisDt emsBwsHisDt = emsBwsHisDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsHisDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsHisDtsById()--err", e);
        }
        return ajaxResult;
    }
}
