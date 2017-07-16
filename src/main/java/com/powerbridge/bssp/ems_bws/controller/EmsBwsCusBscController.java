package com.powerbridge.bssp.ems_bws.controller;

import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.ems_bws.service.*;
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
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusBsc;

import java.util.List;

/**
 * @author huanliu
 * @since 2017-05-25
 */

@Controller
@RequestMapping("/ems_bws/emsBwsCusBsc")
@CrossOrigin
public class EmsBwsCusBscController extends BaseController {

    @Autowired
    private IEmsBwsCusBscService emsBwsCusBscService;
    @Autowired
    private IEmsBwsBscService emsBwsBscService;

    /**
     * 根据id查询账册备案申请信息
     *
     * @param id 主键id
     * @return 账册备案申请信息
     */
    //@RequiresPermissions("emsBwsCusBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsCusBscsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsBwsCusBsc emsBwsCusBsc = emsBwsCusBscService.selectById(id);
            if (StringUtil.isNotEmpty(emsBwsCusBsc.getSeqNo())) {
                EntityWrapper entityWrapper = new EntityWrapper();
                entityWrapper.eq("SEQ_NO", emsBwsCusBsc.getSeqNo());
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsCusBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsCusBscsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请列表
     */
    //@RequiresPermissions("emsBwsCusBsc:list")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsCusBscs(EmsBwsCusBsc emsBwsCusBsc) {
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
            BsspUtil.filterCopEnt(emsBwsCusBsc, null);
            Page<EmsBwsCusBsc> emsCusBscPage = emsBwsCusBscService.selectEmsBwsCusBscList(page, emsBwsCusBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusBscPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsCusBscs()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 根据账册编号查询账册备案申请信息
     *
     * @param bwsNo 账册编号
     * @return 账册备案申请信息
     */
    //@RequiresPermissions("emsBwsCusBsc:list:view")
    @RequestMapping(value = "/list/selectByBwsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsCusBscByBwsNo(String bwsNo) {
        AjaxResult ajaxResult;
        try {

            if (StringUtil.isEmpty(bwsNo)) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_EMSNO_ERROR);
                return ajaxResult;
            }
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("BWS_NO", bwsNo);
            entityWrapper.ge("FINISH_VALID_DATE", DateUtil.today());
            EmsBwsCusBsc emsCusBsc = emsBwsCusBscService.selectOne(entityWrapper);
            if (emsCusBsc != null) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsCusBscByBwsNo()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 选择账册正式表一条数据生成备案变更的数据
     *
     * @param emsCusBscId 账册正式表主键
     * @param appId
     * @return
     */
    //@RequiresPermissions("emsBwsCusBsc:list:generateChgData")
    @RequestMapping(value = "/list/generateChgData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult generateEmsBwsBscChgData(String emsCusBscId, String appId) {
        AjaxResult ajaxResult = null;
        try {
            EmsBwsCusBsc emsBwsCusBsc = emsBwsCusBscService.selectById(emsCusBscId);
            String bwsNo = emsBwsCusBsc.getBwsNo();
            if (isExistRecord(bwsNo)) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_EMS_ISNOTCHANGE, bwsNo);
                return ajaxResult;
            }
            EmsBwsBsc emsBwsBsc = emsBwsCusBscService.generateEmsBwsChgData(emsBwsCusBsc, appId);
            if (emsBwsBsc != null) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("generateEmsBwsBscChgData()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 判断当前账册是否存在暂存或审批不通过的记录
     *
     * @param bwsNo
     * @return
     */
    private Boolean isExistRecord(String bwsNo) {
        Boolean flag = false;
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("BWS_NO", bwsNo);
        entityWrapper.ne("CHK_STATUS", "P");
        List<EmsBwsBsc> emsBwsBscList = emsBwsBscService.selectList(entityWrapper);
        if (emsBwsBscList.size() > 0) {
            flag = true;
        }
        return flag;
    }
    /**
     * 根据录入单位编码查找所有账册编号
     *
     * @param emsBwsCusBsc
     */
    @RequestMapping(value = "/list/bwsNoList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectBwsNoList(EmsBwsCusBsc emsBwsCusBsc) {
        AjaxResult ajaxResult = null;
        try {
            BsspUtil.filterCopEnt(emsBwsCusBsc, null);
            List<EmsBwsCusBsc> emsBwsCusBscList = emsBwsCusBscService.selectBwsNoList(emsBwsCusBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsCusBscList, emsBwsCusBscList.size());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectBwsNoList()--err", e);
        }
        return ajaxResult;
    }
}