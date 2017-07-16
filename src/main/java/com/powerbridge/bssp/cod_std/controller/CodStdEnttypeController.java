package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdEnttype;
import com.powerbridge.bssp.cod_std.service.ICodStdEnttypeService;
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
 * 类名称：CodStdEnttypeController
 * 类描述：企业类型接口类
 * 创建人：willChen
 * 创建时间：2017/5/10 21:18
 * 修改人：willChen
 * 修改时间：2017/5/10 21:18
 */
@Controller
@RequestMapping("/cod_std/codStdEnttype")
@CrossOrigin
public class CodStdEnttypeController extends BaseController {
    //获取当前时间
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @Autowired
    private ICodStdEnttypeService codStdEnttypeService;

    /**
     * 新增数据
     */
    @RequiresPermissions("codStdEnttype:list:add")
    @RequestMapping(value = "/list/add",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodStdEnttype(HttpServletRequest request, HttpServletResponse response, CodStdEnttype codStdEnttype){
        if(null==codStdEnttype.getCode() && null==codStdEnttype.getName()){
            return json(MessageConstants.BSSP_STATUS_FAIL,"数据缺失");
        }
        //使用生成器生成id
        String uId = UUIDGenerator.getUUID();
        if("".equals(uId) && null==uId){
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL,"id生成失败");
        }
        codStdEnttype.setUid(uId);
        codStdEnttype.setIsEnable("Y");
        codStdEnttype.setCreateTime(timestamp);
        codStdEnttype.setCreateBy(SingletonLoginUtils.getSystemUserName());
        codStdEnttype.setUpdateTime(timestamp);
        codStdEnttype.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        if(codStdEnttypeService.insert(codStdEnttype)){
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
    @RequiresPermissions("codStdEnttype:list:delete")
    @RequestMapping(value = "/list/{id}/delete",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteById(HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        CodStdEnttype codStdEnttype = new CodStdEnttype();
        codStdEnttype.setUid(id);
        codStdEnttype.setIsEnable("N");
        codStdEnttype.setUpdateTime(timestamp);
        codStdEnttype.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        //逻辑删除，更改启用状态
        if(codStdEnttypeService.updateById(codStdEnttype)){
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id批量删除
     */
    @RequiresPermissions("codStdEnttype:list:delete")
    @RequestMapping(value = "/list/delete/byIds",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteByExample(HttpServletRequest request, HttpServletResponse response, String idList){
        String[] ids = idList.split(",");
        EntityWrapper entityWrapper = new EntityWrapper<CodStdEnttype>();
        entityWrapper.in("UID",ids);
        CodStdEnttype codStdEnttype = new CodStdEnttype();
        codStdEnttype.setIsEnable("N");
        codStdEnttype.setUpdateTime(timestamp);
        codStdEnttype.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        if(codStdEnttypeService.update(codStdEnttype,entityWrapper)){
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id修改
     */
    @RequiresPermissions("codStdEnttype:list:edit")
    @RequestMapping(value = "/list/update",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodStdEnttype(HttpServletRequest request, HttpServletResponse response, CodStdEnttype codStdEnttype){
        codStdEnttype.setUpdateTime(timestamp);
        codStdEnttype.setUpdateBy(SingletonLoginUtils.getSystemUserName());
        if (null == codStdEnttype.getUid() && "".equals(codStdEnttype.getUid())) {
            return result(MessageConstants.BSSP_STATUS_CODE_NOTEXIST);
        }
        if(codStdEnttypeService.updateById(codStdEnttype)){
            //成功
            return result(MessageConstants.BSSP_STATUS_SUCCESS);
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("codStdEnttype:list:view")
    @RequestMapping(value = "/list/{id}/view",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectById(HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        CodStdEnttype codStdEnttype = codStdEnttypeService.selectById(id);
        return json(MessageConstants.BSSP_STATUS_SUCCESS,codStdEnttype);
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codStdEnttype:list:view")
    @RequestMapping(value = "/list",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectByExample(HttpServletRequest request, HttpServletResponse response, CodStdEnttype codStdEnttype, Integer pageSize, Integer pageNumber, String sort, String sortOrder){
        EntityWrapper entityWrapper = new EntityWrapper<CodStdEnttype>();
        if(!(null == codStdEnttype.getCode() || "".equals(codStdEnttype.getCode().trim()))){
            entityWrapper.like("CODE",codStdEnttype.getCode());
        }
        if(!(null == codStdEnttype.getName() || "".equals(codStdEnttype.getName().trim()))){
            entityWrapper.like("NAME",codStdEnttype.getName());
        }
        if(!(null == codStdEnttype.getIsEnable() || "".equals(codStdEnttype.getIsEnable().trim()))){
            entityWrapper.like("IS_ENABLE",codStdEnttype.getIsEnable());
        }
        if(!(null == codStdEnttype.getCreateBy() || "".equals(codStdEnttype.getCreateBy().trim()))){
            entityWrapper.like("CREATE_BY",codStdEnttype.getCreateBy());
        }
        if(!(null == codStdEnttype.getUpdateBy() || "".equals(codStdEnttype.getUpdateBy().trim()))){
            entityWrapper.like("UPDATE_BY",codStdEnttype.getUpdateBy());
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
        Page<CodStdEnttype> codStdEnttypes = codStdEnttypeService.selectPage(page,entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS,codStdEnttypes.getRecords(),page.getTotal());
    }
}
