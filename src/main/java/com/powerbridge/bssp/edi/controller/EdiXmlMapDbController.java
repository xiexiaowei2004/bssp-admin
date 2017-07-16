package com.powerbridge.bssp.edi.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdAreaCode;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.service.IEdiXmlMapDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


/**
 * 项目名称： bssp Maven Webapp
 * 类名称： EdiXmlMapDbController
 * 类描述：字段关系控制器
 * 创建人： xwq
 * 创建时间：2017年5月16日10:26:27
 *@version
 */
@Controller
@RequestMapping("/edi/ediXmlMapDb")
@CrossOrigin
public class EdiXmlMapDbController extends BaseController {
    private static ValidatorFactory factory;
	private static Validator validator;
	
	 @Autowired
     private IEdiXmlMapDbService ediXmlMapDbService;

    private Gson gson = null;

    public EdiXmlMapDbController() {
        gson=new Gson();
	 }
	 	 
	  // 查询方法
      private EntityWrapper getEntityWrapper(EdiXmlMapDb ediXmlMapDb) {
          String sort = getParameter("sort");
	        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
	        EntityWrapper entityWrapper = new EntityWrapper<CodStdAreaCode>();
          if (ediXmlMapDb.getAreaCode() != null && !"".equals(ediXmlMapDb.getAreaCode())) {
              entityWrapper.like("AREA_CODE", ediXmlMapDb.getAreaCode());
          }
          if (ediXmlMapDb.getDocType() != null && !"".equals(ediXmlMapDb.getDocType())) {
              entityWrapper.like("DOC_TYPE", ediXmlMapDb.getDocType());
          }
          if (ediXmlMapDb.getBizType() != null && !"".equals(ediXmlMapDb.getBizType())) {
              entityWrapper.like("BIZ_TYPE", ediXmlMapDb.getBizType());
          }
          if (ediXmlMapDb.getDbTable() != null && !"".equals(ediXmlMapDb.getDbTable())) {
              entityWrapper.like("DB_TABLE", ediXmlMapDb.getDbTable());
          }
          if (ediXmlMapDb.getDbColumn() != null && !"".equals(ediXmlMapDb.getDbColumn())) {
              entityWrapper.like("DB_COLUMN", ediXmlMapDb.getDbColumn());
          }
          if (ediXmlMapDb.getStatus() != null && !"".equals(ediXmlMapDb.getStatus())) {
              entityWrapper.like("STATUS", ediXmlMapDb.getStatus());
          }
		  if (sort != null && !"".equals(sort)) {
			entityWrapper.orderBy(sort, sortOrder);  // 排序
		  }
		  else{
			  entityWrapper.orderBy("DOC_TYPE,ORDER_NO",true);  // 单据类型 排序号
		  }
	        return entityWrapper;
	    }
	    
	  

	    /**
         * @param ediXmlMapDb
         * @return AjaxResult
	     * @throws
	     * @Description: 进入字段关系页面:(列表查询)
	     */
//	    @RequiresPermissions("ediXmlMapDb:list:view")
        @RequestMapping("/list")
	    @ResponseBody
        public AjaxResult showEdiXmlMapDb(EdiXmlMapDb ediXmlMapDb) {
            AjaxResult ajaxResult = null;
	        try {
                EntityWrapper entityWrapper = getEntityWrapper(ediXmlMapDb);  //查询调用方法
                Page page = getPage();  // 分页
                Page<EdiXmlMapDb> ediXmlMapDbList = ediXmlMapDbService.selectPage(page, entityWrapper);
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediXmlMapDbList.getRecords(), page.getTotal()); // 格式要返回的数据
            } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                logger.error("showEdiXmlMapDb()--error", e);
            }
	        return ajaxResult;
	    }
	    
	    /**
         * @param ediXmlMapDb
         * @return AjaxResult
	     * @throws
	     * @Description: 新增字段关系
	     */
