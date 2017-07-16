package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdCarSort;
import com.powerbridge.bssp.cod_std.service.ICodStdCarSortService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodStdCarSortController
 * 类描述：车辆分类控制器
 * 创建人：haihuihuang
 * 创建时间：2017年4月29日 下午10:12:17
 */
@Controller
@RequestMapping("/cod_std/codStdCarSort")
@CrossOrigin
public class CodStdCarSortController extends BaseController {
    @Autowired
    private ICodStdCarSortService codStdCarSortService;

    // 查询方法
    private EntityWrapper getEntityWrapper(CodStdCarSort codStdCarSort) {
        String sort = getParameter("sort");//排序列名
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));//排序规则
        EntityWrapper entityWrapper = new EntityWrapper<CodStdCarSort>();
        if (codStdCarSort.getUid() != null && !"".equals(codStdCarSort.getUid())) {
            entityWrapper.like("UID", codStdCarSort.getUid());
        }
        if (codStdCarSort.getCode() != null && !"".equals(codStdCarSort.getCode())) {
            entityWrapper.like("CODE", codStdCarSort.getCode());
        }
        if (codStdCarSort.getName() != null && !"".equals(codStdCarSort.getName())) {
            entityWrapper.like("NAME", codStdCarSort.getName());
        }
        if (codStdCarSort.getEnable() != null && !"".equals(codStdCarSort.getEnable())) {
            entityWrapper.like("IS_ENABLE", codStdCarSort.getEnable());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }

    /**
     * @param codStdCarSort
     * @return AjaxResult
     * @throws
     * @Description: 进入业务分类页面:(列表查询)
     */
    @RequiresPermissions("codStdCarSort:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodStdCarSoriList(CodStdCarSort codStdCarSort) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codStdCarSort);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodStdCarSort> codStdCarSortList = codStdCarSortService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdCarSortList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodStdCarSortList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param codStdCarSort
     * @return AjaxResult
     * @throws
     * @Description: 新增业务分类
     */
    @RequiresPermissions("codStdCarSort:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodStdCarSort(CodStdCarSort codStdCarSort) {
        //使用生成器生成id
        String uId = UUIDGenerator.getUUID();
        if ("".equals(uId) && null == uId) {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
        }
        codStdCarSort.setUid(uId);
        String user = SingletonLoginUtils.getSystemUserName();//收取用户名字
        codStdCarSort.setCreateBy(user);
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codStdCarSortService.insert(codStdCarSort);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdCarSort);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodStdCarSort()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codStdCarSort:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodStdCarSori(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodStdCarSort codStdCarSort = codStdCarSortService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdCarSort);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodStdCarSori()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param codStdCarSort
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codStdCarSort:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodStdCarSori(CodStdCarSort codStdCarSort) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codStdCarSortService.updateById(codStdCarSort);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodStdCarSort()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("codStdCarSort:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdCarSort(@PathVariable String id) {
        CodStdCarSort codStdCarSort = new CodStdCarSort();
        codStdCarSort.setEnable("N");
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codStdCarSortService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdCarSort()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequiresPermissions("codStdCarSort:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdCarSortByList() {
        CodStdCarSort codStdCarSort = new CodStdCarSort();
        codStdCarSort.setEnable("N");
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codStdCarSortService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdCarSortByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 企业数据源
     */
   /* @RequestMapping(value = "/getDataSource",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getDataSource() {
        AjaxResult ajaxResult = null;
        try {
            List<CodStdCarSort> CodStdCarSort = codStdCarSortService.selectCarSortByList(); //企业下拉选择数据源
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, CodStdCarSort);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("getDataSource()--err", e);
        }
        return ajaxResult;
    }*/
}

