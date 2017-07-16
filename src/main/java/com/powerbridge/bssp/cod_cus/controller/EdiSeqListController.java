package com.powerbridge.bssp.cod_cus.controller;


import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqList;
import com.powerbridge.bssp.cod_cus.service.IEdiSeqListService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtCusExg;


@Controller
@RequestMapping("/cod_cus/ediSeqList")
@CrossOrigin
public class EdiSeqListController extends BaseController {

    @Autowired
    private IEdiSeqListService ediSeqListService;
    /**
     * 新增数据
     */
    
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEdiSeqList(EdiSeqList ediSeqList) {
        AjaxResult ajaxResult = null;
        try {
            //使用工具类生成id
            String uId = UUIDGenerator.getUUID();
            if ("".equals(uId) && null == uId) {
                //失败
                return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
            }
            ediSeqList.setUid(uId);

            boolean flag = ediSeqListService.insert(ediSeqList);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediSeqList);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addEdiSeqList()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 根据id删除
     */
  
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (ediSeqListService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("ediSeqList:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByEdiSeqList(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<EdiSeqList>();
        entityWrapper.in("UID", ids);
        if (ediSeqListService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    /**
     * 根据id修改
     */
   
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEdiSeqList(EdiSeqList ediSeqList) {
        if (null == ediSeqList.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (ediSeqListService.updateById(ediSeqList)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    /**
     * 根据id查询
     */
    
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
    	EdiSeqList ediSeqList = ediSeqListService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, ediSeqList);
    }

    /**
     * 根据条件查询
     */
   
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByEdiSeqList(EdiSeqList ediSeqList, Integer pageSize, Integer pageNumber) {
        EntityWrapper<EdiSeqList> entityWrapper = new EntityWrapper<>();
    
        if (StringUtil.isNotEmpty(ediSeqList.getEdiSeqUid())) {
        	 entityWrapper.like("EDI_SEQ_UID", ediSeqList.getEdiSeqUid());
        } else {
        	return  result(MessageConstants.BSSP_STATUS_FAIL);
        }
       
        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<EdiSeqList> ediSeqLists = ediSeqListService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, ediSeqLists.getRecords(), page.getTotal());
    }

 
}
