package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtHisAcmpFormDt;
import com.powerbridge.bssp.cop_et.service.IEtHisAcmpFormDtService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EtHisAcmpFormDtController
 * 类描述：企业联网档案库成品接口实现
 * 创建人：willChen
 * 创建时间：2017/5/22 16:18
 * 修改人：willChen
 * 修改时间：2017/5/22 16:18
 */
@Controller
@RequestMapping("/cop_et/etHisAcmpFormDt")
@CrossOrigin
public class EtHisAcmpFormDtController extends BaseController {
    @Autowired
    private IEtHisAcmpFormDtService etHisAcmpFormDtService;

    /**
     * 根据id查询
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        EtHisAcmpFormDt etHisAcmpFormDt = etHisAcmpFormDtService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etHisAcmpFormDt);
    }

    /**
     * 根据条件查询
     *
     * @param etHisAcmpFormDt 查询条件
     * @param pageSize        页面容量
     * @param pageNumber      第几页
     * @param sort            排序列名
     * @param sortOrder       排序规则
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtHisAcmpFormDtList(EtHisAcmpFormDt etHisAcmpFormDt, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtHisAcmpFormDt> etHisAcmpFormDtPage;
        try {
            // 分页
            etHisAcmpFormDtPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etHisAcmpFormDtPage.setAsc(super.getOrderSort(sortOrder));
                etHisAcmpFormDtPage.setOrderByField(sort);
            } else {
                //默认商品序号排序
                etHisAcmpFormDtPage.setOrderByField("A.ACMP_FORM_SEQNO");
            }
            etHisAcmpFormDtPage = etHisAcmpFormDtService.selectEtHisAcmpFormDtList(etHisAcmpFormDtPage, etHisAcmpFormDt);
        } catch (Exception e) {
            logger.error("selectEtHisAcmpFormDtList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etHisAcmpFormDtPage.getRecords(), etHisAcmpFormDtPage.getTotal());
    }
}
