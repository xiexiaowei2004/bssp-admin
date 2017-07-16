package com.powerbridge.bssp.cop.controller;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.common.constants.DocTypeConstants;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.BsspUtil;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop.entity.CopBusiness;
import com.powerbridge.bssp.cop.entity.CopEnt;
import com.powerbridge.bssp.cop.service.ICopBusinessService;
import com.powerbridge.bssp.cop.service.ICopEntService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：CopEntController
 * 类描述：企业备案控制器
 * 创建人：haihuihuang
 * 创建时间：2017年5月9日  上午09:59:00
 */
@Controller
@RequestMapping("/cop")
@CrossOrigin
public class CopEntController extends BaseController {
    @Autowired
    private ICopEntService copEntService;

    @Autowired
    private ICopBusinessService copBusinessService;

    /**
     * @param copEnt
     * @return AjaxResult
     * @throws
     * @Description: 进入企业备案管理页面:(列表查询)
     */
    @RequiresPermissions("copEnt:list:view")
    @RequestMapping(value = "/copEnt/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCopEntList(CopEnt copEnt) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页

            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                page.setAsc(true);
                page.setOrderByField("updateTime"); //默认按最后变更日期倒排序
            }

            BsspUtil.filterCopEnt(copEnt,"tradeCode");

            Page<CopEnt> copEntList = copEntService.selectByCopEnt(page, copEnt);

            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, copEntList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCopEntList()--error", e);
        }
        return ajaxResult;
    }

    /**     * @param copEnt
     * @return AjaxResult
     * @throws
     * @Description: 验证
     */
    @RequiresPermissions("copEnt:list:edit")
    @RequestMapping(value = "/validate", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult validateCopEnt(CopEnt copEnt) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = new EntityWrapper<CopEnt>();
            if (StringUtil.isEmpty(copEnt.getCopGbCode())) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            } else if (StringUtil.isEmpty(copEnt.getCustomsCode())) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            } else if (StringUtil.isEmpty(copEnt.getAreaCode())) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            } else {
                entityWrapper.eq("COP_GB_CODE", copEnt.getCopGbCode());
                entityWrapper.eq("CUSTOMS_CODE", copEnt.getCustomsCode());
                entityWrapper.eq("AREA_CODE", copEnt.getAreaCode());
                List<CopEnt> copEntList = copEntService.selectList(entityWrapper);
                if (copEntList.size() != 0) {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_COPENT_SOLE);
                } else {
                    ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
                }
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("validateCopEnt()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param copEnt
     * @return AjaxResult
     * @throws
     * @Description: 新增企业备案
     */
    @RequiresPermissions("copEnt:list:add")
    @RequestMapping(value = "/copEnt/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCopEnt(@RequestBody @Valid CopEnt copEnt, BindingResult result) {
        AjaxResult ajaxResult = null;
        AjaxResult validResult = valid(result);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
        try {
            copEnt.setUid(UUIDGenerator.getUUID());
            //设定单据类别和
            DocTypeConstants.setDocType(copEnt,null);
       /*     List<CopBusiness> copBusinessByList = copEnt.getCopBusiness();

            for (CopBusiness copBusiness : copBusinessByList) {
                copBusiness.setUid(UUIDGenerator.getUUID());
            }*/

            Boolean flag = copEntService.txInsertCopEnt(copEnt, null);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, copEnt);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setJson(0,e.getMessage());
            logger.error("addCopEnt()--err", e);
        }
        return ajaxResult;
    }


    /**
     * @param: copent
    * 检查企业海关代码重复
    * */

    /**
     * @return AjaxResult
     * @throws
     * @Description: 新增企业备案
     */
    @RequiresPermissions("copEnt:list:add")
    @RequestMapping(value = "/copEnt/list/checkRepeat", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map checkRepeat() throws Exception {
        Map ajaxResult = null;
        try {
            EntityWrapper<CopEnt> entityWrapper = new EntityWrapper<CopEnt>();
            entityWrapper.eq("TRADE_CODE", SingletonLoginUtils.getSystemUser().getInputCopNo());
            //获取匹配的企业数
            Integer count = copEntService.selectCount(entityWrapper);
            ajaxResult = setJson(MessageConstants.BSSP_STATUS_SUCCESS, count);
        } catch (Exception e) {
            ajaxResult = setJson(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("copEnt:list:view")
    @RequestMapping(value = "/copEnt/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCopEnt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CopEnt copEnt = copEntService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, copEnt);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCopEnt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param copEnt
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("copEnt:list:edit")
    @RequestMapping(value = "/copEnt/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCopEnt(@Valid CopEnt copEnt, BindingResult result) {
        AjaxResult ajaxResult = null;
        AjaxResult validResult = valid(result);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
        try {
            Boolean flag = copEntService.updateById(copEnt);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("updateCopEnt()--err", e);
        }
        return ajaxResult;
    }
    	

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("copEnt:list:delete")
    @RequestMapping(value = "/copEnt/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCopEnt(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            boolean flag = copEntService.txDeleteCopEnt(id); // 删除主表，同时删除子表数据 传过来的值实为seqNo的值
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCopEnt()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param idList 主键集合
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequiresPermissions("copEnt:list:delete")
    @RequestMapping(value = "/copEnt/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCopEntByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = copEntService.txDeleteCopEntByList(Arrays.asList(idList.split(","))); // 删除主表，同时删除子表数据 传过来的值实为seqNo的值
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCopEntByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param tradeCode
     * @Desciption：根据企业备案中的单位代码信息查找企业备案
     */
    @RequestMapping(value = "/copEnt/list/getCopEnt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getCopEnt(String tradeCode) {
        AjaxResult ajaxResult = null;
        try {
           EntityWrapper<CopEnt> entEntityWrapper = new EntityWrapper<>();
           entEntityWrapper.eq("TRADE_CODE",tradeCode);
           CopEnt copEnt = copEntService.selectOne(entEntityWrapper);
            if (copEnt != null) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS,copEnt);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getCopEnt()--err", e);
        }
        return ajaxResult;
    }

    //---------------------------------------------------------------------分界线 备案在上 经营在下----------------------------------------------------------------------------------


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 经营资料列表页面:(列表查询)
     */
//    @RequiresPermissions("copBuiness:list:view")
    @RequestMapping(value = "/copBusiness/list", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult showCopBusinessList() {
        AjaxResult ajaxResult = null;
        try {
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            String seqNo = getParameter("seqNo");
            EntityWrapper entityWrapper = new EntityWrapper<CopBusiness>();
            if (StringUtil.isNotEmpty(seqNo)) {
                entityWrapper.eq("SEQ_NO", seqNo);
            }
            if (StringUtil.isNotEmpty(sort)) {
                entityWrapper.orderBy(sort, sortOrder);  // 排序
            } else {
                entityWrapper.orderBy("UPDATE_TIME", false);
            }
            Page page = getPage();  // 分页
            Page<CopBusiness> copBuinessList = copBusinessService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, copBuinessList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCopBusinessList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param copBusiness
     * @return AjaxResult
     * @throws
     * @Description: 新增经营资料
     */
    @RequiresPermissions("copBusiness:list:add")
    @RequestMapping(value = "/copBusiness/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCopBusiness(@Valid CopBusiness copBusiness, BindingResult result) {
        AjaxResult ajaxResult = null;
        AjaxResult validResult = valid(result);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
        try {
            copBusiness.setUid(UUIDGenerator.getUUID());
            Boolean fail = copBusinessService.insert(copBusiness);
            if (fail) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, copBusiness);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCopBusiness()--err", e);
        }
        return ajaxResult;
    }

//    /**
//     * @param copBusinessByList
//     * @return AjaxResult
//     * @throws
//     * @Description: 批量新增经营资料
//     */
//    @RequiresPermissions("copBusiness:list:add")
//    @RequestMapping(value = "/copBusiness/list/addByList", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public AjaxResult addCopBusinessByList(@RequestBody List<CopBusiness> copBusinessByList) {
//        AjaxResult ajaxResult = null;
//        try {
//            for (CopBusiness copBusiness : copBusinessByList) {
//                copBusiness.setUid(UUIDGenerator.getUUID());
//                copBusiness.setCreateBy(String.valueOf(SingletonLoginUtils.getSystemUserId()));
//                copBusiness.setCreateName(SingletonLoginUtils.getSystemUserName());
//                copBusiness.setCreateTime(DateUtil.now());
//                copBusiness.setUpdateBy(String.valueOf(SingletonLoginUtils.getSystemUserId()));
//                copBusiness.setUpdateName(SingletonLoginUtils.getSystemUserName());
//                copBusiness.setUpdateTime(DateUtil.now());
//            }
//            Boolean fail = copBusinessService.insertBatch(copBusinessByList);
//            if (fail) {
//                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
//            } else {
//                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
//            }
//        } catch (Exception e) {
//            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
//            logger.error("addCopBusiness()--err", e);
//        }
//        return ajaxResult;
//    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面(经营资料)
     */
    @RequiresPermissions("copBusiness:list:view")
    @RequestMapping(value = "/copBusiness/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCopBusiness(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CopBusiness copBusiness = copBusinessService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, copBusiness);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCopBusiness()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param copBusiness
     * @return AjaxResult
     * @throws
     * @Description: 修改数据(经营资料)
     */
    @RequiresPermissions("copBusiness:list:edit")
    @RequestMapping(value = "/copBusiness/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCopBusiness(@Valid CopBusiness copBusiness, BindingResult result) {
        AjaxResult ajaxResult = null;
        AjaxResult validResult = valid(result);
		if (!ObjectUtils.isEmpty(validResult)) {
			return validResult;
		}
        try {
            Boolean fail = copBusinessService.updateById(copBusiness);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCopBusiness()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据(经营资料)
     */
    @RequiresPermissions("copBusiness:list:delete")
    @RequestMapping(value = "/copBusiness/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCopBusiness(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            Boolean fail = copBusinessService.deleteById(id);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCopBusiness()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作(经营资料)
     */
    @RequiresPermissions("copBusiness:list:delete")
    @RequestMapping(value = "/copBusiness/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCopBusinessByList() {
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean fail = copBusinessService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCopBusinessByList()--err", e);
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/copEnt/list/getCopEntInfo",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getCopEntInfo(String tradeCode){
        AjaxResult ajaxResult = null;
        if (StringUtil.isNotEmpty(tradeCode)){
            try {
                EntityWrapper<CopEnt> wrapper = new EntityWrapper<CopEnt>();
                wrapper.in("TRADE_CODE",tradeCode);
                List<CopEnt> list = copEntService.selectList(wrapper);
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, list);
            } catch (Exception e) {
                ajaxResult = setErrorJson(e.getMessage());
                logger.error("getCopEntInfo()--err", e);
            }
        }
        /*try {
            List<CopEnt> list = copEntService.selectByTradeCode(tradeCode);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, list);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getCopEntInfo()--err", e);
        }*/
        return ajaxResult;
    }
}

