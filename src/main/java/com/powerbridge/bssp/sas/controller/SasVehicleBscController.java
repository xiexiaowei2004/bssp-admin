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
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;
import com.powerbridge.bssp.sas.entity.SasVehicleCus;
import com.powerbridge.bssp.sas.service.ISasVehicleBscService;
import com.powerbridge.bssp.sas.service.ISasVehicleCusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：SasVehicleBscController
 * 类描述：车辆信息备案申请控制器
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日  下午10:59:20
 */
@Controller
@RequestMapping("/sas/sasVehicleBsc")
@CrossOrigin
public class SasVehicleBscController extends BaseController {
    @Autowired
    private ISasVehicleBscService sasVehicleBscService;
    @Autowired
    private ISasVehicleCusService sasVehicleCusService;

    /**
     * @param sasVehicleBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入车辆备案申请管理页面:(列表查询)
     */
    //@RequiresPermissions("sasVehicleBsc:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showVehicleBscList(SasVehicleBsc sasVehicleBsc) {
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
            BsspUtil.filterCopEnt(sasVehicleBsc,null); //过滤非本企业的数据
            Page<SasVehicleBsc> vehicleBscList = sasVehicleBscService.selectByVehicleBsc(page, sasVehicleBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, vehicleBscList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showVehicleBscList()--error", e);
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
            SasVehicleBsc sasVehicleBsc = sasVehicleBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasVehicleBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editVehicleBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasVehicleBsc
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    //@RequiresPermissions("sasVehicleBsc:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasVehicleBsc(SasVehicleBsc sasVehicleBsc) {
        AjaxResult ajaxResult = null;
        try {
            sasVehicleBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            Boolean flag = sasVehicleBscService.updateById(sasVehicleBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasVehicleBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateSasVehicleBsc()--err", e);
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
            SasVehicleBsc sasVehicleBsc=sasVehicleBscService.selectById(id);
            //只有单据状态为暂存和审批不通过的数据才可以删除
            if (!BsspUtil.checkStatusEdit(sasVehicleBsc.getChkStatus())){
                return result(MessageConstants.BSSP_STATUS_APPROVAL_NOTDELETE);
            }
            boolean flag = sasVehicleBscService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteVehicleBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    //@RequiresPermissions("sasVehicleBsc:list:delete")
    @RequestMapping(value = "/copEnt/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteVehicleBscByList(String idList, String seqNoList) {
        AjaxResult ajaxResult = null;
       /* try {
            Boolean flag = copEntService.txDeleteCopEntByList(Arrays.asList(idList.split(",")), Arrays.asList(seqNoList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCopEntByList()--err", e);
        }*/
        return ajaxResult;
    }


    /**
     * @param sasVehicleBsc
     * @return AjaxResult
     * @throws
     * @Description: 新增车辆备案
     */
    //@RequiresPermissions("sasVehicleBsc:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addVehicleBsc(SasVehicleBsc sasVehicleBsc) {
        AjaxResult ajaxResult = null;
        try {
            sasVehicleBsc.setUid(UUIDGenerator.getUUID());
            DocTypeConstants.setDocType(sasVehicleBsc, null);
            sasVehicleBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            sasVehicleBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);
            Boolean flag = sasVehicleBscService.insert(sasVehicleBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasVehicleBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addVehicleBsc()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param sasVehicleBsc
     * @return AjaxResult
     * @throws
     * @Description: 申报
     */
//    @RequiresPermissions("dcrChgoffBsc:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/submit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult submitSasVehicleBsc(SasVehicleBsc sasVehicleBsc) {
        Boolean flag=false;
        AjaxResult ajaxResult = null;
        try {
            if (StringUtil.isEmpty(sasVehicleBsc.getUid())) {
                sasVehicleBsc.setUid(UUIDGenerator.getUUID());
                DocTypeConstants.setDocType(sasVehicleBsc, null);
                sasVehicleBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
                sasVehicleBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);
            }else{
                if(StringUtil.isEmpty(sasVehicleBsc.getDocType()) || StringUtil.isEmpty(sasVehicleBsc.getBizType())){
                    DocTypeConstants.setDocType(sasVehicleBsc, null);
                }
            }
            /*ajaxResult = check(sasVehicleBsc);
            if (!ObjectUtils.isEmpty(ajaxResult)) {
                return ajaxResult;
            }*/
            flag = sasVehicleBscService.insertOrUpdate(sasVehicleBsc);
            sasVehicleBsc=sasVehicleBscService.selectById(sasVehicleBsc.getUid());
            if(flag){
                if(!sasVehicleBsc.getDclTypecd().equals(ChkStatusConstant.DCL_TYPECD_STOCK_2)){
                    EntityWrapper<SasVehicleBsc> entityWrapper=new EntityWrapper();
                    entityWrapper.where("VEHICLE_NO={0}",sasVehicleBsc.getVehicleNo()).and().eq("CHK_STATUS",ChkStatusConstant.CHK_STATUS_D).or("CHK_STATUS={0}",ChkStatusConstant.CHK_STATUS_P);
                    List<SasVehicleBsc> sasVehicleBscList=sasVehicleBscService.selectList(entityWrapper);
                    if(sasVehicleBscList.size()>0){
                        ajaxResult=json(MessageConstants.BSSP_STATUS_VEHICLENO_IsExist,sasVehicleBsc);
                        return ajaxResult;
                    }
                    if(!sasVehicleBsc.getSecVehicleIcNo().equals("")){
                        EntityWrapper<SasVehicleBsc> entity=new EntityWrapper();
                        entity.where("SEC_VEHICLE_IC_NO={0}",sasVehicleBsc.getSecVehicleIcNo()).and().eq("CHK_STATUS",ChkStatusConstant.CHK_STATUS_D).or("CHK_STATUS={0}",ChkStatusConstant.CHK_STATUS_P);
                        List<SasVehicleBsc> list=sasVehicleBscService.selectList(entity);
                        if(list.size()>0){
                            ajaxResult=json(MessageConstants.BSSP_STATUS_VEHICLENO_Exist,sasVehicleBsc);
                            return ajaxResult;
                        }
                    }
                }
                try{
                    //sasVehicleBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
                    BsspUtil.checkStatusDeclare(sasVehicleBsc,null,null,true);
                    sasVehicleBsc.setRetChannel("");
                    sasVehicleBsc.setStucd("0");//状态设为已申请
                    sasVehicleBscService.updateById(sasVehicleBsc);
                    ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasVehicleBsc);
                }catch (Exception e){
                    ajaxResult = result(MessageConstants.BSSP_STATUS_SUBMIT_FAIL);
                }

            }else{
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUBMIT_FAIL);
            }
        }catch (Exception e){
            ajaxResult=setErrorJson(e.getMessage());
        }

        return ajaxResult;
    }

