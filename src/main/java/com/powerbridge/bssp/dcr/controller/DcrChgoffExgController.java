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
import com.powerbridge.bssp.dcr.entity.DcrChgoffExg;
import com.powerbridge.bssp.dcr.service.IDcrChgoffExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：DcrChgoffExgController
 * 类描述：账册报核成品 控制器
 * 创建人：haihuihuang
 * 创建时间：2017年5月22日  上午15:59:00
 */
@Controller
@RequestMapping("/dcr/dcrChgoffExg")
@CrossOrigin
public class DcrChgoffExgController extends BaseController {

    @Autowired
    private IDcrChgoffExgService dcrChgoffExgService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrChgoffExg:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrChgoffExgList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<DcrChgoffExg> dcrChgoffExgList = dcrChgoffExgService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffExgList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrChgoffExgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffExg
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
//    @RequiresPermissions("dcrChgoffExg:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addDcrChgoffExg(DcrChgoffExg dcrChgoffExg) {
        AjaxResult ajaxResult = null;
        try {
            ajaxResult = check(dcrChgoffExg);
            if (!ObjectUtils.isEmpty(ajaxResult)) {
                return ajaxResult;
            }
            dcrChgoffExg.setUid(UUIDGenerator.getUUID());

            Boolean flag = dcrChgoffExgService.insert(dcrChgoffExg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffExg);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addDcrChgoffExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrChgoffExg:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrChgoffExg(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffExg dcrChgoffExg = dcrChgoffExgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffExg);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrChgoffExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffExg
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("dcrChgoffExg:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateDcrChgoffExg(DcrChgoffExg dcrChgoffExg) {
        AjaxResult ajaxResult = check(dcrChgoffExg);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        try {
            boolean flag = dcrChgoffExgService.updateById(dcrChgoffExg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,dcrChgoffExg);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateDcrChgoffExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("dcrChgoffExg:list:delete")
//    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public AjaxResult deleteDcrChgoffExg(@PathVariable String id) {
//        AjaxResult ajaxResult = null;
//        try {
//            boolean flag = dcrChgoffExgService.deleteById(id);
//            if (flag) {
//                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
//            }
//        } catch (Exception e) {
//            ajaxResult = setErrorJson(e.getMessage());
//            logger.error("deleteDcrChgoffExg()--err", e);
//        }
//        return ajaxResult;
//    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("dcrChgoffExg:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteDcrChgoffExgByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffExg dcrChgoffExg = dcrChgoffExgService.selectById(idList);
            boolean flag = dcrChgoffExgService.deleteById(idList);
            if (flag) {
                dcrChgoffExgService.updateGdsSeqno(dcrChgoffExg.getSeqNo(),dcrChgoffExg.getGdsSeqno());
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteDcrChgoffExgByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @return AjaxResult
     * @throws
     * @Description: 获取序号
     */
    @RequestMapping(value = "/list/getGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getGdsSeqno(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            int gdsSeqNo = 1;
            EntityWrapper<DcrChgoffExg> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO",seqNo);
            entityWrapper.orderBy("GDS_SEQNO",false);
            List<DcrChgoffExg> dcrChgoffExgList = dcrChgoffExgService.selectList(entityWrapper);
            if (!dcrChgoffExgList.isEmpty()){
                gdsSeqNo = dcrChgoffExgList.get(0).getGdsSeqno() + 1;
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,gdsSeqNo);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getGdsSeqno()--err", e);
        }
        return ajaxResult;
    }
}
