package com.powerbridge.bssp.ems_bws.controller;

import java.util.Arrays;
import java.util.List;

import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsBsc;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsAcmpFormDtService;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsBscService;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsDtService;

/**
 * @author huanliu
 * @since 2017-05-25
 * 修改人：jokylao
 * 修改时间：2017/6/11 19:49
 */

@Controller
@RequestMapping("/ems_bws/emsBwsBsc")
@CrossOrigin
public class EmsBwsBscController extends BaseController {

    @Autowired
    private IEmsBwsBscService emsBwsBscService;
    private IEmsBwsDtService emsBwsDtService;
    private IEmsBwsAcmpFormDtService emsBwsAcmpFormDtService;


    /**
     * 新增账册备案申请
     *
     * @param emsBwsBsc 详细信息
     * @return
     */
    //@RequiresPermissions("emsBwsBsc:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEmsBwsBsc(EmsBwsBsc emsBwsBsc) {
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        AjaxResult ajaxResult = null;
        try {
            emsBwsBsc.setUid(uId);
            emsBwsBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_BWS_5);
            emsBwsBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            DocTypeConstants.setDocType(emsBwsBsc, "");
            emsBwsBsc.setDeclareMark(emsBwsBsc.getDclTypecd());

            boolean flag = emsBwsBscService.insert(emsBwsBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addEmsBwsBsc()--err", e);
        }
        return ajaxResult;
    }
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/declare", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult declare(EmsBwsBsc emsBwsBsc) {
        try {
            if (StringUtil.isEmpty(emsBwsBsc.getUid())) {
                String uid = UUIDGenerator.getUUID();
                emsBwsBsc.setUid(uid);
                emsBwsBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);
                emsBwsBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
                DocTypeConstants.setDocType(emsBwsBsc, "");
                emsBwsBsc.setDeclareMark(emsBwsBsc.getDclTypecd());
                emsBwsBsc.setDecTime(DateUtil.now());
                boolean flag = emsBwsBscService.insert(emsBwsBsc);
                if (!flag) {
                    return result(MessageConstants.BSSP_STATUS_FAIL);
                }
            } else {
                emsBwsBsc.setDecTime(DateUtil.now());
                boolean flag = emsBwsBscService.updateById(emsBwsBsc);
                if (!flag) {
                    return result(MessageConstants.BSSP_STATUS_FAIL);
                }
                emsBwsBsc = emsBwsBscService.selectById(emsBwsBsc.getUid());
            }
            try {
                BsspUtil.checkStatusDeclare(emsBwsBsc, "", "", true);
                emsBwsBsc.setEmapvStucd(ChkStatusConstant.EMAPV_MARKCD_BWS_0);
                emsBwsBsc.setRetChannel("");
                emsBwsBscService.updateById(emsBwsBsc);
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            } catch (Exception e) {
                return json(MessageConstants.BSSP_STATUS_SUBMIT_FAIL, e.getMessage());
            }
        } catch (Exception e) {
            logger.error("emsBwsBscDeclare()--err", e);
            return json(MessageConstants.BSSP_STATUS_SUBMIT_FAIL, e.getMessage());
        }
    }

    /**
     * 根据id删除账册备案申请
     *
     * @param id uid主键
     * @return
     */
    //@RequiresPermissions("emsBwsBsc:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsBwsBscById(@PathVariable String id) {
        try {
            EmsBwsBsc emsBwsBsc = emsBwsBscService.selectById(id);
            if (emsBwsBsc == null) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该记录不存在");
            }
            String chkStatus = emsBwsBsc.getChkStatus();
            //S:暂存  N：审批不通过 可修改
            if ((!StringUtil.isEmpty(chkStatus) || chkStatus != null) && (!chkStatus.equals("S") || !chkStatus.equals("N"))) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该状态数据无法删除");
            }
            //执行删除
            //先删除子表数据
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", emsBwsBsc.getSeqNo());
            emsBwsDtService.delete(entityWrapper);
            emsBwsAcmpFormDtService.delete(entityWrapper);

            //再删除主表数据
            if (emsBwsBscService.deleteById(id)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("deleteEmsBwsBscById()--err", e);
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
    //@RequiresPermissions("emsBwsBsc:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsBwsBscByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = emsBwsBscService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteEmsBwsBscByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 修改账册备案申请
     *
     * @param emsBwsBsc 详细信息
     * @return
     */
    //@RequiresPermissions("emsBwsBsc:list:update")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEmsBwsBsc(EmsBwsBsc emsBwsBsc) {
        AjaxResult ajaxResult = null;
        try {
            if (StringUtil.isEmpty(emsBwsBsc.getUid())) {
                return json(MessageConstants.BSSP_STATUS_CODE_NOTEXIST, "主键不能为空");
            }
            emsBwsBsc.setDeclareMark(emsBwsBsc.getDclTypecd());
            emsBwsBsc.setDecTime(DateUtil.now());
            boolean flag = emsBwsBscService.updateById(emsBwsBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateEmsBwsBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id查询账册备案申请信息
     *
     * @param id 主键id
     * @return 账册备案申请信息
     */
    //@RequiresPermissions("emsBwsBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsBscsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsBwsBsc emsBwsBsc = emsBwsBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsBscsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请列表
     */
    //@RequiresPermissions("emsBwsBsc:list")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsBwsBscs(EmsBwsBsc emsBwsBsc) {
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
            BsspUtil.filterCopEnt(emsBwsBsc, null);
            Page<EmsBwsBsc> emsBwsBscPage = emsBwsBscService.selectEmsBwsBscList(page, emsBwsBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsBwsBscPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsBwsBscs()--error", e);
        }
        return ajaxResult;
    }
}