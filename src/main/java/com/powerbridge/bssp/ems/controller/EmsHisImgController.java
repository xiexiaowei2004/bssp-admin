package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsHisImg;
import com.powerbridge.bssp.ems.service.IEmsHisImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsHisImgController
 * 类描述：账册备案历史表料件控制层
 * 创建人：jokylao
 * 创建时间：2017/7/5
 */
@Controller
@RequestMapping("/ems/emsHisImg")
@CrossOrigin
public class EmsHisImgController extends BaseController {
    @Autowired
    private IEmsHisImgService emsHisImgService;

    /**
     * 根据id查询账册备案正式表料件信息
     *
     * @param id uid主键
     * @return 账册备案正式表料件信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisImgsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsHisImg emsPutrecImg = emsHisImgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisImgsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案正式表料件列表
     *
     * @return 账册备案正式表料件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsHisImgs(EmsHisImg emsHisImg) {
        AjaxResult ajaxResult = null;
        if (StringUtil.isEmpty(emsHisImg.getSeqNo())) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
            return ajaxResult;
        }
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
            Page<EmsHisImg> emsPutrecImgPage = emsHisImgService.selectEmsHisImgList(page, emsHisImg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsHisImgList()--error", e);
        }
        return ajaxResult;
    }
}
