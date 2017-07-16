package com.powerbridge.bssp.sas_cmb.controller;

import java.util.Arrays;
import java.util.List;

import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
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
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.sas_cmb.entity.SasCmbBsc;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbBscService;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbImgService;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbInvtService;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbRbgService;

/**
 * <p>
 * 耗料单基本信息 前端控制器
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Controller
@RequestMapping("/sas_cmb/sasCmbBsc")
@CrossOrigin
public class SasCmbBscController extends BaseController {
    @Autowired
    private ISasCmbBscService sasCmbBscService;
    @Autowired
    private ISasCmbImgService sasCmbImgService;
    @Autowired
    private ISasCmbInvtService sasCmbInvtService;
    @Autowired
    private ISasCmbRbgService sasCmbRbgService;


    /**
     * 新增耗料单
     *
     * @param sasCmbBsc 详细信息
     * @return
     */

    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasCmbBsc(SasCmbBsc sasCmbBsc) {
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        AjaxResult ajaxResult = null;
        try {
            sasCmbBsc.setUid(uId);//
            sasCmbBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);//审批状态:暂存
            sasCmbBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);//单据状态:暂存


            boolean flag = sasCmbBscService.insert(sasCmbBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addSasCmbBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 申报耗料单
     *
     * @param sasCmbBsc 详细信息
     * @return
     */
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/declare", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult declare(String id) {
        SasCmbBsc sasCmbBsc = sasCmbBscService.selectById(id);
        try {
            String uid = sasCmbBsc.getUid();
            if (StringUtil.isEmpty(uid)) {
                uid = UUIDGenerator.getUUID();
                sasCmbBsc.setUid(uid);
                sasCmbBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);//审批状态:暂存
                sasCmbBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);//单据状态:暂存
                sasCmbBsc.setDecTime(DateUtil.now());//录入日期
                boolean flag = sasCmbBscService.insert(sasCmbBsc);
                if (!flag) {
                    return result(MessageConstants.BSSP_STATUS_FAIL);
                }
            } else {
                boolean flag = sasCmbBscService.updateById(sasCmbBsc);
                if (!flag) {
                    return result(MessageConstants.BSSP_STATUS_FAIL);
                }
            }
            try {
                BsspUtil.checkStatusDeclare(sasCmbBsc, "", "", true);
                sasCmbBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_B);//审批状态:已申报
                sasCmbBscService.updateById(sasCmbBsc);
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            } catch (Exception e) {
                return json(MessageConstants.BSSP_STATUS_SUBMIT_FAIL, e.getMessage());
            }
        } catch (Exception e) {
            logger.error("sasCmbBscDeclare()--err", e);
            return json(MessageConstants.BSSP_STATUS_SUBMIT_FAIL, e.getMessage());
        }
    }
    /**
     * 根据id删除耗料单
     *
     * @param id uid主键
     * @return
     */

    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasCmbBscById(@PathVariable String id) {
        try {
            SasCmbBsc sasCmbBsc = sasCmbBscService.selectById(id);
            if (sasCmbBsc == null) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该记录不存在");
            }
            String chkStatus = sasCmbBsc.getChkStatus();//获取单据状态
            //S:暂存  N：审批不通过 可修改
            if ((!StringUtil.isEmpty(chkStatus) || chkStatus != null) && (!chkStatus.equals("S") || !chkStatus.equals("N"))) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该状态数据无法删除");
            }
            //执行删除
            //先删除子表数据
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", sasCmbBsc.getSeqNo());
            sasCmbInvtService.delete(entityWrapper);
            sasCmbImgService.delete(entityWrapper);
            sasCmbRbgService.delete(entityWrapper);

            //再删除主表数据
            if (sasCmbBscService.deleteById(id)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("deleteSasCmbBscById()--err", e);
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
    public AjaxResult deleteSasCmbBscByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = sasCmbBscService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteSasCmbBscByList()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 修改耗料单
     *
     * @param sasCmbBsc 详细信息
     * @return
     */
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasCmbBsc(SasCmbBsc sasCmbBsc) {
        AjaxResult ajaxResult = null;
        try {
            if (StringUtil.isEmpty(sasCmbBsc.getUid())) {
                return json(MessageConstants.BSSP_STATUS_CODE_NOTEXIST, "主键不能为空");
            }
            sasCmbBsc.setDecTime(DateUtil.now());//录入日期
            boolean flag = sasCmbBscService.updateById(sasCmbBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateSasCmbBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id查询耗料单
     *
     * @param id 主键id
     * @return 账册备案申请信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectSasCmbBscsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            SasCmbBsc sasCmbBsc = sasCmbBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectSasCmbBscsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询耗料单列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectSasCmbBscs(SasCmbBsc sasCmbBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按录入日期倒排序
                page.setAsc(false);
                page.setOrderByField("decTime");  // 排序
            }
            BsspUtil.filterCopEnt(sasCmbBsc, null);
            Page<SasCmbBsc> sasCmbBscPage = sasCmbBscService.selectSasCmbBscList(page, sasCmbBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbBscPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectSasCmbBscs()--error", e);
        }
        return ajaxResult;
    }
    
    /**
     * 查询单条耗料单是否可变更
     *
     * @param seqNo 统一编号
     * @return
     */
    @RequestMapping(value = "/list/{seqNo}/isCanChange", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Boolean isCanChange(@PathVariable String seqNo) {
        try {
            //该统一编号存在不为审批通过的记录则不可变更
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.notIn("CHK_STATUS", ChkStatusConstant.CHK_STATUS_P);
            List<EtArcrpBsc> etArcrpBscList = sasCmbBscService.selectList(entityWrapper);
            if (etArcrpBscList.size() > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.error("selectSasCmbBscsById()--err", e);
            return false;
        }
    }
}