//	    @RequiresPermissions("ediXmlMapDb:list:add")
        @RequestMapping(value = "/list/add")
	    @ResponseBody
        public AjaxResult addEdiXmlMapDb(EdiXmlMapDb ediXmlMapDb) {
            AjaxResult ajaxResult = null;
	        try {
                ediXmlMapDb.setUid(UUIDGenerator.getUUID());
                ediXmlMapDb.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                ediXmlMapDb.setCreateName(SingletonLoginUtils.getSystemUserName());//创建人

                Boolean fail = ediXmlMapDbService.insert(ediXmlMapDb);
                if (fail) {
                    ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediXmlMapDb);
                } else {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            }
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                logger.error("addEdiXmlMapDb()--err", e);
            }
	        return ajaxResult;
	    }
	    
	    
	    /**
         * @param ediXmlMapDb
         * @return AjaxResult
	     * @throws
	     * @Description: 编辑页面
	     */
//	    @RequiresPermissions("ediXmlMapDb:list:view")
        @RequestMapping("/list/edit")
	    @ResponseBody
        public AjaxResult editEdiXmlMapDb() {
            AjaxResult ajaxResult = null;
	        try {
                EdiXmlMapDb ediXmlMapDb = ediXmlMapDbService.selectById(getParameter("id"));
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediXmlMapDb);
            } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                logger.error("editEdiXmlMapDb()--err", e);
            }
	        return ajaxResult;
	    }
	    
	    
	    /**
         * @param ediXmlMapDb
         * @return AjaxResult
	     * @throws
	     * @Description: 修改数据
	     */
//	    @RequiresPermissions("ediXmlMapDb:list:edit")
        @RequestMapping("/list/update")
	    @ResponseBody
        public AjaxResult updateEdiXmlMapDb(EdiXmlMapDb ediXmlMapDb) {
            AjaxResult ajaxResult = null;
	        try {
                ediXmlMapDb.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                ediXmlMapDb.setUpdateName(SingletonLoginUtils.getSystemUserName());//修改人
               /* String str =codStdAreaCode.getCustomsCode();
	            codStdAreaCode.setCustomsCode(str.substring(str.length()-4, str.length()));*/

                Boolean fail = ediXmlMapDbService.updateById(ediXmlMapDb);
                if (fail) {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
	            } else {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            }
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                logger.error("updateEdiXmlMapDb()--err", e);
            }
	        return ajaxResult;
	    }
	    
	    
	    /**
         * @param ediXmlMapDb
         * @return AjaxResult
	     * @throws
	     * @Description: 删除单条数据
	     */
//	    @RequiresPermissions("ediXmlMapDb:list:delete")
        @RequestMapping("/list/delete")
	    @ResponseBody
	    public AjaxResult deleteCodStdAreaCode() {
	        AjaxResult ajaxResult = null;
	        try {
                Boolean fail = ediXmlMapDbService.deleteById(getParameter("id"));
                if (fail) {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
	            } else {
	                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
	            }
	        } catch (Exception e) {
	            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                logger.error("deleteEdiXmlMapDb()--err", e);
            }
	        return ajaxResult;
	    }
	    
	    
		/**
		 * @Description：批量删除
		 * @param model
		 * @return
		 * @throws
		 */
//		@RequiresPermissions("ediXmlMapDb:list:delete")
        @RequestMapping("/list/deleteByList")
		@ResponseBody
        public AjaxResult deleteEdiXmlMapDbByList() {

			AjaxResult ajaxResult = null;
			try {
				// 获取前台传输的主键集合
				String idList = getParameter("idList");
                Boolean fail = ediXmlMapDbService.deleteBatchIds(Arrays.asList(idList.split(",")));
                if (fail) {
					ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
				} else {
					ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
				}
			} catch (Exception e) {
				ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
                logger.error("deleteEdiXmlMapDbByList()--err", e);
            }

			return ajaxResult;
		}
}
  
    