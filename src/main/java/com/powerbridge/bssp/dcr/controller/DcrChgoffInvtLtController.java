package com.powerbridge.bssp.dcr.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.dcr.entity.DcrChgoffInvtLt;
import com.powerbridge.bssp.dcr.service.IDcrChgoffInvtLtService;
import com.powerbridge.bssp.inv.entity.InvCusBsc;
import com.powerbridge.bssp.inv.service.IInvCusBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：DcrChgoffInvtLtController
 * 类描述：账册报核清单 控制器
 * 创建人：haihuihuang
 * 创建时间：2017年5月22日  上午15:59:00
 */
@Controller
@RequestMapping("/dcr/dcrChgoffInvtLt")
@CrossOrigin
public class DcrChgoffInvtLtController extends BaseController {

    @Autowired
    private IDcrChgoffInvtLtService dcrChgoffInvtLtService;

    @Autowired
    private IInvCusBscService invCusBscService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrChgoffInvtLt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrChgoffInvtLtList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<DcrChgoffInvtLt> dcrChgoffInvtLtList = dcrChgoffInvtLtService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffInvtLtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrChgoffInvtLtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffInvtLt
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
//    @RequiresPermissions("dcrChgoffInvtLt:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addDcrChgoffInvtLt(DcrChgoffInvtLt dcrChgoffInvtLt) {
        AjaxResult ajaxResult = check(dcrChgoffInvtLt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        try {
            dcrChgoffInvtLt.setUid(UUIDGenerator.getUUID());
            Boolean flag = dcrChgoffInvtLtService.insert(dcrChgoffInvtLt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffInvtLt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addDcrChgoffInvtLt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrChgoffInvtLt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrChgoffInvtLt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffInvtLt dcrChgoffInvtLt = dcrChgoffInvtLtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffInvtLt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrChgoffInvtLt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffInvtLt
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("dcrChgoffInvtLt:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateDcrChgoffInvtLt(DcrChgoffInvtLt dcrChgoffInvtLt) {
        AjaxResult ajaxResult = check(dcrChgoffInvtLt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        try {
            Boolean flag = dcrChgoffInvtLtService.updateById(dcrChgoffInvtLt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffInvtLt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateDcrChgoffInvtLt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("dcrChgoffInvtLt:list:delete")
//    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public AjaxResult deleteDcrChgoffInvtLt(@PathVariable String id) {
//        AjaxResult ajaxResult = null;
//        try {
//            boolean flag = dcrChgoffInvtLtService.deleteById(id);
//            if (flag) {
//                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
//            }
//        } catch (Exception e) {
//            ajaxResult = setErrorJson(e.getMessage());
//            logger.error("deleteDcrChgoffInvtLt()--err", e);
//        }
//        return ajaxResult;
//    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("dcrChgoffInvtLt:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteDcrChgoffInvtLtByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffInvtLt dcrChgoffInvtLt = dcrChgoffInvtLtService.selectById(idList);
            boolean flag = dcrChgoffInvtLtService.deleteById(idList);
            if (flag) {
                dcrChgoffInvtLtService.updateLno(dcrChgoffInvtLt.getSeqNo(),dcrChgoffInvtLt.getlNo());
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteDcrChgoffInvtLtByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @return AjaxResult
     * @throws
     * @Description: 获取序号
     */
    @RequestMapping(value = "/list/getLno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getLno(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            int lNo = 1;
            EntityWrapper<DcrChgoffInvtLt> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.orderBy("L_NO", false);
            List<DcrChgoffInvtLt> dcrChgoffInvtLtList = dcrChgoffInvtLtService.selectList(entityWrapper);
            if (!dcrChgoffInvtLtList.isEmpty()) {
                lNo = dcrChgoffInvtLtList.get(0).getlNo() + 1;
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, lNo);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getLno()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @return AjaxResult
     * @throws
     * @Description: 从报税核注清单编号获取数据
     */
    @RequestMapping(value = "/list/getInvDataList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getInvDataList(String emsNo, String chgoffDueTime, String seqNo, String chgTmsCnt, String dclTypecd) {
        AjaxResult ajaxResult = null;
        try {
            if (StringUtil.isEmpty(emsNo)) {
                return setErrorJson("账册编号不能为空");
            }
            if (StringUtil.isEmpty(chgoffDueTime)) {
                return setErrorJson("报核截止日期不能为空");
            }
            EntityWrapper<InvCusBsc> entityWrapper = new EntityWrapper<>();  //查询调用方法
            entityWrapper.eq("PUTREC_NO", emsNo); //账册号=报核表头账册号
            entityWrapper.le("INVT_DCL_TIME", chgoffDueTime + " 23:59:59");//申报日期<=报核截止日期
//            entityWrapper.eq("CHK_STATUS", ChkStatusConstant.CHK_STATUS_P);//审批通过
            entityWrapper.ne("VRFDED_MARKCD", ChkStatusConstant.VRFDED_MARKCD_3);//核扣标志不等于3-已核销
            List<InvCusBsc> invBscList = invCusBscService.selectList(entityWrapper);
            if (!invBscList.isEmpty()) {
                boolean flag = dcrChgoffInvtLtService.getDcrChgoffInvtLtByList(invBscList, seqNo, dclTypecd, chgTmsCnt);
                if (flag) {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
                }else {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_UNKOWN);
                }
            } else {
                ajaxResult = setErrorJson("该账册编号无清单数据");
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getInvData()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @return AjaxResult
     * @throws
     * @Description: 从报税核注清单编号获取数据
     */
    @RequestMapping(value = "/list/getInvData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getInvDatat(DcrChgoffInvtLt dcrChgoffInvtLt) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper<InvCusBsc> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("BOND_INVT_NO", dcrChgoffInvtLt.getBondInvtNo());
            InvCusBsc invCusBsc = invCusBscService.selectOne(entityWrapper);
            if (invCusBsc != null) {
                dcrChgoffInvtLt.setIeFlag(invCusBsc.getImpexpMarkcd());   //出入标记
                dcrChgoffInvtLt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_3); //修改标记 3-新增
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffInvtLt);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getInvData()--err", e);
        }
        return ajaxResult;
    }
}
