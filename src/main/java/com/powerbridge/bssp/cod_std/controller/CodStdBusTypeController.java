package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdBusType;
import com.powerbridge.bssp.cod_std.service.ICodStdBusTypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;



/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodStdBusTypeController
 * 类描述：业务分类控制器
 * 创建人：haihuihuang
 * 创建时间：2017年4月29日 下午10:12:17
 */
@Controller
@RequestMapping("/cod_std/codStdBusType")
@CrossOrigin
public class CodStdBusTypeController extends BaseController {
    @Autowired
    private ICodStdBusTypeService codStdBusTypeService;
    

    // 查询方法
    private EntityWrapper getEntityWrapper(CodStdBusType codStdBusType) {
        String sort = getParameter("sort");//排序列名
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));//排序规则
        EntityWrapper entityWrapper = new EntityWrapper<CodStdBusType>();
        if (codStdBusType.getUid() != null && !"".equals(codStdBusType.getUid())) {
            entityWrapper.like("UID", codStdBusType.getUid());
        }
        if (codStdBusType.getCode() != null && !"".equals(codStdBusType.getCode())) {
            entityWrapper.like("CODE", codStdBusType.getCode());
        }
        if (codStdBusType.getName() != null && !"".equals(codStdBusType.getName())) {
            entityWrapper.like("NAME", codStdBusType.getName());
        }
        if (codStdBusType.getEnable() != null && !"".equals(codStdBusType.getEnable())) {
            entityWrapper.like("IS_ENABLE", codStdBusType.getEnable());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }
        return entityWrapper;
    }

    /**
     * @param codStdBusType
     * @return AjaxResult
     * @throws
     * @Description: 进入车辆分类页面:(列表查询)
     */
    @RequiresPermissions("codStdBusType:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodStdBusTypeList(CodStdBusType codStdBusType) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = getEntityWrapper(codStdBusType);  //查询调用方法
            Page page = getPage();  // 分页
            Page<CodStdBusType> codStdBusTypeList = codStdBusTypeService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdBusTypeList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodStdBusTypeList()--error", e);
        }
        return ajaxResult;
    }

    /**
     * @param codStdBusType
     * @return AjaxResult
     * @throws
     * @Description: 新增车辆分类
     */
    @RequiresPermissions("codStdBusType:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodStdBusType(CodStdBusType codStdBusType) {
    	  //使用生成器生成id
        String uId = UUIDGenerator.getUUID();
        if("".equals(uId) && null==uId){
            //失败
            return json(MessageConstants.BSSP_STATUS_FAIL,"id生成失败");
        }
        codStdBusType.setUid(uId);
        Date date = new Date();
    	Timestamp nousedate = new Timestamp(date.getTime());//获取当前时间（mysl：timestamp类型）
    	String user=SingletonLoginUtils.getSystemUserName();//收取用户名字
    	codStdBusType.setCreateBy(user);
        AjaxResult ajaxResult = null;
        try {
            Boolean fail = codStdBusTypeService.insert(codStdBusType);
            if (fail) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdBusType);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodStdBusType()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 编辑页面
     */
    @RequiresPermissions("codStdBusType:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodStdBusType(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            CodStdBusType CodStdBusType = codStdBusTypeService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, CodStdBusType);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodStdBusType()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param codStdBusType
     * @return AjaxResult
     * @throws
     * @Description: 修改数据
     */
    @RequiresPermissions("codStdBusType:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodStdBusType(CodStdBusType codStdBusType) {
        AjaxResult ajaxResult = null;
        try {
            Boolean fail = codStdBusTypeService.updateById(codStdBusType);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodStdBusType()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 删除单条数据
     */
    @RequiresPermissions("codStdBusType:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdBusType(@PathVariable String id) {
    	/*CodStdBusType codStdBusType=new CodStdBusType();
    	codStdBusType.setEnable("N");*/
        AjaxResult ajaxResult = null;
        try {
            Boolean fail = codStdBusTypeService.deleteById(id);
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdBusType()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 批量删除操作
     */
    @RequiresPermissions("codStdBusType:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodStdBusTypeByList() {
    	/*CodStdBusType codStdBusType=new CodStdBusType();
    	codStdBusType.setEnable("N");*/
        AjaxResult ajaxResult = null;
        try {
            //获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean fail = codStdBusTypeService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (fail) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodStdBusTypeByList()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 主管海关数据源
     */
/*    @RequestMapping(value = "/getDataSource")
    @ResponseBody
    public AjaxResult getDataSource() {
        AjaxResult ajaxResult = null;
        try {
            List<CodStdBusType> CodStdBusType = CodStdBusTypeService.selectBusTypeByList(); //主管海关下拉选择数据源
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, CodStdBusType);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("getDataSource()--err", e);
        }
        return ajaxResult;
    }*/
}
