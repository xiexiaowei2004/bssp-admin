package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.security.SystemAuthorizingUser;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclBsc;
import com.powerbridge.bssp.sas.entity.SasDclCusBsc;
import com.powerbridge.bssp.sas.entity.SasDclDt;
import com.powerbridge.bssp.sas.service.ISasDclBscService;
import com.powerbridge.bssp.sas.service.ISasDclCusBscService;
import com.powerbridge.bssp.sas.service.ISasDclDtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 业务申报表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
@Controller
@RequestMapping("/sas/sasDclBsc")
@CrossOrigin
public class SasDclBscController extends BaseController {

    @Autowired
    private ISasDclBscService sasDclBscService;

    @Autowired
    private ISasDclDtService sasDclDtService;

    @Autowired
    private ISasDclCusBscService sasDclCusBscService;

    /**
     * @param sasDclBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclBsc:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclBscList(SasDclBsc sasDclBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }else {
                page.setAsc(false);
                page.setOrderByField("decTime");
            }
            BsspUtil.filterCopEnt(sasDclBsc, null);
            Page<SasDclBsc> sasDclBscList = sasDclBscService.selectByList(page, sasDclBsc);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclBscList()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param sasDclBsc
     * @return AjaxResult
     * @throws
     * @Description: 核注清单进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclBsc:list:view")
    @RequestMapping(value = "/inv/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclBscListForInv(SasDclBsc sasDclBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }
            BsspUtil.filterCopEnt(sasDclBsc, null);
            Page<SasDclBsc> sasDclBscList = sasDclBscService.selectBySasDclBscList(page,sasDclBsc);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclBscList()--error", e);
        }
        return ajaxResult;
    }

     /**
     * @param sasDclBsc
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
//    @RequiresPermissions("sasDclBsc:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasDclBsc(SasDclBsc sasDclBsc) {
        AjaxResult ajaxResult = null;
        try {
            sasDclBsc.setUid(UUIDGenerator.getUUID());
            sasDclBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_A); // 审批状态 暂存
            sasDclBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S); // 单据状态 暂存
//            sasDclBsc.setDclTime(DateUtil.now());           // 申报日期默认当天日期
//            sasDclBsc.setBillsType(ChkStatusConstant.BILLS_TYPE_1);  // 单据类别 1-物流
            DocTypeConstants.setDocType(sasDclBsc, sasDclBsc.getBusType());
            Boolean flag = sasDclBscService.insert(sasDclBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclBsc);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addSasDclBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("sasDclBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasDclBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclBsc sasDclBsc = sasDclBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclBsc);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 业务变更或业务结案 页面
     */
//    @RequiresPermissions("sasDclBsc:list:view")
    @RequestMapping(value = "/list/{id}/edit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getSasDclBsc(@PathVariable String id, String dclTypecd) {
        AjaxResult ajaxResult = null;
        try {
            SasDclBsc sasDclBsc = new SasDclBsc();
            if (dclTypecd.equals(ChkStatusConstant.DCL_TYPECD_2)){
                sasDclBsc = sasDclBscService.selectById(id);
            } else {
                SasDclCusBsc sasDclCusBsc = sasDclCusBscService.selectById(id);
                sasDclBsc = new SasDclBsc();
                String[] ignorProperties = {"uid","retChannel","decTime","dclTime"};
                BeanUtils.copyProperties(sasDclCusBsc,sasDclBsc,ignorProperties);
                sasDclBsc.setDclTime(DateUtil.now());   // 申报日期
                sasDclBsc.setDecTime(DateUtil.now());   // 录入日期
                sasDclBsc.setDclTypecd(ChkStatusConstant.DCL_TYPECD_3);
                SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
                sasDclBsc.setInputCopNo(sysUser.getInputCopNo());
                sasDclBsc.setInputCopName(sysUser.getInputCopName());
                sasDclBsc.setInputerName(sysUser.getUserName());
                sasDclBsc.setInputerCode(String.valueOf(sysUser.getUserId()));
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclBsc);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasDclBsc
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("sasDclBsc:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasDclBsc(SasDclBsc sasDclBsc) {
        AjaxResult ajaxResult = null;
        SasDclBsc sasDclBscByCheck = sasDclBscService.selectById(sasDclBsc.getUid());
//        if (sasDclBscByCheck.getDclTypecd().equals(ChkStatusConstant.DCL_TYPECD_1)) {   // 备案情况 判断
            if (!BsspUtil.checkStatusEdit(sasDclBscByCheck.getChkStatus())) {
                return setErrorJson("当前记录单据状态不允许修改");
            }
//        }
        try {
            boolean flag = sasDclBscService.updateById(sasDclBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDclBsc);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateSasDclBsc()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param sasDclBsc
     * @return AjaxResult
     * @throws
     * @Description: 业务变更，业务结案暂存
     */
//    @RequiresPermissions("sasDclBsc:list:edit")
//    @RequestMapping(value = "/list/changeUpdate", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public AjaxResult changeUpdateSasDclBsc(SasDclBsc sasDclBsc) {
//        AjaxResult ajaxResult = null;
//        try {
//            sasDclBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_A); // 审批状态 暂存
//            sasDclBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S); // 单据状态 暂存
//            sasDclBsc.setDclTime(DateUtil.now());           // 申报日期默认当天日期
//            boolean flag = sasDclBscService.txUpdateById(sasDclBsc);
//            if (flag) {
//                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDclBsc);
//            }
//        } catch (Exception e) {
//            ajaxResult = setErrorJson(e.getMessage());
//            logger.error("changeUpdateSasDclBsc()--err", e);
//        }
//        return ajaxResult;
//    }

    /**
     * @param sasDclBsc
     * @return AjaxResult
     * @throws
     * @Description: 申报
     */
//    @RequiresPermissions("sasDclBsc:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/submit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult submitSasDclBsc(SasDclBsc sasDclBsc) {
        AjaxResult ajaxResult = null;
        try {
            boolean flag = false;
            String uid = sasDclBsc.getUid();
            if (StringUtil.isEmpty(uid)){
                sasDclBsc.setUid(UUIDGenerator.getUUID());
                sasDclBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_A); // 审批状态 暂存
                sasDclBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S); // 单据状态 暂存
//                sasDclBsc.setDclTime(DateUtil.now());           // 申报日期默认当天日期
//                sasDclBsc.setBillsType(ChkStatusConstant.BILLS_TYPE_1);  // 单据类别 1-物流
                DocTypeConstants.setDocType(sasDclBsc, sasDclBsc.getBusType());
            }
//            ajaxResult = check(sasDclBsc);
//            if (!ObjectUtils.isEmpty(ajaxResult)) {
//                return ajaxResult;
//            }
            flag = sasDclBscService.insertOrUpdate(sasDclBsc);
            if (flag) {
                sasDclBsc = sasDclBscService.selectById(sasDclBsc.getUid());
                if (!sasDclBsc.getDclTypecd().equals(ChkStatusConstant.DCL_TYPECD_3)){
                    EntityWrapper entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq("SEQ_NO",sasDclBsc.getSeqNo());
                    List<SasDclDt> sasDclDtList = sasDclDtService.selectList(entityWrapper);
                    if (sasDclDtList.isEmpty()){
                        return setErrorJson("商品窗口不能为空",sasDclBsc);
                    }
                    String check = checkSasDclSeqNo(sasDclBsc);     // 判断申报序号不能重复
                    if (!"".equals(check)){
                        return setErrorJson(check,sasDclBsc);
                    }
                }
                BsspUtil.checkStatusDeclare(sasDclBsc,null,null,true);
                sasDclBsc.setRetChannel("");
                sasDclBscService.updateById(sasDclBsc);
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDclBsc);
            }else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUBMIT_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("submitSasDclBsc()--err", e);
        }
        return ajaxResult;
    }

