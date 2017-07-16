package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclAcmpFormDt;
import com.powerbridge.bssp.sas.service.ISasDclAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 业务申报随附单证表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
@Controller
@RequestMapping("/sas/sasDclAcmpFormDt")
@CrossOrigin
public class SasDclAcmpFormDtController extends BaseController {

    @Autowired
    private ISasDclAcmpFormDtService sasDclAcmpFormDtService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclAcmpFormDtList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<SasDclAcmpFormDt> sasDclAcmpFormDtList = sasDclAcmpFormDtService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclAcmpFormDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasDclAcmpFormDt
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
//    @RequiresPermissions("sasDclAcmpFormDt:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasDclAcmpFormDt(SasDclAcmpFormDt sasDclAcmpFormDt) {
        AjaxResult ajaxResult = null;
        try {
//            String check = checkAcmpFormSeqno(sasDclAcmpFormDt.getSeqNo());
//            if (!"".equals(check)){
//                return setErrorJson(check);
//            }
            ajaxResult = check(sasDclAcmpFormDt);
            if (!ObjectUtils.isEmpty(ajaxResult)) {
                return ajaxResult;
            }
            sasDclAcmpFormDt.setUid(UUIDGenerator.getUUID());
            Boolean flag = sasDclAcmpFormDtService.insert(sasDclAcmpFormDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclAcmpFormDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addSasDclAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("sasDclAcmpFormDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasDclAcmpFormDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclAcmpFormDt sasDclAcmpFormDt = sasDclAcmpFormDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclAcmpFormDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasDclAcmpFormDt
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("sasDclAcmpFormDt:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasDclAcmpFormDt(SasDclAcmpFormDt sasDclAcmpFormDt) {
        AjaxResult ajaxResult = null;
        try {
//            String check = checkAcmpFormSeqno(sasDclAcmpFormDt.getSeqNo());
//            if (!"".equals(check)){
//                return setErrorJson(check);
//            }
            ajaxResult = check(sasDclAcmpFormDt);
            if (!ObjectUtils.isEmpty(ajaxResult)) {
                return ajaxResult;
            }
            Boolean flag = sasDclAcmpFormDtService.updateById(sasDclAcmpFormDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDclAcmpFormDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateSasDclAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("sasDclAcmpFormDt:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasDclAcmpFormDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclAcmpFormDt sasDclAcmpFormDt = sasDclAcmpFormDtService.selectById(id);
            boolean flag = sasDclAcmpFormDtService.deleteById(id);
            /*sasDclAcmpFormDtService.updateAcmpFormSeqno(sasDclAcmpFormDt.getSeqNo(),sasDclAcmpFormDt.getAcmpFormSeqno());
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }*/
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteSasDclAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("sasDclAcmpFormDt:list:delete")
//    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public AjaxResult deleteSasDclAcmpFormDtByList(String idList) {
//        AjaxResult ajaxResult = null;
//        try {
//            Boolean flag = sasDclAcmpFormDtService.deleteBatchIds(Arrays.asList(idList.split(",")));
//            if (flag) {
//                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
//            }
//        } catch (Exception e) {
//            ajaxResult = setErrorJson(e.getMessage());
//            logger.error("deleteSasDclAcmpFormDtByList()--err", e);
//        }
//        return ajaxResult;
//    }

    @RequestMapping(value = "/list/getAcmpFormSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getAcmpFormSeqno(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            int acmpFormSeqno = 1;
            EntityWrapper<SasDclAcmpFormDt> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO",seqNo);
            entityWrapper.orderBy("ACMP_FORM_SEQNO",false);
            List<SasDclAcmpFormDt> sasDclAcmpFormDtList = sasDclAcmpFormDtService.selectList(entityWrapper);
            if (!sasDclAcmpFormDtList.isEmpty()){
                acmpFormSeqno = sasDclAcmpFormDtList.get(0).getAcmpFormSeqno() + 1;
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,acmpFormSeqno);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getAcmpFormSeqno()--err", e);
        }
        return ajaxResult;
    }

    private String checkAcmpFormSeqno(String seqNo){
        String errorInfo = "";

        EntityWrapper<SasDclAcmpFormDt> entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("COUNT(ACMP_FORM_SEQNO) AS ACMPFORMSEQNO");
        entityWrapper.eq("SEQ_NO",seqNo);
        entityWrapper.groupBy("ACMP_FORM_SEQNO");
        entityWrapper.having("COUNT(ACMP_FORM_SEQNO)>1");
        List<SasDclAcmpFormDt> sasDclAcmpFormDtList = sasDclAcmpFormDtService.selectList(entityWrapper);
        if (!sasDclAcmpFormDtList.isEmpty()){
            errorInfo = "窗口中存在随附单证序号重复的记录，请检查";
        }
        return errorInfo;
    }
}
