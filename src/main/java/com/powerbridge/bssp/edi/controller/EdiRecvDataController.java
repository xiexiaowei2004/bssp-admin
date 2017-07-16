package com.powerbridge.bssp.edi.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.edi.entity.EdiRecvData;
import com.powerbridge.bssp.edi.service.IEdiRecvDataService;
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
 * Created by HQGK-004 on 2017/6/15.
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/edi/ediRecvData")
public class EdiRecvDataController extends BaseController {
    @Autowired
    private IEdiRecvDataService ediRecvDataService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult selectByPage(EdiRecvData entity) {
        AjaxResult result = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL);
        EntityWrapper<EdiRecvData> wrapper = new EntityWrapper<EdiRecvData>();
        if (StringUtil.isNotEmpty(entity.getDocId())){
            wrapper.like("DOC_ID",entity.getDocId());//编号查询
        }
        if (StringUtil.isNotEmpty(entity.getAreaCode())){
            wrapper.like("AREA_CODE",entity.getAreaCode());//监管场所模糊查询
        }

        //根据排序规则进行排序
        String sort = getParameter("sort");
        Boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        if (StringUtil.isNotEmpty(sort)) {
            wrapper.orderBy(sort, sortOrder);
        }else {
            wrapper.orderBy("inputerTime", false);
        }
        try {
            Page<EdiRecvData> pages = ediRecvDataService.selectPage(getPage(), wrapper);

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
   /*@RequiresPermissions("EdiRecvData:list:view")*/
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult view(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            EdiRecvData ediRecvData = ediRecvDataService.selectById(id);

            if (ediRecvData.getBigData() != null) {
                //不对结果格式化
                String str = new String(ediRecvData.getBigData(), "UTF-8");
 /*               if (str.startsWith("<?xml") && str.endsWith(">")) {
                    //如果错误信息为.xml时，总是用<?xml开头，用>结尾，所以这样区分错误信息是否为xml,为xml时，需要格式化

                    //对数据进行格式化，让数据显示有层次
                    StringWriter out = new StringWriter();
                    SAXReader saxReader = new SAXReader();
                    Document doc = saxReader.read(new ByteArrayInputStream(ediRecvData.getBigData()));
                    OutputFormat format = OutputFormat.createPrettyPrint();
                    *//*format.setEncoding("UTF-8");*//*
                    XMLWriter writer = new XMLWriter(out, format);
                    writer.setEscapeText(false);
                    writer.write(doc);
                    writer.close();
                    str = out.toString();

                }*/
                ediRecvData.setBigDataStr(str);
            }

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediRecvData);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("view()--err", e);
        }

        return ajaxResult;
    }
}
