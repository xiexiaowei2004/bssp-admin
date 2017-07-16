package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdTransportType;
import com.powerbridge.bssp.cod_std.service.ICodStdTransportTypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;

/**
 * 项目名称：bssp-admin
 * 类名称：CodStdTransportTypeController
 * 类描述：车辆运输类型接口类
 * 创建人：zsl
 * 创建时间：2017/5/12 10:00
 */
@Controller
@RequestMapping("/cod_std/codStdTransportType")
@CrossOrigin
public class CodStdTransportTypeController extends BaseController {
    @Autowired
    private ICodStdTransportTypeService codStdTransportTypeService;
    
    // 查询方法
    private EntityWrapper getEntityWrapper(CodStdTransportType codStdTransportType) {
        //String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        EntityWrapper entityWrapper = new EntityWrapper<CodStdTransportType>();
        if (codStdTransportType.getCode() != null && !"".equals(codStdTransportType.getCode())) {
        	entityWrapper.like("CODE", codStdTransportType.getCode());
        }
        if (codStdTransportType.getName() != null && !"".equals(codStdTransportType.getName())) {
            entityWrapper.like("NAME", codStdTransportType.getName());
        }
       /* if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }*/
        entityWrapper.orderBy("CUSTOMS_CODE,AREA_CODE,CODE" ,sortOrder);
       /* entityWrapper.orderBy("AREA_CODE", sortOrder);
        entityWrapper.orderBy("CODE", sortOrder);*/
        return entityWrapper;
    }

    /**
     * 新增数据
     */
    @RequiresPermissions("codStdTransportType:list:add")
    @RequestMapping(value = "/list/add",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addcodStdTransportType(HttpServletRequest request, HttpServletResponse response, CodStdTransportType codStdTransportType){
        //使用生成器生成id
        String uid = UUIDGenerator.getUUID();
        if("".equals(uid) && null==uid){
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL,"id生成失败");
        }
        codStdTransportType.setUid(uid);
        
        if(codStdTransportTypeService.insert(codStdTransportType)){
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }else {
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL,"数据添加失败");
        }
    }

    /**
     * 根据id删除
     */
    @RequiresPermissions("codStdTransportType:list:delete")
    @RequestMapping(value = "/list/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult deleteCodStdTransportTypeById(HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        if(codStdTransportTypeService.deleteById(id)){
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 批量删除
     */
    @RequiresPermissions("codStdTransportType:list:delete")
    @RequestMapping(value = "/list/delete")
    @ResponseBody
    public AjaxResult deleteCodStdTransportTypeList(HttpServletRequest request, HttpServletResponse response, CodStdTransportType codStdTransportType){
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codStdTransportTypeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusBriefByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codStdTransportType:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodStdTransportType(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodStdTransportType codStdTransportType = codStdTransportTypeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdTransportType);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusBrief()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param codStdTransportType
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codStdTransportType:list:edit")
    @RequestMapping("/list/update")
    @ResponseBody
    public AjaxResult updateCodStdTransportType(CodStdTransportType codStdTransportType) {
        AjaxResult ajaxResult = null;
        try {
        	codStdTransportType.setUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        	codStdTransportType.setUpdateBy(SingletonLoginUtils.getSystemUserName());
            Boolean flag = codStdTransportTypeService.updateById(codStdTransportType);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusBrief()--err", e);
        }
        return ajaxResult;
    }


    /**
     * 根据条件查询
     */
    @RequiresPermissions("codStdTransportType:list:view")
    @RequestMapping(value = "/list")
    @ResponseBody
    public AjaxResult showCodStdTransportTypeList(HttpServletRequest request, HttpServletResponse response, CodStdTransportType codStdTransportType, Integer pageSize, Integer pageNumber){
    	EntityWrapper entityWrapper = getEntityWrapper(codStdTransportType);
    	if(StringUtils.isNotEmpty(codStdTransportType.getCustomsCode())){
        	entityWrapper.eq("CUSTOMS_CODE",codStdTransportType.getCustomsCode());
        }
        
        if(StringUtils.isNotEmpty(codStdTransportType.getAreaCode())){
        	entityWrapper.eq("AREA_CODE",codStdTransportType.getAreaCode());
        }
        Page page = new Page(pageNumber/pageSize + 1,pageSize);
        Page<CodStdTransportType> codStdTransportTypes = codStdTransportTypeService.selectPage(page,entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS,codStdTransportTypes.getRecords(),page.getTotal());
    }
}
