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
import com.powerbridge.bssp.erp.entity.ErpPreDtExg;
import com.powerbridge.bssp.erp.service.IErpPreDtExgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 项目名称：bssp-admin
 * 类名称：ErpMidDtExgController
 * 类描述：成品预录入表记录接口实现
 * 创建人：lindapeng
 * 创建时间：2017/6/2 19:49
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/erp/erpPreDtExg")
@CrossOrigin
public class ErpPreDtExgController extends BaseController {
    @Autowired
    private IErpPreDtExgService erpPreDtExgService;

    /**
     * 查询联网企业档案库列表
     *
     * @param erpPreDtExg
     * @return 企业原始成品列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showList(ErpPreDtExg erpPreDtExg) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            }
            else {
                page.setAsc(sortOrder);
                page.setOrderByField("gdsSeqno");  // 排序
            }
            BsspUtil.filterCopEnt(erpPreDtExg, null);
            Page<ErpPreDtExg> erpPreDtExgList = erpPreDtExgService.getListData(page, erpPreDtExg);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreDtExgList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showErpPreDtImgList()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param: erpPreDtExg
     * 检查成品料号重复
     * */
    private void checkRepeat(ErpPreDtExg erpPreDtExg) throws Exception{
        EntityWrapper<ErpPreDtExg> entityWrapper = new EntityWrapper<ErpPreDtExg>();
        entityWrapper.eq("GDS_MTNO", erpPreDtExg.getGdsMtno());
        String tradeCode = SingletonLoginUtils.getSystemUser().getInputCopNo();
        entityWrapper.eq("INPUT_COP_NO",tradeCode);
        entityWrapper.ne("UID", erpPreDtExg.getUid());
        //获取匹配的企业数
        Integer count = erpPreDtExgService.selectCount(entityWrapper);
        if (count > 0) {
            throw new Exception("成品料号重复");
        }
    }
    /**
     * @param erpPreDtExg
     * @return AjaxResult
     * @throws
     * @Description: 新增企业原始成品
     */
    //@RequiresPermissions("erpPreDtExg:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addErpPreDtExg(ErpPreDtExg erpPreDtExg) {
        AjaxResult ajaxResult;
        ajaxResult = null;
        /*AjaxResult validResult = check(erpPreDtExg);
        if (!ObjectUtils.isEmpty(validResult)) {
            return validResult;
        }*/
        try {
            //校验料件料号是否重复
            checkRepeat(erpPreDtExg);
            erpPreDtExg.setUid(UUIDGenerator.getUUID());
            erpPreDtExg.setSuccessFlag("0");
            SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
            erpPreDtExg.setCreateBy(sysUser.getUserName());
            erpPreDtExg.setCreateTime(sysUser.getCreateTime());
            erpPreDtExg.setInputCopNo(sysUser.getInputCopNo());
            erpPreDtExg.setInputEtpsSccd(sysUser.getCopGbCode());
            erpPreDtExg.setInputCopName(sysUser.getInputCopName());
            Boolean flag = erpPreDtExgService.insert(erpPreDtExg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreDtExg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addErpPreDtExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param erpPreDtExg
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("erpPreDtExg:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasDclBsc(ErpPreDtExg erpPreDtExg) {
        AjaxResult ajaxResult = null;
        try {
            //校验料件料号是否重复
            checkRepeat(erpPreDtExg);
            Boolean flag = erpPreDtExgService.updateById(erpPreDtExg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,erpPreDtExg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateErpPreDtExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
//    @RequiresPermissions("erpPreDtExg:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editSasDclBsc(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            ErpPreDtExg erpPreDtExg = erpPreDtExgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreDtExg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("viewErpPreDtExg()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
//    @RequiresPermissions("erpPreDtExg:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteErpPreDtExg(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            boolean flag = erpPreDtExgService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteErpPreDtExg()--err", e);
        }
        return ajaxResult;
    }
    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 获取最大的商品序号
     */
    @RequestMapping(value = "/list/getMaxGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getMaxGdsSeqno() {
        AjaxResult ajaxResult = null;
        try {

            BigDecimal seqno = erpPreDtExgService.getMaxGdsSeqno();
            if (null != seqno) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,seqno);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getMaxGdsSeqno_ErpPreDtExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 根据序号取成品信息
     */
    @RequestMapping(value = "/list/getDataByGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getDataByGdsSeqno(String gdsSeqno) {
        AjaxResult ajaxResult = null;
        try {

            ErpPreDtExg erpPreDtExg = erpPreDtExgService.getDataByGdsSeqno(gdsSeqno);
            if (null != erpPreDtExg) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,erpPreDtExg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getDataByGdsSeqno_ErpPreDtExg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 取中间表信息
     */
    @RequestMapping(value = "/list/getMidExgData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getMidExgData() {
        AjaxResult ajaxResult = null;
        String str = erpPreDtExgService.getMidExgData();
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
     * @param ids
     * @return AjaxResult
     * @throws
     * @Description: 将企业原始成品数据写入账册成品
     */
    @RequestMapping(value = "/list/insertEmsExgDataByIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insertEmsExgDataByIds(String ids, String emsId) {
        AjaxResult ajaxResult = null;
        try {
            String mess = erpPreDtExgService.insertEmsExgDataByIds(ids, emsId);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, mess);
        } catch (Exception e) {
            ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return ajaxResult;
    }
}