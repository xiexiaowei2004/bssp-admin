package com.powerbridge.bssp.entry.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.entry.entity.EntryHead;
import com.powerbridge.bssp.entry.service.IEntryHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

/**
 * <p>
 * 报关单表头 前端控制器
 * </p>
 *
 * @author yeyingdong
 * @since 2017-07-10
 */
@Controller
@RequestMapping("/entry/entryHead")
@CrossOrigin
public class EntryHeadController extends BaseController {

    @Autowired
    private IEntryHeadService entryHeadService;

    /**
     * 根据条件查询
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEntryHead(EntryHead entryHead) {
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
                page.setOrderByField("DCL_TIME");  // 排序
            }
            BsspUtil.filterCopEnt(entryHead, null);
            Page<EntryHead> entryHeadPage = entryHeadService.selectEntryHead(page, entryHead);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entryHeadPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEntryHead()--error", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasDragcarBscList(EntryHead entryHead) {
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        AjaxResult ajaxResult = null;
        try {
            entryHead.setUid(uId);//
            entryHead.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            boolean flag = entryHeadService.insert(entryHead);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, entryHead);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addEntryHead()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        System.out.println(id);
        try{
            if (entryHeadService.deleteById(id)) {
                //成功
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
            return result(MessageConstants.BSSP_STATUS_FAIL);
        }catch (Exception e){
            e.printStackTrace();
            return result(MessageConstants.BSSP_STATUS_FAIL);
        }
    }
}
