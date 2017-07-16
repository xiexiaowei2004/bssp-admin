package com.powerbridge.bssp.cod_cus.controller;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;
import com.powerbridge.bssp.cod_cus.service.ICodCusComplexService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;

import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： CodCusComplexController
 * 类描述： 商品税则表控制器
 * 创建人： LC
 * 创建时间： 2017年5月8日
 */
@Controller
@RequestMapping("/cod_cus/codCusComplex")
@CrossOrigin
public class CodCusComplexController extends BaseController {
    @Autowired
    private ICodCusComplexService codCusComplexService;

    private EntityWrapper getEntityWrapper(CodCusComplex codCusComplex) {
        String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));

        EntityWrapper entityWrapper = new EntityWrapper<CodCusComplex>();
        if (codCusComplex.getUnit1() != null && !"".equals(codCusComplex.getUnit1())) {
            entityWrapper.like("UNIT_1", (codCusComplex.getUnit1()));
        }
        if (codCusComplex.getgName() != null && !"".equals(codCusComplex.getgName())) {
            entityWrapper.like("G_NAME", codCusComplex.getgName());
        }
        if (codCusComplex.getCodeT() != null && !"".equals(codCusComplex.getCodeT())) {
            entityWrapper.like("CODE_T", codCusComplex.getCodeT());
        }
        if (codCusComplex.getUnit2() != null && !"".equals(codCusComplex.getUnit2())) {
            entityWrapper.like("UNIT_2", codCusComplex.getUnit2());
        }
        if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);
        }
        return entityWrapper;
    }


    /**
     * @param codCusComplex
     * @return
     * @throws
     * @Description：分页查询
     */
    @RequiresPermissions("codCusComplex:list:view")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult showCodCusComplexList(
            CodCusComplex codCusComplex) {

        AjaxResult ajaxResult = null;
        try {

            EntityWrapper entityWrapper = getEntityWrapper(codCusComplex); // 查询调用方法
            Page page = getPage(); // 分页
            Page<CodCusComplex> codCusComplexList = codCusComplexService.selectPage(page, entityWrapper);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusComplexList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodCusComplexList()--error", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusComplex
     * @return
     * @throws
     * @Description：新增数据
     */
    @RequiresPermissions("codCusComplex:list:add")
    @RequestMapping(value = "/list/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult addCodCusComplex(
            CodCusComplex codCusComplex) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusComplexService.insert(codCusComplex);
            if (flag) {
                ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusComplex);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("addCodCusComplex()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param id
     * @return
     * @throws
     * @Description：编辑页面
     */
    @RequiresPermissions("codCusComplex:list:view")
    @RequestMapping(value = "/list/{id}/view", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult editCodCusComplex(@PathVariable String id) {
        AjaxResult ajaxResult = null;
        try {
            CodCusComplex codCusComplex = codCusComplexService.selectById(id);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusComplex);
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("editCodCusComplex()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param codCusComplex
     * @return
     * @throws
     * @Description：修改数据
     */
    @RequiresPermissions("codCusComplex:list:edit")
    @RequestMapping(value = "/list/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult updateCodCusComplex(
            CodCusComplex codCusComplex) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusComplexService.updateById(codCusComplex);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("updateCodCusComplex()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：单个删除
     */
    @RequiresPermissions("codCusComplex:list:delete")
    @RequestMapping(value = "/list/{id}/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusComplex(@PathVariable String id) {

        AjaxResult ajaxResult = null;
        try {
            Boolean flag = codCusComplexService.deleteById(id);
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusComplex()--err", e);
        }

        return ajaxResult;
    }


    /**
     * @param
     * @return
     * @throws
     * @Description：批量删除
     */
    @RequiresPermissions("codCusComplex:list:delete")
    @RequestMapping(value = "/list/deleteByList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult deleteCodCusComplexByList() {

        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String idList = getParameter("idList");
            Boolean flag = codCusComplexService.deleteBatchIds(Arrays.asList(idList.split(",")));
            if (flag) {
                ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
            } else {
                ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            }
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusComplexByList()--err", e);
        }

        return ajaxResult;
    }

    /**
     * @param
     * @return
     * @throws
     * @Description：商品
     */
    @RequestMapping(value = "/list/chooseData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult chooseData(CodCusComplex codCusComplex) {
        AjaxResult ajaxResult = null;
        try {
            EntityWrapper entityWrapper = new EntityWrapper<>(); // 查询调用方法
            if (StringUtil.isNotEmpty(codCusComplex.getCodeT())){
                entityWrapper.like("CODE_T", codCusComplex.getCodeT(), SqlLike.RIGHT);  // 左匹配
            }
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按序号排序
                page.setAsc(true);
                page.setOrderByField("pkSeq");  // 排序
            }
            Page<CodCusComplex> codCusComplexList = codCusComplexService.selectComplexList(page, codCusComplex);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codCusComplexList.getRecords(), page.getTotal()); // 格式要返回的数据
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("chooseData()--err", e);
        }
        return ajaxResult;
    }

    /**
     * @param code
     * @return
     * @throws
     * @Description：获取商品数据
     */
    @RequestMapping(value = "/list/getCodCusComplex", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult getCodCusComplex(String code) {
        AjaxResult ajaxResult = null;
        try {
            String codeT = code.substring(0,8); //HS编码
            String codeS = code.substring(8,10);    //附加编码
            EntityWrapper<CodCusComplex> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("CODE_T",codeT);
            entityWrapper.eq("CODE_S",codeS);
            CodCusComplex codCusComplex = codCusComplexService.selectOne(entityWrapper);
            if (codCusComplex == null){
                return result(MessageConstants.BSSP_STATUS_FAIL);
            }
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);
        } catch (Exception e) {
            ajaxResult = setErrorJson(e.getMessage());
            logger.error("getCodCusComplex()--err", e);
        }

        return ajaxResult;
    }
    /**
     * @param gdecd 商品编码
     * @return
     * @throws
     * @Description：获取商品数据
     */
    @RequestMapping(value = "/list/selectCountByGdecd", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult selectCountByGdecd(String gdecd) {
        String rowsCount = codCusComplexService.selectCountByGdecd(gdecd);
        if("0".equals(rowsCount)){
            return setErrorJson("商品编码不存在");
        }
        return  result(MessageConstants.BSSP_STATUS_SUCCESS);
    }
}
