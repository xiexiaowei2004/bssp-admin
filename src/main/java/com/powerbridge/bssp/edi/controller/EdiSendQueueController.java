package com.powerbridge.bssp.edi.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.edi.entity.EdiSendQueue;
import com.powerbridge.bssp.edi.service.IEdiSendQueueService;
import com.powerbridge.bssp.edi.xml.prod.TransTypeEnum;
import com.powerbridge.bssp.sas.entity.SasDragcarBsc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 宋轲 on 2017/6/1.
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/edi/ediSendQueue")
public class EdiSendQueueController extends BaseController {

    @Autowired
    private IEdiSendQueueService ediSendQueueService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult selectByPage(EdiSendQueue entity) {
        AjaxResult result = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
        EntityWrapper<EdiSendQueue> wrapper = new EntityWrapper<EdiSendQueue>();
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
            Page<EdiSendQueue> pages = ediSendQueueService.selectPage(getPage(), wrapper);

            result = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
        }catch (Exception e){
            logger.error("selectByPage error: " + e);
        }
        return result;
    }
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        EdiSendQueue ediSendQueue = ediSendQueueService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, ediSendQueue);
    }
    /**
     * 根据id修改
     */

    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEdiSendQueueList(EdiSendQueue ediSendQueue) {
        if (null == ediSendQueue.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (ediSendQueueService.updateById(ediSendQueue)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    
    @RequestMapping(value = "/emsList",method = {RequestMethod.POST, RequestMethod.GET})
    public AjaxResult selectByEdiSendQueue() {
        AjaxResult result = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
		//1.查询所有Status为“1”要处理的队列表数据
        EntityWrapper entityWrapper = new EntityWrapper<EdiSendQueue>();
        entityWrapper.eq("Status", "Y");//处理标识
        List<EdiSendQueue> ediSendQueueList = ediSendQueueService.selectList(entityWrapper);
        try {
            result = json(MessageConstants.BSSP_STATUS_SUCCESS, ediSendQueueList);
        } catch (Exception e) {
			result = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("ediSendQueueList()--error", e);
        }
    	return result;
    }
    
    @RequestMapping(value = "/emsSave",method = {RequestMethod.POST, RequestMethod.GET})
    public AjaxResult saveByEdiSendQueue(EdiSendQueue entity) {
        AjaxResult ajaxResult = null;
    	if(StringUtil.isEmpty(entity.getAreaCode())){
			return ajaxResult = json(MessageConstants.BSSP_STATUS_EMS_AREACODE_ERROR, entity.getAreaCode());
		}
    	if(StringUtil.isEmpty(entity.getDocType())){
			return ajaxResult = json(MessageConstants.BSSP_STATUS_EMS_DOCTYPE_ERROR, entity.getDocType());
		}
    	if(StringUtil.isEmpty(entity.getBizType())){
			return ajaxResult = json(MessageConstants.BSSP_STATUS_EMS_BIZTYPE_ERROR, entity.getBizType());
		}
    	if(StringUtil.isEmpty(entity.getSeqNo())){
			return ajaxResult = json(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR, entity.getSeqNo());
		}
    	try {

            EdiSendQueue ediSendQueue = new EdiSendQueue();
            ediSendQueue.setUid(UUIDGenerator.getUUID());
            ediSendQueue.setAreaCode(entity.getAreaCode());
            ediSendQueue.setBizType(entity.getBizType());
            ediSendQueue.setDocType(entity.getDocType());
            ediSendQueue.setStatus(TransTypeEnum.N);
            ediSendQueue.setSeqNo(entity.getSeqNo());
            ediSendQueue.setRemarks(entity.getRemarks());
            ediSendQueue.setChgTmsCnt(entity.getChgTmsCnt());
            ediSendQueueService.insert(ediSendQueue);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediSendQueue);
        } catch (Exception e) {
			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("ediSendQueueList()--error", e);
        }
    	return ajaxResult;
    }
}
