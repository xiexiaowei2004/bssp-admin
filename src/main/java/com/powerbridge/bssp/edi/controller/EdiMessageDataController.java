package com.powerbridge.bssp.edi.controller;

import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.edi.entity.EdiMessageData;
import com.powerbridge.bssp.edi.service.IEdiMessageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 宋轲 on 2017/6/1.
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/edi/ediMessageData")
public class EdiMessageDataController extends BaseController {

    @Autowired
    private IEdiMessageDataService ediMessageDataService;

    
    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult view(String uid) {

        AjaxResult ajaxResult = null;
        try {
        	EdiMessageData ediMessageData = ediMessageDataService.selectById(uid);
        	//对数据进行格式化，让数据显示有层次
            /*
        	StringWriter out = new StringWriter();
        	SAXReader saxReader = new SAXReader();
        	Document doc = saxReader.read(new ByteArrayInputStream(ediMessageData.getBigData()));
        	OutputFormat format = OutputFormat.createPrettyPrint();
         	format.setEncoding("UTF-8");
         	XMLWriter writer = new XMLWriter(out, format);
         	writer.setEscapeText(false);
         	writer.write(doc);
         	writer.close();
         	String str=out.toString();
        	*/

        	 //不进行格式化 就是简单几句
        	 String str=new String(ediMessageData.getBigData(),"UTF-8");

        	ediMessageData.setBigDataStr(str);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediMessageData);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("view()--err", e);
        }

        return ajaxResult;
    }
    
}
