package com.powerbridge.bssp.edi.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.edi.entity.EdiBusinessCheckReceipt;
import com.powerbridge.bssp.edi.service.IEdiBusinessCheckReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 宋轲 on 2017/6/2.
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/edi/checkReceipt")
public class EdiBusinessCheckReceiptController extends BaseController {

    @Autowired
    private IEdiBusinessCheckReceiptService ediBusinessCheckReceiptService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult selectByPage(EdiBusinessCheckReceipt entity){
        AjaxResult result = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
        EntityWrapper<EdiBusinessCheckReceipt> wrapper = new EntityWrapper<EdiBusinessCheckReceipt>();

        if (StringUtil.isNotEmpty(entity.getEtpsPreentNo())){
            wrapper.eq("ETPS_PREENT_NO",entity.getEtpsPreentNo());//企业预录入编号
        }
        if (StringUtil.isNotEmpty(entity.getBusinessId())){
            wrapper.eq("BUSINESS_ID",entity.getBusinessId());//业务编号
        }

        try {
            Page<EdiBusinessCheckReceipt> pages = ediBusinessCheckReceiptService.selectPage(getPage(), wrapper);

            result = json(MessageConstants.BSSP_STATUS_SUCCESS, pages.getRecords(), pages.getTotal());
        }catch (Exception e){
            logger.error("selectByPage error: " + e);
        }
        return result;
    }
}
