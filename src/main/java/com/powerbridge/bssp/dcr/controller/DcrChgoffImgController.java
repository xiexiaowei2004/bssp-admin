package com.powerbridge.bssp.dcr.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.dcr.entity.DcrChgoffImg;
import com.powerbridge.bssp.dcr.service.IDcrChgoffImgService;
import com.powerbridge.bssp.ems.entity.EmsCusBsc;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.ems.service.IEmsCusBscService;
import com.powerbridge.bssp.ems.service.IEmsCusImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：DcrChgoffImgController
 * 类描述：账册报核料件 控制器
 * 创建人：haihuihuang
 * 创建时间：2017年5月22日  上午15:59:00
 */
@Controller
@RequestMapping("/dcr/dcrChgoffImg")
@CrossOrigin
public class DcrChgoffImgController extends BaseController {

    @Autowired
    private IDcrChgoffImgService dcrChgoffImgService;

    @Autowired
    private IEmsCusImgService emsCusImgService;

    @Autowired
    private IEmsCusBscService emsCusBscService;

    /**
     * @param seqNo
     * @return AjaxResult
     * @throws
     * @Description: 进入列表管理页面:(列表查询)
     */
    //@RequiresPermissions("dcrChgoffImg:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showDcrChgoffImgList(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }

            Page<DcrChgoffImg> dcrChgoffImgList = dcrChgoffImgService.selectByList(page, seqNo);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffImgList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("showDcrChgoffImgList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffImg
     * @return AjaxResult
     * @throws
     * @Description: 新增
     */
//    @RequiresPermissions("dcrChgoffImg:list:add")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addDcrChgoffImg(DcrChgoffImg dcrChgoffImg) {
        AjaxResult ajaxResult = check(dcrChgoffImg);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        try {
            dcrChgoffImg.setUid(UUIDGenerator.getUUID());
            Boolean flag = dcrChgoffImgService.insert(dcrChgoffImg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffImg);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addDcrChgoffImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("dcrChgoffImg:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editDcrChgoffImg(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffImg dcrChgoffImg = dcrChgoffImgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffImg);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("editDcrChgoffImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffImg
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("dcrChgoffImg:list:edit")
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateDcrChgoffImg(DcrChgoffImg dcrChgoffImg) {
        AjaxResult ajaxResult = check(dcrChgoffImg);
        if (!ObjectUtils.isEmpty(ajaxResult)) {
            return ajaxResult;
        }
        try {
            Boolean flag = dcrChgoffImgService.updateById(dcrChgoffImg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffImg);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateDcrChgoffImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("dcrChgoffImg:list:delete")
//    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public AjaxResult deleteDcrChgoffImg(@PathVariable String id) {
//        AjaxResult ajaxResult = null;
//        try {
//            boolean flag = dcrChgoffImgService.deleteById(id);
//            if (flag) {
//                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
//            }
//        } catch (Exception e) {
//            ajaxResult = setErrorJson(e.getMessage());
//            logger.error("deleteDcrChgoffImg()--err", e);
//        }
//        return ajaxResult;
//    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
//    @RequiresPermissions("dcrChgoffImg:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteDcrChgoffImgByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            DcrChgoffImg dcrChgoffImg = dcrChgoffImgService.selectById(idList);
            Boolean flag = dcrChgoffImgService.deleteById(idList);
            if (flag) {
                dcrChgoffImgService.updateGdsSeqno(dcrChgoffImg.getSeqNo(),dcrChgoffImg.getGdsSeqno());
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("deleteDcrChgoffImgByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @return AjaxResult
     * @throws
     * @Description: 获取序号
     */
    @RequestMapping(value = "/list/getGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getGdsSeqno(String seqNo) {
        AjaxResult ajaxResult = null;
        try {
            int gdsSeqNo = 1;
            EntityWrapper<DcrChgoffImg> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.orderBy("GDS_SEQNO", false);
            List<DcrChgoffImg> dcrChgoffImgList = dcrChgoffImgService.selectList(entityWrapper);
            if (!dcrChgoffImgList.isEmpty()) {
                gdsSeqNo = dcrChgoffImgList.get(0).getGdsSeqno() + 1;
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, gdsSeqNo);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getGdsSeqno()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param emsNo 账册编号
     * @return AjaxResult
     * @throws
     * @Description: 获取账册料件数据，更新子表
     */
    @RequestMapping(value = "/list/getEmsCusImgByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getEmsCusImgByList(String emsNo, String seqNo, String chgTmsCnt) {
        AjaxResult ajaxResult = null;
        try {
            if (StringUtil.isEmpty(emsNo)) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_EMSNO_ERROR);
                return ajaxResult;
            }
            EntityWrapper entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("EMS_NO", emsNo);

            EmsCusBsc emsCusBsc = emsCusBscService.selectOne(entityWrapper);
            if (emsCusBsc == null){
                return setErrorJson("该账册号不存在");
            }
            entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO",emsCusBsc.getSeqNo());
            List<EmsCusImg> emsCusImgList = emsCusImgService.selectList(entityWrapper);
            if (!emsCusImgList.isEmpty()) {
                boolean flag = dcrChgoffImgService.getDcrChgoffImgByList(emsCusImgList, seqNo , chgTmsCnt);
                if (flag) {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
                }else {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_UNKOWN);
                }
            } else {
                ajaxResult = setErrorJson("该账册编号无料件数据");
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getEmsCusImgByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param dcrChgoffImg 料件数据
     * @return AjaxResult
     * @throws
     * @Description: 获取账册料件数据，更新数据
     */
    @RequestMapping(value = "/list/getEmsCusImg", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getEmsCusImg(DcrChgoffImg dcrChgoffImg) {
        AjaxResult ajaxResult = null;
        try {
            if (StringUtil.isEmpty(dcrChgoffImg.getEmsNo())) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_EMSNO_ERROR);
                return ajaxResult;
            }
            EntityWrapper entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("EMS_NO", dcrChgoffImg.getEmsNo());

            EmsCusBsc emsCusBsc = emsCusBscService.selectOne(entityWrapper);
            if (emsCusBsc == null){
                return setErrorJson("该账册号不存在");
            }
            entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("SEQ_NO",emsCusBsc.getSeqNo());
            entityWrapper.eq("GDS_SEQNO", dcrChgoffImg.getGdsSeqno());
            EmsCusImg emsCusImg = emsCusImgService.selectOne(entityWrapper);
            if (emsCusImg != null) {
                dcrChgoffImg.setCopGNo(emsCusImg.getGdsMtno());//料号
                dcrChgoffImg.setCodeTs(emsCusImg.getGdecd());   // 商品编码
                dcrChgoffImg.setgName(emsCusImg.getGdsNm());//商品名称
                dcrChgoffImg.setUnit(emsCusImg.getDclUnitcd());//计量单位
                dcrChgoffImg.setTypecd(emsCusImg.getMtpckEndprdTypecd());//料件成品标记
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, dcrChgoffImg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getEmsCusImg()--err", e);
        }
        return ajaxResult;
    }
}
