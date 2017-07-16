package com.powerbridge.bssp.cod_cus.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqInfo;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqList;
import com.powerbridge.bssp.cod_cus.service.IEdiSeqInfoService;
import com.powerbridge.bssp.cod_cus.service.IEdiSeqListService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.cop.entity.CopBusiness;
import com.powerbridge.bssp.cop.entity.CopEnt;
import com.powerbridge.bssp.cop_et.entity.EtArcrpAcmpFormDt;
import com.powerbridge.bssp.cop_et.entity.EtArcrpBsc;
import com.powerbridge.bssp.cop_et.entity.EtArcrpExg;
import com.powerbridge.bssp.cop_et.entity.EtArcrpImg;




@Controller
@RequestMapping("/cod_cus/ediSeqInfo")
@CrossOrigin
public class EdiSeqInfoController extends BaseController {

    //获取当前时间
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	@Autowired
    private IEdiSeqInfoService ediSeqInfoService;

    @Autowired
    private IEdiSeqListService ediSeqListService;

    /**
     * 新增联网企业档案库
     *
     * @param etArcrpBsc 详细信息
     * @param isCheck    是否校验,申报时的保存需要校验
     * @return
     */
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addEdiSeqInfo(EdiSeqInfo ediSeqInfo) {
        AjaxResult ajaxResult = null;
        try {
            //使用工具类生成id
            String uId = UUIDGenerator.getUUID();
            if ("".equals(uId) && null == uId) {
                //失败
                return json(MessageConstants.BSSP_STATUS_FAIL, "id生成失败");
            }
            ediSeqInfo.setUid(uId);

            boolean flag = ediSeqInfoService.insert(ediSeqInfo);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediSeqInfo);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addEdiSeqInfo()--err", e);
        }
        return ajaxResult;
    }
    
    /**
     * 根据id删除联网企业档案库
     *
     * @param id uid主键
     * @return
     */
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEdiSeqInfoById(@PathVariable String id) {
        try {
        	EdiSeqInfo ediSeqInfo = ediSeqInfoService.selectById(id);
            if (ediSeqInfo == null) {
                return json(MessageConstants.BSSP_STATUS_FAIL, "该记录不存在");
            }
//           
            //执行删除
            //先删除子表数据
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("EDI_SEQ_UID", ediSeqInfo.getUid());
            ediSeqListService.delete(entityWrapper);
            
            //再删除主表数据
            if (ediSeqListService.deleteById(id)) {
                return result(MessageConstants.BSSP_STATUS_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("deleteEdiSeqInfoById()--err", e);
            return json(MessageConstants.BSSP_STATUS_FAIL, e.getMessage());
        }
        return result(MessageConstants.BSSP_STATUS_FAIL);
    }
    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteEdiSeqInfoByList(String idList) {
        AjaxResult ajaxResult = null;
        try {
            Boolean flag = ediSeqInfoService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteEdiSeqInfoByList()--err", e);
        }
        return ajaxResult;
    }
    /**
     * 修改联网企业档案库
     *
     * @param etArcrpBsc 详细信息
     * @return
     */
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateEdiSeqInfo(EdiSeqInfo ediSeqInfo) {
        AjaxResult ajaxResult = null;
        try {
            if (StringUtil.isEmpty(ediSeqInfo.getUid())) {
                return json(MessageConstants.BSSP_STATUS_CODE_NOTEXIST, "主键不能为空");
            }
          

            boolean flag = ediSeqInfoService.updateById(ediSeqInfo);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediSeqInfo);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateEdiSeqInfo()--err", e);
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
    public AjaxResult selectEdiSeqInfoById(@PathVariable String id) {
        AjaxResult ajaxResult;
        try {
        	EdiSeqInfo ediSeqInfo = ediSeqInfoService.selectById(id);
            if (StringUtil.isNotEmpty(ediSeqInfo.getUid())) {
                EntityWrapper entityWrapper = new EntityWrapper();
                entityWrapper.eq("EDI_SEQ_UID", ediSeqInfo.getUid());
                List<EdiSeqList> ediSeqLists = ediSeqListService.selectList(entityWrapper);
               
                ediSeqInfo.setEdiSeqList(ediSeqLists);
           
            }
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, ediSeqInfo);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("selectEdiSeqInfoById()--err", e);
        }
        return ajaxResult;
    }
   

    /**
     * @param ediSeqInfo
     * @return EntityWrapper
     * @throws
     * @Description: 查询方法:(列表查询)
     */
    private EntityWrapper<EdiSeqInfo> getEntityWrapper(EdiSeqInfo ediSeqInfo) {
        EntityWrapper<EdiSeqInfo> entityWrapper = new EntityWrapper<EdiSeqInfo>();
        if (StringUtil.isNotEmpty(ediSeqInfo.getDocType())) {
            entityWrapper.like("DOC_TYPE", ediSeqInfo.getDocType(), SqlLike.RIGHT); 
        }
        if (StringUtil.isNotEmpty(ediSeqInfo.getServerType())) {
            entityWrapper.like("SERVER_TYPE", ediSeqInfo.getServerType(), SqlLike.RIGHT); 
        }
       
        
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        if (StringUtil.isNotEmpty(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        } else {
            entityWrapper.orderBy("UPDATE_TIME", false); //默认按最后变更日期倒排序
        }
        return entityWrapper;
    }


    /**
     * @param copEnt
     * @return AjaxResult
     * @throws
     * @Description: 进入单据编码管理页面:(列表查询)
     
     */
   
    @RequestMapping(value = "/list", method ={RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showEdiSeqInfoList(EdiSeqInfo ediSeqInfo) {
    	
    	
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper<EdiSeqInfo> entityWrapper = getEntityWrapper(ediSeqInfo);  //查询调用方法
            Page<EdiSeqInfo> page = getPage();  // 分页
            Page<EdiSeqInfo> copEntList = ediSeqInfoService.selectPage(page, entityWrapper);


            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, copEntList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showEdiSeqInfoList()--error", e);
        }
        return ajaxResult;
    }

  


  

   
}



