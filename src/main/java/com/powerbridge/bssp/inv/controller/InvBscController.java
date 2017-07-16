package com.powerbridge.bssp.inv.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.security.SystemAuthorizingUser;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsCusExg;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.ems.service.IEmsCusExgService;
import com.powerbridge.bssp.ems.service.IEmsCusImgService;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsCusDtService;
import com.powerbridge.bssp.inv.entity.*;
import com.powerbridge.bssp.inv.service.*;
import com.powerbridge.bssp.sas.entity.SasDclCusDt;
import com.powerbridge.bssp.sas.entity.SasStockCus;
import com.powerbridge.bssp.sas.entity.SasStockCusDt;
import com.powerbridge.bssp.sas.service.ISasDclCusDtService;
import com.powerbridge.bssp.sas.service.ISasStockCusDtService;
import com.powerbridge.bssp.sas.service.ISasStockCusService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <p>
 * 保税核注清单表  前端控制器
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
@Controller
@RequestMapping("/inv")
@CrossOrigin
public class InvBscController extends BaseController {

    @Autowired
    private IInvBscService invBscService;

    @Autowired
    private IInvAcmpFormDtService invAcmpFormDtService;

    @Autowired
    private IInvExgService invExgService;

    @Autowired
    private IInvDecDtService invDecDtService;

    @Autowired
    private IInvDtService invDtService;

    @Autowired
    private IInvDecBscService invDecBscService;

    @Autowired
    private ISasDclCusDtService sasDclCusDtService;

    private Gson gson = null;

    public InvBscController(){
        gson=new Gson();
    }

    @Autowired
    private IEmsCusExgService emsCusExgService;

    @Autowired
    private IEmsCusImgService emsCusImgService;

    @Autowired
    private IEmsBwsCusDtService emsBwsCusDtService;

    @Autowired
    private ISasStockCusDtService sasStockCusDtService;

    @Autowired
    private ISasStockCusService sasStockCusService;

    @Autowired
    private IInvDclStockService invDclStockService;

    @Autowired
    private IInvDtSimpleService invDtSimpleService;


    /**
     * @param invBsc
     * @return EntityWrapper
     * @throws
     * @Description: 查询方法:(列表查询)
     */
 /*   private EntityWrapper getEntityWrapper(InvBsc invBsc) {
        EntityWrapper entityWrapper = new EntityWrapper<InvBsc>();
        if (StringUtil.isNotEmpty(invBsc.getDocType())) {
            entityWrapper.eq("DOC_TYPE",invBsc.getDocType());//根据业务类型过滤
        }
        if (StringUtil.isNotEmpty(invBsc.getBondInvtNo())) {
            entityWrapper.eq("BOND_INVT_NO", invBsc.getBondInvtNo()); // 保税清单编号
        }
        if (StringUtil.isNotEmpty(invBsc.getEtpsInnerInvtNo())) {
            entityWrapper.eq("ETPS_INNER_INVT_NO", invBsc.getEtpsInnerInvtNo()); // 企业内部清单编号
        }
        if (StringUtil.isNotEmpty(invBsc.getPutrecNo())) {
            entityWrapper.eq("PUTREC_NO", invBsc.getPutrecNo()); // 备案编号
        }
        if (StringUtil.isNotEmpty(invBsc.getSeqNo())) {
            entityWrapper.eq("SEQ_NO",invBsc.getSeqNo());
        }
        if (StringUtil.isNotEmpty(invBsc.getiEFlag())) {
            entityWrapper.eq("I_E_FLAG", invBsc.getiEFlag()); // 出入区标识
        }
        if (StringUtil.isNotEmpty(invBsc.getInvtStucd())) {
            entityWrapper.eq("INVT_STUCD", invBsc.getInvtStucd()); // 清单状态
        }
        if (StringUtil.isNotEmpty(invBsc.getBondInvtTypecd())) {
            entityWrapper.eq("BOND_INVT_TYPECD", invBsc.getBondInvtTypecd()); // 清单类型
        }
       *//* if (StringUtil.isNotEmpty(getParameter("decTimeStart"))) {
            entityWrapper.ge("DEC_TIME", getParameter("decTimeStart")); // 录入日期起始
        }
        if (StringUtil.isNotEmpty(getParameter("decTimeEnd"))) {
            entityWrapper.le("DEC_TIME", getParameter("decTimeEnd")); // 录入日期结束
        }*//*
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        if (StringUtil.isNotEmpty(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }*/

