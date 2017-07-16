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
import com.powerbridge.bssp.erp.entity.ErpPreDtImg;
import com.powerbridge.bssp.erp.service.IErpPreDtImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 项目名称：bssp-admin
 * 类名称：ErpMidDtImgController
 * 类描述：料件预录入表记录接口实现
 * 创建人：lindapeng
 * 创建时间：2017/6/2 19:49
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/erp/erpPreDtImg")
@CrossOrigin
public class ErpPreDtImgController extends BaseController {

    @Autowired
    private IErpPreDtImgService erpPreDtImgService;
    /**
     * 查询联网企业档案库列表
     *
     * @param erpPreDtImg
     * @return 企业原始料件列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showList(ErpPreDtImg erpPreDtImg) {
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
            BsspUtil.filterCopEnt(erpPreDtImg, null);
            Page<ErpPreDtImg> erpPreDtImgList = erpPreDtImgService.getListData(page, erpPreDtImg);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreDtImgList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showErpPreDtImgList()--error", e);
        }
        return ajaxResult;
    }
    /*public AjaxResult selectErpPreDtImg(@RequestParam(name = "gdsSeqno", required = false) String gdsSeqno, @RequestParam(name = "gdsMtno", required = false) String gdsMtno,
                                        @RequestParam(name = "gdecd", required = false) String gdecd, @RequestParam(name = "gdsNm", required = false) String gdsNm,
                                        @RequestParam(name = "filterCond", required = false) String filterCond,@RequestParam(name = "createTimeStart", required = false) String createTimeStart,
                                        @RequestParam(name = "createTimeEnd", required = false) String createTimeEnd,
                                        @RequestParam(name = "successFlag", required = false) String successFlag,
                                        Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
       EntityWrapper entityWrapper = new EntityWrapper();
        if (StringUtil.isNotEmpty(gdsSeqno)) {
            entityWrapper.eq("GDS_SEQNO", gdsSeqno);
        }
        if (StringUtil.isNotEmpty(gdsMtno)) {
            entityWrapper.like("GDS_MTNO",  gdsMtno , SqlLike.RIGHT);
        }
        if (StringUtil.isNotEmpty(gdecd)) {
            entityWrapper.eq("GDECD", gdecd);
        }
        if (StringUtil.isNotEmpty(gdsNm)) {
            entityWrapper.like("GDS_NM",  "%" + gdsNm + "%");
        }
        if (StringUtil.isNotEmpty(createTimeStart)) {
            entityWrapper.ge("CREATE_TIME", createTimeStart + " 00:00:00");
        }
        if (StringUtil.isNotEmpty(createTimeEnd)) {
            entityWrapper.le("CREATE_TIME", createTimeEnd + " 23:59:59");
        }
        if (StringUtil.isNotEmpty(filterCond)) {
            //2:已备案 3:未备案 4:保税 5:非保税
            if (filterCond.equals("2")) {
                 entityWrapper.eq("SUCCESS_FLAG", "1");
             }
             else  if (filterCond.equals("3")) {
                 entityWrapper.eq("SUCCESS_FLAG", "0");
             }
             else  if (filterCond.equals("4")) {
                 entityWrapper.eq("BONDED_FLAG", "1");
             }
             else  if (filterCond.equals("5")) {
                 entityWrapper.eq("BONDED_FLAG", "0");
             }
        }
        if (StringUtil.isNotEmpty(successFlag)) {
            entityWrapper.eq("SUCCESS_FLAG", successFlag);
        }
        String tradeCode = SingletonLoginUtils.getSystemUser().getInputCopNo();
        entityWrapper.eq("INPUT_COP_NO",tradeCode);
        if (!StringUtil.isEmpty(sort)) {
            // 排序
            entityWrapper.orderBy(sort, super.getOrderSort(sortOrder));
        }

        Page page;
        if (pageSize >= 1) {
            //分页
            page = new Page(pageNumber / pageSize + 1, pageSize);
        } else {
            page = new Page();
        }
        Page<ErpPreDtImg> erpPreDtImgPage = erpPreDtImgService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreDtImgPage.getRecords(), page.getTotal());
    }*/

/*    *
     * @param: erpPreDtImg
     * 检查料件料号重复
     * */
    private void checkRepeat(ErpPreDtImg erpPreDtImg) throws Exception{
        EntityWrapper<ErpPreDtImg> entityWrapper = new EntityWrapper<ErpPreDtImg>();
        entityWrapper.eq("GDS_MTNO", erpPreDtImg.getGdsMtno());
        String tradeCode = SingletonLoginUtils.getSystemUser().getInputCopNo();
        entityWrapper.eq("INPUT_COP_NO",tradeCode);
        entityWrapper.ne("UID", erpPreDtImg.getUid());
        //获取匹配的企业数
        Integer count = erpPreDtImgService.selectCount(entityWrapper);
        if (count > 0) {
            throw new Exception("料件料号重复");
        }
    }
    /**
     * @param erpPreDtImg
     * @return AjaxResult
     * @throws
     * @Description: 新增企业原始料件
     */
    //@RequiresPermissions("erpPreDtImg:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addErpPreDtImg(ErpPreDtImg erpPreDtImg) {
        AjaxResult ajaxResult;
        ajaxResult = null;
        /*AjaxResult validResult = check(erpPreDtImg);
        if (!ObjectUtils.isEmpty(validResult)) {
            return validResult;
        }*/
        try {
            //校验料件料号是否重复
            checkRepeat(erpPreDtImg);
            erpPreDtImg.setUid(UUIDGenerator.getUUID());
            erpPreDtImg.setSuccessFlag("0");
            SystemAuthorizingUser sysUser = SingletonLoginUtils.getSystemUser();
            erpPreDtImg.setCreateBy(sysUser.getUserName());
            erpPreDtImg.setCreateTime(sysUser.getCreateTime());
            erpPreDtImg.setInputCopNo(sysUser.getInputCopNo());
            erpPreDtImg.setInputEtpsSccd(sysUser.getCopGbCode());
            erpPreDtImg.setInputCopName(sysUser.getInputCopName());
            Boolean flag = erpPreDtImgService.insert(erpPreDtImg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreDtImg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addErpPreDtImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param erpPreDtImg
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
//    @RequiresPermissions("erpPreDtImg:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateSasDclBsc(ErpPreDtImg erpPreDtImg) {
        AjaxResult ajaxResult = null;
        try {
            //校验料件料号是否重复
            checkRepeat(erpPreDtImg);
            Boolean flag = erpPreDtImgService.updateById(erpPreDtImg);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,erpPreDtImg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateErpPreDtImg()--err", e);
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
            ErpPreDtImg erpPreDtImg = erpPreDtImgService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, erpPreDtImg);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("viewErpPreDtImg()--err", e);
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
            boolean flag = erpPreDtImgService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteErpPreDtImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 取最大序号
     */
    @RequestMapping(value = "/list/getMaxGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getMaxGdsSeqno(ErpPreDtImg erpPreDtImg) {
        AjaxResult ajaxResult = null;
        try {

            BigDecimal seqno = erpPreDtImgService.getMaxGdsSeqno();
            if (null != seqno) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,seqno);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getMaxGdsSeqno_ErpPreDtImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 根据序号取料件信息
     */
    @RequestMapping(value = "/list/getDataByGdsSeqno", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getDataByGdsSeqno(String gdsSeqno) {
        AjaxResult ajaxResult = null;
        try {

            ErpPreDtImg erpPreDtImg = erpPreDtImgService.getDataByGdsSeqno(gdsSeqno);
            if (null != erpPreDtImg) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,erpPreDtImg);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getDataByGdsSeqno_ErpPreDtImg()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 取中间表信息
     */
    @RequestMapping(value = "/list/getMidImgData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getMidImgData() {
        AjaxResult ajaxResult = null;
        String str = erpPreDtImgService.getMidImgData();
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
     * @Description: 将企业原始料件数据写入账册料件
     */
    @RequestMapping(value = "/list/insertEmsImgDataByIds", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult insertEmsImgDataByIds(String ids, String emsId) {
        AjaxResult ajaxResult = null;
        try {
            String mess = erpPreDtImgService.insertEmsImgDataByIds(ids, emsId);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, mess);
        } catch (Exception e) {
            ajaxResult = json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return ajaxResult;
    }
}
