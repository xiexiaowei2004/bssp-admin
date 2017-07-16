package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtArcrpImg;
import com.powerbridge.bssp.cop_et.entity.EtCusImg;
import com.powerbridge.bssp.cop_et.service.IEtArcrpImgService;
import com.powerbridge.bssp.cop_et.service.IEtCusImgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 项目名称：bssp-admin
 * 类名称：EtCusImgController
 * 类描述：企业联网档案库正式表料件接口实现
 * 创建人：willChen
 * 创建时间：2017/5/22 16:18
 * 修改人：willChen
 * 修改时间：2017-07-03 11:00:10
 */
@Controller
@RequestMapping("/cop_et/etCusImg")
@CrossOrigin
public class EtCusImgController extends BaseController {
    @Autowired
    private IEtCusImgService etCusImgService;
    @Autowired
    private IEtArcrpImgService etArcrpImgService;

    /**
     * 根据id查询
     *
     * @param id 料件表主键 UID
     * @return 料件信息
     */
    @RequiresPermissions("etArcrpImg:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        EtCusImg etCusImg = etCusImgService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etCusImg);
    }

    /**
     * 根据条件查询
     *
     * @param etCusImg   查询条件
     * @param pageSize   页面容量
     * @param pageNumber 第几页
     * @param sort       排序列名
     * @param sortOrder  排序规则
     * @return 料件列表
     */
    @RequiresPermissions("etArcrpImg:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtCusImgList(EtCusImg etCusImg, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtCusImg> etCusImgPage;
        try {
            // 分页
            etCusImgPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etCusImgPage.setAsc(super.getOrderSort(sortOrder));
                etCusImgPage.setOrderByField(sort);
            } else {
                //默认商品序号排序
                etCusImgPage.setAsc(true);
                etCusImgPage.setOrderByField("A.GDS_SEQNO");
            }
            etCusImgPage = etCusImgService.selectEtCusImgList(etCusImgPage, etCusImg);
        } catch (Exception e) {
            logger.error("selectEtCusImgList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etCusImgPage.getRecords(), etCusImgPage.getTotal());
    }

    /**
     * 获取变更商品列表，且列表中不包含当前档案库已存在的商品
     *
     * @param seqNo      单据编号
     * @param chgTmsCnt  变更次数
     * @param pageSize   页面容量
     * @param pageNumber 第几页
     * @param sort       排序列名
     * @param sortOrder  排序规则
     * @return 商品列表
     */
    @RequiresPermissions("etArcrpImg:list:view")
    @RequestMapping(value = "/list/changeChooseList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult changeChooseList(@RequestParam("seqNo") String seqNo, @RequestParam("chgTmsCnt") Integer chgTmsCnt, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtCusImg> etCusImgPage;
        try {
            // 分页
            etCusImgPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etCusImgPage.setAsc(super.getOrderSort(sortOrder));
                etCusImgPage.setOrderByField(sort);
            } else {
                //默认按录入日期倒排序
                etCusImgPage.setAsc(true);
                etCusImgPage.setOrderByField("GDS_SEQNO");
            }

            //查询出申请表中已有的商品
            EntityWrapper imgEntityWrapper = new EntityWrapper();
            imgEntityWrapper.eq("SEQ_NO", seqNo);
            imgEntityWrapper.eq("CHG_TMS_CNT", chgTmsCnt);
            List<EtArcrpImg> etArcrpImgList = etArcrpImgService.selectList(imgEntityWrapper);
            //商品编码集合
            Set<String> gdecdSet = new HashSet();
            for (EtArcrpImg etArcrpImg : etArcrpImgList) {
                gdecdSet.add(etArcrpImg.getGdecd());
            }

            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            //遍历set
            for (String gdecd : gdecdSet) {
                entityWrapper.notLike("GDECD", gdecd);
            }
            //修改标记不为删除
            entityWrapper.notLike("MODF_MARKCD", ChkStatusConstant.MODF_MARKCD_2);

            etCusImgPage = etCusImgService.selectPage(etCusImgPage, entityWrapper);
        } catch (Exception e) {
            logger.error("changeChooseList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etCusImgPage.getRecords(), etCusImgPage.getTotal());
    }

}
