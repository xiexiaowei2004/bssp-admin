package com.powerbridge.bssp.sas.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;
import com.powerbridge.bssp.cod_cus.service.ICodCusComplexService;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsCusExg;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.ems.service.IEmsCusExgService;
import com.powerbridge.bssp.ems.service.IEmsCusImgService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsCusDtService;
import com.powerbridge.bssp.sas.entity.*;
import com.powerbridge.bssp.sas.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 * <p>
 * 业务申报商品表 前端控制器
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
@Controller
@RequestMapping("/sas/sasDclDt")
@CrossOrigin
public class SasDclDtController extends BaseController {
    @Autowired
    private ISasDclDtService sasDclDtService;

    @Autowired
    private ISasDclBscService sasDclBscService;

    @Autowired
    private ISasDclCusDtService sasDclCusDtService;

    @Autowired
    private ISasStockDtService sasStockDtService;

    @Autowired
    private ICodCusComplexService codCusComplexService;

    @Autowired
    private ISasStockBscService sasStockBscService;

    @Autowired
    private IEmsBwsCusDtService emsBwsCusDtService;

    @Autowired
    private IEmsCusImgService emsCusImgService;

    @Autowired
    private IEmsCusExgService emsCusExgService;

    @Autowired
    private ISasStockCusDtService sasStockCusDtService;

