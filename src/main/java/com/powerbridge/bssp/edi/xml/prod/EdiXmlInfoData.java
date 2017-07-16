package com.powerbridge.bssp.edi.xml.prod;

import com.powerbridge.bssp.common.listener.WebContextListener;
import com.powerbridge.bssp.edi.entity.EdiXmlMapDb;
import com.powerbridge.bssp.edi.service.IEdiXmlMapDbService;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName EdiXmlInfoData
 * @Description 字段关系数据
 * @author Simon.xie
 * @Date 2017年6月1日 下午10:39:16
 * @version 1.0.0
 */
public class EdiXmlInfoData {
    private WebApplicationContext wac = WebContextListener.getCurrentWebApplicationContext();   // 获取容器（上下文环境）

    //字段关系服务
    private IEdiXmlMapDbService ediXmlMapDbService;

    public EdiXmlInfoData(){
        // 通过容器 获取service
        ediXmlMapDbService = (IEdiXmlMapDbService) wac.getBean("ediXmlMapDbService");
    }
    /**
     *
     * @Description 根据单据类型，业务类型，表名，字段名查询对应报文字段名
     * @param docType   单据类型
     * @param bizType  业务类型
     * @return
     */
    public List<EdiXmlMapDb> getEdiXmlInfo(final String docType, final String bizType, String dbTable) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("docType", docType);
        map.put("bizType", bizType);
        map.put("dbTable", dbTable);
        return ediXmlMapDbService.getEdiXmlMapDb(map);
    }
}
