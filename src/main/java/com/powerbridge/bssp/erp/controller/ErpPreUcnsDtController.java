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
import com.powerbridge.bssp.erp.entity.ErpPreUcnsDt;
import com.powerbridge.bssp.erp.service.IErpPreUcnsDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：bssp-admin
 * 类名称：ErpPreUcnsDtController
 * 类描述：单损耗预录入表记录接口实现
 * 创建人：lindapeng
 * 创建时间：2017/6/2 19:49
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/erp/erpPreUcnsDt")
@CrossOrigin
public class ErpPreUcnsDtController extends BaseController {

        @Autowired
        private IErpPreUcnsDtService erpPreUcnsDtService;
        /**
         * @param erpPreUcnsDt
         * @return AjaxResult
         * @throws
         * @Description: 进入企业备案管理页面:(列表查询)
         */
        //@RequiresPermissions("ErpPreUcnsDt:list:view")
        @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
        @ResponseBody
        public AjaxResult showList(ErpPreUcnsDt erpPreUcnsDt) {
                AjaxResult ajaxResult = null;
                try {
                        Page page = getPage();  // 分页
                        String sort = getParameter("sort");
                        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
                        if (StringUtil.isNotEmpty(sort)) {
                                page.setAsc(sortOrder);
                                page.setOrderByField(sort);  // 排序
                        }
                        BsspUtil.filterCopEnt(erpPreUcnsDt, null);
                        Page<ErpPreUcnsDt> erpPreUcnsDtList = erpPreUcnsDtService.getListData(page, erpPreUcnsDt);

                        ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreUcnsDtList.getRecords(), page.getTotal()); // 格式要返回的数据
                } catch (Exception e) {
                        ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                        logger.error("showErpPreUcnsDtList()--error", e);
                }
                return ajaxResult;
        }

        /**
         * @param erpPreUcnsDt
         * @return AjaxResult
         * @throws
         * @Description: 新增企业原始耗料
         */
        //@RequiresPermissions("erpPreUcnsDt:list:add")
        @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
        @ResponseBody
        public AjaxResult addErpPreUcnsDt(ErpPreUcnsDt erpPreUcnsDt) {
                AjaxResult ajaxResult;
                ajaxResult = null;

                try {
                        //校验单耗是否重复
                        checkRepeat(erpPreUcnsDt);
                        erpPreUcnsDt.setUid(UUIDGenerator.getUUID());
                        erpPreUcnsDt.setSuccessFlag("0");
                        SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
                        erpPreUcnsDt.setCreateBy(sysUser.getUserName());
                        erpPreUcnsDt.setCreateTime(sysUser.getCreateTime());
                        erpPreUcnsDt.setInputCopNo(sysUser.getInputCopNo());
                        erpPreUcnsDt.setInputEtpsSccd(sysUser.getCopGbCode());
                        erpPreUcnsDt.setInputCopName(sysUser.getInputCopName());
                        Boolean flag = erpPreUcnsDtService.insert(erpPreUcnsDt);
                        if (flag) {
                                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreUcnsDt);
                        } else {
                                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                        }
                } catch (Exception e) {
                        ajaxResult = setErrorJson(e.getMessage());
                        logger.error("addErpPreUcnsDt()--err", e);
                }
                return ajaxResult;
        }

        /**
         * @param erpPreUcnsDt
         * @return AjaxResult
         * @throws
         * @Description: 修改数据
         */
//    @RequiresPermissions("erpPreDtImg:list:edit")
        @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
        @ResponseBody
        public AjaxResult updateSasDclBsc(ErpPreUcnsDt erpPreUcnsDt) {
                AjaxResult ajaxResult = null;

                try {
                        //校验单耗是否重复
                        checkRepeat(erpPreUcnsDt);
                        Boolean flag = erpPreUcnsDtService.updateById(erpPreUcnsDt);
                        if (flag) {
                                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,erpPreUcnsDt);
                        } else {
                                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                        }
                } catch (Exception e) {
                        ajaxResult = setErrorJson(e.getMessage());
                        logger.error("updateErpPreUcnsDt()--err", e);
                }
                return ajaxResult;
        }

        /**
         * @param
         * @return AjaxResult
         * @throws
         * @Description: 编辑页面
         */
