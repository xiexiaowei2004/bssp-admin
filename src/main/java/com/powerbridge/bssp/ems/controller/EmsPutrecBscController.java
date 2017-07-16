package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.*;
import com.powerbridge.bssp.ems.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecBscController
 * 类描述：账册备案申请接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：willChen
 * 修改时间：2017/5/17 9:49
 */
@Controller
@RequestMapping("/ems/emsPutrecBsc")
@CrossOrigin
public class EmsPutrecBscController extends BaseController {
    @Autowired
    private IEmsPutrecBscService emsPutrecBscService;
    @Autowired
    private IEmsPutrecExgService emsPutrecExgService;
    @Autowired
    private IEmsPutrecImgService emsPutrecImgService;
    @Autowired
    private IEmsPutrecUcnsDtService emsPutrecUcnsDtService;
    @Autowired
    private IEmsPutrecAcmpFormDtService emsPutrecAcmpFormDtService;
    @Autowired
    private IEmsCusExgService emsCusExgService;
    @Autowired
    private IEmsCusImgService emsCusImgService;

    /**
     * 新增账册备案申请
     *
     * @param emsPutrecBsc 详细信息
     * @return
     */
    //@RequiresPermissions("emsPutrecBsc:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEmsPutrecBsc(EmsPutrecBsc emsPutrecBsc) {
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        AjaxResult ajaxResult = null;
        try {
            emsPutrecBsc.setUid(uId);
            emsPutrecBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);
            emsPutrecBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            String docType = "";
            if (emsPutrecBsc.getEmsTypecd().equals("1")) {
                docType = "jm";
            }
            if (emsPutrecBsc.getEmsTypecd().equals("2")) {
                docType = "jg";
            }
            DocTypeConstants.setDocType(emsPutrecBsc, docType);
            emsPutrecBsc.setDeclareMark(emsPutrecBsc.getDclTypecd());

