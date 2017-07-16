package com.powerbridge.bssp.redis;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.cod_std.entity.CodStdCodes;
import com.powerbridge.bssp.cod_std.service.ICodStdCodesService;
import com.powerbridge.bssp.common.Bean.PullDown;
import com.powerbridge.bssp.common.listener.WebContextListener;
import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.redis.service.IRedisClientTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp Maven webapp
 * 类名称：RedisDataSource
 * 类描述：将下拉数据存入Redis
 * 创建人：haihuihuang
 * 创建时间：2017/5/21 17:32
 */

public class RedisDataTool {

    private WebApplicationContext wac = WebContextListener.getCurrentWebApplicationContext();   // 获取容器（上下文环境）
    private IRedisClientTemplate redisClientTemplate;
    private static final String[] tableNames = new String[]{

//            "codCusBrief",//海关注册企业表
            "codCusBusitype",//加工类别
            "codCusBusttype",//企业类型
//            "codCusClassify",//商品归类表
            "codCusComplex",//商品税则表
            "codCusCotype",//企业性质
            "codCusCountry",//国别地区
            "codCusCurr",//币制代码
            "codCusCustomsfec",//关区代码
            "codCusCustomsrel",//口岸海关代码
            "codCusDistrictrel",//国内地区
            "codCusDisttype",//地区类别
            "codCusLctype",//结汇方式
            "codCusLevymode",//征免方式
            "codCusLevytype",//征免性质
            "codCusLicensedocu",//许可证件
            "codCusMfteccode",//主管外经贸部门
            "codCusMftecode",//外经贸部门
            "codCusPaymode",//保税方式
            "codCusPaytype",//支付方式
            "codCusPortlin",//港口航线
            "codCusProducetype",//生产方式
            "codCusTrade",//监管方式
            "codCusTrademode",//监管方式证件
            "codCusTrans",//成交方式
            "codCusTransf",//运输方式
            "codCusUnit",//计量单位
            "codCusUserto",//用途代码
            "codCusWrap",//包装方式
            "codCusInvestmode",//投资方式
            "codCusInvclass",//引进方式

            "codStdAreaCode",//场地代码
            "codStdClearanceType",//通关业务类型
            "codStdContaParam",//集装箱参数
            "codStdExchangeRate",//汇率维护
            "codStdTransportType",//运输类型
            "codStdCarType",//车辆类型
            "codStdCarSort",//车辆分类
            "codStdBusType",//业务类型
            "codStdSprocesstype",//简单加工方式
            "codStdEnttype",//企业类型
            "codStdRsk",//风险参数基础表
            "codStdBus",//业务参数基础表
            "codStdTrpType",//运输类型基础表

            "codBizBus",//业务参数
            "codBizRsk"//风险参数
    };

    public RedisDataTool() {
        // 通过容器 获取service
        redisClientTemplate = (IRedisClientTemplate) wac.getBean("redisClientTemplate");

    }

    public void setRedisParameter() {

        getCodStdCodes();   // 获取数据字段 塞入 redisClientTemplate
        setRedisData();
    }

    //todo:redis数据的重载功能
    public static void reload(String tableName) {

    }


    private void setRedisData() {
        for (String tableName : tableNames) {
            try {
                if (!StringUtil.isEmpty(tableName)) {
                    Object tableService = wac.getBean(tableName + "Service");
                    List tableList = BeanKit.selectRedisDropDown(tableService);
                    if(tableList!=null){
                        //加载数据的时候先删除缓存
                        if(redisClientTemplate.exists(tableName)){
                            redisClientTemplate.exists(tableName);
                        }
                        redisClientTemplate.set(tableName, tableList, 0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public Map getRedisData(String tableNames){

        String [] tblNames;
        if(tableNames!=null&&tableNames.contains(",")){
            tblNames = tableNames.split(",");
        } else {
            tblNames = new String[]{tableNames};
        }

        Map tableMap = new HashMap();
        for (String tableName : tblNames) {
            if (redisClientTemplate.exists(tableName)) {     // 判断是否存在键值
                List list = (List) redisClientTemplate.get(tableName);
                tableMap.put(tableName,list);
            }
        }

        return  tableMap;
    }


    public Map getRedisCodes(String dictionaryValue){

        String [] tblNames;
        if(dictionaryValue!=null&&dictionaryValue.contains(",")){
            tblNames = dictionaryValue.split(",");
        } else {
            tblNames = new String[]{dictionaryValue};
        }

        Map<String, List<CodStdCodes>> map = (Map<String, List<CodStdCodes>>) redisClientTemplate.get("codStdCodes");

        Map tableMap = new HashMap();
        for (String tableName : tblNames) {
            if (map.containsKey(tableName)) {   // 判断是否存在键值
                List<CodStdCodes> list = map.get(tableName);
                tableMap.put(tableName,list);
            }
        }

        return  tableMap;
    }


    /**
     * @param
     * @throws
     * @Description: 数据字段数据源
     */
    private void getCodStdCodes() {
        ICodStdCodesService codStdCodesService = (ICodStdCodesService) wac.getBean("codStdCodesService");
        EntityWrapper<CodStdCodes> codStdCodesEntityWrapper = new EntityWrapper<>();
        codStdCodesEntityWrapper.eq("IS_ENABLE", "Y"); // 默认启用
        codStdCodesEntityWrapper.orderBy("DICTIONARY_VALUE,ORDER_NO", true); // 先字典值排序，后orderNo排序
        List<CodStdCodes> codStdCodesByList = codStdCodesService.selectList(codStdCodesEntityWrapper);
        Map<String, Object> codStdCodesByMap = new HashMap<>();
        for (CodStdCodes codStdCodes : codStdCodesByList) {
            List<PullDown> pullDownList = new ArrayList<>();
            PullDown pullDown = new PullDown();
            pullDown.setId(codStdCodes.getAttrValue());
            pullDown.setText(codStdCodes.getAttrName());
            if (!codStdCodesByMap.containsKey(codStdCodes.getDictionaryValue())) {
                pullDownList.add(pullDown);
            } else {
                pullDownList = (List<PullDown>) codStdCodesByMap.get(codStdCodes.getDictionaryValue());
                pullDownList.add(pullDown);
            }
            codStdCodesByMap.put(codStdCodes.getDictionaryValue(), pullDownList);
        }
        redisClientTemplate.set("codStdCodes", codStdCodesByMap, 0);
    }
}
