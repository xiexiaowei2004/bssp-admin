package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_std.entity.CodStdClearanceType;
import com.powerbridge.bssp.cod_std.entity.CodStdTransportType;
import com.powerbridge.bssp.cod_std.entity.CodStdTrpType;
import com.powerbridge.bssp.cod_std.service.ICodStdTransportTypeService;
import com.powerbridge.bssp.cod_std.service.ICodStdTrpTypeService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 项目名称：bssp-admin
 * 类名称：CodStdTrpTypeController
 * 类描述：车辆运输类型接口类
 * 创建人：zsl
 * 创建时间：2017/5/12 10:00
 */
@Controller
@RequestMapping("/cod_std/codStdTrpType")
@CrossOrigin
public class CodStdTrpTypeController extends BaseController {
    @Autowired
    private ICodStdTrpTypeService codStdTrpTypeService;
    @Autowired
    private ICodStdTransportTypeService codStdTransportTypeService;

    // 查询方法
    private EntityWrapper getEntityWrapper(CodStdTrpType codStdTrpType) {
        //  String sort = getParameter("sort");
        boolean sortOrder = getOrderSort(getParameter("sortOrder"));
        EntityWrapper entityWrapper = new EntityWrapper<CodStdClearanceType>();
        if (codStdTrpType.getCode() != null && !"".equals(codStdTrpType.getCode())) {
            entityWrapper.like("CODE", codStdTrpType.getCode());
        }
        if (codStdTrpType.getName() != null && !"".equals(codStdTrpType.getName())) {
            entityWrapper.like("NAME", codStdTrpType.getName());
        }
        /*if (sort != null && !"".equals(sort)) {
            entityWrapper.orderBy(sort, sortOrder);  // 排序
        }*/
        entityWrapper.orderBy("CUSTOMS_CODE,AREA_CODE,CODE", sortOrder);
      /*  entityWrapper.orderBy("AREA_CODE", sortOrder);
        entityWrapper.orderBy("CODE", sortOrder);*/
        return entityWrapper;
    }

    /**
     * 根据条件查询
     */
    @RequiresPermissions("codStdTransportType:list:view")
    @RequestMapping(value = "/list")
    @ResponseBody
    public AjaxResult selectCodStdTrpTypeList(HttpServletRequest request, HttpServletResponse response, CodStdTrpType codStdTrpType, Integer pageSize, Integer pageNumber) {
        EntityWrapper entityWrapper = getEntityWrapper(codStdTrpType);  //查询调用方法
        if (StringUtils.isNotEmpty(codStdTrpType.getCustomsCode())) {
            entityWrapper.eq("CUSTOMS_CODE", codStdTrpType.getCustomsCode());
        }

        if (StringUtils.isNotEmpty(codStdTrpType.getAreaCode())) {
            entityWrapper.eq("AREA_CODE", codStdTrpType.getAreaCode());
        }

        Page page = new Page(pageNumber / pageSize + 1, pageSize);
        Page<CodStdTrpType> codStdTransportTypes = codStdTrpTypeService.selectPage(page, entityWrapper);
        return json(MessageConstants.BSSP_STATUS_SUCCESS, codStdTransportTypes.getRecords(), page.getTotal());
    }

    /**
     * 运输类型业务表批量入库
     *
     * @param request
     * @param response
     * @param codStdTrpType
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/list/okByList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult insertCodStdTransportTypeList() {
        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String ids = getParameter("idList");
            List<String> idList = Arrays.asList(ids.split(","));
            List<CodStdTrpType> codStdTrpTypeList = new ArrayList<CodStdTrpType>();
            for (int i = 0; i < idList.size(); i++) {
                codStdTrpTypeList.add(codStdTrpTypeService.selectById(idList.get(i)));
            }
            for (int i = 0; i < codStdTrpTypeList.size(); i++) {
                boolean flag = true;
                Map<String, Object> columnMap = new HashMap<String, Object>();
                columnMap.put("CUSTOMS_CODE", codStdTrpTypeList.get(i).getCustomsCode());
                columnMap.put("AREA_CODE", codStdTrpTypeList.get(i).getAreaCode());
                List<CodStdTransportType> codStdTransportTypeList = codStdTransportTypeService.selectByMap(columnMap);
                if (codStdTransportTypeList.size() == 0) {
                    CodStdTransportType ctt = new CodStdTransportType();
                    ctt.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    ctt.setCreateBy(SingletonLoginUtils.getSystemUserName());
                    BeanUtils.copyProperties(codStdTrpTypeList.get(i), ctt);
                    ctt.setUid(UUIDGenerator.getUUID());
                    codStdTransportTypeService.insert(ctt);
                } else {
                    //按管区代码+场地代码检查运输类型代码相同的记录不入库
                    for (int j = 0; j < codStdTransportTypeList.size(); j++) {
                        if (null != codStdTransportTypeList.get(j).getCode() && codStdTransportTypeList.get(j).getCode().equals(codStdTrpTypeList.get(i).getCode())) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        CodStdTransportType ctt = new CodStdTransportType();
                        ctt.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                        ctt.setCreateBy(SingletonLoginUtils.getSystemUserName());
                        BeanUtils.copyProperties(codStdTrpTypeList.get(i), ctt);
                        ctt.setUid(UUIDGenerator.getUUID());
                        codStdTransportTypeService.insert(ctt);
                    }
                }
            }
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);

        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("deleteCodCusBusitypeByList()--err", e);
        }

        return ajaxResult;
    }


}
