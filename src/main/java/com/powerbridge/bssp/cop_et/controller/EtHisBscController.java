package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtHisBsc;
import com.powerbridge.bssp.cop_et.service.IEtHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EtHisBscController
 * 类描述：企业联网档案库历史表接口实现
 * 创建人：willChen
 * 创建时间：2017-07-03 10:22:13
 * 修改人：willChen
 * 修改时间：2017-07-03 10:22:16
 */
@Controller
@RequestMapping("/cop_et/etHisBsc")
@CrossOrigin
public class EtHisBscController extends BaseController {

    @Autowired
    private IEtHisBscService etHisBscService;

    /**
     * 根据id查询联网企业档案库信息
     *
     * @param id 主键id
     * @return 联网企业档案库信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtHisBscById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EtHisBsc etHisBsc = etHisBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, etHisBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEtHisBscById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询联网企业档案库列表
     *
     * @param etHisBsc   查询条件
     * @param pageSize   页面容量
     * @param pageNumber 第几页
     * @param sort       排序列名
     * @param sortOrder  排序规则
     * @return 联网企业档案库列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtHisBscs(EtHisBsc etHisBsc, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtHisBsc> etHisBscPage;
        try {
            //筛选登录人企业数据
            BsspUtil.filterCopEnt(etHisBsc, null);
            etHisBscPage = getPage();
            // 分页
            if (StringUtil.isNotEmpty(sort)) {
                etHisBscPage.setAsc(super.getOrderSort(sortOrder));
                etHisBscPage.setOrderByField(sort);
            } else {
                //默认按录入日期倒排序
                etHisBscPage.setAsc(false);
                etHisBscPage.setOrderByField("DEC_TIME");
            }
            etHisBscPage = etHisBscService.selectEtHisBscList(etHisBscPage, etHisBsc);
        } catch (Exception e) {
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etHisBscPage.getRecords(), etHisBscPage.getTotal());
    }
}
