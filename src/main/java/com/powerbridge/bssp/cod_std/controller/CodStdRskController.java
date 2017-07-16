package com.powerbridge.bssp.cod_std.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.controller.BaseController;
import com.powerbridge.bssp.cod_biz.entity.CodBizRsk;
import com.powerbridge.bssp.cod_biz.service.ICodBizRskService;
import com.powerbridge.bssp.cod_std.entity.CodStdRsk;
import com.powerbridge.bssp.cod_std.service.ICodStdRskService;
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
 * 类名称：CodStdRskController
 * 类描述：风险参数基础表控制器
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日  上午09:59:00
 */
@Controller
@RequestMapping("/cod_std/codStdRsk")
@CrossOrigin
public class CodStdRskController extends BaseController {
    @Autowired
    private ICodStdRskService codStdRskService;
    @Autowired
    private ICodBizRskService codBizRskService;


    /**
     * @param codStdRsk
     * @return AjaxResult
     * @throws
     * @Description: 进入风险参数管理页面:(列表查询)
     */
    @RequiresPermissions("CopCom:list:view")
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult showCodStdRskList(CodStdRsk codStdRsk) {
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
            Page<CodStdRsk> codStdRskList = codStdRskService.selectByCodStdRsk(page, codStdRsk);
            ajaxResult = json(MessageConstants.BSSP_STATUS_SUCCESS, codStdRskList.getRecords(), page.getTotal()); // 返回json数据
        } catch (Exception e) {
            ajaxResult = result(MessageConstants.BSSP_STATUS_FAIL);
            logger.error("showCodStdRskList()--error", e);
        }
        return ajaxResult;
    }


    /**
     * @param
     * @return AjaxResult
     * @throws
     * @Description: 从风险参数基础数据表中选择数据添加到风险参数表
     */
    @RequiresPermissions("CopCom:list:view")
    @RequestMapping(value = "/list/okByList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult insertListByExample() {
        AjaxResult ajaxResult = null;
        try {
            // 获取前台传输的主键集合
            String ids = getParameter("idList");
            List<String> idList = Arrays.asList(ids.split(","));
            List<CodStdRsk> codStdRskList = new ArrayList<CodStdRsk>();
            for (int i = 0; i < idList.size(); i++) {
                codStdRskList.add(codStdRskService.selectById(idList.get(i)));
            }
            for (int i = 0; i < codStdRskList.size(); i++) {
                boolean flag = true;
                Map<String, Object> columnMap = new HashMap<String, Object>();
                columnMap.put("CUSTOMS_CODE", codStdRskList.get(i).getCustomsCode());
                columnMap.put("AREA_CODE", codStdRskList.get(i).getAreaCode());
                List<CodBizRsk> codBizRskList = codBizRskService.selectByMap(columnMap);
                if (codBizRskList.size() == 0) {
                    CodBizRsk ctt = new CodBizRsk();
                    BeanUtils.copyProperties(codStdRskList.get(i), ctt);
                    ctt.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    ctt.setCreateBy(SingletonLoginUtils.getSystemUserName());
                    ctt.setUid(UUIDGenerator.getUUID());
                    codBizRskService.insert(ctt);
                } else {
                    //按“关区代码+场地代码” 检查参数代码相同的记录不带入
                    for (int j = 0; j < codBizRskList.size(); j++) {
                        if (null != codBizRskList.get(j).getCode() && codBizRskList.get(j).getCode().equals(codStdRskList.get(i).getCode())) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        CodBizRsk ctt = new CodBizRsk();
                        BeanUtils.copyProperties(codStdRskList.get(i), ctt);
                        ctt.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                        ctt.setCreateBy(SingletonLoginUtils.getSystemUserName());
                        ctt.setUid(UUIDGenerator.getUUID());
                        codBizRskService.insert(ctt);
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