    /**
     * @param id
     * @return AjaxResult
     * @throws
     * @Description: 从正式表中选择数据进行变更
     */
//    @RequiresPermissions("SasStockBsc:list:delete")
    @RequestMapping(value = "/list/select", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult select(String id,String vehicleNo) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper<SasVehicleBsc> entityWrapper=new EntityWrapper<>();
            entityWrapper.eq("VEHICLE_NO",vehicleNo);
            entityWrapper.eq("DCl_TYPECD",ChkStatusConstant.DCL_TYPECD_STOCK_2);
            List<SasVehicleBsc> sasVehicleBscList=sasVehicleBscService.selectList(entityWrapper);
            if (sasVehicleBscList.size()>0){
                return setErrorJson("当前车牌号已存在变更记录,请检查!");
            }
            SasVehicleCus sasVehicleCus = sasVehicleCusService.selectById(id);
            SasVehicleBsc sasVehicleBsc = new SasVehicleBsc();
//            String[] ignoreProperties=;
            String[] ignoreProperties={"uid","dclTypecd","dclTime","dclEr","inputerCode","updateTime","updateName","updateBy","createTime","createName","createBy","decTime","retChannel","stucd","chgEmapvTime","putrecEmapvTime","decTime","chkTime"};
            BeanUtils.copyProperties(sasVehicleCus,sasVehicleBsc,ignoreProperties);
            sasVehicleBsc.setDclTypecd(ChkStatusConstant.DCL_TYPECD_STOCK_2);
            sasVehicleBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            sasVehicleBsc.setChgTmsCnt(sasVehicleCus.getChgTmsCnt()+1);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasVehicleBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("select()--err", e);
        }
        return ajaxResult;
    }
}