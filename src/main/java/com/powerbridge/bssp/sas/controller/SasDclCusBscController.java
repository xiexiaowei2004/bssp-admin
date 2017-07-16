package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDclBsc;
import com.powerbridge.bssp.sas.entity.SasDclCusBsc;
import com.powerbridge.bssp.sas.service.ISasDclBscService;
import com.powerbridge.bssp.sas.service.ISasDclCusBscService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 业务申报正式表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Controller
@RequestMapping("/sas/sasDclCusBsc")
@CrossOrigin
public class SasDclCusBscController extends BaseController {

    @Autowired
    private ISasDclCusBscService sasDclCusBscService;

    @Autowired
    private ISasDclBscService sasDclBscService;

    /**
     * @param sasDclCusBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclCusBsc:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showsasDclCusBscList(SasDclCusBsc sasDclCusBsc) {
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
            BsspUtil.filterCopEnt(sasDclCusBsc, null);
            Page<SasDclCusBsc> sasDclCusBscList = sasDclCusBscService.selectByList(page, sasDclCusBsc);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclCusBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showsasDclCusBscList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasDclCusBsc
     * @return AjaxResult
     * @throws
     * @Description: 核注清单进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclBsc:list:view")
    @RequestMapping(value = "/invCus/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclCusBscListForInv(SasDclCusBsc sasDclCusBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }
            BsspUtil.filterCopEnt(sasDclCusBsc, null);
            Page<SasDclCusBsc> sasDclBscList = sasDclCusBscService.selectBySasDclCusBscList(page,sasDclCusBsc);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclBscList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("sasDclCusBsc:list:view")
    @RequestMapping(value = "/list/{id}/edit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editsasDclCusBsc(@PathVariable String id,String dclTypecd) {
        AjaxResult ajaxResult = null;
        try {
            SasDclCusBsc sasDclCusBsc = sasDclCusBscService.selectById(id);
            EntityWrapper<SasDclBsc> sasDclBscEntityWrapper = new EntityWrapper<>();
            sasDclBscEntityWrapper.eq("SEQ_NO",sasDclCusBsc.getSeqNo());
            List<SasDclBsc> sasDclBscList = sasDclBscService.selectList(sasDclBscEntityWrapper);
            if (!sasDclBscList.isEmpty()){
                return setErrorJson("当前记录已存在变更或结案记录！");
            }
            SasDclBsc sasDclBsc = new SasDclBsc();
            String[] ignorProperties = {"uid","retChannel","updateTime","updateName","updateBy","createTime","createName","createBy","decTime","dclTime"};
            BeanUtils.copyProperties(sasDclCusBsc,sasDclBsc,ignorProperties);
            sasDclBsc.setDclTime(DateUtil.now());   // 申报日期
            sasDclBsc.setDecTime(DateUtil.now());   // 录入日期
//            SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
//            sasDclBsc.setInputCopNo(sysUser.getInputCopNo());
//            sasDclBsc.setInputCopName(sysUser.getInputCopName());
//            sasDclBsc.setInputerName(sysUser.getUserName());
//            sasDclBsc.setInputerCode(String.valueOf(sysUser.getUserId()));
            if (dclTypecd.equals(ChkStatusConstant.DCL_TYPECD_2)){
                sasDclBsc.setUid(UUIDGenerator.getUUID());
                sasDclBsc.setDclTypecd(ChkStatusConstant.DCL_TYPECD_2);
                sasDclBsc.setChgTmsCnt(sasDclCusBsc.getChgTmsCnt() + 1);
                sasDclBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_A); // 审批状态 暂存
                sasDclBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S); // 单据状态 暂存
                sasDclBscService.insert(sasDclBsc);
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclBsc);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editsasDclCusBsc()--err", e);
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
    public AjaxResult viewSasDclBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclCusBsc sasDclCusBsc = sasDclCusBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclCusBsc);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("viewSasDclBsc()--err", e);
        }
        return ajaxResult;
    }
}
