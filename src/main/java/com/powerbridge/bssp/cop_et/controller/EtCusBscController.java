package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtCusBsc;
import com.powerbridge.bssp.cop_et.service.IEtCusBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EtCusBscController
 * 类描述：企业联网档案库正式表接口实现
 * 创建人：willChen
 * 创建时间：2017/6/2 9:49
 * 修改人：willChen
 * 修改时间：2017-07-03 10:54:15
 */
@Controller
@RequestMapping("/cop_et/etCusBsc")
@CrossOrigin
public class EtCusBscController extends BaseController {

    @Autowired
    private IEtCusBscService etCusBscService;

    /**
     * 根据id查询联网企业档案库信息
     *
     * @param id 主键id
     * @return 联网企业档案库信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtCusBscById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EtCusBsc etCusBsc = etCusBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, etCusBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEtCusBscById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据seqNo查询联网企业档案库信息
     *
     * @param seqNo 单据编号/预录入统一编号
     * @return 联网企业档案库信息
     */
    @RequestMapping(value = "/list/{seqNo}/changeView", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtCusBscBySeqNo(@PathVariable String seqNo) {
        AjaxResult ajaxResult;
        try {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            List<EtCusBsc> etCusBscList = etCusBscService.selectList(entityWrapper);
            if (etCusBscList.size() > 0) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, etCusBscList.get(0));
            } else {
                ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, "该单据编号记录不存在");
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEtCusBscBySeqNo()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询联网企业档案库列表
     *
     * @param etCusBsc   查询条件
     * @param pageSize   页面容量
     * @param pageNumber 第几页
     * @param sort       排序列名
     * @param sortOrder  排序规则
     * @return 联网企业档案库列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtCusBscs(EtCusBsc etCusBsc, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtCusBsc> etCusBscPage;
        try {
            //筛选登录人企业数据
            BsspUtil.filterCopEnt(etCusBsc, null);
            // 分页
            etCusBscPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etCusBscPage.setAsc(super.getOrderSort(sortOrder));
                etCusBscPage.setOrderByField(sort);
            } else {
                //默认按录入日期倒排序
                etCusBscPage.setAsc(false);
                etCusBscPage.setOrderByField("DEC_TIME");
            }
            etCusBscPage = etCusBscService.selectEtCusBscList(etCusBscPage, etCusBsc);
        } catch (Exception e) {
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etCusBscPage.getRecords(), etCusBscPage.getTotal());
    }
}
