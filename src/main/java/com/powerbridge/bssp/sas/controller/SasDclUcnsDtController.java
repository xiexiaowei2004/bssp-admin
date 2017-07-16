package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclCusDt;
import com.powerbridge.bssp.sas.entity.SasDclCusUcnsDt;
import com.powerbridge.bssp.sas.entity.SasDclDt;
import com.powerbridge.bssp.sas.entity.SasDclUcnsDt;
import com.powerbridge.bssp.sas.service.ISasDclCusDtService;
import com.powerbridge.bssp.sas.service.ISasDclCusUcnsDtService;
import com.powerbridge.bssp.sas.service.ISasDclDtService;
import com.powerbridge.bssp.sas.service.ISasDclUcnsDtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 业务申报单耗表 前端控制器
 * </p>
 *
 * @author jokylao
 * @since 2017-07-13
 */
@Controller
@RequestMapping("/sas/sasDclUcnsDt")
@CrossOrigin
public class SasDclUcnsDtController extends BaseController {
    @Autowired
    private ISasDclUcnsDtService sasDclUcnsDtService;
    @Autowired
    private ISasDclCusUcnsDtService sasDclCusUcnsDtService;
    @Autowired
    private ISasDclDtService sasDclDtService;
    @Autowired
    private ISasDclCusDtService sasDclCusDtService;

    /**
     * @param sasDclUcnsDt
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclDtList(SasDclUcnsDt sasDclUcnsDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {     // 默认申报序号升序
                page.setAsc(true);
            }
            Page<SasDclUcnsDt> sasDclDtList = sasDclUcnsDtService.selectByList(page, sasDclUcnsDt);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclDtList()--error", e);
        }
        return ajaxResult;
    }
    /**
     * @param sasDclUcnsDt
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasDclDt(SasDclUcnsDt sasDclUcnsDt) {
        AjaxResult ajaxResult = checkSasDclUcns(sasDclUcnsDt);
        if(ajaxResult.getData() != null){
            return  ajaxResult;
        }
        try {
            sasDclUcnsDt.setUid(UUIDGenerator.getUUID());
            ajaxResult = check(sasDclUcnsDt);
            if (!ObjectUtils.isEmpty(ajaxResult)) {
                return ajaxResult;
            }
            Boolean flag = sasDclUcnsDtService.insert(sasDclUcnsDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclUcnsDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addSasDclUcnsDt()--err", e);
        }
        return ajaxResult;
    }
    //    @RequiresPermissions("sasDclDt:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasDclDt(SasDclUcnsDt sasDclUcnsDt) {
        AjaxResult ajaxResult = checkSasDclUcns(sasDclUcnsDt);
        if(ajaxResult.getData() != null){
            return  ajaxResult;
        }
        // 后端校验
        ajaxResult = check(sasDclUcnsDt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        try {
            String modfMarkcd = sasDclUcnsDt.getModfMarkcd();
            /**0-未修改 -> 1-修改 ，2-删除不变，1-修改不变，3-新增不变**/
            if (modfMarkcd.equals(ChkStatusConstant.MODF_MARKCD_0)) {
                sasDclUcnsDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_1);
            }
            Boolean flag = sasDclUcnsDtService.updateById(sasDclUcnsDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclUcnsDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateSasDclUcnsDt()--err", e);
        }
        return ajaxResult;
    }
    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("sasDclDt:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasDclUcnsDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            boolean flag = sasDclUcnsDtService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteSasDclUcnsDt()--err", e);
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
    public AjaxResult editSasDclUcnsDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclUcnsDt sasDclUcnsDt = sasDclUcnsDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclUcnsDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclUcnsDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 根据商品序号获取商品信息
     */
