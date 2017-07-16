package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsPutrecAcmpFormDt;
import com.powerbridge.bssp.ems.service.IEmsCusAcmpFormDtService;
import com.powerbridge.bssp.ems.service.IEmsPutrecAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;


/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecAcmpFormDtController
 * 类描述：账册备案申请附件接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：willChen
 * 修改时间：2017/5/17 9:49
 */
@Controller
@RequestMapping("/ems/emsPutrecAcmpFormDt")
@CrossOrigin
public class EmsPutrecAcmpFormDtController extends BaseController {
    @Autowired
    private IEmsPutrecAcmpFormDtService emsPutrecAcmpFormDtService;
    @Autowired
    private IEmsCusAcmpFormDtService emsCusAcmpFormDtService;

    /**
     * 新增账册备案申请附件
     * @param emsPutrecAcmpFormDt 详细信息
     * @return
     */
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEmsPutrecAcmpFormDt(EmsPutrecAcmpFormDt emsPutrecAcmpFormDt) {
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        emsPutrecAcmpFormDt.setUid(uId);

        try {
            //执行新增
            if (emsPutrecAcmpFormDtService.insert(emsPutrecAcmpFormDt)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("addEmsPutrecAcmpFormDt()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    /**
     * 新增账册备案申请附件
     * @param emsPutrecAcmpFormDt 详细信息
     * @return
     */
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEmsPutrecAcmpFormDt(EmsPutrecAcmpFormDt emsPutrecAcmpFormDt) {
        //使用工具类生成id
        String uId = emsPutrecAcmpFormDt.getUid();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id不能为空");
        }
        try {
            //执行修改
            if (emsPutrecAcmpFormDtService.updateById(emsPutrecAcmpFormDt)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("updateEmsPutrecAcmpFormDt()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除账册备案申请附件
     *
     * @param id uid主键
     * @return
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecAcmpFormDtById(@PathVariable String id) {
        try {
            //执行删除
            if (emsPutrecAcmpFormDtService.deleteById(id)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("deleteEmsPutrecAcmpFormDtById()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecAcmpFormDtByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = emsPutrecAcmpFormDtService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteEmsPutrecAcmpFormDtByList()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 查询账册备案申请附件列表
     *
     * @return 账册备案申请附件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecAcmpFormDts(EmsPutrecAcmpFormDt emsPutrecAcmpFormDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按商品序号排序
                page.setAsc(true);
                page.setOrderByField("acmpFormSeqno");  // 排序
            }
            Page<EmsPutrecAcmpFormDt> emsPutrecAcmpFormDtPage = emsPutrecAcmpFormDtService.selectEmsPutrecAcmpFormDtList(page, emsPutrecAcmpFormDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecAcmpFormDtPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id查询账册备案随附单证信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecAcmpFormDtsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsPutrecAcmpFormDt emsPutrecAcmpFormDt = emsPutrecAcmpFormDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecAcmpFormDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecAcmpFormDtsById()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 获取最大的序号
     * @param emsPutrecAcmpFormDt
     * @return 序号
     */
    @RequestMapping(value = "/list/selectMaxAcmpFormDtSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectMaxAcmpFormDtSeqno(EmsPutrecAcmpFormDt emsPutrecAcmpFormDt){
        AjaxResult ajaxResult=null;
        try {
            BigDecimal maxGdsSeqno = emsPutrecAcmpFormDtService.getMaxAcmpFormDtSeqno(emsPutrecAcmpFormDt.getSeqNo());
            BigDecimal cusMaxGdsSeqno = emsCusAcmpFormDtService.getMaxAcmpFormDtSeqno(emsPutrecAcmpFormDt.getSeqNo());
            if (cusMaxGdsSeqno.compareTo(maxGdsSeqno) == 1) {
                maxGdsSeqno = cusMaxGdsSeqno;
            }
            maxGdsSeqno = maxGdsSeqno.add(BigDecimal.valueOf(1));//取最大值加1
            return json(MessageConstants.BSSP_STATUS_SUCCESS,maxGdsSeqno);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectMaxAcmpFormDtSeqno()--error", e);
        }
        return ajaxResult;
    }
}