//    @RequiresPermissions("erpPreDtImg:list:view")
        @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
        @ResponseBody
        public AjaxResult editSasDclBsc(@PathVariable String id) {
                AjaxResult ajaxResult = null;
                try {
                        ErpPreUcnsDt erpPreUcnsDt = erpPreUcnsDtService.getDataById(id);
                        ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,erpPreUcnsDt);
                } catch (Exception e) {
                        ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                        logger.error("viewErpPreUcnsDt()--err", e);
                }
                return ajaxResult;
        }


        /**
         * @param
         * @return AjaxResult
         * @throws
         * @Description: 删除单条数据
         */
//    @RequiresPermissions("erpPreDtImg:list:delete")
        @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
        @ResponseBody
        public AjaxResult deleteErpPreDtImg(@PathVariable String id) {
                AjaxResult ajaxResult = null;
                try {
                        boolean flag = erpPreUcnsDtService.deleteById(id);
                        if (flag) {
                                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
                        }else {
                                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                        }
                } catch (Exception e) {
                        ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                        logger.error("deleteErpPreUcnsDt()--err", e);
                }
                return ajaxResult;
        }

        /**
         * @param: erpPreUcnsDt
         * 检查单耗录入重复
         * */
        private void checkRepeat(ErpPreUcnsDt erpPreUcnsDt) throws Exception{
                EntityWrapper<ErpPreUcnsDt> entityWrapper = new EntityWrapper<ErpPreUcnsDt>();
                entityWrapper.eq("ENDPRD_SEQNO", erpPreUcnsDt.getEndprdSeqno());
                entityWrapper.eq("MTPCK_SEQNO", erpPreUcnsDt.getMtpckSeqno());
                entityWrapper.eq("UCNS_VERNO",erpPreUcnsDt.getUcnsVerno());
                String tradeCode = SingletonLoginUtils.getSystemUser().getInputCopNo();
                entityWrapper.eq("INPUT_COP_NO",tradeCode);
                entityWrapper.ne("UID", erpPreUcnsDt.getUid());
                //获取匹配的企业数
                Integer count = erpPreUcnsDtService.selectCount(entityWrapper);
                if (count > 0) {
                        throw new Exception("单耗重复");
                }
        }

        /**
         * @param
         * @return AjaxResult
         * @throws
         * @Description: 取中间表信息
         */
        @RequestMapping(value = "/list/getMidUcnsData", method = {RequestMethod.POST, RequestMethod.GET})
        @ResponseBody
        public AjaxResult getMidImgData() {
                AjaxResult ajaxResult = null;
                String str = erpPreUcnsDtService.getMidUcnsData();
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

/*                AjaxResult ajaxResult = null;
                try {
                        String s = erpPreUcnsDtService.getMidUcnsData();
                        return json(MessageConstants.BSSP_STATUS_SUCCESS, s, 0);
                } catch (Exception e) {
                        ajaxResult = setErrorJson(e.getMessage());
                        logger.error("getMidUcnsDtData_ErpPreUcnsDt()--err", e);
                }
                return ajaxResult;*/
        }

        /**
         * @param ids
         *
         * @return AjaxResult
         * @throws
         * @Description: 将企业原始料件数据写入账册料件
         */
        @RequestMapping(value = "/list/insertEmsUcnsDataByIds", method = {RequestMethod.POST, RequestMethod.GET})
        @ResponseBody
        public AjaxResult insertEmsUcnsDtDataByIds(String ids, String emsId) {
                AjaxResult ajaxResult = null;
                try {
                        String mess = erpPreUcnsDtService.insertEmsUcnsDataByIds(ids, emsId);
                        ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, mess);
                } catch (Exception e) {
                        ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
                }
                return ajaxResult;
        }
}