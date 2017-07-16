package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas.entity.SasDragcarBsc;
import com.powerbridge.bssp.sas.entity.SasDragcarCus;
import com.powerbridge.bssp.sas.service.ISasDragcarBscService;
import com.powerbridge.bssp.sas.service.ISasDragcarCusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.powerbridge.bssp.base.controller.BaseController;

import java.util.Arrays;
import java.util.List;


/**
 * <p>
 * 扡卡备案表 前端控制器
 * </p>
 *
 * @author huanliu
 * @since 2017-06-21
 */
@Controller
@RequestMapping("/sas/sasDragcarBsc")
@CrossOrigin
public class SasDragcarBscController extends BaseController {
    @Autowired
    private ISasDragcarBscService sasDragcarBscService;

    @Autowired
    private ISasDragcarCusService sasDragcarCusService;

    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasDragcarBscList(SasDragcarBsc sasDragcarBsc) {
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        AjaxResult ajaxResult = null;
        try {
            sasDragcarBsc.setUid(uId);//
            sasDragcarBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);//单据状态:暂存


            boolean flag = sasDragcarBscService.insert(sasDragcarBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDragcarBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addSasDragcarBsc()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 根据id删除
     */

    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (sasDragcarBscService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 批量删除
     */

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deletesasDragcarBscServiceByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = sasDragcarBscService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deletesasDragcarBscServiceByList()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 根据id修改
     */

    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEdiSeqList(SasDragcarBsc sasDragcarBsc) {
        if (null == sasDragcarBsc.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (sasDragcarBscService.updateById(sasDragcarBsc)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    /**
     * 根据id查询
     */

    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        SasDragcarBsc sasDragcarBsc = sasDragcarBscService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, sasDragcarBsc);
    }

    /**
     * 根据条件查询
     */

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectSasDragcarBscs(SasDragcarBsc sasDragcarBsc) {
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
                page.setOrderByField("DEC_TIME");  // 排序
            }
            BsspUtil.filterCopEnt(sasDragcarBsc, null);
            Page<SasDragcarBsc> sasDragcarBscPage = sasDragcarBscService.selectSasDragcarBscList(page, sasDragcarBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDragcarBscPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectSasDragcarBscs()--error", e);
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
    public AjaxResult select(String id,String vehicleFrameNo) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper<SasDragcarBsc> entityWrapper=new EntityWrapper<>();
            entityWrapper.eq("VEHICLE_FRAME_NO",vehicleFrameNo);
            entityWrapper.eq("DCl_TYPECD",ChkStatusConstant.DCL_TYPECD_STOCK_2);
            List<SasDragcarBsc> sasDragcarBscList=sasDragcarBscService.selectList(entityWrapper);
            System.out.println("--------------"+sasDragcarBscList.size()+"--------------");
            if (sasDragcarBscList.size()>0){
                return setErrorJson("当前车架号已存在变更记录,请检查!");
            }
            SasDragcarCus sasDragcarCus = sasDragcarCusService.selectById(id);
            SasDragcarBsc sasDragcarBsc = new SasDragcarBsc();
//            String[] ignoreProperties=;
            String[] ignoreProperties={"uid","dclTypecd","dclTime","dclEr","inputerCode","updateTime","updateName","updateBy","createTime","createName","createBy","decTime","retChannel","stucd","chgEmapvTime","putrecEmapvTime","decTime","chkTime"};
            BeanUtils.copyProperties(sasDragcarCus,sasDragcarBsc,ignoreProperties);
            sasDragcarBsc.setDclTypecd(ChkStatusConstant.DCL_TYPECD_STOCK_2);
            sasDragcarBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            sasDragcarBsc.setChgTmsCnt(sasDragcarCus.getChgTmsCnt()+1);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDragcarBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("select()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param sasDragcarBsc
     * @return AjaxResult
     * @throws
     * @Description: 申报
     */
//    @RequiresPermissions("dcrChgoffBsc:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/submit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult submitSasDragcarBsc(SasDragcarBsc sasDragcarBsc) {
        Boolean flag=false;
        AjaxResult ajaxResult = null;
        try {
            /*if (StringUtil.isEmpty(sasDragcarBsc.getUid())) {
                sasDragcarBsc.setUid(UUIDGenerator.getUUID());
                DocTypeConstants.setDocType(sasDragcarBsc, null);
                sasDragcarBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
                *//**//*sasDragcarBsc.setEmapvMarkcd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);*//**//*
            }else{
                if(StringUtil.isEmpty(sasDragcarBsc.getDocType()) || StringUtil.isEmpty(sasDragcarBsc.getBizType())){
                    DocTypeConstants.setDocType(sasDragcarBsc, null);
                }
            }*/
            /*ajaxResult = check(sasVehicleBsc);
            if (!ObjectUtils.isEmpty(ajaxResult)) {
                return ajaxResult;
            }*/
            /*flag = sasDragcarBscService.insertOrUpdate(sasDragcarBsc);
            sasDragcarBsc=sasDragcarBscService.selectById(sasDragcarBsc.getUid());
            if(flag){
                if(!sasDragcarBsc.getDclTypecd().equals(ChkStatusConstant.DCL_TYPECD_STOCK_2)){
                    EntityWrapper<SasDragcarBsc> entityWrapper=new EntityWrapper();
                    entityWrapper.where("VEHICLE_NO={0}",sasDragcarBsc.getVehicleFrameNo()).and().eq("CHK_STATUS",ChkStatusConstant.CHK_STATUS_D).or("CHK_STATUS={0}",ChkStatusConstant.CHK_STATUS_P);
                    List<SasDragcarBsc> sasVehicleBscList=sasDragcarBscService.selectList(entityWrapper);
                    if(sasVehicleBscList.size()>0){
                        ajaxResult=json(MessageConstants.BSSP_STATUS_VEHICLENO_IsExist,sasDragcarBsc);
                        return ajaxResult;
                    }
                    if(!sasDragcarBsc.getVehicleFrameNo().equals("")){
                        EntityWrapper<SasDragcarBsc> entity=new EntityWrapper();
                        entity.where("SEC_VEHICLE_IC_NO={0}",sasDragcarBsc.getVehicleFrameNo()).and().eq("CHK_STATUS",ChkStatusConstant.CHK_STATUS_D).or("CHK_STATUS={0}",ChkStatusConstant.CHK_STATUS_P);
                        List<SasDragcarBsc> list=sasDragcarBscService.selectList(entity);
                        if(list.size()>0){
                            ajaxResult=json(MessageConstants.BSSP_STATUS_VEHICLENO_Exist,sasDragcarBsc);
                            return ajaxResult;
                        }
                    }
                }
                try{
                    //sasVehicleBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
                    BsspUtil.checkStatusDeclare(sasDragcarBsc,null,null,true);
                    sasDragcarBsc.setRetChannel("");
                    *//**//*sasDragcarBsc.setStucd("0");//状态设为已申请*//**//*
                    sasDragcarBsc.setRetChannel(null);
                    sasDragcarBscService.updateById(sasDragcarBsc);
                    ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDragcarBsc);
                }catch (Exception e){
                    ajaxResult = result(MessageConstants.BSSP_STATUS_SUBMIT_FAIL);
                }
            }else{
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUBMIT_FAIL);
            }*/
        }catch (Exception e){
            ajaxResult=setErrorJson(e.getMessage());
        }

        return ajaxResult;
    }
}