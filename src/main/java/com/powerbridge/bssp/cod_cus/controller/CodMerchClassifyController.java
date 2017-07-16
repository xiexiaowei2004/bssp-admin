package com.powerbridge.bssp.cod_cus.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodMerchClassify;
import com.powerbridge.bssp.cod_cus.entity.CodMerchElement;
import com.powerbridge.bssp.cod_cus.service.ICodMerchClassifyService;
import com.powerbridge.bssp.cod_cus.service.ICodMerchElementService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称：CodMerchClassifyController
 * 类描述：规范申报目录控制器
 * 创建人： xwq
 * 创建时间：2017年5月10日19:52:58
 *@version
 */
@Controller
@RequestMapping("/cod_cus/codMerchClassify")
@CrossOrigin
public class CodMerchClassifyController extends BaseController{
	 @Autowired
	 private ICodMerchClassifyService codMerchClassifyService;
	 
	 @Autowired//查询对应的子表
	 private ICodMerchElementService codMerchElementService;
	 
	 private Gson gson = null;
	 
	 public CodMerchClassifyController(){
		 gson=new Gson();
	 }
	 
	  // 查询方法
	    private EntityWrapper getEntityWrapper(CodMerchClassify codMerchClassify) {
	        String sort = getParameter("sort");
	        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
	        EntityWrapper entityWrapper = new EntityWrapper<CodMerchClassify>();
	        if (StringUtil.isNotEmpty(codMerchClassify.getCodeTs())) {
	            entityWrapper.like("CODE_TS", codMerchClassify.getCodeTs(), SqlLike.RIGHT);//左匹配模糊查询
	        }
	      
	        if (sort != null && !"".equals(sort)) {
	            entityWrapper.orderBy(sort, sortOrder);  // 排序
	        }
	        return entityWrapper;
	    }

	    /**
	     * @param codMerchClassify
	     * @return AjaxResult
	     * @throws
	     * @Description: 进入规范申报目录页面:(列表查询)
	     */
//	    @RequiresPermissions("codMerchClassify:list:view")
	    @RequestMapping("/list")
	    @ResponseBody
	    public AjaxResult showCodMerchClassify(CodMerchClassify codMerchClassify) {
	        AjaxResult ajaxResult = null;
	        try {
	            EntityWrapper entityWrapper = getEntityWrapper(codMerchClassify);  //查询调用方法
	            Page page = getPage();  // 分页
	            Page<CodMerchClassify> codMerchClassifyList = codMerchClassifyService.selectPage(page, entityWrapper);
	    		ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codMerchClassifyList.getRecords(), page.getTotal()); // 格式要返回的数据
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("showCodMerchClassifyList()--error", e);
	        }
	        return ajaxResult;
	    }
	    
	    
	    /**
	     * @param
	     * @return AjaxResult
	     * @throws
	     * @Description: 查询子表
	     */
//	    @RequiresPermissions("codMerchClassify:list:view")
	    @RequestMapping(value = "/list/view", method = {RequestMethod.POST, RequestMethod.GET})
	    @ResponseBody
	    public AjaxResult view(String codeTs) {
	        AjaxResult ajaxResult = null;
	        try {
	        	EntityWrapper entityWrapper = new EntityWrapper();
	        	entityWrapper.eq("CODE_TS",StringUtil.sub(codeTs,0,4));
	        	entityWrapper.or();
				entityWrapper.eq("CODE_TS",StringUtil.sub(codeTs,0,5));
				entityWrapper.or();
				entityWrapper.eq("CODE_TS",StringUtil.sub(codeTs,0,6));
				entityWrapper.or();
				entityWrapper.eq("CODE_TS",StringUtil.sub(codeTs,0,7));
				entityWrapper.or();
				entityWrapper.eq("CODE_TS",StringUtil.sub(codeTs,0,8));
	        	List<CodMerchElement> list = codMerchElementService.selectList(entityWrapper);
	            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, list);
	            
	         } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            logger.error("view()--err", e);
	        }
	        return ajaxResult;
	    }
	    
	    

}
  
    