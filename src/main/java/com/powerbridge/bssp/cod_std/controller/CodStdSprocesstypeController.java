package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdSprocesstype;
import com.powerbridge.bssp.cod_std.service.ICodStdSprocesstypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * 项目名称：bssp-admin
 * 类名称：CodStdSprocesstypeController
 * 类描述：加工方式接口类
 * 创建人：willChen
 * 创建时间：2017/5/10 21:18
 * 修改人：willChen
 * 修改时间：2017/5/10 21:18
 */
@Controller
@RequestMapping("/cod_std/codStdSprocesstype")
@CrossOrigin
public class CodStdSprocesstypeController extends BaseController {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @Autowired
    private ICodStdSprocesstypeService codStdSprocesstypeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codStdSprocesstype:list:add")
    @RequestMapping(value = "/list/add",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodStdSprocesstype(HttpServletRequest request, HttpServletResponse response, CodStdSprocesstype codStdSprocesstype){
        if(null==codStdSprocesstype.getCode() && null==codStdSprocesstype.getName()){
            return json(MessageConstants.BSSP_STATUS_FAIL,"数据缺失");
        }
        //使用生成器生成id
        String uId = UUIDGenerator.getUUID();
        if("".equals(uId) && null==uId){
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL,"id生成失败");
        }
        codStdSprocesstype.setUid(uId);
        codStdSprocesstype.setIsEnable("Y");
        codStdSprocesstype.setCreateTime(timestamp);
        codStdSprocesstype.setCreateBy(SingletonLoginUtils.getSystemUserName());
        codStdSprocesstype.setUpdateTime(timestamp);
        codStdSprocesstype.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        if(codStdSprocesstypeService.insert(codStdSprocesstype)){
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
    @RequiresPermissions("codStdSprocesstype:list:delete")
    @RequestMapping(value = "/list/{id}/delete",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        CodStdSprocesstype codStdSprocesstype = new CodStdSprocesstype();
        codStdSprocesstype.setUid(id);
        codStdSprocesstype.setIsEnable("Y");
        codStdSprocesstype.setUpdateTime(timestamp);
        codStdSprocesstype.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        //逻辑删除，更改启用状态
        if(codStdSprocesstypeService.updateById(codStdSprocesstype)){
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id批量删除
     */
    @RequiresPermissions("codStdSprocesstype:list:delete")
    @RequestMapping(value = "/list/delete/byIds",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(HttpServletRequest request, HttpServletResponse response, String idList){
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodStdSprocesstype>();
        entityWrapper.in("UID",ids);
        CodStdSprocesstype codStdSprocesstype = new CodStdSprocesstype();
        codStdSprocesstype.setIsEnable("N");
        codStdSprocesstype.setUpdateTime(timestamp);
        codStdSprocesstype.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        if(codStdSprocesstypeService.update(codStdSprocesstype,entityWrapper)){
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codStdSprocesstype:list:edit")
    @RequestMapping(value = "/list/update",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodStdSprocesstype(HttpServletRequest request, HttpServletResponse response, CodStdSprocesstype codStdSprocesstype){
        codStdSprocesstype.setUpdateTime(timestamp);
        codStdSprocesstype.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        if (null == codStdSprocesstype.getUid() && "".equals(codStdSprocesstype.getUid())) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if(codStdSprocesstypeService.updateById(codStdSprocesstype)){
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codStdSprocesstype:list:view")
    @RequestMapping(value = "/list/{id}/view",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        CodStdSprocesstype codStdSprocesstype = codStdSprocesstypeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS,codStdSprocesstype);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codStdSprocesstype:list:view")
    @RequestMapping(value = "/list",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(HttpServletRequest request, HttpServletResponse response, CodStdSprocesstype codStdSprocesstype, Integer pageSize, Integer pageNumber, String sort, String sortOrder){
        EntityWrapper entityWrapper = new EntityWrapper();
        if(!(null == codStdSprocesstype.getCode() || "".equals(codStdSprocesstype.getCode().trim()))){
            entityWrapper.like("CODE",codStdSprocesstype.getCode());
        }
        if(!(null == codStdSprocesstype.getName() || "".equals(codStdSprocesstype.getName().trim()))){
            entityWrapper.like("NAME",codStdSprocesstype.getName());
        }
        if(!(null == codStdSprocesstype.getIsEnable() || "".equals(codStdSprocesstype.getIsEnable().trim()))){
            entityWrapper.like("IS_ENABLE",codStdSprocesstype.getIsEnable());
        }
        if(!(null == codStdSprocesstype.getCreateBy() || "".equals(codStdSprocesstype.getCreateBy().trim()))){
            entityWrapper.like("CREATE_BY",codStdSprocesstype.getCreateBy());
        }
        if(!(null == codStdSprocesstype.getUpdateBy() || "".equals(codStdSprocesstype.getUpdateBy().trim()))){
            entityWrapper.like("UPDATE_BY",codStdSprocesstype.getUpdateBy());
        }
        if (! StringUtil.isEmpty(sort)) {
            // 排序
            entityWrapper.orderBy(sort, super.getOrderSort(sortOrder));
        }
        Page page = null;
        if(pageSize>=1){
            page = new Page(pageNumber/pageSize + 1,pageSize);
        }else{
            page = new Page();
        }
        Page<CodStdSprocesstype> codStdSprocesstypes = codStdSprocesstypeService.selectPage(page,entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS,codStdSprocesstypes.getRecords(),page.getTotal());
    }
}
