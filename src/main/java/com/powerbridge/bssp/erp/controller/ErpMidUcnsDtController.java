package com.powerbridge.bssp.erp.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.erp.entity.ErpMidUcnsDt;
import com.powerbridge.bssp.erp.service.IErpMidUcnsDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：ErpMidDtImgController
 * 类描述：企业中间表料件记录接口实现
 * 创建人：lindapeng
 * 创建时间：2017/6/2 19:49
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/erp/erpMidUcnsDt")
@CrossOrigin
public class ErpMidUcnsDtController extends BaseController {
    @Autowired
    private IErpMidUcnsDtService erpMidUcnsDtService;
    /**
     //* @param erpMidDtExg
     * @return AjaxResult
     * @throws
     * @Description: 从中间表取数
     */
    //@RequiresPermissions("ErpMidUcnsDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showList() {
        AjaxResult ajaxResult = null;
        EntityWrapper entityWrapper = new EntityWrapper();
        try {
            List<ErpMidUcnsDt> erpMidUcnsDtList = erpMidUcnsDtService.selectList(entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpMidUcnsDtList); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("getErpMidUcnsDtList()--error", e);
        }
        return ajaxResult;
    }
	
}
