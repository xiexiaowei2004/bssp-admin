package com.powerbridge.bssp.edi.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.edi.entity.EdiSendLog;
import com.powerbridge.bssp.edi.entity.EdiSendQueue;
import com.powerbridge.bssp.edi.service.IEdiSendLogService;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

/**
 * Created by 宋轲 on 2017/6/1.
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/edi/ediSendLog")
public class EdiSendLogController extends BaseController {

    @Autowired
    private IEdiSendLogService ediSendLogService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult selectByPage(EdiSendLog entity) {
        AjaxResult result = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
        EntityWrapper<EdiSendLog> wrapper = new EntityWrapper<EdiSendLog>();
        if (StringUtil.isNotEmpty(entity.getAreaCode())){
            wrapper.like("AREA_CODE",entity.getAreaCode());
        }
        if (StringUtil.isNotEmpty(entity.getDocType())){
            wrapper.eq("DOC_TYPE",entity.getDocType());
        }
        if (StringUtil.isNotEmpty(entity.getBizType())){
            wrapper.eq("BIZ_TYPE",entity.getBizType());
        }
        if (StringUtil.isNotEmpty(entity.getSeqNo())){
            wrapper.eq("SEQ_NO",entity.getSeqNo());
        }
        //根据排序规则进行排序
        String sort = getParameter("sort");
        Boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        if (StringUtil.isNotEmpty(sort)) {
            wrapper.orderBy(sort, sortOrder);
        } else {
            wrapper.orderBy("UPDATE_TIME", false);
        }
        try {
            Page<EdiSendLog> pages = ediSendLogService.selectPage(getPage(), wrapper);

            result = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
        }catch (Exception e){
            logger.error("selectByPage error: " + e);
        }
        return result;
    }
    
    
    
    /**
     * @param id
     * @return
     * @throws
     * @Description：查询
     */
//    @RequiresPermissions("EdiMessageLog:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult view(@PathVariable String id) {
      /*  AjaxResult ajaxResult = null;
            EdiSendLog ediSendLog = ediSendLogService.selectById(id);

        ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediSendLog);
        return ajaxResult;
    }*/
    EdiSendLog ediSendLog = ediSendLogService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, ediSendLog);
}
    /**
     * 根据id修改
     */

    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEdiSendLogList(EdiSendLog ediSendLog) {
        if (null == ediSendLog.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (ediSendLogService.updateById(ediSendLog)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
}