    /**
     * @param sasDclDt
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("sasDclDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showSasDclDtList(SasDclDt sasDclDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {     // 默认申报序号升序
                page.setAsc(true);
                page.setOrderByField("sasDclSeqno");
            }
            Page<SasDclDt> sasDclDtList = sasDclDtService.selectByList(page, sasDclDt);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclDtList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showSasDclDtList()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param sasDclDt
     * @return AjaxResult
     * @throws
     * @Description: 进入列表选择页面
     */
    //@RequiresPermissions("sasDclDt:list:view")
    @RequestMapping(value = "/list/chooseList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult chooseSasDclDtList(SasDclDt sasDclDt) {
        AjaxResult ajaxResult = null;
        try {
            /*EntityWrapper<SasDclDt> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SAS_DCL_NO",sasDclDt.getSasDclNo());
            entityWrapper.groupBy("CHG_TMS_CNT");
            entityWrapper.orderBy("CHG_TMS_CNT",true);
            List<SasDclDt> sasDclDtList = sasDclDtService.selectList(entityWrapper);
            if (sasDclDtList.size() > 0){
                sasDclDt.setChgTmsCnt(sasDclDtList.get(0).getChgTmsCnt());
            }
            ajaxResult = showSasDclDtList(sasDclDt); // 格式要返回的数据*/
            String sasDclNo = getParameter("sasDclNo");
            if (StringUtil.isNotEmpty(sasDclNo)) {
                EntityWrapper<SasDclBsc> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("SAS_DCL_NO", sasDclNo);
                SasDclBsc sasDclBsc = sasDclBscService.selectOne(entityWrapper);
                if (sasDclBsc != null) {
                    String chkStatus = sasDclBsc.getChkStatus();
                    if (chkStatus.equals(ChkStatusConstant.CHK_STATUS_P)) {
                            Page page = getPage();  // 分页
                            String sort = getParameter("sort");
                            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
                            if (StringUtil.isNotEmpty(sort)) {
                                page.setAsc(sortOrder);
                                page.setOrderByField(sort);  // 排序
                            } else {     // 默认申报序号升序
                                page.setAsc(true);
                                page.setOrderByField("sasDclSeqno");
                            }
                            Page<SasDclDt> SasStockDtList = sasDclDtService.selectByList(page, sasDclDt);
                            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, SasStockDtList.getRecords(), page.getTotal()); // 格式要返回的数据
                    }
                }
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("chooseSasDclDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @return AjaxResult
     * @throws
     * @Description: 获取申报序号
     */
//    @RequiresPermissions("sasDclDt:list:add")
    @RequestMapping(value = "/list/getSasDclSeqNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getSasDclSeqNo(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            int sasDclSeqno = 1;
            EntityWrapper entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO", seqNo);
//            entityWrapper.eq("CHG_TMS_CNT",chgTmsCnt);
            SasDclBsc sasDclBsc = sasDclBscService.selectOne(entityWrapper);
            String dclTypecd = sasDclBsc.getDclTypecd();
            if(StringUtil.isNotEmpty(getParameter("mtpckEndprdTypecd"))){
                entityWrapper.eq("MTPCK_ENDPRD_TYPECD",getParameter("mtpckEndprdTypecd"));
            }
            entityWrapper.orderBy("SAS_DCL_SEQNO",false);
            if (dclTypecd.equals(ChkStatusConstant.DCL_TYPECD_1)){      // 申报类型代码=“1-备案” 读取该记录的所有商品记录中的MAX(申报序号)
                List<SasDclDt> sasDclDtList = sasDclDtService.selectList(entityWrapper);
                if (!sasDclDtList.isEmpty()){
                    sasDclSeqno = sasDclDtList.get(0).getSasDclSeqno() + 1;
                }
            }else if (dclTypecd.equals(ChkStatusConstant.DCL_TYPECD_2)){ // 申报类型代码=“2-变更”
                 entityWrapper.eq("MODF_MARKCD",ChkStatusConstant.MODF_MARKCD_3);
                List<SasDclDt> sasDclDtList = sasDclDtService.selectList(entityWrapper);
                if (sasDclDtList.isEmpty()){
                    String sasDclNo = sasDclBsc.getSasDclNo();
                    entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq("SAS_DCL_NO",sasDclNo);
                    entityWrapper.eq("MTPCK_ENDPRD_TYPECD",ChkStatusConstant.MTPCK_ENDPRD_TYPECD_I);
                    entityWrapper.orderBy("SAS_DCL_SEQNO",false);
                    List<SasDclCusDt> sasDclCusDtList = sasDclCusDtService.selectList(entityWrapper);
                    if (!sasDclCusDtList.isEmpty()){
                        sasDclSeqno =  sasDclCusDtList.get(0).getSasDclSeqno() + 1;
                    }
                }else {
                    sasDclSeqno = sasDclDtList.get(0).getSasDclSeqno() + 1;
                }
            }
//            entityWrapper.orderBy("SAS_DCL_SEQNO", false);
//            List<SasDclDt> sasDclDtList = sasDclDtService.selectList(entityWrapper);
//            if (!sasDclDtList.isEmpty()) {
//                sasDclSeqno = sasDclDtList.get(0).getSasDclSeqno() + 1;
//            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclSeqno);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getSasDclSeqNo()--err", e);
        }
        return ajaxResult;
    }

//    private int getSasDclSeqNo(List<SasDclDt> sasDclDtList){
//        int sasDclSeqno = 0;
//        if (sasDclDtList.isEmpty()){
//            sasDclSeqno = 1;
//        }else {
//            sasDclSeqno = sasDclDtList.get(0).getSasDclSeqno() + 1;
//        }
//        return sasDclSeqno;
//    }

    /**
     * @param sasDclDt
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasDclDt(SasDclDt sasDclDt) {
        AjaxResult ajaxResult = null;
        try {
            // 检查申报序号是否重复
//            String check = checkSasDclSeqNo(sasDclDt.getSeqNo());
//            if (!"".equals(check)) {
//                return setErrorJson(check);
//            }
            sasDclDt.setUid(UUIDGenerator.getUUID());
//            sasDclDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_3);    // 修改标志代码 3-增加
//            sasDclDt.setMtpckEndprdTypecd(ChkStatusConstant.MTPCK_ENDPRD_TYPECD_I); //料件成品标志代码 I
            ajaxResult = check(sasDclDt);
            if (!ObjectUtils.isEmpty(ajaxResult)) {
                return ajaxResult;
            }
            Boolean flag = sasDclDtService.insert(sasDclDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addSasDclDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("sasDclDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasDclDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclDt sasDclDt = sasDclDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editSasDclDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param sasDclDt
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("sasDclDt:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasDclDt(SasDclDt sasDclDt) {
        AjaxResult ajaxResult = null;
        // 检查申报序号是否重复
//        String check = checkSasDclSeqNo(sasDclDt.getSeqNo());
//        if (!"".equals(check)) {
//            return setErrorJson(check);
//        }
        // 后端校验
        ajaxResult = check(sasDclDt);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        try {
            String modfMarkcd = sasDclDt.getModfMarkcd();
            /**0-未修改 -> 1-修改 ，2-删除不变，1-修改不变，3-新增不变**/
            if (modfMarkcd.equals(ChkStatusConstant.MODF_MARKCD_0)) {
                sasDclDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_1);
            }
            Boolean flag = sasDclDtService.updateById(sasDclDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasDclDt);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateSasDclDt()--err", e);
        }
        return ajaxResult;
    }

    private String checkSasDclSeqNo(String seqNo) {
        String errorInfo = "";
//        EntityWrapper<SasDclDt> entityWrapper = new EntityWrapper<>();
//        entityWrapper.eq("SEQ_NO",sasDclDt.getSeqNo());
//        entityWrapper.eq("CHG_TMS_CNT",sasDclDt.getChgTmsCnt());
//        entityWrapper.eq("SAS_DCL_SEQNO",sasDclDt.getSasDclSeqno());
//        if (isEdit){
//            entityWrapper.ne("UID",sasDclDt.getUid());
//        }
//        List<SasDclDt> sasDclDtList = sasDclDtService.selectList(entityWrapper);
//        if (!sasDclDtList.isEmpty()){
//            return "窗口中存在申报序号重复的记录，请检查";
//        }
//        /******以下检查适用于有正式表数据*******/
//        if (ChkStatusConstant.MODF_MARKCD_3.equals(sasDclDt.getModfMarkcd()) && StringUtil.isNotEmpty(sasDclDt.getSasDclNo())) {
//            entityWrapper = new EntityWrapper<>();
//            entityWrapper.eq("SAS_DCL_NO", sasDclDt.getSasDclNo());
//            entityWrapper.eq("MTPCK_ENDPRD_TYPECD", ChkStatusConstant.MTPCK_ENDPRD_TYPECD_I);
//            entityWrapper.eq("SAS_DCL_SEQNO", sasDclDt.getSasDclSeqno());
//            if (isEdit){
//                entityWrapper.ne("UID",sasDclDt.getUid());
//            }
//            sasDclDtList = sasDclDtService.selectList(entityWrapper);
//            if (!sasDclDtList.isEmpty()) {
//                return "新增商品的申报序号与正式表中申报序号重复，请检查";
//            }
//        }

        EntityWrapper<SasDclDt> entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("COUNT(SAS_DCL_SEQNO) AS SASDCLSEQNO");
        entityWrapper.eq("SEQ_NO", seqNo);
        entityWrapper.groupBy("SAS_DCL_SEQNO");
        entityWrapper.having("COUNT(SAS_DCL_SEQNO)>1");
        List<SasDclDt> sasDclDtList = sasDclDtService.selectList(entityWrapper);
        if (!sasDclDtList.isEmpty()) {
            errorInfo = "窗口中存在申报序号重复的记录，请检查";
        }
        return errorInfo;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("sasDclDt:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasDclDt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {

            SasDclDt sasDclDt = sasDclDtService.selectById(id);
            /**删除：0-未修改，1-修改 删除时，修改标记代码改为删除状态 2-删除不做任何操作 3-新增直接删除数据库该项记录**/
            String modfMarkcd = sasDclDt.getModfMarkcd();
            boolean flag = sasDclDtService.deleteById(id);
            if (modfMarkcd.equals(ChkStatusConstant.MODF_MARKCD_3)) {
                sasDclDtService.updateBySasDclSeqno(sasDclDt.getSeqNo(),sasDclDt.getSasDclSeqno());
            }
//            else if (modfMarkcd.equals(ChkStatusConstant.MODF_MARKCD_1) || modfMarkcd.equals(ChkStatusConstant.MODF_MARKCD_0)) {
//                sasDclDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_2);
//                flag = sasDclDtService.updateById(sasDclDt);
//            } else {
//                return result(MessageConstants.BSSP_STATUS_FAIL);
//            }
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteSasDclDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("sasDclDt:list:delete")
//    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public AjaxResult deleteSasDclDtByList(String idList) {
//        AjaxResult ajaxResult = null;
//        try {
//            Boolean flag = sasDclDtService.deleteBatchIds(Arrays.asList(idList.split(",")));
//            if (flag) {
//                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
//            }
//        } catch (Exception e) {
//            ajaxResult = setErrorJson(e.getMessage());
//            logger.error("deleteSasDclDtByList()--err", e);
//        }
//        return ajaxResult;
//    }

    /**
     * @param id 主键
     * @return AjaxResult
     * @throws
     * @Description: 复制当前商品
     */
    @RequestMapping(value = "/list/copy", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult copySasDclDt(String id) {
        AjaxResult ajaxResult = null;
        try {
            SasDclDt sasDclDt = sasDclDtService.selectById(id);
            sasDclDt.setUid(UUIDGenerator.getUUID());
            sasDclDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_3);    // 新增
            int sasDclSeqno = (int) getSasDclSeqNo(sasDclDt.getSeqNo()).getData();
            sasDclDt.setSasDclSeqno(sasDclSeqno);
            boolean flag = sasDclDtService.insert(sasDclDt);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_COPY_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_COPY_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteSasDclDtByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param uid
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 从正式表获取数据(涛哥专属)
     */
    @RequestMapping(value = "/list/change", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult changeSasDclDt(String uid, String seqNo) {
        AjaxResult ajaxResult = null;
        EntityWrapper<SasStockBsc> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("SEQ_NO", seqNo);
        SasStockBsc sasStockBsc = sasStockBscService.selectOne(entityWrapper);
        int max = 1;
        EntityWrapper<SasStockDt> wrapper = new EntityWrapper<>();
        wrapper.eq("SEQ_NO", seqNo);
        wrapper.orderBy("SAS_STOCK_SEQNO", false);
        List<SasStockDt> sasStockDtByList = sasStockDtService.selectList(wrapper);
        if (!sasStockDtByList.isEmpty()) {
            max = sasStockDtByList.get(0).getSasStockSeqno() + 1;
        }
        try {
            SasDclCusDt sasDclCusDt = sasDclCusDtService.selectById(uid);
            SasStockDt sasStockDt = new SasStockDt();
            sasStockDt.setSasStockSeqno(max);//商品序号
            sasStockDt.setSeqNo(seqNo);
            sasStockDt.setSasDclSeqno(sasDclCusDt.getSasDclSeqno()); //申报表序号
            sasStockDt.setOriactGdsSeqno(sasDclCusDt.getOriactGdsSeqno()); //备案序号
            sasStockDt.setDclUprcAmt(sasDclCusDt.getDclUprcAmt()); //申报单价
            String gdecd = sasDclCusDt.getGdecd();
            sasStockDt.setGdecd(gdecd); //商品编码
            sasStockDt.setGdsNm(sasDclCusDt.getGdsNm()); //商品名称
            if (StringUtil.isNotEmpty(gdecd) && gdecd.length()==10) {
                EntityWrapper<CodCusComplex> entity = new EntityWrapper<>();
                entity.eq("CODE_T", gdecd.substring(0, 8));
                entity.eq("CODE_S", gdecd.substring(8, 10));
                CodCusComplex codCusComplex = codCusComplexService.selectOne(entity);
                if(codCusComplex!=null){
                sasStockDt.setLawfUnitcd(codCusComplex.getUnit1());  //法定计量单位
                sasStockDt.setSecdLawfUnitcd(codCusComplex.getUnit2()); //法定第二计量单位
                }
            }
            double num1 = sasStockDtService.selectSum(sasStockBsc.getSasDclNo(), sasDclCusDt.getSasDclSeqno());
            double num2 = sasStockCusDtService.selectSum(sasStockBsc.getSasDclNo(), sasDclCusDt.getSasDclSeqno());
            sasStockDt.setDclQty(new BigDecimal((sasDclCusDt.getDclQty().doubleValue()-num1-num2), MathContext.DECIMAL32));
            sasStockDt.setGdsSpcfModelDesc(sasDclCusDt.getGdsSpcfModelDesc()); //商品规格型号
            sasStockDt.setDclUnitcd(sasDclCusDt.getDclUnitcd()); //申报计量单位代码
            sasStockDt.setDclCurrcd(sasDclCusDt.getDclCurrcd()); //申报币制代码
            if(sasStockDt.getDclUnitcd().equals(sasStockDt.getLawfUnitcd())){
                sasStockDt.setLawfQty(sasStockDt.getDclQty());
            }
            sasStockDt.setTotal(sasDclCusDt.getDclQty()); //商品总数量
            sasStockDt.setMtpckEndprdTypecd(sasDclCusDt.getMtpckEndprdTypecd()); //料件成品标志代码
            sasStockDt.setOperateType(sasStockBsc.getOperateType()); //操作类型
            sasStockDt.setStockTypecd(sasStockBsc.getStockTypecd()); //出入库单类型
            sasStockDt.setCopEntNo(sasStockBsc.getCopEntNo()); //企业备案号
            sasStockDt.setEtpsPreentNo(sasStockBsc.getEtpsPreentNo()); //企业预录入编号
            sasStockDt.setSasStockNo(sasStockBsc.getSasStockNo()); //出入库单编号
            if (sasStockDt != null) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasStockDt);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * @param uid
     * @return AjaxResult
     * @throws
     * @Description: 从正式表获取数据
     */
    @RequestMapping(value = "/list/getSasDclCusDt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getSasDclCusDt(String uid,String optype) {
        AjaxResult ajaxResult = null;
        try {
            SasDclCusDt sasDclCusDt = sasDclCusDtService.selectById(uid);
            SasDclDt sasDclDt = new SasDclDt();
            BeanUtils.copyProperties(sasDclCusDt,sasDclDt,"uid");
            sasDclDt.setChgTmsCnt(sasDclCusDt.getChgTmsCnt() + 1);
            switch (optype){
                case "modify":
                    sasDclDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_1);
                    break;
                case "delete":
                    sasDclDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_2);
                    break;
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDclDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description:
     */
    @RequestMapping(value = "/list/emsBwsCusDt/save", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getEmsBwsCusDt() {
        AjaxResult ajaxResult = null;
        try {
            EmsBwsCusDt emsBwsCusDt = emsBwsCusDtService.selectById(getParameter("id"));
            SasDclDt sasDclDt = setParameter(new SasDclDt());
            sasDclDt.setOriactGdsSeqno(emsBwsCusDt.getGdsSeqno().intValue()); // 底帐商品序号
            sasDclDt.setGdecd(emsBwsCusDt.getGdecd());// 商品序号
            sasDclDt.setGdsNm(emsBwsCusDt.getGdsNm());// 商品名称
            sasDclDt.setGdsSpcfModelDesc(emsBwsCusDt.getGdsSpcfModelDesc());   // 商品规格型号描述
            sasDclDt.setDclUnitcd(emsBwsCusDt.getDclUnitcd()); // 申报计量单位
            sasDclDt.setDclCurrcd(emsBwsCusDt.getDclCurrcd());//币制代码
            sasDclDt.setMtpckEndprdTypecd(ChkStatusConstant.MTPCK_ENDPRD_TYPECD_I);// 默认 I-料件
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDclDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getEmsBwsCusDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description:
     */
    @RequestMapping(value = "/list/emsCusImg/save", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getEmsCusImg() {
        AjaxResult ajaxResult = null;
        try {
            EmsCusImg emsCusImg = emsCusImgService.selectById(getParameter("id"));
            SasDclDt sasDclDt = setParameter(new SasDclDt());
            sasDclDt.setOriactGdsSeqno(emsCusImg.getGdsSeqno().intValue()); // 底帐商品序号
            sasDclDt.setGdecd(emsCusImg.getGdecd());// 商品序号
            sasDclDt.setGdsNm(emsCusImg.getGdsNm());// 商品名称
            sasDclDt.setGdsSpcfModelDesc(emsCusImg.getEndprdGdsSpcfModelDesc());   // 商品规格型号描述
            sasDclDt.setDclUnitcd(emsCusImg.getDclUnitcd()); // 申报计量单位
            sasDclDt.setDclCurrcd(emsCusImg.getDclCurrcd());//币制代码
            sasDclDt.setMtpckEndprdTypecd(emsCusImg.getMtpckEndprdTypecd());//料件成品序号
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDclDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getEmsCusImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description:
     */
    @RequestMapping(value = "/list/emsCusExg/save", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getEmsCusExg() {
        AjaxResult ajaxResult = null;
        try {
            EmsCusExg emsCusExg = emsCusExgService.selectById(getParameter("id"));
            SasDclDt sasDclDt = setParameter(new SasDclDt());
            sasDclDt.setOriactGdsSeqno(emsCusExg.getGdsSeqno().intValue()); // 底帐商品序号
            sasDclDt.setGdecd(emsCusExg.getGdecd());// 商品序号
            sasDclDt.setGdsNm(emsCusExg.getGdsNm());// 商品名称
            sasDclDt.setGdsSpcfModelDesc(emsCusExg.getEndprdGdsSpcfModelDesc());   // 商品规格型号描述
            sasDclDt.setDclUnitcd(emsCusExg.getDclUnitcd()); // 申报计量单位
            sasDclDt.setDclCurrcd(emsCusExg.getDclCurrcd());//币制代码
            sasDclDt.setMtpckEndprdTypecd(emsCusExg.getMtpckEndprdTypecd());//料件成品序号
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,sasDclDt);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getEmsCusExg()--err", e);
        }
        return ajaxResult;
    }

    private SasDclDt setParameter(SasDclDt sasDclDt){
        String seqNo = getParameter("seqNo");
        int chgTmsCnt = Integer.parseInt(getParameter("chgTmsCnt"));
        String sasDclNo = getParameter("sasDclNo");
        String etpsPreentNo = getParameter("etpsPreentNo");
//        sasDclDt.setUid(UUIDGenerator.getUUID());   // 主键
        sasDclDt.setSeqNo(seqNo);   // 单据编号
        sasDclDt.setSasDclSeqno((int)getSasDclSeqNo(seqNo).getData());  // 申报序号
        sasDclDt.setSasDclNo(sasDclNo); // 申报表序号
        sasDclDt.setEtpsPreentNo(etpsPreentNo);//企业预录入编号
        sasDclDt.setChgTmsCnt(chgTmsCnt);// 变更次数
        sasDclDt.setModfMarkcd(ChkStatusConstant.MODF_MARKCD_3);// 修改标记 3-新增
        return sasDclDt;
    }
}