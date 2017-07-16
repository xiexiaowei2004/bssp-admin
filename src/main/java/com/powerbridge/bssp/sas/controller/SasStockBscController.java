package com.powerbridge.bssp.sas.controller;

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
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclCusBsc;
import com.powerbridge.bssp.sas.entity.SasStockBsc;
import com.powerbridge.bssp.sas.entity.SasStockCus;
import com.powerbridge.bssp.sas.entity.SasStockDt;
import com.powerbridge.bssp.sas.service.ISasDclCusBscService;
import com.powerbridge.bssp.sas.service.ISasStockBscService;
import com.powerbridge.bssp.sas.service.ISasStockCusService;
import com.powerbridge.bssp.sas.service.ISasStockDtService;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;
import com.powerbridge.bssp.saspass.service.ISasPassportRltService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：SasStockBscController
 * 类描述：出入库单表 控制器
 * 创建人：xuzuotao
 * 创建时间：2017年6月1日  下午10:59:20
 */
@Controller
@RequestMapping("/sas/sasStockBsc")
@CrossOrigin
public class SasStockBscController extends BaseController {
    @Autowired
    private ISasStockBscService sasStockBscService;
    @Autowired
    private ISasDclCusBscService sasDclCusBscService;
    @Autowired
    private ISasPassportRltService sasPassportRltService;
    @Autowired
    private ISasStockDtService sasStockDtService;
    @Autowired
    private ISasStockCusService sasStockCusService;

