package com.powerbridge.bssp.dcr.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccAcmpFormDt;
import com.powerbridge.bssp.dcr.service.IDcrAdjaccAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 项目名称：bssp Maven webapp
 * 类名称：DcrAdjaccAcmpFormDtController
 * 类描述：账册核销随附单证明细控制器
 * 创建人：haihuihuang
 * 创建时间：2017年5月22日  上午15:59:00
 */
@Controller
@RequestMapping("/dcr/dcrAdjaccAcmpFormDt")
@CrossOrigin
public class DcrAdjaccAcmpFormDtController extends BaseController {

    @Autowired
    private IDcrAdjaccAcmpFormDtService dcrAdjaccAcmpFormDtService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrAdjaccAcmpFormDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrAdjaccAcmpFormDtList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<DcrAdjaccAcmpFormDt> dcrAdjaccAcmpFormDtList = dcrAdjaccAcmpFormDtService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrAdjaccAcmpFormDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrAdjaccAcmpFormDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrAdjaccAcmpFormDt
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
//    @RequiresPermissions("dcrAdjaccAcmpFormDt:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addDcrAdjaccAcmpFormDt(DcrAdjaccAcmpFormDt dcrAdjaccAcmpFormDt) {
        AjaxResult ajaxResult = null;
//        String check = checkEndprdSeqno(dcrAdjaccAcmpFormDt.getSeqNo());
//        if (!"".equals(check)) {
//            return setErrorJson(check);
//        }
        ajaxResult = check(dcrAdjaccAcmpFormDt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        try {
            dcrAdjaccAcmpFormDt.setUid(UUIDGenerator.getUUID());
            Boolean flag = dcrAdjaccAcmpFormDtService.insert(dcrAdjaccAcmpFormDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrAdjaccAcmpFormDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addDcrAdjaccAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrAdjaccAcmpFormDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrAdjaccAcmpFormDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrAdjaccAcmpFormDt dcrAdjaccAcmpFormDt = dcrAdjaccAcmpFormDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrAdjaccAcmpFormDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrAdjaccAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrAdjaccAcmpFormDt
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("dcrAdjaccAcmpFormDt:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateDcrAdjaccAcmpFormDt(DcrAdjaccAcmpFormDt dcrAdjaccAcmpFormDt) {
        AjaxResult ajaxResult = null;
//        String check = checkEndprdSeqno(dcrAdjaccAcmpFormDt.getSeqNo());
//        if (!"".equals(check)) {
//            return setErrorJson(check);
//        }
        ajaxResult = check(dcrAdjaccAcmpFormDt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        try {
            Boolean flag = dcrAdjaccAcmpFormDtService.updateById(dcrAdjaccAcmpFormDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrAdjaccAcmpFormDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateDcrAdjaccAcmpFormDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("dcrAdjaccAcmpFormDt:list:delete")
//    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public AjaxResult deleteDcrAdjaccAcmpFormDt(@PathVariable String id) {
//        AjaxResult ajaxResult = null;
//        try {
//            boolean flag = dcrAdjaccAcmpFormDtService.deleteById(id);
//            if (flag) {
//                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
//            }
//        } catch (Exception e) {
//            ajaxResult = setErrorJson(e.getMessage());
//            logger.error("deleteDcrAdjaccAcmpFormDt()--err", e);
//        }
//        return ajaxResult;
//    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 删除操作
     */
//    @RequiresPermissions("dcrAdjaccAcmpFormDt:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteDcrAdjaccAcmpFormDtByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            DcrAdjaccAcmpFormDt dcrAdjaccAcmpFormDt = dcrAdjaccAcmpFormDtService.selectById(idList);
            Boolean flag = dcrAdjaccAcmpFormDtService.deleteById(idList);
            if (flag) {
                dcrAdjaccAcmpFormDtService.updateAcmpFormSeqno(dcrAdjaccAcmpFormDt.getSeqNo(),dcrAdjaccAcmpFormDt.getAcmpFormSeqno());
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteDcrAdjaccAcmpFormDtByList()--err", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/list/getAcmpFormSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getAcmpFormSeqno(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            int acmpFormSeqno = 1;
            EntityWrapper<DcrAdjaccAcmpFormDt> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.orderBy("ACMP_FORM_SEQNO", false);
            List<DcrAdjaccAcmpFormDt> sasDclAcmpFormDtList = dcrAdjaccAcmpFormDtService.selectList(entityWrapper);
            if (!sasDclAcmpFormDtList.isEmpty()) {
                acmpFormSeqno = sasDclAcmpFormDtList.get(0).getAcmpFormSeqno() + 1;
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, acmpFormSeqno);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getAcmpFormSeqno()--err", e);
        }
        return ajaxResult;
    }

    private String checkEndprdSeqno(String seqNo) {
        String errorInfo = "";

        EntityWrapper<DcrAdjaccAcmpFormDt> entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("COUNT(ACMP_FORM_SEQNO) AS ACMPFORMSEQNO");
        entityWrapper.eq("SEQ_NO", seqNo);
        entityWrapper.groupBy("ACMP_FORM_SEQNO");
        entityWrapper.having("COUNT(ACMP_FORM_SEQNO)>1");
        List<DcrAdjaccAcmpFormDt> dcrAdjaccAcmpFormDts = dcrAdjaccAcmpFormDtService.selectList(entityWrapper);
        if (!dcrAdjaccAcmpFormDts.isEmpty()) {
            errorInfo = "窗口中存在随附单证序号重复的记录，请检查";
        }
        return errorInfo;
    }
}
