package com.powerbridge.bssp.edi.xml.util;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.common.listener.WebContextListener;
import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.cop.entity.CopEnt;
import com.powerbridge.bssp.edi.entity.EdiBusinessConfig;
import com.powerbridge.bssp.edi.service.IEdiBusinessConfigService;
import org.dom4j.Element;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ReceiptUtil
 * 类描述：菜单控制器
 * 创建人：danagao
 * 创建时间：2017/6/12 11:08
 *
 * @version 1.0
 */
public class ReceiptUtil {

    private static WebApplicationContext wac = WebContextListener.getCurrentWebApplicationContext();   // 获取容器（上下文环境）
    private ReceiptUtil receiptUtil = new ReceiptUtil();

    public static Map<String,String> getDataFromTable(String docType, String bizType, String etpsPreentNoValue) throws Exception{
        IEdiBusinessConfigService ediBusinessConfigService = (IEdiBusinessConfigService) wac.getBean("ediBusinessConfigService");
        Map<String,String> dataMap = new HashMap<>();

        EntityWrapper entityWrapper = new EntityWrapper<EdiBusinessConfig>();
        entityWrapper.like("DOC_TYPE", docType, SqlLike.RIGHT);//单据类型
        entityWrapper.eq("BIZ_TYPE", bizType);//业务类型
        entityWrapper.eq("STATUS", "Y");//处理标识
        entityWrapper.orderBy("ORDER_NO");
        EdiBusinessConfig ediBusinessConfig = ediBusinessConfigService.selectOne(entityWrapper);
        String etpsPreentNo =ediBusinessConfig.getEtpsPreentNo();
        String tableService =ediBusinessConfig.getTableService();

        EntityWrapper entityWrapper2 = new EntityWrapper<CopEnt>();
        entityWrapper2.eq(etpsPreentNo,etpsPreentNoValue);
        Object oneBean = BeanUtil.selectOne(wac.getBean(tableService), entityWrapper2);

        if (oneBean == null) {
            return null;
        }

        dataMap.put("seqNo",(String) BeanKit.getProperty(oneBean,"seqNo")) ;
        dataMap.put("docType",(String) BeanKit.getProperty(oneBean,"docType")) ;
        dataMap.put("bizType",(String) BeanKit.getProperty(oneBean,"bizType")) ;
        dataMap.put("areaCode",(String) BeanKit.getProperty(oneBean,"areaCode")) ;


        return dataMap;
    }


    /**  xml对应的节点中element 解析出数据，  存储到Object（实体类）中，
     * 注意：1：service的值为ediXmlMapDbService
     * 	   2：对应的Object的实体类中，如果属性是对应的时间在其set方法中，进行以下判断：如
	 *		public void setCol3(String col3) {
	 *			this.col3 = DateUtil.timeSwitch(col3);
	 *		}
     * @Title: wayAll
     * @Description: TODO 
     * @param: @param obj
     * @param: @param element
     * @param: @param service
     * @param: @return
     * @param: @throws Exception 
     * @return: Object
     */
    public static Object wayAll(Object obj,Element  element,IService service) throws Exception{
		Map<String,String> map=BeanUtil.getXmlToBean(obj, service);
		if(null != element ){
            List<Element> elementList = element.elements();
            for (Element e : elementList) {
                BeanUtil.setProperty(obj, map.get(e.getName()), e.getText());
				
			}
			
		}
		return obj;
	}

}
