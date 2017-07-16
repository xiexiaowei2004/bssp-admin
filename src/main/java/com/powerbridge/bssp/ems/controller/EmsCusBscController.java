package com.powerbridge.bssp.ems.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.ems.entity.*;
import com.powerbridge.bssp.ems.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 项目名称：bssp-admin
 * 类名称：EmsCusBscController
 * 类描述：账册备案申请接口实现
 * 创建人：jokylao
 * 创建时间：2017/6/4 11:23
 * 修改人：jokylao
 * 修改时间：2017/6/4 11:23
 */
@Controller
@RequestMapping("/ems/emsCusBsc")
@CrossOrigin
public class EmsCusBscController extends BaseController {
    @Autowired
    private IEmsCusBscService emsCusBscService;
    @Autowired
    private IEmsPutrecBscService emsPutrecBscService;

    /**
     * 根据id查询账册备案申请信息
     *
     * @param id 主键id
     * @return 账册备案申请信息
     */
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusBscsById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
            EmsCusBsc emsCusBsc = emsCusBscService.selectById(id);
            if (StringUtil.isNotEmpty(emsCusBsc.getSeqNo())) {
                EntityWrapper entityWrapper = new EntityWrapper();
                entityWrapper.eq("SEQ_NO", emsCusBsc.getSeqNo());
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusBsc);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusBscsById()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请列表
     */
    //@RequiresPermissions("emsCusBsc:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusBscs(EmsCusBsc emsCusBsc) {
        if(StringUtil.isEmpty(emsCusBsc.getEmsTypecd())){
            return result(MessageConstants.BSSP_STATUS_FAIL);
        }
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按录入日期倒排序
                page.setAsc(false);
                page.setOrderByField("decTime");  // 排序
            }
            BsspUtil.filterCopEnt(emsCusBsc, null);
            Page<EmsCusBsc> emsCusBscPage = emsCusBscService.selectEmsCusBscList(page, emsCusBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusBscPage.getRecords(), page.getTotal());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusBscList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * 查询账册备案申请列表
     */
    //@RequiresPermissions("emsCusBsc:list:view")
    @RequestMapping(value = "/list/selectByEmsNo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsCusBscByEmsNo(EmsCusBsc emsCusBscParam) {
        AjaxResult ajaxResult;
        try {

            if (StringUtil.isEmpty(emsCusBscParam.getEmsNo())) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_EMSNO_ERROR);
                return ajaxResult;
            }
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("EMS_NO", emsCusBscParam.getEmsNo());
            entityWrapper.ge("FINISH_VALID_DATE", DateUtil.today());
            if (!StringUtil.isEmpty(emsCusBscParam.getEmsTypecd())){
                entityWrapper.eq("EMS_TYPECD",emsCusBscParam.getEmsTypecd());
            }
            EmsCusBsc emsCusBsc = emsCusBscService.selectOne(entityWrapper);
            if (emsCusBsc != null) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsCusBscByEmsNo()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 根据录入单位编码查找所有旗下的账册编号
     *
     * @param emsCusBsc
     */
    @RequestMapping(value = "/list/emsNoList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectEmsNoList(EmsCusBsc emsCusBsc) {
        AjaxResult ajaxResult = null;
        try {
            BsspUtil.filterCopEnt(emsCusBsc, null);
            emsCusBsc.setChkStatus("P");//审批通过
            List<EmsCusBsc> emsCusBscList = emsCusBscService.selectEmsNoList(emsCusBsc);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsCusBscList, emsCusBscList.size());
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEmsNoList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 选择账册正式表一条数据生成备案变更的数据
     *
     * @param emsCusBscId 账册正式表主键
     * @param appId
     * @return
     */
    @RequestMapping(value = "/list/generateEmsPutrecBscChgData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult generateEmsPutrecBscChgData(String emsCusBscId, String appId) {
        AjaxResult ajaxResult = null;
        try {
            EmsCusBsc emsCusBsc = emsCusBscService.selectById(emsCusBscId);
            String emsNo = emsCusBsc.getEmsNo();
            if (isExistRecord(emsNo)) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_EMS_ISNOTCHANGE, emsNo);
                return ajaxResult;
            }
            EmsPutrecBsc emsPutrecBsc = emsCusBscService.getEmsPutrecBscChgData(emsCusBsc, appId);
            Boolean flag = emsPutrecBscService.insert(emsPutrecBsc);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, emsPutrecBsc);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("generateEmsPutrecBscChgData()--err", e);
        }
        return ajaxResult;
    }

    /**
     * 判断当前账册是否存在暂存或审批不通过的记录
     *
     * @param emsNo
     * @return
     */
    private Boolean isExistRecord(String emsNo) {
        Boolean flag = false;
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("EMS_NO", emsNo);
        entityWrapper.ne("CHK_STATUS", "P");
        List<EmsPutrecBsc> emsPutrecBscList = emsPutrecBscService.selectList(entityWrapper);
        if (emsPutrecBscList.size() > 0) {
            flag = true;
        }
        return flag;
    }
}