//    @RequiresPermissions("sasDclBsc:list:view")
    @RequestMapping(value = "/list/getDtInfo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getDtInfo(String seqNo,String sasDclSeqno,String mtpckEndprdTypecd,String optype) {
        AjaxResult ajaxResult = null;
            try {
                EntityWrapper entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("SEQ_NO", seqNo);
                entityWrapper.eq("SAS_DCL_SEQNO",sasDclSeqno);
                entityWrapper.eq("MTPCK_ENDPRD_TYPECD",mtpckEndprdTypecd);
                if(optype.equals("change")){
                    SasDclCusDt sasDclCusDt = sasDclCusDtService.selectOne(entityWrapper);
                    if (sasDclCusDt != null) {
                        ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclCusDt);
                    } else {
                        ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, null);
                    }
                }else{
                    SasDclDt sasDclDt = sasDclDtService.selectOne(entityWrapper);
                    if (sasDclDt != null) {
                        ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclDt);
                    } else {
                        ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, null);
                    }
                }

            } catch (Exception e) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, null);
                logger.error("getDtInfo()--err", e);
            }
        
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 判断是否存在相同的成品序号和料件序号
     */
//    @RequiresPermissions("sasDclBsc:list:view")
    @RequestMapping(value = "/list/checkSasDclUcns", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult checkSasDclUcns(SasDclUcnsDt sasDclUcnsDt) {
        AjaxResult ajaxResult = null;
        if (sasDclUcnsDt != null) {
            try {
                EntityWrapper<SasDclUcnsDt> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("SEQ_NO", sasDclUcnsDt.getSeqNo());
                entityWrapper.eq("ENDPRD_SEQNO",sasDclUcnsDt.getEndprdSeqno());
                entityWrapper.eq("MTPCK_SEQNO",sasDclUcnsDt.getMtpckSeqno());
                SasDclUcnsDt sasDclUcns = sasDclUcnsDtService.selectOne(entityWrapper);
                if (sasDclUcns != null) {
                    ajaxResult = setErrorJson("已存在成品序号+料件序号相同的记录",sasDclUcns);
                } else{
                EntityWrapper<SasDclCusUcnsDt> entityWrapper2 = new EntityWrapper<>();
                entityWrapper2.eq("SEQ_NO", sasDclUcnsDt.getSeqNo());
                entityWrapper2.eq("ENDPRD_SEQNO",sasDclUcnsDt.getEndprdSeqno());
                entityWrapper2.eq("MTPCK_SEQNO",sasDclUcnsDt.getMtpckSeqno());
                SasDclCusUcnsDt sasDclCusUcnsDt = sasDclCusUcnsDtService.selectOne(entityWrapper2);
                if(sasDclCusUcnsDt != null){
                    ajaxResult = setErrorJson("已存在成品序号+料件序号相同的记录", sasDclCusUcnsDt);
                  }
                }
            } catch (Exception e) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, null);
                logger.error("checkSasDclUcns()--err", e);
            }
        }
        return ajaxResult;
    }

    /**
     * @param uid
     * @return AjaxResult
     * @throws
     * @Description: 从正式表获取数据
     */
    @RequestMapping(value = "/list/getSasDclCusUcnsDt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getSasDclCusUcnsDt(String uid,String optype) {
        AjaxResult ajaxResult = null;
        try {
            SasDclCusUcnsDt sasDclCusUcnsDt = sasDclCusUcnsDtService.selectById(uid);
            EntityWrapper<SasDclUcnsDt> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("ENDPRD_SEQNO", sasDclCusUcnsDt.getEndprdSeqno());
            entityWrapper.eq("MTPCK_SEQNO",sasDclCusUcnsDt.getMtpckSeqno());
            entityWrapper.eq("SEQ_NO",sasDclCusUcnsDt.getSeqNo());
            SasDclUcnsDt sasDclUcnsDt = sasDclUcnsDtService.selectOne(entityWrapper);
            if (sasDclUcnsDt != null) {
                return  ajaxResult = setErrorJson("已存在成品序号+料件序号相同的变更记录");
            }
            SasDclUcnsDt sasDclUcns=new SasDclUcnsDt();
            BeanUtils.copyProperties(sasDclCusUcnsDt,sasDclUcns,"uid");
            sasDclUcns.setChgTmsCnt(sasDclCusUcnsDt.getChgTmsCnt() + 1);
            switch (optype){
                case "modify":
                    sasDclUcnsDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_1);
                    break;
                case "delete":
                    sasDclUcnsDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_2);
                    break;
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDclUcns);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
        }
        return ajaxResult;
    }
}