            boolean flag = emsPutrecBscService.insert(emsPutrecBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addEmsPutrecBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 申报
     *
     * @param emsPutrecBsc
     * @return
     */
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/declare", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult declare(EmsPutrecBsc emsPutrecBsc) {
        try {
            if (StringUtil.isEmpty(emsPutrecBsc.getUid())) {
                String uid = UUIDGenerator.getUUID();
                emsPutrecBsc.setUid(uid);
                emsPutrecBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);
                emsPutrecBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
                emsPutrecBsc.setDecTime(DateUtil.now());
                String emsTypecd = emsPutrecBsc.getEmsTypecd();
                String emsType = "";
                if ("1".equals(emsTypecd)) {
                    emsType = "jm";
                }
                if ("2".equals(emsTypecd)) {
                    emsType = "jg";
                }
                DocTypeConstants.setDocType(emsPutrecBsc, emsType);
                emsPutrecBsc.setDeclareMark(emsPutrecBsc.getDclTypecd());
                boolean flag = emsPutrecBscService.insert(emsPutrecBsc);
                if (!flag) {
                    return result(MessageConstants.BSSP_STATUS_FAIL);
                }
            } else {
                emsPutrecBsc.setDecTime(DateUtil.now());
                boolean flag = emsPutrecBscService.updateById(emsPutrecBsc);
                if (!flag) {
                    return result(MessageConstants.BSSP_STATUS_FAIL);
                }
                emsPutrecBsc = emsPutrecBscService.selectById(emsPutrecBsc.getUid());
            }
            //判断表体必填项
            AjaxResult emptyAajxResult = checkIsEmpty(emsPutrecBsc);
            if (emptyAajxResult.getCode() != 1) {
                return emptyAajxResult;
            }
            //判断序号是否重复
            String checkMsg = declareCheckData(emsPutrecBsc);
            if (StringUtil.isNotEmpty(checkMsg)) {
                return setErrorJson(checkMsg);
            }
            try {
                BsspUtil.checkStatusDeclare(emsPutrecBsc, "", "", false);
                emsPutrecBsc.setRetChannel("");
                emsPutrecBscService.updateById(emsPutrecBsc);
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            } catch (Exception e) {
                return json(MessageConstants.BSSP_STATUS_SUBMIT_FAIL, e.getMessage());
            }
        } catch (Exception e) {
            logger.error("deleteEmsPutrecBscById()--err", e);
            return json(MessageConstants.BSSP_STATUS_SUBMIT_FAIL, e.getMessage());
        }
    }

    /**
     * 判断序号是否重复
     *
     * @param emsPutrecBsc
     * @return
     */
    private String declareCheckData(EmsPutrecBsc emsPutrecBsc) {
        String msg = "";
        String seqNo = emsPutrecBsc.getSeqNo();
        //申请表过滤条件：获取当前记录下修改标志为新增的商品
        EntityWrapper preEntityWrapper = new EntityWrapper();
        preEntityWrapper.eq("MODF_MARKCD", "3");
        preEntityWrapper.eq("SEQ_NO", seqNo);
        preEntityWrapper.eq("CHG_TMS_CNT", emsPutrecBsc.getChgTmsCnt());
        List<EmsPutrecImg> emsPutrecImgList = emsPutrecImgService.selectList(preEntityWrapper);
        ArrayList<BigDecimal> imgArrayList = new ArrayList<>();
        for (EmsPutrecImg emsPutrecImg : emsPutrecImgList) {
            imgArrayList.add(emsPutrecImg.getGdsSeqno());
        }
        List<EmsPutrecExg> emsPutrecExgList = emsPutrecExgService.selectList(preEntityWrapper);
        ArrayList<BigDecimal> exgArrayList = new ArrayList<>();
        for (EmsPutrecExg emsPutrecExg : emsPutrecExgList) {
            exgArrayList.add(emsPutrecExg.getGdsSeqno());
        }
        //正式表过滤条件：获取正式表的商品
        EntityWrapper cusEntityWrapper = new EntityWrapper();
        cusEntityWrapper.eq("SEQ_NO", seqNo);
        cusEntityWrapper.in("GDS_SEQNO", imgArrayList.toString());
        List<EmsCusImg> emsCusImgList = emsCusImgService.selectList(cusEntityWrapper);
        if (emsCusImgList.size() > 0) {
            msg = "料件商品序号重复,不可申报";
        }
        List<EmsPutrecExg> emsCusExgList = emsCusExgService.selectList(cusEntityWrapper);
        if (emsCusExgList.size() > 0) {
            msg = "成品商品序号重复,不可申报";
        }
        return msg;
    }

    /**
     * 判断表体必填项
     *
     * @param emsPutrecBsc
     * @return
     */
    private AjaxResult checkIsEmpty(EmsPutrecBsc emsPutrecBsc) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", emsPutrecBsc.getSeqNo());
        entityWrapper.eq("CHG_TMS_CNT", emsPutrecBsc.getChgTmsCnt());
        List<EmsPutrecImg> emsPutrecImgs = emsPutrecImgService.selectList(entityWrapper);
        List<EmsPutrecExg> emsPutrecExgs = emsPutrecExgService.selectList(entityWrapper);
        List<EmsPutrecUcnsDt> emsPutrecUcnsDts = emsPutrecUcnsDtService.selectList(entityWrapper);
        //检查料件必填项
        String checkImgMsg = emsPutrecImgService.checkListEmpty(emsPutrecImgs);
        if (StringUtil.isNotEmpty(checkImgMsg)) {
            return setErrorJson(checkImgMsg);
        }
        //检查成品必填项
        String checkExgMsg = emsPutrecExgService.checkListEmpty(emsPutrecExgs);
        if (StringUtil.isNotEmpty(checkExgMsg)) {
            return setErrorJson(checkExgMsg);
        }
        //检查单耗必填项
        String checkUcnsMsg = emsPutrecUcnsDtService.checkListEmpty(emsPutrecUcnsDts);
        if (StringUtil.isNotEmpty(checkUcnsMsg)) {
            return setErrorJson(checkUcnsMsg);
        }
        return result(MessageConstants.BSSP_STATUS_SUCCESS);
    }

    /**
     * 根据id删除账册备案申请
     *
     * @param id uid主键
     * @return
     */
    //@RequiresPermissions("emsPutrecBsc:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecBscById(@PathVariable String id) {
        try {
            EmsPutrecBsc emsPutrecBsc = emsPutrecBscService.selectById(id);
            if (emsPutrecBsc == null) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该记录不存在");
            }
            String chkStatus = emsPutrecBsc.getChkStatus();
            //S:暂存  N：审批不通过 可修改
            if (!BsspUtil.checkStatusEdit(chkStatus)) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该状态数据无法删除");
            }
            //执行删除
            //先删除子表数据
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", emsPutrecBsc.getSeqNo());
            emsPutrecExgService.delete(entityWrapper);
            emsPutrecImgService.delete(entityWrapper);
            emsPutrecUcnsDtService.delete(entityWrapper);
            emsPutrecAcmpFormDtService.delete(entityWrapper);
            //再删除主表数据
            if (emsPutrecBscService.deleteById(id)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("deleteEmsPutrecBscById()--err", e);
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
    //@RequiresPermissions("emsPutrecBsc:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecBscByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的uid集合
            String idList = getParameter("idList");
            boolean flag = emsPutrecBscService.deleteBySeqNoList(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteEmsPutrecBscByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 修改账册备案申请
     *
     * @param emsPutrecBsc 详细信息
     * @return
     */
    //@RequiresPermissions("emsPutrecBsc:list:update")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEmsPutrecBsc(EmsPutrecBsc emsPutrecBsc) {
        AjaxResult ajaxResult = null;
        try {
            if (StringUtil.isEmpty(emsPutrecBsc.getUid())) {
                return json(MessageConstants.BSSP_STATUS_CODE_NOTEXIST, "主键不能为空");
            }
            emsPutrecBsc.setDeclareMark(emsPutrecBsc.getDclTypecd());
            emsPutrecBsc.setDecTime(DateUtil.now());
            boolean flag = emsPutrecBscService.updateById(emsPutrecBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addInvBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id查询账册备案申请信息
     *
     * @param id 主键id
     * @return 账册备案申请信息
     */
    //@RequiresPermissions("emsPutrecBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecBscsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsPutrecBsc emsPutrecBsc = emsPutrecBscService.selectById(id);
            if (StringUtil.isNotEmpty(emsPutrecBsc.getSeqNo())) {
                EntityWrapper entityWrapper = new EntityWrapper();
                entityWrapper.eq("SEQ_NO", emsPutrecBsc.getSeqNo());
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecBscsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请列表
     */
    //@RequiresPermissions("emsPutrecBsc:list")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecBscs(EmsPutrecBsc emsPutrecBsc) {
        if (StringUtil.isEmpty(emsPutrecBsc.getEmsTypecd())) {
            return result(MessageConstants.BSSP_STATUS_FAIL);
        }
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
            BsspUtil.filterCopEnt(emsPutrecBsc, null);
            Page<EmsPutrecBsc> emsPutrecBscPage = emsPutrecBscService.selectEmsPutrecBscList(page, emsPutrecBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecBscPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecBscList()--error", e);
        }
        return ajaxResult;
    }
}
