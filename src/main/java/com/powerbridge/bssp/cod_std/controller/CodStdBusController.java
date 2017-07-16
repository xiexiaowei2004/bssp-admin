package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_biz.entity.CodBizBus;
import com.powerbridge.bssp.cod_biz.service.ICodBizBusService;
import com.powerbridge.bssp.cod_std.entity.CodStdBus;
import com.powerbridge.bssp.cod_std.service.ICodStdBusService;
import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：CodStdBusController
 * 类描述：业务参数基础表控制器
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日  上午09:59:00
 */
@Controller
@RequestMapping("/cod_std/codStdBus")
@CrossOrigin
public class CodStdBusController extends BaseController {
    @Autowired
    private ICodStdBusService codStdBusService;
    @Autowired
    private ICodBizBusService codBizBusService;


    /**
     * @param codStdBus
     * @return AjaxResult
     * @throws
     * @Description: 进入业务参数管理页面:(列表查询)
     */
    @RequiresPermissions("CopCom:list:view")
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult showCodStdBusList(CodStdBus codStdBus) {
        AjaxResult ajaxResult = null;
        try {
            Page page = getPage();  // 分页
            String sort = getParameter("sort");
            boolean sortOrder = getOrderSort(getParameter("sortOrder"));
            if (StringUtil.isNotEmpty(sort)) {
                page.setAsc(sortOrder);
                page.setOrderByField(sort);  // 排序
            } else {
                //默认按关区代码+场地代码+参数代码值从小到大顺排
                page.setAsc(true);
                page.setOrderByField("customsCode,areaCode,code");  // 排序
            }
            Page<CodStdBus> codStdBusList = codStdBusService.selectByCodStdBus(page, codStdBus);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdBusList.getRecords(), page.getTotal()); // 返回json数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodStdBusList()--error", e);
        }
        return ajaxResult;
    }


    @RequestMapping(value = "/list/okByList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult insertListByExample() {
        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String ids = getParameter("idList");
            List<String> idList = Arrays.asList(ids.split(","));
            List<CodStdBus> codStdBusList = new ArrayList<CodStdBus>();
            for (int i = 0; i < idList.size(); i++) {
                codStdBusList.add(codStdBusService.selectById(idList.get(i)));
            }
            for (int i = 0; i < codStdBusList.size(); i++) {
                boolean flag = true;
                Map<String, Object> columnMap = new HashMap<String, Object>();
                columnMap.put("CUSTOMS_CODE", codStdBusList.get(i).getCustomsCode());
                columnMap.put("AREA_CODE", codStdBusList.get(i).getAreaCode());
                List<CodBizBus> codBizBusList = codBizBusService.selectByMap(columnMap);
                if (codBizBusList.size() == 0) {
                    CodBizBus ctt = new CodBizBus();
                    BeanUtils.copyProperties(codStdBusList.get(i), ctt);
                    ctt.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    ctt.setCreateBy(SingletonLoginUtils.getSystemUserName());
                    ctt.setUid(UUIDGenerator.getUUID());
                    codBizBusService.insert(ctt);
                } else {
                    //按“关区代码+场地代码” 检查参数代码相同的记录不带入
                    for (int j = 0; j < codBizBusList.size(); j++) {
                        if (null != codBizBusList.get(j).getCode() && codBizBusList.get(j).getCode().equals(codStdBusList.get(i).getCode())) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        CodBizBus ctt = new CodBizBus();
                        BeanUtils.copyProperties(codStdBusList.get(i), ctt);
                        ctt.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                        ctt.setCreateBy(SingletonLoginUtils.getSystemUserName());
                        ctt.setUid(UUIDGenerator.getUUID());
                        codBizBusService.insert(ctt);
                    }
                }
            }
            ajaxResult = result(MessageConstants.BSSP_STATUS_SUCCESS);

        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("insertListByExample()--err", e);
        }

        return ajaxResult;
    }
}

