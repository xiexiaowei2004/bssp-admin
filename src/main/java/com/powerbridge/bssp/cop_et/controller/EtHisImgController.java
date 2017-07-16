package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtHisImg;
import com.powerbridge.bssp.cop_et.service.IEtHisImgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：EtHisImgController
 * 类描述：企业联网档案库正式表料件接口实现
 * 创建人：willChen
 * 创建时间：2017-07-03 10:34:28
 * 修改人：willChen
 * 修改时间：2017-07-03 10:34:33
 */
@Controller
@RequestMapping("/cop_et/etHisImg")
@CrossOrigin
public class EtHisImgController extends BaseController {
    @Autowired
    private IEtHisImgService etHisImgService;

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
        EtHisImg etHisImg = etHisImgService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etHisImg);
    }

    /**
     * 根据条件查询
     *
     * @param etHisImg   查询条件
     * @param pageSize   页面容量
     * @param pageNumber 第几页
     * @param sort       排序列名
     * @param sortOrder  排序规则
     * @return 料件列表
     */
    @RequiresPermissions("etArcrpImg:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtHisImgList(EtHisImg etHisImg, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtHisImg> etHisImgPage;
        try {
            //分页
            etHisImgPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etHisImgPage.setAsc(super.getOrderSort(sortOrder));
                etHisImgPage.setOrderByField(sort);
            } else {
                //默认商品序号排序
                etHisImgPage.setAsc(true);
                etHisImgPage.setOrderByField("A.GDS_SEQNO");
            }
            etHisImgPage = etHisImgService.selectEtHisImgList(etHisImgPage, etHisImg);
        } catch (Exception e) {
            logger.error("selectEtHisImgList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etHisImgPage.getRecords(), etHisImgPage.getTotal());
    }

}
