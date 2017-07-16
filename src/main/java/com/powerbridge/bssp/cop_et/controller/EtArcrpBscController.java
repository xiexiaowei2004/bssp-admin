package com.powerbridge.bssp.cop_et.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.Aspect.RepeatSubmitValidation;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.ChkStatusConstant;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import com.powerbridge.bssp.cop_et.service.IEtArcrpBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：EtArcrpBscController
 * 类描述：企业联网档案库基本表接口实现
 * 创建人：willChen
 * 创建时间：2017/5/22 9:49
 * 修改人：willChen
 * 修改时间：2017-07-03 11:06:00
 */
@Controller
@RequestMapping("/cop_et/etArcrpBsc")
@CrossOrigin
public class EtArcrpBscController extends BaseController {

    @Autowired
    private IEtArcrpBscService etArcrpBscService;

    /**
     * 新增联网企业档案库
     *
     * @param etArcrpBsc 详细信息
     * @param isCheck    是否校验,申报时的保存需要校验
     * @return 档案库信息
     */
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEtArcrpBsc(EtArcrpBsc etArcrpBsc, boolean isCheck) {
        AjaxResult ajaxResult;
        try {
            //使用工具类生成id
            String uId = UUIDGenerator.getUUID();
            etArcrpBsc.setUid(uId);

            //判断是否验证数据合法性
            if (isCheck) {
                //验证
                check(etArcrpBsc);
            } else {
                etArcrpBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            }

            DocTypeConstants.setDocType(etArcrpBsc, null);
            //申报本地保存之前先提交信息到队列表
            if (isCheck) {
                BsspUtil.checkStatusDeclare(etArcrpBsc, null, null, false);
            }
            if (etArcrpBscService.insert(etArcrpBsc)) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("addEtArcrpBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id删除联网企业档案库
     *
     * @param id uid主键
     * @return 0 失败 1 成功
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEtArcrpBscById(@PathVariable String id) {
        try {
            //查询记录是否存在
            EtArcrpBsc etArcrpBsc = etArcrpBscService.selectById(id);
            if (etArcrpBsc == null) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该记录不存在");
            }

            //判断该单据状态是否可删除
            if (!BsspUtil.checkStatusEdit(etArcrpBsc.getChkStatus())) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该状态数据无法删除");
            }
            //执行删除
            if (etArcrpBscService.deleteEtArcrpBscById(etArcrpBsc)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("deleteEtArcrpBscById()--err", e);
            return setErrorJson(e.getMessage());
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 修改联网企业档案库
     *
     * @param etArcrpBsc 详细信息
     * @return 档案库信息
     */
    @RepeatSubmitValidation
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEtArcrpBsc(EtArcrpBsc etArcrpBsc, boolean isCheck) {
        AjaxResult ajaxResult;
        try {
            if (StringUtil.isEmpty(etArcrpBsc.getUid())) {
                return json(MessageConstants.BSSP_STATUS_CODE_NOTEXIST, "主键不能为空");
            }
            //判断该单据状态是否可修改
            if (!BsspUtil.checkStatusEdit(etArcrpBsc.getChkStatus())) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该状态数据无法修改");
            }
            //判断是否验证数据合法性
            if (isCheck) {
                //验证
                check(etArcrpBsc);
                //申报本地保存之前先提交信息到队列表
                BsspUtil.checkStatusDeclare(etArcrpBsc, null, null, false);
            } else {
                etArcrpBsc.setChkStatus(ChkStatusConstant.CHK_STATUS_S);
            }


            if (etArcrpBscService.updateById(etArcrpBsc)) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateEtArcrpBsc()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据id查询联网企业档案库信息
     *
     * @param id 主键id
     * @return 联网企业档案库信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtArcrpBscById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EtArcrpBsc etArcrpBsc = etArcrpBscService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpBsc);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("selectEtArcrpBscById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询联网企业档案库列表
     *
     * @param etArcrpBsc 查询条件
     * @param pageSize   页面容量
     * @param pageNumber 第几页
     * @param sort       排序列名
     * @param sortOrder  排序规则
     * @return 联网企业档案库列表
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEtArcrpBscList(EtArcrpBsc etArcrpBsc, Integer pageSize, Integer pageNumber, String sort, String sortOrder) {
        Page<EtArcrpBsc> etArcrpBscPage;
        try {
            //筛选登录人企业数据
            BsspUtil.filterCopEnt(etArcrpBsc, null);
            // 分页
            etArcrpBscPage = getPage();
            if (StringUtil.isNotEmpty(sort)) {
                etArcrpBscPage.setAsc(super.getOrderSort(sortOrder));
                etArcrpBscPage.setOrderByField(sort);
            } else {
                //默认按录入日期倒排序
                etArcrpBscPage.setAsc(false);
                etArcrpBscPage.setOrderByField("DEC_TIME");
            }
            etArcrpBscPage = etArcrpBscService.selectEtArcrpBscList(etArcrpBscPage, etArcrpBsc);
        } catch (Exception e) {
            logger.error("selectEtArcrpBscList()--err", e);
            return setErrorJson(e.getMessage());
        }
        return json(MessageConstants.BSSP_STATUS_SUCCESS, etArcrpBscPage.getRecords(), etArcrpBscPage.getTotal());
    }

    /**
     * 查询单条联网企业档案库是否可变更
     *
     * @param seqNo 统一编号
     * @return true false
     */
    @RequestMapping(value = "/list/{seqNo}/isCanChange", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Boolean isCanChange(@PathVariable String seqNo) {
        try {
            //该统一编号存在不为审批通过的记录则不可变更
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("SEQ_NO", seqNo);
            entityWrapper.notIn("CHK_STATUS", ChkStatusConstant.CHK_STATUS_P);
            List<EtArcrpBsc> etArcrpBscList = etArcrpBscService.selectList(entityWrapper);
            return etArcrpBscList.size() <= 0;
        } catch (Exception e) {
            logger.error("isCanChange()--err", e);
            return false;
        }
    }
}
