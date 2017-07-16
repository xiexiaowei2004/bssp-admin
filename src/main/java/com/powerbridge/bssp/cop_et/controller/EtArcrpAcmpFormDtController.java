package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtArcrpAcmpFormDt;
import com.powerbridge.bssp.cop_et.service.IEtArcrpAcmpFormDtService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EtArcrpAcmpFormDtController
 * 类描述：企业联网档案库成品接口实现
 * 创建人：willChen
 * 创建时间：2017/5/22 16:18
 * 修改人：willChen
 * 修改时间：2017/5/22 16:18
 */
@Controller
@RequestMapping("/cop_et/etArcrpAcmpFormDt")
@CrossOrigin
public class EtArcrpAcmpFormDtController extends BaseController {
    @Autowired
    private IEtArcrpAcmpFormDtService etArcrpAcmpFormDtService;

    /**
     * 新增数据
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEtArcrpAcmpFormDt(EtArcrpAcmpFormDt etArcrpAcmpFormDt) {
        AjaxResult ajaxResult;
        try {
            //使用工具类生成id
            String uId = UUIDGenerator.getUUID();
            etArcrpAcmpFormDt.setUid(uId);

            if (etArcrpAcmpFormDtService.insert(etArcrpAcmpFormDt)) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpAcmpFormDt);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addEtArcrpAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (etArcrpAcmpFormDtService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据条件删除
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:delete")
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<EtArcrpAcmpFormDt>();
        entityWrapper.in("UID", ids);
        if (etArcrpAcmpFormDtService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEtArcrpAcmpFormDt(EtArcrpAcmpFormDt etArcrpAcmpFormDt) {
        if (null == etArcrpAcmpFormDt.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (etArcrpAcmpFormDtService.updateById(etArcrpAcmpFormDt)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        EtArcrpAcmpFormDt etArcrpAcmpFormDt = etArcrpAcmpFormDtService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpAcmpFormDt);
    }

    /**
     * 根据条件查询
     *
     * @param etArcrpAcmpFormDt 查询条件
     * @param pageSize          页面容量
     * @param pageNumber        第几页
     * @param sort              排序列名
     * @param sortOrder         排序规则
     */
    @RequiresPermissions("etArcrpAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtArcrpAcmpFormDtList(EtArcrpAcmpFormDt etArcrpAcmpFormDt, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtArcrpAcmpFormDt> etArcrpAcmpFormDtPage;
        try {
            // 分页
            etArcrpAcmpFormDtPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etArcrpAcmpFormDtPage.setAsc(super.getOrderSort(sortOrder));
                etArcrpAcmpFormDtPage.setOrderByField(sort);
            } else {
                //默认商品序号排序
                etArcrpAcmpFormDtPage.setOrderByField("A.ACMP_FORM_SEQNO");
            }
            etArcrpAcmpFormDtPage = etArcrpAcmpFormDtService.selectEtArcrpAcmpFormDtList(etArcrpAcmpFormDtPage, etArcrpAcmpFormDt);
        } catch (Exception e) {
            logger.error("selectEtArcrpAcmpFormDtList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpAcmpFormDtPage.getRecords(), etArcrpAcmpFormDtPage.getTotal());
    }
}
