package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtArcrpExg;
import com.powerbridge.bssp.cop_et.entity.EtCusExg;
import com.powerbridge.bssp.cop_et.service.IEtArcrpExgService;
import com.powerbridge.bssp.cop_et.service.IEtCusExgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 项目名称：bssp-admin
 * 类名称：EtCusExgController
 * 类描述：企业联网档案库正式表成品接口实现
 * 创建人：willChen
 * 创建时间：2017/6/2 16:18
 * 修改人：willChen
 * 修改时间：2017-07-03 10:56:05
 */
@Controller
@RequestMapping("/cop_et/etCusExg")
@CrossOrigin
public class EtCusExgController extends BaseController {
    @Autowired
    private IEtCusExgService etCusExgService;
    @Autowired
    private IEtArcrpExgService etArcrpExgService;

    /**
     * 根据id查询
     *
     * @param id 成品表主键 UID
     * @return 成品信息
     */
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        EtCusExg etCusExg = etCusExgService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etCusExg);
    }

    /**
     * 根据条件查询
     *
     * @param etCusExg   查询条件
     * @param pageSize   页面容量
     * @param pageNumber 第几页
     * @param sort       排序列名
     * @param sortOrder  排序规则
     * @return 成品列表
     */
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtCusExgList(EtCusExg etCusExg, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtCusExg> etCusExgPage;
        try {
            // 分页
            etCusExgPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etCusExgPage.setAsc(super.getOrderSort(sortOrder));
                etCusExgPage.setOrderByField(sort);
            } else {
                //默认商品序号排序
                etCusExgPage.setOrderByField("A.GDS_SEQNO");
            }
            etCusExgPage = etCusExgService.selectEtCusExgList(etCusExgPage, etCusExg);
        } catch (Exception e) {
            logger.error("selectEtCusExgList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etCusExgPage.getRecords(), etCusExgPage.getTotal());
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
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list/changeChooseList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult changeChooseList(@RequestParam("seqNo") String seqNo, @RequestParam("chgTmsCnt") Integer chgTmsCnt, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        // 分页
        Page<EtCusExg> etCusExgPage;
        try {
            // 分页
            etCusExgPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etCusExgPage.setAsc(super.getOrderSort(sortOrder));
                etCusExgPage.setOrderByField(sort);
            } else {
                //默认按录入日期倒排序
                etCusExgPage.setAsc(true);
                etCusExgPage.setOrderByField("GDS_SEQNO");
            }

            //查询出申请表中已有的商品
            EntityWrapper exgEntityWrapper = new EntityWrapper();
            exgEntityWrapper.eq("SEQ_NO", seqNo);
            exgEntityWrapper.eq("CHG_TMS_CNT", chgTmsCnt);
            List<EtArcrpExg> etArcrpExgList = etArcrpExgService.selectList(exgEntityWrapper);
            //商品编码集合
            Set<String> gdecdSet = new HashSet();
            for (EtArcrpExg etArcrpExg : etArcrpExgList) {
                gdecdSet.add(etArcrpExg.getGdecd());
            }

            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            //遍历set
            for (String gdecd : gdecdSet) {
                entityWrapper.notLike("GDECD", gdecd);
            }
            //修改标记不为删除
            entityWrapper.notLike("MODF_MARKCD", ChkStatusConstant.MODF_MARKCD_2);

            etCusExgPage = etCusExgService.selectPage(etCusExgPage, entityWrapper);
        } catch (Exception e) {
            logger.error("changeChooseList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etCusExgPage.getRecords(), etCusExgPage.getTotal());
    }
}
