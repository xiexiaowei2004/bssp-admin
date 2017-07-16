package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsCusBsc;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.ems.entity.EmsPutrecImg;
import com.powerbridge.bssp.ems.service.IEmsCusBscService;
import com.powerbridge.bssp.ems.service.IEmsCusImgService;
import com.powerbridge.bssp.ems.service.IEmsPutrecImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsCusImgController
 * 类描述：账册备案正式表料件接口实现
 * 创建人：willChen
 * 创建时间：2017/5/17 9:49
 * 修改人：jokylao
 * 修改时间：2017/6/4 11:23
 */
@Controller
@RequestMapping("/ems/emsCusImg")
@CrossOrigin
public class EmsCusImgController extends BaseController {
    @Autowired
    private IEmsPutrecImgService emsPutrecImgService;
    @Autowired
    private IEmsCusImgService emsCusImgService;
    @Autowired
    private IEmsCusBscService emsCusBscService;

    /**
     * 根据id查询账册备案正式表料件信息
     *
     * @param id uid主键
     * @return 账册备案正式表料件信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusImgsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsCusImg emsPutrecImg = emsCusImgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusImgsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案正式表料件列表
     *
     * @return 账册备案正式表料件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusImgs(EmsCusImg emsCusImg) {
        AjaxResult ajaxResult = null;
        if (StringUtil.isEmpty(emsCusImg.getSeqNo())) {
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
            Page<EmsCusImg> emsPutrecImgPage = emsCusImgService.selectEmsCusImgList(page, emsCusImg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusImgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案正式表料件列表
     *
     * @return 账册备案正式表料件列表
     */
    @RequestMapping(value = "/list/selectByEmsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusImgByEmsNo(EmsCusImg emsCusImg) {
        AjaxResult ajaxResult = null;
        try {
            //根据账册编号查找单据编号
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("EMS_NO", emsCusImg.getEmsNo());
            EmsCusBsc emsCusBsc = emsCusBscService.selectOne(entityWrapper);
            if (emsCusBsc != null) {
                String seqNo = emsCusBsc.getSeqNo();
                if (StringUtil.isEmpty(seqNo)) {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_EMS_SEQNO_ERROR);
                    return ajaxResult;
                }
                emsCusImg.setSeqNo(seqNo);
                emsCusImg.setEmsNo("");
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
            Page<EmsCusImg> emsPutrecImgPage = emsCusImgService.selectEmsCusImgList(page, emsCusImg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecImgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusImgByEmsNo()--error", e);
        }
        return ajaxResult;
    }
    /**
     * 查询账册、序号或料号是否存在
     *
     * @return AjaxResult
     */
    @RequestMapping(value = "/list/selectImgsByEmsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectImgsByEmsNo(EmsCusImg emsCusImg) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("EMS_NO", emsCusImg.getEmsNo());
            EmsCusImg emsCusImg1 = new EmsCusImg();
            int count = emsCusBscService.selectCount(entityWrapper);
            if (count == 0) {
                return setErrorJson("账册编号为[" + emsCusImg.getEmsNo() + "]的数据在正式表中不存在");
            } else {
                if (emsCusImg.getGdsSeqno() != null) {
                    entityWrapper.eq("GDS_SEQNO", emsCusImg.getGdsSeqno());
                    List<EmsCusImg> emsCusImgList = emsCusImgService.selectList(entityWrapper);
                    if (emsCusImgList.size() == 0) {
                        return setErrorJson(String.format("序号%s在正式表中不存在", emsCusImg.getGdsSeqno()));
                    }
                    emsCusImg1 = emsCusImgList.get(0);
                }
                if (StringUtil.isNotEmpty(emsCusImg.getGdsMtno())) {
                    entityWrapper.eq("GDS_MTNO", emsCusImg.getGdsMtno());
                    List<EmsCusImg> emsCusImgList = emsCusImgService.selectList(entityWrapper);
                    if (emsCusImgList.size() == 0) {
                        return setErrorJson(String.format("料号%s在正式表中不存在", emsCusImg.getGdsMtno()));
                    }
                    emsCusImg1 = emsCusImgList.get(0);
                }
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,emsCusImg1);
        } catch (Exception e) {
            ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL,e.getMessage());
            logger.error("selectImgsByEmsNo()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 获取最大的商品序号
     *
     * @param emsCusImg
     * @return 商品序号
     */
    @RequestMapping(value = "/list/selectMaxGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectMaxGdsSeqno(EmsCusImg emsCusImg) {
        AjaxResult ajaxResult = null;
        try {
            BigDecimal maxGdsSeqno = emsCusImgService.getMaxGdsSeqno(emsCusImg.getSeqNo());
            maxGdsSeqno = maxGdsSeqno.add(new BigDecimal(1));//取最大值加1
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
    //@RequiresPermissions("emsCusImg:list:copyGds")
    @RequestMapping(value = "/list/copyGds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult copyGds(String idList) {
        AjaxResult ajaxResult = null;
        try {
            List<EmsCusImg> emsPutrecImgList = emsCusImgService.selectBatchIds(Arrays.asList(idList.split(",")));
            List<EmsCusImg> newEmsPutrecImgList = emsCusImgService.copyGds(emsPutrecImgList);
            Boolean flag = emsCusImgService.insertBatch(newEmsPutrecImgList);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("copyEmsCusImg()--error", e);
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
    public Boolean isExistGdsSeqno(String emsNo, BigDecimal gdsSeqno) {
        Boolean flag = emsCusImgService.isExistGdsSeqno(emsNo, gdsSeqno);
        return flag;
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
    public AjaxResult insertToPreImg(String seqNo, BigDecimal chgTmsCnt, String idList) {
        AjaxResult ajaxResult = null;
        try {
            List<EmsPutrecImg> emsPutrecImgList = emsCusImgService.getPreImgFromCus(seqNo, chgTmsCnt, idList);
            boolean flag = emsPutrecImgService.insertBatch(emsPutrecImgList);
            if (!flag) {
                return setErrorJson("料件商品插入失败");
            }
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("copyEmsCusImg()--error", e);
        }
        return ajaxResult;
    }
}
