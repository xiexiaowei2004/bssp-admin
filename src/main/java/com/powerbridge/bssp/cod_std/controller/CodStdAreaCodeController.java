package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdAreaCode;
import com.powerbridge.bssp.cod_std.service.ICodStdAreaCodeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodStdAreaCodeController
 * 类描述：场地代码控制器
 * 创建人： xwq
 * 创建时间：2017年5月10日19:52:58
 *@version
 */
@Controller
@RequestMapping("/cod_std/codStdAreaCode")
@CrossOrigin
public class CodStdAreaCodeController extends BaseController{
	 @Autowired
	 private ICodStdAreaCodeService codStdAreaCodeService;
	 
	 private Gson gson = null;
	 
	 public CodStdAreaCodeController(){
		 gson=new Gson();
	 }
	 
	  // 查询方法
	    private EntityWrapper getEntityWrapper(CodStdAreaCode codStdAreaCode) {
	        String sort = getParameter("sort");
	        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
	        EntityWrapper entityWrapper = new EntityWrapper<CodStdAreaCode>();
	        if (codStdAreaCode.getCustomsCode() != null && !"".equals(codStdAreaCode.getCustomsCode())) {
	            entityWrapper.like("CUSTOMS_CODE", codStdAreaCode.getCustomsCode());
	        }
	        if (codStdAreaCode.getAreaCode() != null && !"".equals(codStdAreaCode.getAreaCode())) {
	            entityWrapper.like("AREA_CODE", codStdAreaCode.getAreaCode());
	        }
	        if (sort != null && !"".equals(sort)) {
	            entityWrapper.orderBy(sort, sortOrder);  // 排序
	        }
	        return entityWrapper;
	    }

	    /**
	     * @param codStdAreaCode
	     * @return AjaxResult
	     * @throws
	     * @Description: 进入场地代码页面:(列表查询)
	     */
        @RequiresPermissions("codStdAreaCode:list:view")
        @RequestMapping("/list")
	    @ResponseBody
	    public AjaxResult showCodStdAreaCode(CodStdAreaCode codStdAreaCode) {
	        AjaxResult ajaxResult = null;
	        try {
	            EntityWrapper entityWrapper = getEntityWrapper(codStdAreaCode);  //查询调用方法
	            Page page = getPage();  // 分页
	            Page<CodStdAreaCode> codStdAreaCodeList = codStdAreaCodeService.selectPage(page, entityWrapper);
	    		ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdAreaCodeList.getRecords(), page.getTotal()); // 格式要返回的数据
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("showCodStdAreaCode()--error", e);
	        }
	        return ajaxResult;
	    }
	    
	    
	    /**
	     * @param codStdAreaCode
	     * @return AjaxResult
	     * @throws
	     * @Description: 新增场地代码
	     */
        @RequiresPermissions("codStdAreaCode:list:add")
        @RequestMapping(value = "/list/add")
	    @ResponseBody
	    public AjaxResult addCodStdAreaCode(@Valid CodStdAreaCode codStdAreaCode,BindingResult result) {
	        AjaxResult ajaxResult = null;
	        AjaxResult validResult = valid(result);
			if (!ObjectUtils.isEmpty(validResult)) {
				return validResult;
			}
	        try {
	        	codStdAreaCode.setUid(UUIDGenerator.getUUID());
	        	codStdAreaCode.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	        	codStdAreaCode.setCreateBy(SingletonLoginUtils.getSystemUserName());//创建人
	        	Boolean fail = codStdAreaCodeService.insert(codStdAreaCode);
	            if (fail) {
	                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdAreaCode);
	            } else {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            }
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("addCodStdAreaCode()--err", e);
	        }
	        return ajaxResult;
	    }
	    
	    
	    /**
         * @param
         * @return AjaxResult
	     * @throws
	     * @Description: 编辑页面
	     */
        @RequiresPermissions("codStdAreaCode:list:view")
        @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
        @ResponseBody
        public AjaxResult editCodStdAreaCode(@PathVariable String id) {
            AjaxResult ajaxResult = null;
	        try {
                CodStdAreaCode codStdAreaCode = codStdAreaCodeService.selectById(id);
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdAreaCode);
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("editCodStdAreaCode()--err", e);
	        }
	        return ajaxResult;
	    }
	    
	    
	    /**
	     * @param codStdAreaCode
	     * @return AjaxResult
	     * @throws
	     * @Description: 修改数据
	     */
        @RequiresPermissions("codStdAreaCode:list:edit")
        @RequestMapping("/list/update")
	    @ResponseBody
	    public AjaxResult updateCodStdAreaCode(CodStdAreaCode codStdAreaCode) {
	        AjaxResult ajaxResult = null;
	        try {
	        	codStdAreaCode.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	            codStdAreaCode.setUpdateBy(SingletonLoginUtils.getSystemUserName());//修改人
	            String str =codStdAreaCode.getCustomsCode();
	            codStdAreaCode.setCustomsCode(str.substring(str.length()-4, str.length()));
	            Boolean fail = codStdAreaCodeService.updateById(codStdAreaCode);
	            if (fail) {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
	            } else {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            }
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("updateCodStdAreaCode()--err", e);
	        }
	        return ajaxResult;
	    }
	    
	    
	    /**
	     * @param codStdAreaCode
	     * @return AjaxResult
	     * @throws
	     * @Description: 删除单条数据
	     */
        @RequiresPermissions("CodStdAreaCode:list:delete")
        @RequestMapping("/list/delete")
	    @ResponseBody
	    public AjaxResult deleteCodStdAreaCode() {
	        AjaxResult ajaxResult = null;
	        try {
	            Boolean fail = codStdAreaCodeService.deleteById(getParameter("id"));
	            if (fail) {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
	            } else {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            }
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("deleteCodStdAreaCode()--err", e);
	        }
	        return ajaxResult;
	    }
	    
		/**
		 * @Description：批量删除
		 * @param model
		 * @return
		 * @throws
		 */
        @RequiresPermissions("codStdAreaCode:list:delete")
        @RequestMapping("/list/deleteByList")
		@ResponseBody
		public AjaxResult deleteCodStdAreaCodeByList() {

			AjaxResult ajaxResult = null;
			try {
				// 获取前台传输的主键集合
				String idList = getParameter("idList");
				Boolean fail = codStdAreaCodeService.deleteBatchIds(Arrays.asList(idList.split(",")));
				if (fail) {
					ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
				} else {
					ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				}
			} catch (Exception e) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				logger.error("deleteCodStdAreaCodeByList()--err", e);
			}

			return ajaxResult;
		}
		
		/**
		 * @Title: getDataSource 用于和关区代码的二级联动
		 * @Description: TODO 
		 * @param: @param str
		 * @param: @return 
		 * @return: AjaxResult
		 */
		@RequestMapping(value = "/getDataSource",method = RequestMethod.GET)
	    @ResponseBody
	    public AjaxResult getDataSource(String str) {//str 为关区代码
	        AjaxResult ajaxResult = null;
	        try {
	            List<CodStdAreaCode> codStdAreaCode = codStdAreaCodeService.selectCarSortByList(str); //企业下拉选择数据源
	            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdAreaCode);
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("getDataSource()--err", e);
	        }
	        return ajaxResult;
	    }
}
  
    