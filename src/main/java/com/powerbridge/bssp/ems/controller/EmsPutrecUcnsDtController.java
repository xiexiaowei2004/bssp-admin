package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.*;
import com.powerbridge.bssp.ems.service.*;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsPutrecUcnsDtController
 * 类描述：账册备案申请耗件接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：willChen
 * 修改时间：2017/5/17 9:49
 */
@Controller
@RequestMapping("/ems/emsPutrecUcnsDt")
@CrossOrigin
public class EmsPutrecUcnsDtController extends BaseController {
    private static final Gson gson = new Gson();
    @Autowired
    private IEmsPutrecUcnsDtService emsPutrecUcnsDtService;
    @Autowired
    private IEmsCusUcnsDtService emsCusUcnsDtService;
    @Autowired
    private IEmsPutrecImgService emsPutrecImgService;
    @Autowired
    private IEmsPutrecExgService emsPutrecExgService;
    @Autowired
    private IEmsCusImgService emsCusImgService;
    @Autowired
    private IEmsCusExgService emsCusExgService;

    /**
     * 新增账册备案申请耗件
     *
     * @param emsPutrecUcnsDt 详细信息
     * @return
     */
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEmsPutrecUcnsDt(EmsPutrecUcnsDt emsPutrecUcnsDt) {
        String emsNo = emsPutrecUcnsDt.getEmsNo();
        BigDecimal ucnsSeqno = emsPutrecUcnsDt.getUcnsSeqno();
        String modyMark = emsPutrecUcnsDt.getModfMarkcd();
        modyMark = modyMark == null ? "" : modyMark;
        //判断序号是否存在
        if (emsPutrecUcnsDtService.isExistGdsSeqno(emsPutrecUcnsDt)) {
            return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
        }
        if (modyMark == "3" && emsCusUcnsDtService.isExistGdsSeqno(emsNo, ucnsSeqno)) {
            return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
        }
        //判断成品序号+料件序号+单耗版本号的记录是否存在
        AjaxResult ajaxResult = checkIsExist(emsPutrecUcnsDt);
        if (ajaxResult.getCode() == 1006) {
            return ajaxResult;
        }
        //使用工具类生成id
        String uId = UUIDGenerator.getUUID();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        emsPutrecUcnsDt.setUid(uId);
        try {
            //执行新增
            if (emsPutrecUcnsDtService.insert(emsPutrecUcnsDt)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }

        } catch (Exception e) {
            logger.error("addEmsPutrecUcnsDt()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id删除账册备案申请耗件
     *
     * @param id uid主键
     * @return
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecUcnsDtById(@PathVariable String id) {
        try {
            EmsPutrecUcnsDt emsPutrecUcnsDt = emsPutrecUcnsDtService.selectById(id);
            if (emsPutrecUcnsDt == null) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该记录不存在");
            }

            //执行删除
            if (emsPutrecUcnsDtService.deleteById(id)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("deleteEmsPutrecUcnsDtById()--err", e);
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
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEmsPutrecUcnsDtByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            //List<EmsPutrecUcnsDt> emsPutrecUcnsDtList = emsPutrecUcnsDtService.selectBatchIds(Arrays.asList(idList.split(",")));
            Boolean flag = emsPutrecUcnsDtService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
                //删除成功后重新排序
                /*for (EmsPutrecUcnsDt emsPutrecUcnsDt : emsPutrecUcnsDtList) {
                    String modyMark = emsPutrecUcnsDt.getModfMarkcd();
                    //删除新增的数据时，重新排序
                    if (modyMark.equals("3")) {
                        resetOrder(emsPutrecUcnsDt);
                    }
                }*/
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteEmsPutrecUcnsDtByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 删除时对新增的数据重新排序
     *
     * @param emsPutrecUcnsDt
     */
    private void resetOrder(EmsPutrecUcnsDt emsPutrecUcnsDt) {
        String seqNo = emsPutrecUcnsDt.getSeqNo();
        BigDecimal gdsSeqno = emsPutrecUcnsDt.getUcnsSeqno();
        if (gdsSeqno.compareTo(BigDecimal.valueOf(1)) == 1) {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.eq("MODF_MARKCD", "3");
            entityWrapper.gt("UCNS_SEQNO", gdsSeqno);//查找大于当前序号的记录
            List<EmsPutrecUcnsDt> emsPutrecUcnsDtList = emsPutrecUcnsDtService.selectList(entityWrapper);
            for (EmsPutrecUcnsDt emsPutrecUcnsDt1 : emsPutrecUcnsDtList) {
                BigDecimal UcnsSeqno1 = emsPutrecUcnsDt1.getUcnsSeqno();
                emsPutrecUcnsDt1.setUcnsSeqno(UcnsSeqno1.subtract(BigDecimal.valueOf(1)));//序号减1
                emsPutrecUcnsDtService.updateById(emsPutrecUcnsDt1);
            }
        }
    }

    /**
     * 手动排序
     * @param seqNo
     * @param chgTmsCnt
     * @return
     */
    @RequestMapping(value = "/list/customResetOrder", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult customResetOrder(String seqNo,BigDecimal chgTmsCnt){
        AjaxResult ajaxResult = null;
        try {
            BigDecimal maxUcnsSeqno = emsPutrecUcnsDtService.getMaxUcnsSeqno(seqNo);
            BigDecimal cusMaxUcnsSeqno = emsCusUcnsDtService.getMaxUcnsSeqno(seqNo);
            if (cusMaxUcnsSeqno.compareTo(maxUcnsSeqno) == 1) {
                maxUcnsSeqno = cusMaxUcnsSeqno;
            }
            maxUcnsSeqno = maxUcnsSeqno.add(BigDecimal.valueOf(1));//取最大值加1
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.eq("CHG_TMS_CNT", chgTmsCnt);
            List<EmsPutrecUcnsDt> emsPutrecUcnsDts = emsPutrecUcnsDtService.selectList(entityWrapper);
            for (EmsPutrecUcnsDt emsPutrecUcnsDt : emsPutrecUcnsDts) {
                emsPutrecUcnsDt.setUcnsSeqno(maxUcnsSeqno);
                maxUcnsSeqno = maxUcnsSeqno.add(BigDecimal.valueOf(1));//序号加1
            }
            boolean flag = emsPutrecUcnsDtService.updateBatchById(emsPutrecUcnsDts);
            if(flag){
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
            else {
                return result(MessageConstants.BSSP_STATUS_FAIL);
            }
        }
        catch (Exception e){
            logger.debug(e.getMessage());
            return setErrorJson(e.getMessage());
        }
    }
    /**
     * 修改账册备案申请耗件
     *
     * @param emsPutrecUcnsDt 详细信息
     * @return
     */
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEmsPutrecUcnsDt(EmsPutrecUcnsDt emsPutrecUcnsDt) {
        String uid = emsPutrecUcnsDt.getUid();
        if (StringUtil.isEmpty(uid)) {
            return json(MessageConstants.BSSP_STATUS_CODE_NOTEXIST, "主键不能为空");
        }
        String emsNo = emsPutrecUcnsDt.getEmsNo();
        BigDecimal ucnsSeqno = emsPutrecUcnsDt.getUcnsSeqno();
        String modyMark = emsPutrecUcnsDt.getModfMarkcd();
        modyMark = modyMark == null ? "" : modyMark;
        //判断序号是否存在
        if (emsPutrecUcnsDtService.isExistGdsSeqno(emsPutrecUcnsDt)) {
            return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
        }
        if (modyMark == "3" && emsCusUcnsDtService.isExistGdsSeqno(emsNo, ucnsSeqno)) {
            return result(MessageConstants.BSSP_STATUS_GDS_SEQNO_EXIST);
        }
        //判断成品序号+料件序号+单耗版本号的记录是否存在
        AjaxResult ajaxResult = checkIsExist(emsPutrecUcnsDt);
        if (ajaxResult.getCode() == 1006) {
            return ajaxResult;
        }
        try {
            if (emsPutrecUcnsDtService.updateById(emsPutrecUcnsDt)) {
                return json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecUcnsDt);
            } else {
                return result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            logger.error("updateEmsPutrecUcnsDt()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
    }

    /**
     * 根据id查询账册备案申请耗件信息
     *
     * @param id uid主键
     * @return 账册备案申请耗件信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecUcnsDtsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsPutrecUcnsDt emsPutrecUcnsDt = emsPutrecUcnsDtService.selectByUid(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecUcnsDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecUcnsDtsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请耗件列表
     *
     * @return 账册备案申请耗件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsPutrecUcnsDts(EmsPutrecUcnsDt emsPutrecUcnsDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按商品序号排序
                page.setAsc(true);
                page.setOrderByField("ucnsSeqno");  // 排序
            }
            Page<EmsPutrecUcnsDt> emsPutrecUcnsDtPage = emsPutrecUcnsDtService.selectEmsPutrecUcnsDtList(page, emsPutrecUcnsDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecUcnsDtPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsPutrecUcnsDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 批量插入数据
     *
     * @param jasonEmsCusUcnsDts 数据集合
     * @return
     */
    //@RequiresPermissions("emsPutrecUcnsDt:list:insertBatchData")
    @RequestMapping(value = "/list/insertBatchData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insertBatchData(String jasonEmsCusUcnsDts) {
        Gson gson = new Gson();
        List<EmsPutrecUcnsDt> emsPutrecUcnsDts = gson.fromJson(jasonEmsCusUcnsDts, new TypeToken<List<EmsPutrecUcnsDt>>() {
        }.getType());
        AjaxResult ajaxResult = null;
        Date newYear = DateUtil.getOffsiteDate(new Date(), Calendar.YEAR, 1);
        String ucnsValidDate = DateUtil.formatDate(newYear); //单耗有效期默认当前日期+1年
        try {
            BigDecimal ucnsSeqno = new BigDecimal(0);
            for (int i = 0; i < emsPutrecUcnsDts.size(); i++) {
                EmsPutrecUcnsDt emsPutrecUcnsDt = emsPutrecUcnsDts.get(i);
                //判断成品序号+料件序号+单耗版本号是否重复
                AjaxResult checkAjax = checkIsExist(emsPutrecUcnsDt);
                if (checkAjax.getCode() != 1) {
                    return checkAjax;
                }
                if (i == 0) {
                    BigDecimal maxUcnsSeqno = emsPutrecUcnsDtService.getMaxUcnsSeqno(emsPutrecUcnsDt.getSeqNo());
                    BigDecimal cusMaxUcnsSeqno = emsCusUcnsDtService.getMaxUcnsSeqno(emsPutrecUcnsDt.getSeqNo());
                    if (cusMaxUcnsSeqno.compareTo(maxUcnsSeqno) == 1) {
                        maxUcnsSeqno = cusMaxUcnsSeqno;
                    }
                    ucnsSeqno = maxUcnsSeqno.add(BigDecimal.valueOf(1));//序号取最大值加1
                } else {
                    ucnsSeqno = ucnsSeqno.add(BigDecimal.valueOf(1));//序号取最大值加1
                }
                emsPutrecUcnsDt.setUcnsSeqno(ucnsSeqno);
                if(StringUtil.isEmpty(emsPutrecUcnsDt.getUcnsValidDate()))
                    emsPutrecUcnsDt.setUcnsValidDate(ucnsValidDate);
            }
            //检查必填项
            String checkMsg = emsPutrecUcnsDtService.checkBeforeInsert(emsPutrecUcnsDts);
            if (StringUtil.isNotEmpty(checkMsg)) {
                return setErrorJson(checkMsg);
            }
            Boolean flag = emsPutrecUcnsDtService.insertBatch(emsPutrecUcnsDts);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("insertBatchData()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 获取最大的序号
     *
     * @param emsPutrecUcnsDt
     * @return 序号
     */
    @Transactional
    @RequestMapping(value = "/list/selectMaxUcnsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectMaxUcnsSeqno(EmsPutrecUcnsDt emsPutrecUcnsDt) {
        AjaxResult ajaxResult = null;
        try {
            BigDecimal maxUcnsSeqno = emsPutrecUcnsDtService.getMaxUcnsSeqno(emsPutrecUcnsDt.getSeqNo());
            BigDecimal cusMaxUcnsSeqno = emsCusUcnsDtService.getMaxUcnsSeqno(emsPutrecUcnsDt.getSeqNo());
            if (cusMaxUcnsSeqno.compareTo(maxUcnsSeqno) == 1) {
                maxUcnsSeqno = cusMaxUcnsSeqno;
            }
            maxUcnsSeqno = maxUcnsSeqno.add(BigDecimal.valueOf(1));//取最大值加1
            return json(MessageConstants.BSSP_STATUS_SUCCESS, maxUcnsSeqno);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectMaxUcnsSeqno()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 成品序号+料件序号+单耗版本号 为联合主键，不能重复，料件、成品序号是否存在
     *
     * @param emsPutrecUcnsDt
     * @return
     */
    private AjaxResult checkIsExist(EmsPutrecUcnsDt emsPutrecUcnsDt) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("SEQ_NO", emsPutrecUcnsDt.getSeqNo());
        entityWrapper.eq("GDS_SEQNO", emsPutrecUcnsDt.getEndprdSeqno());//成品序号
        EmsPutrecExg emsPutrecExg = emsPutrecExgService.selectOne(entityWrapper);
        EmsCusExg emsCusExg = emsCusExgService.selectOne(entityWrapper);
        if (emsPutrecExg == null && emsCusExg == null) {
            return setErrorJson("成品序号为" + emsPutrecUcnsDt.getEndprdSeqno() + "的商品不存在");
        }
        EntityWrapper entityWrapper2 = new EntityWrapper();
        entityWrapper2.eq("SEQ_NO", emsPutrecUcnsDt.getSeqNo());
        entityWrapper2.eq("GDS_SEQNO", emsPutrecUcnsDt.getMtpckSeqno());//料件序号
        EmsPutrecImg emsPutrecImg = emsPutrecImgService.selectOne(entityWrapper2);
        EmsCusImg emsCusImg = emsCusImgService.selectOne(entityWrapper2);
        if (emsPutrecImg == null && emsCusImg == null) {
            return setErrorJson("料件序号为" + emsPutrecUcnsDt.getEndprdSeqno() + "的商品不存在");
        }
        EntityWrapper entityWrapper1 = new EntityWrapper();
        entityWrapper1.eq("ENDPRD_SEQNO", emsPutrecUcnsDt.getEndprdSeqno());
        entityWrapper1.eq("MTPCK_SEQNO", emsPutrecUcnsDt.getMtpckSeqno());
        entityWrapper1.eq("UCNS_VERNO", emsPutrecUcnsDt.getUcnsVerno());
        entityWrapper1.eq("SEQ_NO", emsPutrecUcnsDt.getSeqNo());
        List<EmsCusUcnsDt> emsCusUcnsDts = emsCusUcnsDtService.selectList(entityWrapper1);
        if (emsCusUcnsDts.size() > 0) {
            return result(MessageConstants.BSSP_STATUS_EMS_UCNS_ERROR);
        }
        entityWrapper1.ne("uid", emsPutrecUcnsDt.getUid());
        entityWrapper1.eq("CHG_TMS_CNT", emsPutrecUcnsDt.getChgTmsCnt());
        List<EmsPutrecUcnsDt> emsPutrecUcnsDts = emsPutrecUcnsDtService.selectList(entityWrapper1);
        if (emsPutrecUcnsDts.size() > 0) {
            return result(MessageConstants.BSSP_STATUS_EMS_UCNS_ERROR);
        }
        return result(MessageConstants.BSSP_STATUS_SUCCESS);
    }
}
