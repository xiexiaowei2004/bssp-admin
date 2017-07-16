package com.powerbridge.bssp.dcr.controller;

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
import com.powerbridge.bssp.dcr.entity.DcrChgoffBsc;
import com.powerbridge.bssp.dcr.entity.DcrChgoffExg;
import com.powerbridge.bssp.dcr.entity.DcrChgoffImg;
import com.powerbridge.bssp.dcr.entity.DcrChgoffInvtLt;
import com.powerbridge.bssp.dcr.service.IDcrChgoffBscService;
import com.powerbridge.bssp.dcr.service.IDcrChgoffExgService;
import com.powerbridge.bssp.dcr.service.IDcrChgoffImgService;
import com.powerbridge.bssp.dcr.service.IDcrChgoffInvtLtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：DcrChgoffBscController
 * 类描述：账册报核基础表 控制器
 * 创建人：haihuihuang
 * 创建时间：2017年5月22日  上午15:59:00
 */
@Controller
@RequestMapping("/dcr/dcrChgoffBsc")
@CrossOrigin
public class DcrChgoffBscController extends BaseController {

    @Autowired
    private IDcrChgoffBscService dcrChgoffBscService;

    @Autowired
    private IDcrChgoffInvtLtService dcrChgoffInvtLtService;

    @Autowired
    private IDcrChgoffExgService dcrChgoffExgService;

    @Autowired
    private IDcrChgoffImgService dcrChgoffImgService;

