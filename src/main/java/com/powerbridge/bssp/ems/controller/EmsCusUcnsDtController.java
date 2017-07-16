package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsCusBsc;
import com.powerbridge.bssp.ems.entity.EmsCusUcnsDt;
import com.powerbridge.bssp.ems.entity.EmsPutrecUcnsDt;
import com.powerbridge.bssp.ems.service.IEmsCusBscService;
import com.powerbridge.bssp.ems.service.IEmsCusUcnsDtService;
import com.powerbridge.bssp.ems.service.IEmsPutrecUcnsDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsCusUcnsDtController
 * 类描述：账册备案正式表单损耗接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：jokylao
 * 修改时间：2017/6/4 11:23
 */
@Controller
@RequestMapping("/ems/emsCusUcnsDt")
@CrossOrigin
public class EmsCusUcnsDtController extends BaseController {
    private static final Gson gson = new Gson();
    @Autowired
    private IEmsPutrecUcnsDtService emsPutrecUcnsDtService;
    @Autowired
    private IEmsCusUcnsDtService emsCusUcnsDtService;
    @Autowired
    private IEmsCusBscService emsCusBscService;

    /**
     * 根据id查询账册备案正式表单损耗信息
     *
     * @param id uid主键
     * @return 账册备案正式表单损耗信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusUcnsDtsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsCusUcnsDt emsPutrecImg = emsCusUcnsDtService.selectByUid(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusUcnsDtsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案正式表单损耗列表
     *
     * @return 账册备案正式表单损耗列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusUcnsDts(EmsCusUcnsDt emsCusUcnsDt) {
        AjaxResult ajaxResult = null;
        if(StringUtil.isEmpty(emsCusUcnsDt.getSeqNo())){
            ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
            return  ajaxResult;
        }
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按序号排序
                page.setAsc(true);
                page.setOrderByField("ucnsSeqno");  // 排序
            }
            Page<EmsCusUcnsDt> emsPutrecImgPage = emsCusUcnsDtService.selectEmsCusUcnsDtList(page, emsCusUcnsDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusUcnsDtList()--error", e);
        }
        return ajaxResult;
    }
    /**
     * 查询账册备案正式表单损耗列表
     *
     * @return 账册备案正式表单损耗列表
     */
    @RequestMapping(value = "/list/selectByEmsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusUcnsDtByEmsNo(EmsCusUcnsDt emsCusUcnsDt) {
        AjaxResult ajaxResult = null;
        try {
            //根据账册编号查找单据编号
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("EMS_NO", emsCusUcnsDt.getEmsNo());
            EmsCusBsc emsCusBsc = emsCusBscService.selectOne(entityWrapper);
            if (emsCusBsc != null) {
                String seqNo = emsCusBsc.getSeqNo();
                if (StringUtil.isEmpty(seqNo)) {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
                    return ajaxResult;
                }
                emsCusUcnsDt.setSeqNo(seqNo);
                emsCusUcnsDt.setEmsNo("");
            }
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按序号排序
                page.setAsc(true);
                page.setOrderByField("ucnsSeqno");  // 排序
            }
            Page<EmsCusUcnsDt> emsPutrecImgPage = emsCusUcnsDtService.selectEmsCusUcnsDtList(page, emsCusUcnsDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusUcnsDtByEmsNo()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 查找单耗版本号
     * @param emsCusUcnsDt
     * @return
     */
    @RequestMapping(value = "/list/selectUcnsVerno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectUcnsVerno(EmsCusUcnsDt emsCusUcnsDt){
        AjaxResult ajaxResult = null;
        //账册编号不能为空
        if(StringUtil.isEmpty(emsCusUcnsDt.getEmsNo())){
            ajaxResult = result(MessageConstants.BSSP_STATUS_EMSNO_ERROR);
            return  ajaxResult;
        }
        //成品序号不能为空
        if(StringUtil.isEmpty(String.valueOf(emsCusUcnsDt.getEndprdSeqno()))){
            ajaxResult = result(MessageConstants.BSSP_STATUS_ENDPRD_SEQNO_ERROR);
            return  ajaxResult;
        }
        try {
            BsspUtil.filterCopEnt(emsCusUcnsDt, null);
            List<EmsCusUcnsDt> emsCusUcnsDtList = emsCusUcnsDtService.selectUcnsVerno(emsCusUcnsDt);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusUcnsDtList, emsCusUcnsDtList.size());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectUcnsVerno()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 判断序号是否存在
     *
     * @param emsNo
     * @param ucnsSeqno
     * @return
     */
    @RequestMapping(value = "/list/isExistGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult isExistGdsSeqno(String emsNo, BigDecimal ucnsSeqno) {
        AjaxResult ajaxResult = null;
        Boolean flag = emsCusUcnsDtService.isExistGdsSeqno(emsNo, ucnsSeqno);
        if (flag) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } else {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        }
        return ajaxResult;
    }
    /**
     * 获取最大的商品序号
     * @param emsPutrecExg
     * @return 商品序号
     */
    @RequestMapping(value = "/list/selectMaxUcnsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String selectMaxUcnsSeqno(EmsCusUcnsDt emsPutrecExg){
        Map<String, Object> json = new HashMap<String,Object>();
        try{
            BigDecimal maxGdsSeqno=emsCusUcnsDtService.getMaxUcnsSeqno(emsPutrecExg.getSeqNo());
            maxGdsSeqno=maxGdsSeqno.add(new BigDecimal(1));//取最大值加1
            json=transJson("1","获取商品序号成功",maxGdsSeqno);
        } catch (Exception e) {
            json=transJson("1","获取商品序号失败",1);
            logger.error("selectMaxGdsSeqno()--error", e);
        }
        String jsonStr = gson.toJson(json);
        return jsonStr;
    }
    /**
     * 选择料件商品插入料件申报表中
     *
     * @param seqNo
     * @param chgTmsCnt
     * @param idList
     * @return
     */
    @RequestMapping(value = "/list/insertToPre", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insertToPreUcns(String seqNo, BigDecimal chgTmsCnt, String idList) {
        AjaxResult ajaxResult = null;
        try {
            List<EmsPutrecUcnsDt> emsPutrecUcnsDtList = emsCusUcnsDtService.getPreImgFromCus(seqNo, chgTmsCnt, idList);
            boolean flag = emsPutrecUcnsDtService.insertBatch(emsPutrecUcnsDtList);
            if (!flag) {
                return setErrorJson("料件商品插入失败");
            }
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("insertToPreUcns()--error", e);
        }
        return ajaxResult;
    }
}
