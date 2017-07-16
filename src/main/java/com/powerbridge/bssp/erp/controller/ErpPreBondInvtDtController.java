package com.powerbridge.bssp.erp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.EmsCusImg;
import com.powerbridge.bssp.erp.entity.ErpPreBondInvtDt;
import com.powerbridge.bssp.erp.service.IErpPreBondInvtDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 核注清单表体-预处理 前端控制器
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-19
 */
@Controller
@RequestMapping("/erp/erpPreBondInvtDt")
@CrossOrigin
public class ErpPreBondInvtDtController extends BaseController {
    @Autowired
    private IErpPreBondInvtDtService erpPreBondInvtDtService;
    /**
     * @param erpPreBondInvtDt
     * @return AjaxResult
     * @throws
     * @Description: 进入企业原始清单商品(列表查询)
     */
    //@RequiresPermissions("erpPreBondInvtDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showList(ErpPreBondInvtDt erpPreBondInvtDt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }
            Page<ErpPreBondInvtDt> erpPreBondInvtDtPage = erpPreBondInvtDtService.getListData(page , erpPreBondInvtDt);
            return json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreBondInvtDtPage.getRecords(), page.getTotal());

        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showErpPreBondInvtDtList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作(清单商品)
     */
//    @RequiresPermissions("erpPreBondInvtDt:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            List list = Arrays.asList(idList.split(","));   // idList 转换格式
            boolean flag = erpPreBondInvtDtService.deleteBatchIds(list);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteErpPreBondInvtDtByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 新增账册料件或成品
     */
//    @RequiresPermissions("erpPreBondInvtDt:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addErpPreBondInvtDtByEmsList(String idList,String mtpckEndprdMarkcd, String etpsInnerInvtNo) {
        AjaxResult ajaxResult = null;
        String str = erpPreBondInvtDtService.addPreBondInvtDtByEmsList(idList,mtpckEndprdMarkcd,etpsInnerInvtNo);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(str);
        String code = jsonObject.get("code").toString();
        String message = jsonObject.get("message").toString();
        String data = jsonObject.get("data").toString();
        if (code.equals("1")) {
            Gson gson = new Gson();
            ErpPreBondInvtDt erpPreBondInvtDt = gson.fromJson(data.toString(), new TypeToken<ErpPreBondInvtDt>(){}.getType());
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,erpPreBondInvtDt);
        }
        else {
            ajaxResult = setJson(0, message);
        }
        return ajaxResult;
    }

    /**
     * @param erpPreBondInvtDt
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("erpPreBondInvtDt:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateErpPreBondInvtDt(ErpPreBondInvtDt erpPreBondInvtDt) {
        AjaxResult ajaxResult = null;

        try {
            Boolean flag = erpPreBondInvtDtService.updateById(erpPreBondInvtDt);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,erpPreBondInvtDt);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateErpPreBondInvtDt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("erpPreBondInvtDt:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editErpPreBondInvtBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            ErpPreBondInvtDt erpPreBondInvtDt = erpPreBondInvtDtService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreBondInvtDt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editErpPreBondInvtDt()--err", e);
        }
        return ajaxResult;
    }
}
