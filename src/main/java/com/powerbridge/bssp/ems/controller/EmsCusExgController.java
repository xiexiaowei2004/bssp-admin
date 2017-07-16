package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsCusBsc;
import com.powerbridge.bssp.ems.entity.EmsCusExg;
import com.powerbridge.bssp.ems.entity.EmsPutrecExg;
import com.powerbridge.bssp.ems.entity.EmsPutrecImg;
import com.powerbridge.bssp.ems.service.IEmsCusBscService;
import com.powerbridge.bssp.ems.service.IEmsCusExgService;
import com.powerbridge.bssp.ems.service.IEmsPutrecExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsCusExgController
 * 类描述：账册备案正式表成品接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：jokylao
 * 修改时间：2017/6/4 11:23
 */
@Controller
@RequestMapping("/ems/emsCusExg")
@CrossOrigin
public class EmsCusExgController extends BaseController {

    @Autowired
    private IEmsPutrecExgService emsPutrecExgService;
    @Autowired
    private IEmsCusExgService emsCusExgService;
    @Autowired
    private IEmsCusBscService emsCusBscService;

    /**
     * 根据id查询账册备案正式表成品信息
     *
     * @param id uid主键
     * @return 账册备案正式表成品信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusExgsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsCusExg emsPutrecExg = emsCusExgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecExg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusExgsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案正式表成品列表
     *
     * @return 账册备案正式表成品列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusExgs(EmsCusExg emsCusExg) {
        AjaxResult ajaxResult = null;
        if (StringUtil.isEmpty(emsCusExg.getSeqNo())) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
            return ajaxResult;
        }
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
                page.setOrderByField("gdsSeqno");  // 排序
            }
            Page<EmsCusExg> emsPutrecExgPage = emsCusExgService.selectEmsCusExgList(page, emsCusExg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecExgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusExgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案正式表成品列表
     *
     * @return 账册备案正式表成品列表
     */
    @RequestMapping(value = "/list/selectByEmsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusExgByEmsNo(EmsCusExg emsCusExg) {
        AjaxResult ajaxResult = null;
        try {
            //根据账册编号查找单据编号
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("EMS_NO", emsCusExg.getEmsNo());
            EmsCusBsc emsCusBsc = emsCusBscService.selectOne(entityWrapper);
            if (emsCusBsc != null) {
                String seqNo = emsCusBsc.getSeqNo();
                if (StringUtil.isEmpty(seqNo)) {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
                    return ajaxResult;
                }
                emsCusExg.setSeqNo(seqNo);
                emsCusExg.setEmsNo("");
            }
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按商品序号排序
                page.setAsc(true);
                page.setOrderByField("gdsSeqno");  // 排序
            }
            Page<EmsCusExg> emsPutrecExgPage = emsCusExgService.selectEmsCusExgList(page, emsCusExg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecExgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusExgByEmsNo()--error", e);
        }
        return ajaxResult;
    }
    /**
     * 查询账册、序号或料号是否存在
     *
     * @return AjaxResult
     */
    @RequestMapping(value = "/list/selectExgsByEmsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectExgsByEmsNo(EmsCusExg emsCusExg) {
        AjaxResult ajaxResult = null;
        try {
            EmsCusExg emsCusExg1 = new EmsCusExg();
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("EMS_NO", emsCusExg.getEmsNo());
            int count = emsCusBscService.selectCount(entityWrapper);
            if (count == 0) {
                return setErrorJson("账册编号为[" + emsCusExg.getEmsNo() + "]的数据在正式表中不存在");
            } else {
                if (emsCusExg.getGdsSeqno() != null) {
                    entityWrapper.eq("GDS_SEQNO", emsCusExg.getGdsSeqno());
                    List<EmsCusExg> emsCusExgList = emsCusExgService.selectList(entityWrapper);
                    if (emsCusExgList.size() == 0) {
                        return setErrorJson(String.format("序号%s在正式表中不存在", emsCusExg.getGdsSeqno()));
                    }
                    emsCusExg1 = emsCusExgList.get(0);
                }
                if (StringUtil.isNotEmpty(emsCusExg.getGdsMtno())) {
                    entityWrapper.eq("GDS_MTNO", emsCusExg.getGdsMtno());
                    List<EmsCusExg> emsCusExgList = emsCusExgService.selectList(entityWrapper);
                    if (emsCusExgList.size() == 0) {
                        return setErrorJson(String.format("料号%s在正式表中不存在", emsCusExg.getGdsMtno()));
                    }
                    emsCusExg1 = emsCusExgList.get(0);
                }
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,emsCusExg1);
        } catch (Exception e) {
            ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL,e.getMessage());
            logger.error("selectExgsByEmsNo()--error", e);
        }
        return ajaxResult;
    }
    /**
     * 获取最大的商品序号
     *
     * @param emsCusExg
     * @return 商品序号
     */
    @RequestMapping(value = "/list/selectMaxGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectMaxGdsSeqno(EmsCusExg emsCusExg) {
        AjaxResult ajaxResult = null;
        BigDecimal maxGdsSeqno = BigDecimal.valueOf(0);
        try {
            maxGdsSeqno = emsCusExgService.getMaxGdsSeqno(emsCusExg.getSeqNo());
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, maxGdsSeqno);
        } catch (Exception e) {
            ajaxResult = setErrorJson("获取商品序号失败");
            logger.error("selectMaxGdsSeqno()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 复制账册商品
     *
     * @param idList 主键
     * @return
     */
    //@RequiresPermissions("emsCusExg:list:copyGds")
    @RequestMapping(value = "/list/copyGds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult copyGds(String idList) {
        AjaxResult ajaxResult = null;
        try {
            List<EmsCusExg> emsCusExgList = emsCusExgService.selectBatchIds(Arrays.asList(idList.split(",")));
            List<EmsCusExg> newEmsCusExgList = emsCusExgService.copyGds(emsCusExgList);
            Boolean flag = emsCusExgService.insertBatch(newEmsCusExgList);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("copyEmsCusExg()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 判断序号是否存在
     *
     * @param emsNo
     * @param gdsSeqno
     * @return
     */
    @RequestMapping(value = "/list/isExistGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult isExistGdsSeqno(String emsNo, BigDecimal gdsSeqno) {
        AjaxResult ajaxResult = null;
        Boolean flag = emsCusExgService.isExistGdsSeqno(emsNo, gdsSeqno);
        if (flag) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } else {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
        }
        return ajaxResult;
    }

    /**
     * 选择成品商品插入成品申报表中
     *
     * @param seqNo
     * @param chgTmsCnt
     * @param idList
     * @return
     */
    @RequestMapping(value = "/list/insertToPre", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insertToPreExg(String seqNo, BigDecimal chgTmsCnt, String idList) {
        AjaxResult ajaxResult = null;
        try {
            List<EmsPutrecExg> emsPutrecExgList = emsCusExgService.getPreImgFromCus(seqNo, chgTmsCnt, idList);
            boolean flag = emsPutrecExgService.insertBatch(emsPutrecExgList);
            if (!flag) {
                return setErrorJson("成品商品插入失败");
            }
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("insertToPreExg()--error", e);
        }
        return ajaxResult;
    }
}
