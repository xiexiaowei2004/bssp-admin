package com.powerbridge.bssp.sas_cmb.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.sas_cmb.entity.SasCmbRbg;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbRbgService;

/**
 * <p>
 * 耗料单边角料表 前端控制器
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Controller
@RequestMapping("/sas_cmb/sasCmbRbg")
@CrossOrigin
public class SasCmbRbgController extends BaseController {
    //获取当前时间
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @Autowired
    private ISasCmbRbgService sasCmbRbgService;
    /**
     * 新增边角料
     * @param sasCmbRbg 详细信息
     * @return
     */
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasCmbRbg(SasCmbRbg sasCmbRbg) {
        AjaxResult ajaxResult;
        //TODO:需要添加验证,保证该账单下只有三个附件
        try {
            //使用工具类生成id
            String uId = UUIDGenerator.getUUID();
            if (StringUtil.isEmpty(uId)) {
                //失败
                return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
            }
            sasCmbRbg.setUid(uId);
            boolean flag = sasCmbRbgService.insert(sasCmbRbg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbRbg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addSasCmbRbg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id删除边角料
     *
     * @param id uid主键
     * @return
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (sasCmbRbgService.deleteById(id)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    /**
     * 根据条件删除
     */
    @RequestMapping(value = "/list/delete/byIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteBySasCmbRbg(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<SasCmbRbg>();
        entityWrapper.in("UID", ids);
        if (sasCmbRbgService.delete(entityWrapper)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    /**
     * 根据id修改
     */
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEmsBwsAcmpFormDt(SasCmbRbg sasCmbRbg) {
        if (null == sasCmbRbg.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (sasCmbRbgService.updateById(sasCmbRbg)) {
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    /**
     * 根据id查询
     */

    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(@PathVariable String id) {
        SasCmbRbg sasCmbRbg = sasCmbRbgService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbRbg);
    }

    /**
     * 查询边角料列表
     *
     * @param emsBwsAcmpFormDt
     * @return 账册备案申请附件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectSasCmbRbgs(SasCmbRbg sasCmbRbg) {
        AjaxResult ajaxResult = null;
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
                page.setOrderByField("CMB_NO");  // 排序
            }
            Page<SasCmbRbg> sasCmbRbgPage = sasCmbRbgService.selectSasCmbRbgList(page, sasCmbRbg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbRbgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectSasCmbRbgList()--error", e);
        }
        return ajaxResult;
    }
}


