package com.powerbridge.bssp.erp.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.security.SystemAuthorizingUser;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.json.JSONObject;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.erp.entity.ErpPreBondInvtBsc;
import com.powerbridge.bssp.erp.service.IErpPreBondInvtBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 核注清单表头-预处理 前端控制器
 * </p>
 *
 * @author lindapeng
 * @since 2017-06-19
 */
@Controller
@RequestMapping("/erp/erpPreBondInvtBsc")
@CrossOrigin
public class ErpPreBondInvtBscController extends BaseController {
    @Autowired
    private IErpPreBondInvtBscService erpPreBondInvtBscService;
    /**
     * @param erpPreBondInvtBsc
     * @return AjaxResult
     * @throws
     * @Description: 进入企业备案管理页面:(列表查询)
     */
    //@RequiresPermissions("ErpPreUcnsDt:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showList(ErpPreBondInvtBsc erpPreBondInvtBsc) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }
            BsspUtil.filterCopEnt(erpPreBondInvtBsc, null);
            Page<ErpPreBondInvtBsc> erpPreBondInvtBscPage = erpPreBondInvtBscService.getListData(page , erpPreBondInvtBsc);
            return json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreBondInvtBscPage.getRecords(), page.getTotal());

        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showErpPreBondInvtBscList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param erpPreBondInvtBsc
     * @return AjaxResult
     * @throws
     * @Description: 新增企业原始清单表头
     */
    //@RequiresPermissions("erpPreBondInvtBsc:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addErpPreBondInvtBsc(ErpPreBondInvtBsc erpPreBondInvtBsc) {
        AjaxResult ajaxResult;
        ajaxResult = null;
        try {
            //校验企业内部编号是否重复
            checkRepeat(erpPreBondInvtBsc);
            erpPreBondInvtBsc.setUid(UUIDGenerator.getUUID());
            erpPreBondInvtBsc.setSuccessFlag("0");
            SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
            erpPreBondInvtBsc.setCreateBy(sysUser.getUserName());
            erpPreBondInvtBsc.setCreateTime(sysUser.getCreateTime());
            Boolean flag = erpPreBondInvtBscService.insert(erpPreBondInvtBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreBondInvtBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addErpPreBondInvtBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param erpPreBondInvtBsc
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("erpPreBondInvtBsc:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateErpPreBondInvtBsc(ErpPreBondInvtBsc erpPreBondInvtBsc) {
        AjaxResult ajaxResult = null;

        try {
            //校验单耗是否重复
/*            checkRepeat(erpPreUcnsDt);*/
            Boolean flag = erpPreBondInvtBscService.updateById(erpPreBondInvtBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,erpPreBondInvtBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateErpPreBondInvtBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("invBsc:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editErpPreBondInvtBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            ErpPreBondInvtBsc erpPreBondInvtBsc = erpPreBondInvtBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreBondInvtBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editErpPreBondInvtBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除清单整单数据
     */
//    @RequiresPermissions("erpPreBondInvtBsc:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteErpPreBondInvtBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            boolean flag = erpPreBondInvtBscService.deletePreBondInvt(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteErpPreBondInvtBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param: erpPreBondInvtBsc
     * 检查企业内部编号重复
     * */
    private void checkRepeat(ErpPreBondInvtBsc erpPreBondInvtBsc) throws Exception{
        EntityWrapper<ErpPreBondInvtBsc> entityWrapper = new EntityWrapper<ErpPreBondInvtBsc>();
        entityWrapper.eq("ETPS_INNER_INVT_NO", erpPreBondInvtBsc.getEtpsInnerInvtNo());
        entityWrapper.eq("INPUT_COP_NO", erpPreBondInvtBsc.getInputCopNo());
        entityWrapper.ne("UID", erpPreBondInvtBsc.getUid());
        //获取匹配的企业数
        Integer count = erpPreBondInvtBscService.selectCount(entityWrapper);
        if (count > 0) {
            throw new Exception("企业内部编号重复");
        }
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 取中间表信息
     */
    @RequestMapping(value = "/list/getMidData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getMidData() {
        AjaxResult ajaxResult = null;
        String str = erpPreBondInvtBscService.getMidData();
        JSONObject jsonObject = (JSONObject) JSONObject.parse(str);
        String code = jsonObject.get("code").toString();
        String message = jsonObject.get("message").toString();
        if (code.equals("1")) {
            ajaxResult = setJson(1, message);
        }
        else {
            ajaxResult = setJson(0, message);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 生成核注清单
     */
    @RequestMapping(value = "/list/genBondInvt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult genBondInvt(String id) {
        AjaxResult ajaxResult = null;
        String str = erpPreBondInvtBscService.genBondInvt(id);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(str);
        String code = jsonObject.get("code").toString();
        String message = jsonObject.get("message").toString();
        if (code.equals("1")) {
            ajaxResult = setJson(1, message);
        }
        else {
            ajaxResult = setJson(0, message);
        }
        return ajaxResult;
    }
}
