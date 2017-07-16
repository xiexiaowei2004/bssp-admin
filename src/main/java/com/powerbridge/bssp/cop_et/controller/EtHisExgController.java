package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtHisExg;
import com.powerbridge.bssp.cop_et.service.IEtHisExgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EtHisExgController
 * 类描述：企业联网档案库正式表成品接口实现
 * 创建人：willChen
 * 创建时间：2017-07-03 10:32:10
 * 修改人：willChen
 * 修改时间：2017-07-03 10:32:15
 */
@Controller
@RequestMapping("/cop_et/etHisExg")
@CrossOrigin
public class EtHisExgController extends BaseController {
    @Autowired
    private IEtHisExgService etHisExgService;

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
        EtHisExg etHisExg = etHisExgService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etHisExg);
    }

    /**
     * 根据条件查询
     *
     * @param etHisExg   查询条件
     * @param pageSize   页面容量
     * @param pageNumber 第几页
     * @param sort       排序列名
     * @param sortOrder  排序规则
     * @return 成品列表
     */
    @RequiresPermissions("etArcrpExg:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtHisExgList(EtHisExg etHisExg, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtHisExg> etHisExgPage;
        try {
            // 分页
            etHisExgPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etHisExgPage.setAsc(super.getOrderSort(sortOrder));
                etHisExgPage.setOrderByField(sort);
            } else {
                //默认商品序号排序
                etHisExgPage.setAsc(true);
                etHisExgPage.setOrderByField("A.GDS_SEQNO");
            }
            etHisExgPage = etHisExgService.selectEtHisExgList(etHisExgPage, etHisExg);
        } catch (Exception e) {
            logger.error("selectEtHisExgList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etHisExgPage.getRecords(), etHisExgPage.getTotal());
    }

}
