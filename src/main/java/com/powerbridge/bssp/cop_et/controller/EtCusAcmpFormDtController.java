package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtCusAcmpFormDt;
import com.powerbridge.bssp.cop_et.service.IEtCusAcmpFormDtService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EtCusAcmpFormDtController
 * 类描述：企业联网档案库成品接口实现
 * 创建人：willChen
 * 创建时间：2017/5/22 16:18
 * 修改人：willChen
 * 修改时间：2017/5/22 16:18
 */
@Controller
@RequestMapping("/cop_et/etCusAcmpFormDt")
@CrossOrigin
public class EtCusAcmpFormDtController extends BaseController {
    @Autowired
    private IEtCusAcmpFormDtService etCusAcmpFormDtService;

    /**
     * 根据id查询
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        EtCusAcmpFormDt etCusAcmpFormDt = etCusAcmpFormDtService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etCusAcmpFormDt);
    }

    /**
     * 根据条件查询
     *
     * @param etCusAcmpFormDt 查询条件
     * @param pageSize        页面容量
     * @param pageNumber      第几页
     * @param sort            排序列名
     * @param sortOrder       排序规则
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtCusAcmpFormDtList(EtCusAcmpFormDt etCusAcmpFormDt, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtCusAcmpFormDt> etCusAcmpFormDtPage;
        try {
            // 分页
            etCusAcmpFormDtPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etCusAcmpFormDtPage.setAsc(super.getOrderSort(sortOrder));
                etCusAcmpFormDtPage.setOrderByField(sort);
            } else {
                //默认商品序号排序
                etCusAcmpFormDtPage.setOrderByField("A.ACMP_FORM_SEQNO");
            }
            etCusAcmpFormDtPage = etCusAcmpFormDtService.selectEtCusAcmpFormDtList(etCusAcmpFormDtPage, etCusAcmpFormDt);
        } catch (Exception e) {
            logger.error("selectEtCusAcmpFormDtList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etCusAcmpFormDtPage.getRecords(), etCusAcmpFormDtPage.getTotal());
    }
}
