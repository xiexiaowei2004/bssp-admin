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
import com.powerbridge.bssp.sas_cmb.entity.SasCmbImg;
import com.powerbridge.bssp.sas_cmb.entity.SasCmbRbg;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbImgService;
import com.powerbridge.bssp.sas_cmb.service.ISasCmbRbgService;

/**
 * <p>
 * 耗料单料件表 前端控制器
 * </p>
 *
 * @author huanliu
 * @since 2017-06-18
 */
@Controller
@RequestMapping("/sas_cmb/sasCmbImg")
@CrossOrigin
public class SasCmbImgController extends BaseController {
    //获取当前时间
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @Autowired
    private ISasCmbImgService sasCmbImgService;
    /**
     * 新增料件
     * @param sasCmbImg 详细信息
     * @return
     */
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addSasCmbRbg(SasCmbImg sasCmbImg) {
        AjaxResult ajaxResult;
        //TODO:需要添加验证,保证该账单下只有三个附件
        try {
            //使用工具类生成id
            String uId = UUIDGenerator.getUUID();
            if (StringUtil.isEmpty(uId)) {
                //失败
                return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
            }
            sasCmbImg.setUid(uId);
            boolean flag = sasCmbImgService.insert(sasCmbImg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbImg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addSasCmbImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id删除料件
     *
     * @param id uid主键
     * @return
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(@PathVariable String id) {
        if (sasCmbImgService.deleteById(id)) {
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
    public AjaxResult deleteBySasCmbImg(String idList) {
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<SasCmbImg>();
        entityWrapper.in("UID", ids);
        if (sasCmbImgService.delete(entityWrapper)) {
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
    public AjaxResult updateSasCmbImg(SasCmbImg sasCmbImg) {
        if (null == sasCmbImg.getUid()) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if (sasCmbImgService.updateById(sasCmbImg)) {
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
        SasCmbImg sasCmbImg = sasCmbImgService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbImg);
    }

    /**
     * 查询料件列表
     *
     * @param emsBwsAcmpFormDt
     * @return 账册备案申请附件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectSasCmbImgs(SasCmbImg sasCmbImg) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认料件序号排序
                page.setAsc(true);
                page.setOrderByField("cmbNo");  // 排序
            }
            Page<SasCmbImg> sasCmbImgPage = sasCmbImgService.selectSasCmbImgList(page, sasCmbImg);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, sasCmbImgPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectsasCmbImgList()--error", e);
        }
        return ajaxResult;
    }
}