    private String checkSasDclSeqNo(SasDclBsc sasDclBsc){
        String errorInfo = "";
        if (!ChkStatusConstant.DCL_TYPECD_3.equals(sasDclBsc.getDclTypecd())){
            // 检查窗口序号是否重复
//            EntityWrapper<SasDclDt> entityWrapper = new EntityWrapper<>();
//            entityWrapper.setSqlSelect("COUNT(SAS_DCL_SEQNO) AS SASDCLSEQNO");
//            entityWrapper.eq("SEQ_NO",sasDclBsc.getSeqNo());
//            entityWrapper.eq("CHG_TMS_CNT",sasDclBsc.getChgTmsCnt());
//            entityWrapper.groupBy("SAS_DCL_SEQNO");
//            entityWrapper.having("COUNT(SAS_DCL_SEQNO)>1");
//            List<SasDclDt> sasDclDtList = sasDclDtService.selectList(entityWrapper);
//            if (!sasDclDtList.isEmpty()){
//                return "窗口中存在申报序号重复的记录，请检查";
//            }
//            // 检查是否跟正式表序号重复
//            entityWrapper = new EntityWrapper<>();
//            entityWrapper.eq("SEQ_NO",sasDclBsc.getSeqNo());
//            entityWrapper.eq("CHG_TMS_CNT",sasDclBsc.getChgTmsCnt());
//            entityWrapper.eq("MODF_MARKCD",ChkStatusConstant.MODF_MARKCD_3);
//            sasDclDtList = sasDclDtService.selectList(entityWrapper);
//            if (!sasDclDtList.isEmpty()){
//                for (SasDclDt sasDclDt : sasDclDtList){
//                    entityWrapper = new EntityWrapper<>();
//                    entityWrapper.eq("SAS_DCL_NO",sasDclDt.getSasDclNo());
//                    entityWrapper.eq("MTPCK_ENDPRD_TYPECD", ChkStatusConstant.MTPCK_ENDPRD_TYPECD_I);
//                    entityWrapper.eq("SAS_DCL_SEQNO", sasDclDt.getSasDclSeqno());
//                    List<SasDclDt> sasDclDts = sasDclDtService.selectList(entityWrapper);
//                    if (!sasDclDts.isEmpty()){
//                        return  "商品TAB新增商品的申报序号重复";
//                    }
//                }
//            }
            EntityWrapper<SasDclDt> entityWrapper = new EntityWrapper<>();
            entityWrapper.setSqlSelect("COUNT(SAS_DCL_SEQNO) AS SASDCLSEQNO");
            entityWrapper.eq("SEQ_NO",sasDclBsc.getSeqNo());
            entityWrapper.groupBy("SAS_DCL_SEQNO");
            entityWrapper.having("COUNT(SAS_DCL_SEQNO)>1");
            List<SasDclDt> sasDclDtList = sasDclDtService.selectList(entityWrapper);
            if (!sasDclDtList.isEmpty()){
                return "窗口中存在申报序号重复的记录，请检查";
            }
        }
        return errorInfo;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("sasDclBsc:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasDclBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper<SasDclBsc> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO",id);
//            entityWrapper.eq("CHG_TMS_CNT",chgTmsCnt);
            SasDclBsc sasDclBsc = sasDclBscService.selectOne(entityWrapper);
            if (!BsspUtil.checkStatusEdit(sasDclBsc.getChkStatus())) {
                return result(MessageConstants.BSSP_STATUS_APPROVAL_NOTDELETE);
            }
            /**判断是否可以删除:单据状态为审批不通过且为暂存，申报类型为备案可删除**/
//            if (!BsspUtil.checkStatusEdit(sasDclBsc.getChkStatus()) || !ChkStatusConstant.DCL_TYPECD_1.equals(sasDclBsc.getDclTypecd())){
//                return result(MessageConstants.BSSP_STATUS_APPROVAL_NOTDELETE);
//            }
            boolean flag = sasDclBscService.txDeleteSasDclBsc(id); // 删除主表，同时删除子表数据 传过来的值实为seqNo的值
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteSasDclBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("sasDclBsc:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasDclBscByList(String idList,String chgTmsCntByList) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper<SasDclBsc> entityWrapper = new EntityWrapper<>();
            entityWrapper.in("SEQ_NO",idList);  // idList 实为seqNo
            List<SasDclBsc> sasDclBscList = sasDclBscService.selectList(entityWrapper);
            for (SasDclBsc sasDclBsc : sasDclBscList) {
                String chkStatus = sasDclBsc.getChkStatus();
                if (!BsspUtil.checkStatusEdit(chkStatus)) {
                    return result(MessageConstants.BSSP_STATUS_APPROVAL_NOTDELETE);
                }
            }
            boolean flag = sasDclBscService.txDeleteSasDclBsc(idList); // 删除主表，同时删除子表数据 传过来的值实为seqNo的值
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteSasDclBscByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 判断页面是否可以进行业务变更或业务结案
     */
    @RequestMapping(value = "/list/{id}/judgeBusinessChange", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult judgeBusinessChange(@PathVariable String id, String dclTypecd) {
        AjaxResult ajaxResult = null;
        try {
            SasDclBsc sasDclBsc = sasDclBscService.selectById(id);
            String chkStatus = sasDclBsc.getChkStatus();
            String dclTypecdCheck = sasDclBsc.getDclTypecd();
            String constantChkStatus = ChkStatusConstant.CHK_STATUS_P;  // 单据状态 通过
            String constantDclTypecd = ChkStatusConstant.DCL_TYPECD_3;  // 申报类型 结案
            /****单据状态通过，且没有进行业务结案的单据才能进行业务变更和业务结案的操作****/
            if (constantChkStatus.equals(chkStatus) && !constantDclTypecd.equals(dclTypecdCheck)){
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclBsc);
            }else {
                if (ChkStatusConstant.DCL_TYPECD_2.equals(dclTypecd)){
                    ajaxResult = result(MessageConstants.BSSP_STATUS_SASDCLBSC_ISNOTCHANGE);
                }else {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_SASDCLBSC_ISNOTCLOSE);
                }
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("judgeBusinessChange()--err", e);
        }
        return ajaxResult;
    }

//    private SasDclBsc getSasDclBsc(String seqNo){
//        SasDclBsc sasDclBsc = null;
//        EntityWrapper<SasDclBsc> entityWrapper = new EntityWrapper<>();
//        entityWrapper.eq("SEQ_NO",seqNo);
//        entityWrapper.orderBy("CHG_TMS_CNT",false);
//        List<SasDclBsc> sasDclBscList = sasDclBscService.selectList(entityWrapper);
//        if (sasDclBscList.size() > 0){
//            sasDclBsc = sasDclBscList.get(0);
//        }
//        return sasDclBsc;
//    }
}