    /**
     * @param invBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入保税核注清单管理页面:(列表查询)
     */
//    @RequiresPermissions("invBsc:list:view")
    @RequestMapping(value = "/invBsc/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvBscList(InvBsc invBsc) {
        AjaxResult ajaxResult = null;
        try {
            //String docType = getParameter("docType");
       //     EntityWrapper entityWrapper = getEntityWrapper(invBsc);  //查询调用方法
            //  entityWrapper.eq("I_E_FLAG",invBsc.getiEFlag());//出入区标志过滤
            Page page = getPage();  // 分页
            BsspUtil.filterCopEnt(invBsc, null);
            Page<InvBsc> invBscPage = invBscService.selectByInveBsc(page, invBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invBscPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvBscList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param invBsc
     * @return AjaxResult
     * @throws
     * @Description: 新增保税核注清单
     */
//    @RequiresPermissions("invBsc:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/invBsc/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addInvBsc(InvBsc invBsc) {
        AjaxResult ajaxResult = null;
        try {
            invBsc.setUid(UUIDGenerator.getUUID());
            try{
                if(StringUtil.isNotEmpty(invBsc.getDocType())) {
                    if (invBsc.getDocType().equals("A0601")) {
                        DocTypeConstants.setDocType(invBsc, "bs");
                        invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_2);
                    } else if (invBsc.getDocType().equals("A0602")) {
                        DocTypeConstants.setDocType(invBsc, "jm");
                        invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_3);
                        invBsc.setImpexpMarkcd(invBsc.getiEFlag1());
                    } else if (invBsc.getDocType().equals("A0603")) {
                        DocTypeConstants.setDocType(invBsc, "wl");
                        invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_1);
                    } else if (invBsc.getDocType().equals("A0604")) {
                        DocTypeConstants.setDocType(invBsc, "jbbs");
                        invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_2);
                    } else if (invBsc.getDocType().equals("A0605")) {
                        DocTypeConstants.setDocType(invBsc, "jbwl");
                        invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_1);
                    }else if(invBsc.getDocType().equals("A0606")){
                        DocTypeConstants.setDocType(invBsc, "jdjg");
                        invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_1);
                    }
                }
            }catch (Exception e){
                return ajaxResult=setErrorJson(e.getMessage());
            }
            invBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            invBsc.setInvtStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);
            invBsc.setDeclareMark(ChkStatusConstant.DCL_TYPECD_1);
            invBsc.setChgTmsCnt(BigDecimal.valueOf(0));
            invBsc.setDecTime(DateUtil.formatDateTime(new Date()));
            invBsc.setInvtDclTime(DateUtil.formatDateTime(new Date()));
            invBsc.setVrfdedMarkcd("0"); //未核扣
            if (StringUtils.isNotEmpty(invBsc.getDocType())&&invBsc.getDocType().equals("A0606")){
                Map<String,Object> columnMap = new HashMap<String,Object>();
                columnMap.put("SAS_DCL_NO",invBsc.getApplyNo());
                List<SasDclCusDt> sasDclCusDtList = sasDclCusDtService.selectByMap(columnMap);
                List<InvDt> invDtList = new ArrayList<InvDt>();
                List<InvDtSimple> invDtSimpleList = new ArrayList<InvDtSimple>();
                if(sasDclCusDtList.size()>0){
                    int lj=0;
                    int cp =0;
                    for (int i=0;i<sasDclCusDtList.size();i++){
                      String  mtpckEndprdTypecd = sasDclCusDtList.get(i).getMtpckEndprdTypecd();
                        if (StringUtils.isNotEmpty(mtpckEndprdTypecd)){
                            if(mtpckEndprdTypecd.equals("I")){
                                lj++;
                                InvDt invDt = new InvDt();
                                BeanUtils.copyProperties(sasDclCusDtList.get(i),invDt);
                                invDt.setUid(UUIDGenerator.getUUID());
                                invDt.setSeqNo(invBsc.getSeqNo());
                                invDt.setPutrecSeqno(BigDecimal.valueOf(sasDclCusDtList.get(i).getOriactGdsSeqno()));
                                invDt.setGdsSeqno(BigDecimal.valueOf(lj));
                                invDt.setEndprdGdsSpcfModelDesc(sasDclCusDtList.get(i).getGdsSpcfModelDesc());
                                invDtList.add(invDt);
                            }else if (mtpckEndprdTypecd.equals("E")){
                                cp++;
                                InvDtSimple invDtSimple = new InvDtSimple();
                                BeanUtils.copyProperties(sasDclCusDtList.get(i),invDtSimple);
                                invDtSimple.setUid(UUIDGenerator.getUUID());
                                invDtSimple.setSeqNo(invBsc.getSeqNo());
                                invDtSimple.setGdsSeqno(BigDecimal.valueOf(cp));
                                invDtSimple.setPutrecSeqno(String.valueOf(sasDclCusDtList.get(i).getOriactGdsSeqno()));
                                invDtSimpleList.add(invDtSimple);
                            }
                        }
                    }
                    if(invDtList.size()>0)
                        invDtService.insertBatch(invDtList);
                    if (invDtSimpleList.size()>0)
                        invDtSimpleService.insertBatch(invDtSimpleList);
                }
            }
            boolean flag = invBscService.insert(invBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addInvBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param invBsc
     * @return AjaxResult
     * @throws
     * @Description: 申报
     */
//    @RequiresPermissions("invExg:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/invBsc/list/submit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult submitInvBsc(InvBsc invBsc) {
        Boolean flag=false;
        AjaxResult ajaxResult = null;
        try{
            EntityWrapper entityWrapper = new EntityWrapper<InvDt>();
            entityWrapper.eq("SEQ_NO",invBsc.getSeqNo());
            //没有清单商品不能进行申报
            List<InvDt> invImgList = invDtService.selectList(entityWrapper);
            Map resultMap = invDtService.checkInvDtList(invImgList); //清单商品数据检查
            if (null != resultMap.get("0")){
                ajaxResult = new AjaxResult(0 ,resultMap.get("0")+"");
                return  ajaxResult;
            }
            if(StringUtil.isNotEmpty(invBsc.getDocType())){
                if(invBsc.getDocType().equals("A0601")){//加工
                    invBsc.setiEFlag(invBsc.getiEFlag1());
                    invBsc.setImpexpMarkcd(invBsc.getiEFlag1());
                    DocTypeConstants.setDocType(invBsc, "bs");
                }else if(invBsc.getDocType().equals("A0602")){
                    DocTypeConstants.setDocType(invBsc, "jm");
                    invBsc.setImpexpMarkcd(invBsc.getiEFlag1());
                }else if(invBsc.getDocType().equals("A0603")){
                    invBsc.setiEFlag(invBsc.getiEFlag1());
                    invBsc.setImpexpMarkcd(invBsc.getiEFlag1());
                    DocTypeConstants.setDocType(invBsc, "wl");
                }else if (invBsc.getDocType().equals("A0604")) {
                    invBsc.setiEFlag(invBsc.getiEFlag1());
                    invBsc.setImpexpMarkcd(invBsc.getiEFlag1());
                    DocTypeConstants.setDocType(invBsc, "jbbs");
                    invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_2);
                } else if (invBsc.getDocType().equals("A0605")) {
                    invBsc.setiEFlag(invBsc.getiEFlag1());
                    invBsc.setImpexpMarkcd(invBsc.getiEFlag1());
                    DocTypeConstants.setDocType(invBsc, "jbwl");
                    invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_1);
                }
            }
        }catch (Exception e){
            return ajaxResult=setErrorJson(e.getMessage());
        }
        invBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
        invBsc.setInvtStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_A);
        invBsc.setChgTmsCnt(BigDecimal.valueOf(0));
        invBsc.setInvtDclTime(DateUtil.formatDateTime(new Date()));
        if(invBsc.getUid().isEmpty()){
            invBsc.setUid(UUIDGenerator.getUUID());
            invBsc.setDeclareMark(ChkStatusConstant.DCL_TYPECD_1);
            flag = invBscService.insert(invBsc);
        }else {
            flag = invBscService.updateById(invBsc);
        }
        if(flag){
            try {
                BsspUtil.checkStatusDeclare(invBsc,null,null,true);
                invBsc.setInvtStucd(ChkStatusConstant.EMAPV_MARKCD_SAS_B);
                if(StringUtil.isNotEmpty(invBsc.getDocType())){
                    if(invBsc.getDocType().equals("A0601")){//加工
                        invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_2);
                    }else if(invBsc.getDocType().equals("A0602")){
                        invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_3);
                    }else if(invBsc.getDocType().equals("A0603")){
                        invBsc.setBillType(ChkStatusConstant.BILLS_TYPE_1);
                    }
                }
                invBsc.setRetChannel("");
                invBscService.updateById(invBsc);
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,invBsc);
            } catch (Exception e) {
                ajaxResult=setErrorJson(e.getMessage());
            }
        }else{
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUBMIT_FAIL);
        }
        return ajaxResult;
    }

    /**
     * @param InvImg
     * @return AjaxResult
     * @throws
     * @Description: 新增报税核注清单料件商品明细
     *//*
//    @RequiresPermissions("invBsc:list:add")
    @RequestMapping(value = "/invImg/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addInvImg(InvImg invImg) {
    	AjaxResult ajaxResult = null;
    	try {

    		invImg.setUid(UUIDGenerator.getUUID());
    		invImg.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    		invImg.setCreateBy(String.valueOf(SingletonLoginUtils.getSystemUserId()));
    		invImg.setCreateName(SingletonLoginUtils.getSystemUserName());
    		invImg.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    		invImg.setUpdateBy(String.valueOf(SingletonLoginUtils.getSystemUserId()));
    		invImg.setUpdateName(SingletonLoginUtils.getSystemUserName());
    		boolean flag = invImgService.insert(invImg);
    		if (flag) {
    			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invImg);
    		} else {
    			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
    		logger.error("addInvBsc()--err", e);
    	}
    	return ajaxResult;
    }

    *//**
     * @param InvImg
     * @return AjaxResult
     * @throws
     * @Description: 新增报税核注清单成品商品明细
     *//*
//    @RequiresPermissions("invBsc:list:add")
    @RequestMapping(value = "/invImg/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addInvImg(InvExg invExg) {
    	AjaxResult ajaxResult = null;
    	try {

    		invExg.setUid(UUIDGenerator.getUUID());
    		invExg.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    		invExg.setCreateBy(String.valueOf(SingletonLoginUtils.getSystemUserId()));
    		invExg.setCreateName(SingletonLoginUtils.getSystemUserName());
    		invExg.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    		invExg.setUpdateBy(String.valueOf(SingletonLoginUtils.getSystemUserId()));
    		invExg.setUpdateName(SingletonLoginUtils.getSystemUserName());
    		boolean flag = invExgService.insert(invExg);
    		if (flag) {
    			ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invExg);
    		} else {
    			ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
    		logger.error("addInvBsc()--err", e);
    	}
    	return ajaxResult;
    }
*/
    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("invBsc:list:view")
    @RequestMapping(value = "/invBsc/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvBsc invBsc = invBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param invBsc
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("invBsc:list:edit")
    @RequestMapping(value = "/invBsc/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateInvBsc(InvBsc invBsc) {
        AjaxResult ajaxResult = null;
        try {
            if (StringUtil.isEmpty(invBsc.getUid())) {
                return json(MessageConstants.BSSP_STATUS_CODE_NOTEXIST, "主键不能为空");
            }
            invBsc.setUpdateBy(String.valueOf(SingletonLoginUtils.getSystemUserId()));
            invBsc.setUpdateName(SingletonLoginUtils.getSystemUserName());
            boolean flag = invBscService.updateById(invBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,invBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateInvBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("invBsc:list:delete")
    @RequestMapping(value = "/invBsc/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteInvBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvBsc invBsc = invBscService.selectById(id);
            if (invBsc == null) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该记录不存在");
            }
            // boolean isDelete = isDelete(invBsc.getInvtStucd());
            String chkStatus = invBsc.getChkStatus();
            //S:暂存  N：审批不通过 可修改
            if ((!StringUtil.isEmpty(chkStatus)) && (!chkStatus.equals("S") && !chkStatus.equals("N"))) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该状态数据无法删除");
            }
            // 判断当前单据是否可以删除
            invBscService.txDeleteInvBsc(id, invBsc.getSeqNo());
            boolean flag = invBscService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteInvBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("invBsc:list:delete")
    @RequestMapping(value = "/invBsc/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteInvBscByList(String idList,String docType) {
        AjaxResult ajaxResult = null;
        try {
            List list = Arrays.asList(idList.split(","));   // idList 转换格式
            List listBySeqNo = new ArrayList();
            List<InvBsc> invBscList = invBscService.selectBatchIds(list);
            for (InvBsc invBsc : invBscList) {
                listBySeqNo.add(invBsc.getSeqNo());
                String chkStatus = invBsc.getChkStatus();
                //S:暂存  N：审批不通过 可修改
                if ((!StringUtil.isEmpty(chkStatus)) && (!chkStatus.equals("S") && !chkStatus.equals("N"))) {
                    return json(MessageConstants.BSSP_STATUS_FAIL, "该状态数据无法删除");
                }
            }
            if(StringUtil.isNotEmpty(docType)){
                if(docType.equals("A0604") || docType.equals("A0605")){ //加工、物流集报
                    List<SasStockCus> sasNewStockBscList = new ArrayList<SasStockCus>();
                    EntityWrapper<SasStockCus> entityWrapper1 = new EntityWrapper<>();
                    entityWrapper1.in("RLT_BOND_INVT_NO", listBySeqNo);
                    List<SasStockCus> sasStockBscList = sasStockCusService.selectList(entityWrapper1);
                    for (int i=0;i<sasStockBscList.size();i++){
                        SasStockCus sasStockBsc = sasStockBscList.get(i);
                        sasStockBsc.setRltBondInvtNo("");
                        sasStockBsc.setCentralizedDclTypecd("1");//集报标志为未集报
                        sasNewStockBscList.add(sasStockBsc);
                    }
                    if(sasNewStockBscList.size()>0){
                        sasStockCusService.updateBatchById(sasNewStockBscList);
                        EntityWrapper<InvDclStock> entityWrapper2 = new EntityWrapper<>();
                        entityWrapper1.in("BOND_INVT_NO", listBySeqNo);
                        invDclStockService.delete(entityWrapper2);//删除出入库单证明细
                    }
                }else if(docType.equals("A0606")){  //简单加工
                    Map<String,Object> columnMap = new HashMap<String,Object>();
                    columnMap.put("SEQ_NO",listBySeqNo.get(0));
                    invDtSimpleService.deleteByMap(columnMap);
                }
            }
            boolean flag = invBscService.txDeleteInvBscByList(list, listBySeqNo);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }else{
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteInvBscByList()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param retChannel
     * @return boolean
     * @throws
     * @Description: 判断是否可以删除
     */
    private boolean isDelete(String retChannel) {
        boolean flag = false;
//        审批状态代码=“A-暂存、2-退单 3-删单、Z-入库失败”
        if ("A".equals(retChannel) || "2".equals(retChannel) || "3".equals(retChannel) || "Z".equals(retChannel)) {
            flag = true;
        }
        return flag;
    }

    //-----------------------------------------------------------------分界线 核注清单在上 料件在下------------------------------------------------------------------------------

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 进入保税核注清单料件管理页面:(列表查询)
     */
//    @RequiresPermissions("invImg:list:view")
    @RequestMapping(value = "/invImg/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvImgList(InvDt invImg) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            Page<InvDt> invImgPage = invDtService.selectByInvImg(page,invImg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invImgPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvImgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 简单加工成品明细
     */
//    @RequiresPermissions("invImg:list:view")
    @RequestMapping(value = "/invDtSimple/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvDtSimpleList(InvDtSimple invDtSimple) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            Page<InvDtSimple> invDtSimplePage = invDtSimpleService.selectByInvDtSimple(page,invDtSimple);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invDtSimplePage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvImgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量新增保税核注清单料件
     */
//    @RequiresPermissions("invImg:list:add")
    @RequestMapping(value = "/invImg/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addInvImgByList(String idList,String mtpckEndprdMarkcd,String seqNo,String docType,String iEFlag,String putrecNo) {
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
                if(StringUtil.isNotEmpty(docType)){
                    if (docType.equals("A0603")){ //物流
                        EmsBwsCusDt emsBwsCusDt = emsBwsCusDtService.selectById(idList);
                        Map<String,Object> columnMap = new HashMap<String,Object>();
                        columnMap.put("SEQ_NO",seqNo);
                        List<InvDt> invImgList = invDtService.selectByMap(columnMap);
                        InvDt invImg = new InvDt();
                        BeanUtils.copyProperties(emsBwsCusDt,invImg);
                        invImg.setSeqNo(seqNo);
                        invImg.setBondInvtNo(seqNo);
                        invImg.setDclQty(null);
                        invImg.setChgTmsCnt(BigDecimal.valueOf(0));
                        invImg.setPutrecSeqno(emsBwsCusDt.getGdsSeqno());
                        invImg.setiEFlag(iEFlag);
                        if(invImgList.size()>0){
                            invImg.setGdsSeqno(BigDecimal.valueOf(invImgList.size()+1));
                        }else{
                            invImg.setGdsSeqno(BigDecimal.valueOf(1));
                        }
                        invImg.setUid(UUIDGenerator.getUUID());
                        invDtService.insert(invImg);
                        ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,invImg);
                        flag = true;
                    }else if(docType.equals("A0604") || docType.equals("A0605")){//加工/物流集报
                        Map<String,Object> columnMap = new HashMap<String,Object>();
                        columnMap.put("SEQ_NO",seqNo);
                        List<InvDt> invNewImgList = new ArrayList<InvDt>();
                        List<InvDt> invImgList = new ArrayList<InvDt>();
                        invImgList = invDtService.selectByMap(columnMap);
                        List<SasStockCus> sasNewStockBscList = new ArrayList<SasStockCus>();
                        EntityWrapper<SasStockCusDt> entityWrapper = new EntityWrapper<>();
                        EntityWrapper<SasStockCus> entityWrapper1 = new EntityWrapper<>();
                        entityWrapper.in("SEQ_NO", idList);
                        entityWrapper1.in("SEQ_NO", idList);
                        List<SasStockCus> sasStockBscList = sasStockCusService.selectList(entityWrapper1);
                        List<InvDclStock> invDclStockList = new ArrayList<InvDclStock>();
                        for (int i=0;i<sasStockBscList.size();i++){
                            SasStockCus sasStockCus = sasStockBscList.get(i);
                            sasStockCus.setRltBondInvtNo(seqNo); ////回填核注清单编号
                            sasStockCus.setCentralizedDclTypecd("2");//集报标志为已集报
                            sasNewStockBscList.add(sasStockCus);
                            InvDclStock invDclStock = new InvDclStock();
                            invDclStock.setUid(UUIDGenerator.getUUID());
                            invDclStock.setBondInvtNo(seqNo);
                            invDclStock.setSasStockNo(sasStockCus.getSasStockNo());
                            invDclStockList.add(invDclStock);
                        }
                        if(sasNewStockBscList.size()>0){
                            sasStockCusService.updateBatchById(sasNewStockBscList);
                            invDclStockService.insertBatch(invDclStockList);
                        }
                        List<SasStockCusDt> sasStockDtList = sasStockCusDtService.selectList(entityWrapper);
                        if(sasStockDtList.size()>0){
                            for (int i=0;i<sasStockDtList.size();i++){
                                InvDt invImg = new InvDt();
                                BeanUtils.copyProperties(sasStockDtList.get(i),invImg);
                                invImg.setSeqNo(seqNo);
                                invImg.setBondInvtNo(seqNo);
                                invImg.setDclQty(null);
                                invImg.setChgTmsCnt(BigDecimal.valueOf(0));
                                invImg.setiEFlag(iEFlag);
                                invImg.setPutrecSeqno(BigDecimal.valueOf(sasStockDtList.get(i).getSasStockSeqno()));
                                invImg.setEndprdGdsSpcfModelDesc(sasStockDtList.get(i).getGdsSpcfModelDesc());
                                invImg.setDclQty(sasStockDtList.get(i).getDclQty());
                                invImg.setSasSeqNo(sasStockDtList.get(i).getSeqNo());
                                if(invImgList.size()>0){
                                    invImg.setGdsSeqno(BigDecimal.valueOf(invImgList.size()+1));
                                }else{
                                    invImg.setGdsSeqno(BigDecimal.valueOf(1));
                                }
                                invImg.setUid(UUIDGenerator.getUUID());
                                invImgList.add(invImg);
                                invNewImgList.add(invImg);
                            }
                        }
                        if(invNewImgList.size()>0){
                            invDtService.insertBatch(invNewImgList);
                        }
                        ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
                        flag = true;
                    }else{ //加贸、加工
                        if (StringUtil.isNotEmpty(mtpckEndprdMarkcd)){
                            if (mtpckEndprdMarkcd.equals("I")){
                                EmsCusImg emsCusImg = emsCusImgService.selectById(idList);
                                Map<String,Object> columnMap = new HashMap<String,Object>();
                                columnMap.put("SEQ_NO",seqNo);
                                List<InvDt> invImgList = invDtService.selectByMap(columnMap);
                                InvDt invImg = new InvDt();
                                BeanUtils.copyProperties(emsCusImg,invImg);
                                invImg.setSeqNo(seqNo);
                                invImg.setBondInvtNo(seqNo);
                                invImg.setDclQty(null);
                                invImg.setChgTmsCnt(BigDecimal.valueOf(0));
                                invImg.setPutrecSeqno(emsCusImg.getGdsSeqno());
                                invImg.setiEFlag(iEFlag);
                                if(invImgList.size()>0){
                                    invImg.setGdsSeqno(BigDecimal.valueOf(invImgList.size()+1));
                                }else{
                                    invImg.setGdsSeqno(BigDecimal.valueOf(1));
                                }
                                invImg.setUid(UUIDGenerator.getUUID());
                                invDtService.insert(invImg);
                                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,invImg);
                                flag = true;
                            }else if (mtpckEndprdMarkcd.equals("E")){
                                EmsCusExg emsCusexg = emsCusExgService.selectById(idList);
                                Map<String,Object> columnMap = new HashMap<String,Object>();
                                columnMap.put("SEQ_NO",seqNo);
                                List<InvDt> invImgList = invDtService.selectByMap(columnMap);
                                InvDt invImg = new InvDt();
                                BeanUtils.copyProperties(emsCusexg,invImg);
                                invImg.setSeqNo(seqNo);
                                invImg.setBondInvtNo(seqNo);
                                invImg.setDclQty(null);
                                invImg.setChgTmsCnt(BigDecimal.valueOf(0));
                                invImg.setPutrecSeqno(emsCusexg.getGdsSeqno());
                                invImg.setiEFlag(iEFlag);
                                if(invImgList.size()>0){
                                    invImg.setGdsSeqno(BigDecimal.valueOf(invImgList.size()+1));
                                }else{
                                    invImg.setGdsSeqno(BigDecimal.valueOf(1));
                                }
                                invImg.setUid(UUIDGenerator.getUUID());
                                invDtService.insert(invImg);
                                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,invImg);
                                flag = true;
                            }
                        }
                    }
                }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addInvImgByList()--err", e);
        }
        if (!flag) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面(保税核注清单料件)
     */
//    @RequiresPermissions("invImg:list:view")
    @RequestMapping(value = "/invImg/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvImg(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvDt invImg = invDtService.selectById(id);
            // invImg.setGdsSeqno(invImgService.getMaxSeqno(invImg.getSeqNo()));; //获取最大商品序号
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invImg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvImg()--err", e);
        }
        return ajaxResult;
    }


    @RequestMapping(value = "/invDtSimple/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvDtSimple(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvDtSimple invDtSimple = invDtSimpleService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invDtSimple);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param invImg
     * @return AjaxResult
     * @throws
     * @Description: 修改数据(保税核注清单料件)
     */
//    @RequiresPermissions("invImg:list:edit")
    @RequestMapping(value = "/invImg/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateInvImg(InvDt invImg) {
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
            if(StringUtil.isEmpty(invImg.getUid())){
                invImg.setUid(UUIDGenerator.getUUID());
                flag = invDtService.insert(invImg);
            }else{
                flag = invDtService.updateAllColumnById(invImg);
            }
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateInvImg()--err", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/invDtSimple/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateInvDtSimple(InvDtSimple invDtSimple) {
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
            if(StringUtil.isEmpty(invDtSimple.getUid())){
                invDtSimple.setUid(UUIDGenerator.getUUID());
                flag = invDtSimpleService.insert(invDtSimple);
            }else{
                flag = invDtSimpleService.updateAllColumnById(invDtSimple);
            }
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateInvImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param  id
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据(保税核注清单料件)
     */
//    @RequiresPermissions("invImg:list:delete")
    @RequestMapping(value = "/invImg/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteInvImg(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            boolean flag = invDtService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteInvImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作(保税核注清单料件)
     */
//    @RequiresPermissions("invImg:list:delete")
    @RequestMapping(value = "/invImg/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteInvImgByList(String idList,String seqNo,String delType) {
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
            List list = Arrays.asList(idList.split(","));   // idList 转换格式
            if (StringUtils.isNotEmpty(delType)){
                if (!delType.equals("cp")){
                     flag = invDtService.deleteBatchIds(list);
                     resetOrder(seqNo);
                    if (flag) {
                        HashMap<String, Object> columnMap = new HashMap<String,Object>();
                        columnMap.put("SEQ_NO", seqNo);
                        invDecDtService.deleteByMap(columnMap);
                        invDecBscService.deleteByMap(columnMap);
                }
             }else{
                    flag = invDtSimpleService.deleteBatchIds(list);
                    resetSimpleOrder(seqNo);
                }
            }
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteInvImgByList()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作(出入库单)
     */
//    @RequiresPermissions("invImg:list:delete")
    @RequestMapping(value = "/deleteSasList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteSasList(String idList) {
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
            List list = Arrays.asList(idList.split(","));   // idList 转换格式
            SasStockCus sasStockCus = sasStockCusService.selectById(idList);
            if (null != sasStockCus){
                String seqNo = sasStockCus.getRltBondInvtNo();
               String sasSeqNo = sasStockCus.getSeqNo();
                EntityWrapper entityWrapper = new EntityWrapper<InvDt>();
                entityWrapper.eq("SEQ_NO",seqNo);
                invDecDtService.delete(entityWrapper);
                entityWrapper.eq("SAS_SEQ_NO",sasSeqNo);
                invDtService.delete(entityWrapper);
                resetOrder(seqNo);//删除后重新排序
                sasStockCus.setCentralizedDclTypecd("1");//未集报
                sasStockCus.setRltBondInvtNo(null);
                flag = sasStockCusService.updateAllColumnById(sasStockCus);
            }
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }else{
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteInvImgByList()--err", e);
        }
        return ajaxResult;
    }


    public void resetOrder(String seqNo){
         Map columnMap = new HashMap<String,Object>();
         columnMap.put("SEQ_NO",seqNo);
        List<InvDt> invDtList =  invDtService.selectByMap(columnMap);
        List<InvDt> invNewDtList = new ArrayList<InvDt>();
         for(int i=0;i<invDtList.size();i++){
             InvDt invDt =invDtList.get(i);
             invDt.setGdsSeqno(BigDecimal.valueOf(i+1));
             invNewDtList.add(invDt);
         }
         if(invNewDtList.size()>0){
             invDtService.insertOrUpdateBatch(invNewDtList);
         }

    }


    public void resetSimpleOrder(String seqNo){
        Map columnMap = new HashMap<String,Object>();
        columnMap.put("SEQ_NO",seqNo);
        List<InvDtSimple> invDtSimpleList =  invDtSimpleService.selectByMap(columnMap);
        List<InvDtSimple> invNewDtSimpleList = new ArrayList<InvDtSimple>();
        for(int i=0;i<invNewDtSimpleList.size();i++){
            InvDtSimple invDtSimple =invDtSimpleList.get(i);
            invDtSimple.setGdsSeqno(BigDecimal.valueOf(i+1));
            invNewDtSimpleList.add(invDtSimple);
        }
        if(invNewDtSimpleList.size()>0){
            invDtSimpleService.insertOrUpdateBatch(invNewDtSimpleList);
        }

    }



    //-----------------------------------------------------------------分界线 料件清单在上 报关商品在下------------------------------------------------------------------------------



    /**
     * @param invDeclareBill
     * @return AjaxResult
     * @throws
     * @Description: 进入保税核注清单报关商品管理页面:(列表查询)
     */
//    @RequiresPermissions("invExg:list:view")
    @RequestMapping(value = "/invDeclare/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showInvExgList(InvDecDt invDeclareBill) {
        AjaxResult ajaxResult = null;
        try {
          /*  String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            EntityWrapper entityWrapper = new EntityWrapper();  //查询调用方法
            if (StringUtil.isNotEmpty(seqNo)) {
                entityWrapper.eq("SEQ_NO", seqNo);
            }
            if (StringUtil.isNotEmpty(sort)) {
                entityWrapper.orderBy(sort, sortOrder);  // 排序
            }*/
            Page page = getPage();  // 分页
            // Page<InvExg> invExgPage = invExgService.selectPage(page, entityWrapper);
            Page<InvDecDt> invDeclareBillPage = invDecDtService.selectByDeclare(page,invDeclareBill);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invDeclareBillPage.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showInvExgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 生成报关商品
     */
//    @RequiresPermissions("invExg:list:add")
    @RequestMapping(value = "/invExg/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addInvExgByList(String seqNo,String orgFlag) {
        HashMap<String, String> imgMap = new HashMap<String,String>();
        imgMap.put("seqNo", seqNo);
        imgMap.put("orgFlag", orgFlag);
        AjaxResult ajaxResult = null;
        try {
            List<InvExg> invExgList = new ArrayList<InvExg>();
          /*  List<InvImg> invImgList = invImgService.selectByBondInvtImgList();  // 获取归并组合后的报关商品数据
            for (int i=0;i<invImgList.size();i++){
                InvImg invImg = invImgList.get(i);
                InvExg invExg = new InvExg();
                BeanUtils.copyProperties(invImg, invExg);
                invExg.setUid(UUIDGenerator.getUUID());
                invExg.setEntryGdsSeqno(BigDecimal.valueOf(i+1));  // 生成报关商品序号
               
                invExgList.add(invExg);
            }*/

            boolean flag = invExgService.txInsertByInvExg(invExgList,seqNo,orgFlag);     // 用于生成报关商品
//            boolean flag = invExgServ
            if (flag) {
                //ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invImgList);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addInvExgByList()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面(保税核注清单报关商品)
     */
//    @RequiresPermissions("invExg:list:view")
    @RequestMapping(value = "/invExg/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editInvExg(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            InvExg invExg = invExgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invExg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editInvExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param invExg
     * @return AjaxResult
     * @throws
     * @Description: 修改数据(保税核注清单报关商品)
     */
//    @RequiresPermissions("invExg:list:edit")
    @RequestMapping(value = "/invExg/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateInvExg(InvExg invExg) {
        AjaxResult ajaxResult = null;
        try {
            boolean flag = invExgService.updateById(invExg);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateInvExg()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: (自动合并生成报关单序号回填报关单商品序号)
     */
    @RequestMapping(value = "/list/auto",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public  AjaxResult autoMergeBill(String seqNo){
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
            List<InvDt> invImgList = invDtService.autoGetInvtImgList(seqNo);// 分组合并数据
            if(invImgList.size()>0){
                for (int i=0;i<invImgList.size();i++){
                    Map<String,Object> columnMap = new HashMap<String,Object>();
                    columnMap.put("SEQ_NO",invImgList.get(i).getSeqNo());
                    columnMap.put("GDECD",invImgList.get(i).getGdecd());
                    columnMap.put("DCL_UNITCD",invImgList.get(i).getDclUnitcd());
                    columnMap.put("NATCD",invImgList.get(i).getNatcd());
                    columnMap.put("DCL_CURRCD",invImgList.get(i).getDclCurrcd());
                    columnMap.put("LVYRLF_MODECD",invImgList.get(i).getLvyrlfModecd());
                    List<InvDt> oldInvImgList = invDtService.selectByMap(columnMap);
                    for(int j=0;j<oldInvImgList.size();j++){
                        oldInvImgList.get(j).setEntryGdsSeqno(BigDecimal.valueOf(i+1));//回填报关单商品序号
                        invDtService.updateById(oldInvImgList.get(j));
                    }
                }
            }
            flag= true;
        }catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateInvExg()--err", e);
            return  ajaxResult;
        }
        if (flag) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } else {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: (一对一合并)
     */
    @RequestMapping(value = "/list/sinGalMerge",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public  AjaxResult oneToOneMergeBill(String seqNo){
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
            Map<String,Object> columnMap = new HashMap<String,Object>();
            columnMap.put("SEQ_NO",seqNo);
            List<InvDt> invImgList = invDtService.selectByMap(columnMap);
            List<InvDt> invNewImgList = new ArrayList<>();
            for(InvDt invImg:invImgList){
                invImg.setEntryGdsSeqno(invImg.getGdsSeqno());
                invNewImgList.add(invImg);
            }
            if(invNewImgList.size()>0){
                invDtService.insertOrUpdateBatch(invNewImgList);
            }
            flag=true;
        }catch (Exception e){
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateInvExg()--err", e);
            return  ajaxResult;
        }

        if (flag) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } else {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: (报关数据检查)
     */
    @RequestMapping(value = "/list/declareCheck",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public  AjaxResult delcareCheck(String seqNo,String mergeType){
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
            Map<String,Object> columnMap = new HashMap<String,Object>();
            columnMap.put("SEQ_NO",seqNo);
            List<InvDt> invImgList = invDtService.selectByMap(columnMap);
            String tempStr = "";
            for(InvDt invImg:invImgList){
                if (null ==invImg.getEntryGdsSeqno()){
                    tempStr+=invImg.getGdsSeqno()+",";
                }
            }
            //报关单商品序号检查
            if(StringUtil.isNotEmpty(tempStr)){
                tempStr = tempStr.substring(0,tempStr.length()-1);
                ajaxResult = new AjaxResult(0 ,"商品序号"+tempStr+"所在报关单商品序号栏位不能为空");
                return  ajaxResult;
            }
            //相同报关单商品序号 五项合并条件必须一致检查
            String checkResult = invDtService.checkDeclareData(seqNo);
            if (StringUtil.isNotEmpty(checkResult)){
                ajaxResult = new AjaxResult(0 ,"商品序号"+checkResult+"存在相同报关单的商品序号的五项合并条件必须一致，不允许生成报关单；");
                return  ajaxResult;
            }
            //生成的报关单商品序号不能超过50项
           if(StringUtil.isNotEmpty(mergeType) && mergeType.equals("2")){
               if(invImgList.size()>50){
                   ajaxResult = new AjaxResult(0 ,"生成的报关单商品序号不能超过50项");
                   return  ajaxResult;
               }
           }else if(StringUtil.isNotEmpty(mergeType) && mergeType.equals("1")){
               invImgList.clear();
               invImgList = invDtService.autoGetInvtImgList(seqNo);
               if(invImgList.size()>50){
                   ajaxResult = new AjaxResult(0 ,"生成的报关单商品序号不能超过50项");
                   return  ajaxResult;
               }
           }else if(StringUtil.isNotEmpty(mergeType) && mergeType.equals("1")){
               invImgList.clear();
               invImgList = invDtService.handlerGetInvtImgList(seqNo);
               if(invImgList.size()>50){
                   ajaxResult = new AjaxResult(0 ,"生成的报关单商品序号不能超过50项");
                   return  ajaxResult;
               }
           }
            flag = true;
        }catch (Exception e){
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateInvExg()--err", e);
            return ajaxResult;
        }
        if (flag) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } else {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 生成报关单
     */
    @RequestMapping(value = "/list/generateBill",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public AjaxResult generateEntryBill(String seqNo,String mergeType){
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
            Map<String,Object> columnMap = new HashMap<String,Object>();
            columnMap.put("SEQ_NO",seqNo);
            List<InvBsc> invBscList = invBscService.selectByMap(columnMap);
            if (invBscList.size()>0){
                invDecBscService.deleteByMap(columnMap);
                invDecDtService.deleteByMap(columnMap);
                InvDecBsc invDecBsc = new InvDecBsc();
                BeanUtils.copyProperties(invBscList.get(0),invDecBsc);
                if(StringUtil.isNotEmpty(invBscList.get(0).getDclcusFlag()) && invBscList.get(0).getDclcusFlag().equals("1")){
                    if(StringUtil.isNotEmpty(invBscList.get(0).getDclcusTypecd()) && invBscList.get(0).getDclcusTypecd().equals("1")){
                        invDecBsc.setDclEtpsno(invBscList.get(0).getCorrEntryDclEtpsno());
                        invDecBsc.setDclEtpsNm(invBscList.get(0).getCorrEntryDclEtpsNm());
                        invDecBsc.setDclEtpsSccd(invBscList.get(0).getCorrEntryDclEtpsSccd());
                    }/*else if(StringUtil.isNotEmpty(invBscList.get(0).getDclcusTypecd()) && invBscList.get(0).getDclcusTypecd().equals("2")){
                        invDecBsc.setBizopEtpsno(invBscList.get(0).getRltEntryRcvgdEtpsno());
                        invDecBsc.setBizopEtpsNm(invBscList.get(0).getRltEntryRcvgdEtpsNm());
                        invDecBsc.setBizopEtpsSccd(invBscList.get(0).getRltEntryRvsngdEtpsSccd());
                        invDecBsc.setRcvgdEtpsno(invBscList.get(0).getRltEntryBizopEtpsno());
                        invDecBsc.setRcvgdEtpsNm(invBscList.get(0).getRltEntryBizopEtpsNm());
                        invDecBsc.setRvsngdEtpsSccd(invBscList.get(0).getRltEntryBizopEtpsSccd());
                        invDecBsc.setDclEtpsno(invBscList.get(0).getRltEntryDclEtpsno());
                        invDecBsc.setDclEtpsNm(invBscList.get(0).getRltEntryDclEtpsNm());
                        invDecBsc.setDclEtpsSccd(invBscList.get(0).getRltEntryDclEtpsSccd());
                    }*/
                }
                invDecBscService.insert(invDecBsc);
                List<InvDt> invImgList = new ArrayList<InvDt>();
                if(mergeType.equals("2")){//一对一合并
                    invImgList = invDtService.selectByMap(columnMap);
                }else{//自动合并、手动合并
                    invImgList = invDtService.handlerGetInvtImgList(seqNo);
                }
                for (int i=0;i<invImgList.size();i++){
                    InvDecDt invDeclareBill = new InvDecDt();
                    BeanUtils.copyProperties(invImgList.get(i),invDeclareBill);
                    invDeclareBill.setGdsSeqno(BigDecimal.valueOf(i+1));
                    invDeclareBill.setUid(UUIDGenerator.getUUID());
                    invDeclareBill.setChgTmsCnt(BigDecimal.valueOf(0));
                    invDecDtService.insert(invDeclareBill);
                }
                flag = true;
            }
        }catch (Exception e){
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateInvExg()--err", e);
            return  ajaxResult;
        }
        if (flag) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } else {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        }
        return  ajaxResult;
    }

    @RequestMapping(value = "/invBsc/list/getStockDt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getSasStockDt(String seqNoList) {
        AjaxResult ajaxResult = null;
        if (StringUtil.isNotEmpty(seqNoList)) {
            try {
                EntityWrapper<InvDt> entityWrapper = new EntityWrapper<InvDt>();
                entityWrapper.in("SEQ_NO", seqNoList);
                List<InvDt> list = invDtService.selectList(entityWrapper);
                if (list.size() > 0) {
                    ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, list);
                } else {
                    ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL,null);
                }
            } catch (Exception e) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL,null);
                logger.error("getSasStockDt()--err", e);
            }
        }
        return ajaxResult;
    }

    /**
     * 获取最大的商品序号
     * @param
     * @return 商品序号
     */
    @RequestMapping(value = "/list/selectMaxSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String selectMaxSeqno(String seqNo,String btnFlag){
        Map<String, Object> json = new HashMap<String,Object>();
        try{
            BigDecimal maxGdsSeqno;
            Map<String,Object> columnMap = new HashMap<String,Object>();
            columnMap.put("SEQ_NO",seqNo);
            if (null!=btnFlag && btnFlag.equals("cp")){
                List<InvDtSimple> invDtSimpleList = invDtSimpleService.selectByMap(columnMap);
                if(invDtSimpleList.size()>0){
                    maxGdsSeqno = BigDecimal.valueOf(invDtSimpleList.size()+1);
                }else{
                    maxGdsSeqno = BigDecimal.valueOf(1);
                }
            }else{
                List<InvDt> invImgList = invDtService.selectByMap(columnMap);
                if(invImgList.size()>0){
                    maxGdsSeqno = BigDecimal.valueOf(invImgList.size()+1);
                }else{
                    maxGdsSeqno = BigDecimal.valueOf(1);
                }
            }

            //maxGdsSeqno=maxGdsSeqno.add(new BigDecimal(1));//取最大值加1
            json=transJson("1","获取商品序号成功",maxGdsSeqno);
        } catch (Exception e) {
            json=transJson("0","获取商品序号失败",1);
            logger.error("selectMaxGdsSeqno()--error", e);
        }
        String jsonStr = gson.toJson(json);
        return jsonStr;
    }

    /**
     * 物流出区选择已添加账册商品
     * @param emsNo
     * @return
     */
    @RequestMapping(value = "/list/selectWlGds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectWlGds(String emsNo){
        AjaxResult ajaxResult = null;
        Map<String, Object> json = new HashMap<String,Object>();
        try{
            String seqNo;
            Map<String,Object> columnMap = new HashMap<String,Object>();
            columnMap.put("PUTREC_NO",emsNo);
            List<InvBsc> invBscList = invBscService.selectByMap(columnMap);
            List<InvDt> invImgList = new ArrayList<>();
            for (int i=0;i<invBscList.size();i++){
                seqNo = invBscList.get(i).getSeqNo();
                columnMap.clear();
                columnMap.put("SEQ_NO",seqNo);
                invImgList.addAll(invDtService.selectByMap(columnMap));
            }
            if(invImgList.size()>0){
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, invImgList);
            }else{
                ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL,null);
            }

        } catch (Exception e) {
            ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL,null);
            logger.error(" --selectWlGds error", e);
        }
        return ajaxResult;
    }

    /**
     * 批量插入清单表头数据
     *
     * @param
     * @return
     */
    //@RequiresPermissions("emsPutrecImg:list:insertBatchData")
    @RequestMapping(value = "/list/insertBscBatchData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insertInvBscBatchData(String jasonBsc,String jasonImg) {
        Gson gson = new Gson();
        InvBsc invBsc = gson.fromJson(jasonBsc,InvBsc.class);
        invBsc.setiEFlag1(invBsc.getImpexpMarkcd());
        SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        invBsc.setInvtDclTime(dateFormat.format(new Date()));
        invBsc.setChkStatus("S");
        AjaxResult ajaxResult = null;
        boolean flag = false;
        try {
            if(null != invBsc){
                flag = invBscService.insert(invBsc);
            }
            //检查数据
            if (flag) {
                List<InvDt> invImgList = gson.fromJson(jasonImg, new TypeToken<List<InvDt>>() {
                }.getType());
                flag = invDtService.insertBatch(invImgList);
                if (flag) {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
                }
                else {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                }
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("insertBatchData()--error", e);
        }
        return ajaxResult;
    }

}
