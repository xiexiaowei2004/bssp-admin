package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.DateUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasFixedCardHis;
import com.powerbridge.bssp.sas.service.ISasFixedCardHisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.sas.service.ISasFixedCardBscService;
import com.powerbridge.bssp.sas.entity.SasFixedCardBsc;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luohongsu
 * @since 2017-07-05
 */
@Controller
@RequestMapping("/sas/sasFixedCardBsc")
@CrossOrigin
public class SasFixedCardBscController extends BaseController {
    @Autowired
    private ISasFixedCardBscService sasFixedCardBscService;
    @Autowired
    private ISasFixedCardHisService sasFixedCardHisService;
    /**
     * @param sasFixedCardBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入长期卡备案申请管理页面:(列表查询)
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showFixedCardBscList(SasFixedCardBsc sasFixedCardBsc) {
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
            BsspUtil.filterCopEnt(sasFixedCardBsc,null); //过滤非本企业的数据s
            Page<SasFixedCardBsc> FixedCardBscList = sasFixedCardBscService.selectByFixedCardBsc(page, sasFixedCardBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, FixedCardBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showFixedCardBscList()--error", e);
        }
        return ajaxResult;
    }



    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    //@RequiresPermissions("sasVehicleBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editVehicleBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasFixedCardBsc sasFixedCardBsc = sasFixedCardBscService.selectById(id);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(sasFixedCardBsc.getValidDate());
            sasFixedCardBsc.setValidDate(sdf.format(date));
            Date date1 = sdf.parse(sasFixedCardBsc.getRegDate());
            sasFixedCardBsc.setRegDate(sdf.format(date1));
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasFixedCardBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editVehicleBsc()--err", e);
        }
        return ajaxResult;
    }
    /**
     * @param sasFixedCardBsc
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    //@RequiresPermissions("sasVehicleBsc:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasFixedCardBsc(SasFixedCardBsc sasFixedCardBsc) {
        AjaxResult ajaxResult = null;
        try {
/*            sasVehicleBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            //变更时设置回执状态为空
            if (sasVehicleBsc.getDclTypecd().equals("2")) {
                sasVehicleBsc.setRetChannel("");
            }*/
            Boolean flag = sasFixedCardBscService.updateById(sasFixedCardBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasFixedCardBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updatesasFixedCardBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    //@RequiresPermissions("sasVehicleBsc:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteVehicleBsc(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            SasFixedCardBsc sasFixedCardBsc=sasFixedCardBscService.selectById(id);
/*            //只有单据状态为暂存和审批不通过的数据才可以删除
            if (!BsspUtil.checkStatusEdit(sasFixedCardBsc.getChkStatus())){
                return result(MessageConstants.BSSP_STATUS_APPROVAL_NOTDELETE);
            }*/
            boolean flag = sasFixedCardBscService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deletesasFixedCardBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasFixedCardBsc
     * @return AjaxResult
     * @throws
     * @Description: 新增长期卡备案
     */
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addFixedCarBsc(SasFixedCardBsc sasFixedCardBsc) {
        AjaxResult ajaxResult = null;
        try {
            sasFixedCardBsc.setUid(UUIDGenerator.getUUID());
            Date date = new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            sasFixedCardBsc.setRegDate(sdf.format(date));
            sasFixedCardBsc.setFixedCardStatus("Y");
            sasFixedCardBsc.setIcType("L");
            sasFixedCardBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            Boolean flag = sasFixedCardBscService.insert(sasFixedCardBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasFixedCardBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addFixedCardBsc()--err", e);
        }
        return ajaxResult;
    }
    /**
     * @param icCode
     * @return AjaxResult
     * @throws
     * @Description: 查询IC卡编号
     */
    @RequestMapping(value = "/list/select", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult select(String icCode){
        AjaxResult ajaxResult = null;
        try {
            SasFixedCardBsc sasFixedCardBsc = sasFixedCardBscService.selectByIcCode(icCode);
            if (sasFixedCardBsc == null){
                return result(MessageConstants.BSSP_STATUS_FAIL);
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasFixedCardBsc);
        } catch (Exception e) {
            logger.error("addFixedCardBsc()--err", e);
            return result(MessageConstants.BSSP_STATUS_FAIL);
        }
        return ajaxResult;
    }

    /**
     * @param sasFixedCardBsc
     * @return AjaxResult
     * @throws
     * @Description: 新增长期卡备案
     */
    @RequestMapping(value = "/list/move", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult moveFixedCarBsc(SasFixedCardBsc sasFixedCardBsc) {
        AjaxResult ajaxResult = null;
        try {
           SasFixedCardHis sasFixedCardHis = new SasFixedCardHis();
            BeanUtils.copyProperties(sasFixedCardBsc,sasFixedCardHis);
            Boolean flag = sasFixedCardHisService.insert(sasFixedCardHis);
            if (flag) {
                sasFixedCardBscService.deleteById(sasFixedCardBsc.getUid());
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasFixedCardBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addFixedCardBsc()--err", e);
        }
        return ajaxResult;
    }
}