    /**
     * @param sasStockBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入出入库单管理页面:(列表查询)
     */
    //@RequiresPermissions("SasStockBsc:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasStockBscList(SasStockBsc sasStockBsc) {
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
            BsspUtil.filterCopEnt(sasStockBsc, null); //过滤非本企业的数据
            Page<SasStockBsc> sasStockBscList = sasStockBscService.selectBySasStockBsc(page, sasStockBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockBscList.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showSasStockBscList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasStockBsc
     * @return AjaxResult
     * @throws
     * @Description: 新增出入库单
     */
//    @RequiresPermissions("SasStockBsc:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasStockBsc(SasStockBsc sasStockBsc) {
        AjaxResult ajaxResult = null;
        try {
            sasStockBsc.setUid(UUIDGenerator.getUUID());
            Boolean flag = sasStockBscService.insert(sasStockBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addSasStockBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("SasStockBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasStockBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasStockBsc sasStockBsc = sasStockBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editSasStockBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasStockBsc
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("SasStockBsc:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasStockBsc(SasStockBsc sasStockBsc) {
        AjaxResult ajaxResult = null;
        try {
            //DocTypeConstants.setDocType(sasStockBsc, null);
            Boolean flag = sasStockBscService.updateById(sasStockBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateSasStockBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("SasStockBsc:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasStockBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper<SasStockBsc> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO", id);
//            entityWrapper.eq("CHG_TMS_CNT",chgTmsCnt);
            SasStockBsc sasStockBsc = sasStockBscService.selectOne(entityWrapper);
            //只有单据状态为暂存和审批不通过的数据才可以删除
            if (!BsspUtil.checkStatusEdit(sasStockBsc.getChkStatus())) {
                return result(MessageConstants.BSSP_STATUS_APPROVAL_NOTDELETE);
            }
            boolean flag = sasStockBscService.txDeleteSasStockBsc(id); // 删除主表，同时删除子表数据 传过来的值实为seqNo的值
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteSasStockBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("SasStockBsc:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasStockBscByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = sasStockBscService.txDeleteSasStockBscByList(Arrays.asList(idList.split(","))); // 删除主表，同时删除子表数据 传过来的值实为seqNo的值
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteSasStockBscByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 从业务申报正式表选择数据
     */
//    @RequiresPermissions("SasStockBsc:list:delete")
    @RequestMapping(value = "/list/select", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult select(String id, String busType) {
        AjaxResult ajaxResult = null;
        try {
            SasDclCusBsc sasDclCusBsc = sasDclCusBscService.selectById(id);
            SasStockBsc sasStockBsc = new SasStockBsc();
            sasStockBsc.setSasDclNo(sasDclCusBsc.getSasDclNo()); //申报表编号
            sasStockBsc.setMasterCuscd(sasDclCusBsc.getMasterCuscd()); //主管关区代码
            sasStockBsc.setStockTypecd(sasDclCusBsc.getDirectionTypecd()); //出入库单类型
            sasStockBsc.setBusinessTypecd(sasDclCusBsc.getBusinessTypecd()); //业务类型代码
            sasStockBsc.setDclEr(sasDclCusBsc.getDclEr()); //申请人
            sasStockBsc.setAreaCode(sasDclCusBsc.getAreaCode()); //场地代码
            sasStockBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S); //单据状态=暂存
            sasStockBsc.setOperateType(ChkStatusConstant.OPERATE_TYPE_0); //操作类型=正常
            sasStockBsc.setCentralizedDclTypecd(ChkStatusConstant.CENTRALIZED_DCL_TYPECD_1); //集报标志=未集报
            sasStockBsc.setPassTypecd("1"); //过卡标志=未过卡
            sasStockBsc.setPassportUsedTypecd("1"); //核放单生成标志=未生成
            //sasStockBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);//审批标志=暂存
            sasStockBsc.setDclTypecd(ChkStatusConstant.DCL_TYPECD_STOCK_1); //申报类型=备案
            DocTypeConstants.setDocType(sasStockBsc, busType); //设置单据类型和业务类型
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteSasStockBscByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 判断是否可进行退运或者作废申请
     */
//    @RequiresPermissions("SasStockBsc:list:delete")
    @RequestMapping(value = "/list/check", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult check(String seqNo) {
        AjaxResult ajaxResult = null;
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("RLT_NO", seqNo);
        List<SasPassportRlt> list = sasPassportRltService.selectList(entityWrapper);
        EntityWrapper entityWrapper2 = new EntityWrapper();
        entityWrapper2.eq("SEQ_NO", seqNo);
        entityWrapper2.eq("DCL_TYPECD", ChkStatusConstant.DCL_TYPECD_STOCK_3);
        List<SasStockBsc> list2 = sasStockBscService.selectList(entityWrapper2);
        if (list.size()>0) {
            ajaxResult = setJson(1,"该单已经做了核放单，请先删除核放单后，再进行作废申请");
        } else if(list2.size()>0){
            ajaxResult = setJson(1,"该单已存在作废申请记录，请检查");
        }else {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        }
        return ajaxResult;
    }

    /**
     * @param sasStockBsc
     * @return AjaxResult
     * @throws
     * @Description: 申报
     */
    @RepeatSubmitValidation
//    @RequiresPermissions("sasStockBsc:list:submit")
    @RequestMapping(value = "/list/submit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult submit(SasStockBsc sasStockBsc) {
        Boolean flag = false;
        AjaxResult ajaxResult = null;
       /* //后台校验
        AjaxResult ajaxResult = check(sasStockBsc);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }*/
        if (StringUtil.isEmpty(sasStockBsc.getUid())) {
            sasStockBsc.setUid(UUIDGenerator.getUUID());
            flag = sasStockBscService.txCopySasStockCusByList(sasStockBsc);
        } else {
            flag = sasStockBscService.updateById(sasStockBsc);
        }
        if (flag) {
            try {
                ajaxResult = getSasStockDt(sasStockBsc.getSeqNo());
                if (ajaxResult.getData() == null) {
                    /*if (sasStockBsc.getDclTypecd().equals(ChkStatusConstant.DCL_TYPECD_STOCK_3)) {
                        sasStockBsc.setDclTypecd(ChkStatusConstant.DCL_TYPECD_STOCK_1);
                        sasStockBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_1);
                        sasStockBscService.updateById(sasStockBsc);
                    }*/
                    ajaxResult = json(MessageConstants.BSSP_STATUS_SAS_STOCK_Dt, sasStockBsc);
                    return ajaxResult;
                }
                //sasStockBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
                BsspUtil.checkStatusDeclare(sasStockBsc, null, null, true);
                sasStockBsc.setRetChannel(null); //每次点击申报时清空回执状态
                sasStockBsc.setStucd("0");//状态设为已申请
                sasStockBscService.updateById(sasStockBsc);
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockBsc);
            } catch (Exception e) {
                ajaxResult = setErrorJson(e.getMessage());
            }
        } else {
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUBMIT_FAIL);
        }

        return ajaxResult;
    }

    /**
     * @param seqNoList
     * @return AjaxResult
     * @throws
     * @Description: 根据单据编号获取所有子表数据
     */
    @RequestMapping(value = "/list/getStockDt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getSasStockDt(String seqNoList) {
        AjaxResult ajaxResult = null;
        if (StringUtil.isNotEmpty(seqNoList)) {
            try {
                EntityWrapper<SasStockDt> entityWrapper = new EntityWrapper<>();
                entityWrapper.in("SEQ_NO", seqNoList);
                List<SasStockDt> sasStockDtList = sasStockDtService.selectList(entityWrapper);
                if (sasStockDtList.size() > 0) {
                    ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockDtList);
                } else {
                    ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, null);
                }
            } catch (Exception e) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, null);
                logger.error("getSasStockDt()--err", e);
            }
        }
        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 从正式表中选择数据进行作废申请
     */
//    @RequiresPermissions("SasStockBsc:list:choose")
    @RequestMapping(value = "/list/choose", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult choose(String id) {
        AjaxResult ajaxResult = null;
        try {
            SasStockCus sasStockCus = sasStockCusService.selectById(id);
            SasStockBsc sasStockBsc = new SasStockBsc();
//            String[] ignoreProperties=;
            String[] ignoreProperties = {"uid", "dclTypecd", "dclTime", "inputerCode", "updateTime", "updateName", "updateBy", "createTime", "createName", "createBy", "decTime", "retChannel", "stucd", "chgEmapvTime", "putrecEmapvTime", "decTime", "chkTime"};
            BeanUtils.copyProperties(sasStockCus, sasStockBsc, ignoreProperties);
            sasStockBsc.setDclTypecd(ChkStatusConstant.DCL_TYPECD_STOCK_3);
            sasStockBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            sasStockBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("choose()--err", e);
        }
        return ajaxResult;
    }

}