    /**
     * @param dcrChgoffBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrChgoffBsc:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrChgoffBscList(DcrChgoffBsc dcrChgoffBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            BsspUtil.filterCopEnt(dcrChgoffBsc,null);   // 过滤企业数据
            Page<DcrChgoffBsc> dcrChgoffBscList = dcrChgoffBscService.selectByList(page, dcrChgoffBsc);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrChgoffBscList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffBsc
     * @return AjaxResult
     * @throws
     * @Description: 新增账册报核
     */
//    @RequiresPermissions("dcrChgoffBsc:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @RepeatSubmitValidation
    public AjaxResult addDcrChgoffBsc(DcrChgoffBsc dcrChgoffBsc) {
        AjaxResult ajaxResult = null;
        try {
            dcrChgoffBsc.setUid(UUIDGenerator.getUUID());
            dcrChgoffBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);  // 单据状态 暂存

            SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
            dcrChgoffBsc.setAreaCode(sysUser.getAreaCode());    // 设置场地代码
//            dcrChgoffBsc.setAreaName(sysUser.getCopEnt().getAreaName());    // 设置场地名称
            dcrChgoffBsc.setMasterCuscd(sysUser.getCustomsCode());    // 设置主管海关
            DocTypeConstants.setDocType(dcrChgoffBsc, dcrChgoffBsc.getBusType());   // 设置业务类型 单据类别 加贸
            Boolean flag = dcrChgoffBscService.insert(dcrChgoffBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffBsc);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addDcrChgoffBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrChgoffBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrChgoffBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffBsc dcrChgoffBsc = dcrChgoffBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffBsc);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrChgoffBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffBsc
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("dcrChgoffBsc:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @RepeatSubmitValidation
    public AjaxResult updateDcrChgoffBsc(DcrChgoffBsc dcrChgoffBsc) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffBsc dcrChgoffBscCheck = dcrChgoffBscService.selectById(dcrChgoffBsc.getUid());
            String chkStatus = dcrChgoffBscCheck.getChkStatus();
            if (!BsspUtil.checkStatusEdit(chkStatus)){
                return result(MessageConstants.BSSP_STATUS_APPROVAL_NOTDELETE);
            }
            boolean flag = dcrChgoffBscService.updateById(dcrChgoffBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,dcrChgoffBsc);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateDcrChgoffBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffBsc
     * @return AjaxResult
     * @throws
     * @Description: 申报
     */
//    @RequiresPermissions("dcrChgoffBsc:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/submit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult submitDcrChgoffBsc(DcrChgoffBsc dcrChgoffBsc) {
        AjaxResult ajaxResult = null;
        try {
            String uid = dcrChgoffBsc.getUid();
            if (StringUtil.isEmpty(uid)){
                dcrChgoffBsc.setUid(UUIDGenerator.getUUID());
                dcrChgoffBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);  // 单据状态 暂存
                SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
                dcrChgoffBsc.setAreaCode(sysUser.getAreaCode());    // 设置场地代码
                dcrChgoffBsc.setMasterCuscd(sysUser.getCustomsCode());    // 设置主管海关
                DocTypeConstants.setDocType(dcrChgoffBsc, dcrChgoffBsc.getBusType());   // 设置业务类型 单据类别
            }
            EntityWrapper entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO",dcrChgoffBsc.getSeqNo());
            List<DcrChgoffInvtLt> dcrChgoffInvtLtList = dcrChgoffInvtLtService.selectList(entityWrapper);
            List<DcrChgoffImg> dcrChgoffImgList = dcrChgoffImgService.selectList(entityWrapper);
            List<DcrChgoffExg> dcrChgoffExgList = dcrChgoffExgService.selectList(entityWrapper);
            if (!dcrChgoffInvtLtList.isEmpty()){
                entityWrapper.setSqlSelect("COUNT(*) AS TOTAL,IE_FLAG AS IEFLAG");
                entityWrapper.groupBy("IE_FLAG");
                List<DcrChgoffInvtLt> list = dcrChgoffInvtLtService.selectList(entityWrapper);
                for (int i=0;i<list.size();i++){
                    String ieFlag = list.get(i).getIeFlag();
                    if (ieFlag.equals(ChkStatusConstant.IMPEXP_MARKCD_I)){  // 进口
                        dcrChgoffBsc.setImpInvtTotalCnt(list.get(i).getTotal());
                    }else if (ieFlag.equals(ChkStatusConstant.IMPEXP_MARKCD_E)){    //出口
                        dcrChgoffBsc.setExpInvtTotalCnt(list.get(i).getTotal());
                    }
                }
            }
            // 字段校验
            ajaxResult = check(dcrChgoffBsc);
            if (!ObjectUtils.isEmpty(ajaxResult)) {
                return ajaxResult;
            }
            boolean flag = dcrChgoffBscService.insertOrUpdate(dcrChgoffBsc);
            if (flag) {
                dcrChgoffBsc = dcrChgoffBscService.selectById(dcrChgoffBsc.getUid());
                // 业务逻辑判断是否可进行申报
                String chgoffTypecd = dcrChgoffBsc.getChgoffTypecd();
                if (chgoffTypecd.equals(ChkStatusConstant.CHGOFF_TYPECD_1)){ // 正常申报
                    if (dcrChgoffInvtLtList.isEmpty() || (dcrChgoffImgList.isEmpty() && dcrChgoffExgList.isEmpty())){
                        return setErrorJson("申报失败，清单和（料件/成品）必须存在数据",dcrChgoffBsc);
                    }
                }else if (chgoffTypecd.equals(ChkStatusConstant.CHGOFF_TYPECD_2)){ // 补充申报
                    return setErrorJson("申报失败，清单必须存在数据",dcrChgoffBsc);
                }
                BsspUtil.checkStatusDeclare(dcrChgoffBsc,null,"chgoffDclTime",true);
                dcrChgoffBsc.setRetChannel(""); // 回执状态清空
                dcrChgoffBscService.updateById(dcrChgoffBsc);
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,dcrChgoffBsc);
            }else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUBMIT_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("submitDcrChgoffBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("dcrChgoffBsc:list:delete")
//    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public AjaxResult deleteDcrChgoffBsc(@PathVariable String id) {
//        AjaxResult ajaxResult = null;
//        try {
//            DcrChgoffBsc dcrChgoffBsc = dcrChgoffBscService.selectById(id);
//            String chkStatus = dcrChgoffBsc.getChkStatus();
//            if (!BsspUtil.checkStatusEdit(chkStatus)){
//                return result(MessageConstants.BSSP_STATUS_APPROVAL_NOTDELETE);
//            }
//            boolean flag = dcrChgoffBscService.txDeleteDcrChgoffBsc(id); // 删除主表，同时删除子表数据 传过来的值实为seqNo的值
//            if (flag) {
//                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
//            }
//        } catch (Exception e) {
//            ajaxResult = setErrorJson(e.getMessage());
//            logger.error("deleteDcrChgoffBsc()--err", e);
//        }
//        return ajaxResult;
//    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("dcrChgoffBsc:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteDcrChgoffBscByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper<DcrChgoffBsc> entityWrapper = new EntityWrapper<>();
            entityWrapper.in("SEQ_NO",idList);  // idList 实为seqNo
            List<DcrChgoffBsc> dcrChgoffBscList = dcrChgoffBscService.selectList(entityWrapper);
            for (DcrChgoffBsc dcrChgoffBsc : dcrChgoffBscList) {
                String chkStatus = dcrChgoffBsc.getChkStatus();
                if (!BsspUtil.checkStatusEdit(chkStatus)) {
                    return result(MessageConstants.BSSP_STATUS_APPROVAL_NOTDELETE);
                }
            }
            boolean flag = dcrChgoffBscService.txDeleteDcrChgoffBscByList(idList); // 删除主表，同时删除子表数据 传过来的值实为seqNo的值
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteDcrChgoffBsc()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @return AjaxResult
     * @throws
     * @Description: 获取报核次数
     */
    @RequestMapping(value = "/list/getChgTmsCnt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getChgTmsCnt(String emsNo,String chgoffTypecd) {
        AjaxResult ajaxResult = null;
        try {
            int chgTmsCnt = 1;
            EntityWrapper<DcrChgoffBsc> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("EMS_NO",emsNo);
            entityWrapper.orderBy("CHG_TMS_CNT",false);
            List<DcrChgoffBsc> dcrChgoffBscList = dcrChgoffBscService.selectList(entityWrapper);
            if (!dcrChgoffBscList.isEmpty()){
                DcrChgoffBsc dcrChgoffBsc = dcrChgoffBscList.get(0);
                if (!dcrChgoffBsc.getChkStatus().equals(ChkStatusConstant.CHK_STATUS_P)){
                    return setErrorJson("当前账册存在记录未通过");
                }
                if (chgoffTypecd.equals(ChkStatusConstant.CHGOFF_TYPECD_1)){
                    chgTmsCnt = dcrChgoffBsc.getChgTmsCnt() + 1;
                }else if (chgoffTypecd.equals(ChkStatusConstant.CHGOFF_TYPECD_2)){
                    chgTmsCnt = dcrChgoffBsc.getChgTmsCnt();
                }
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,chgTmsCnt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getChgTmsCnt()--err", e);
        }
        return ajaxResult;
    }
}
