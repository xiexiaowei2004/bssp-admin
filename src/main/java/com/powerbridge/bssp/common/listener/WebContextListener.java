package com.powerbridge.bssp.common.listener;

import com.powerbridge.bssp.common.constants.GlobalConstants;

import com.powerbridge.bssp.redis.RedisDataTool;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;


/**
 * 项目名称：bssp Maven Webapp
 * 类名称：WebContextListener
 * 类描述：Spring监听器
 * ContextLoaderListener监听器的作用就是启动Web容器时，自动装配ApplicationContext的配置信息。因为它实现了ServletContextListener这个接口，在web.xml配置这个监听器，启动容器时，就会默认执行它实现的方法。
 * 创建人：simon.xie
 * 创建时间：2017年4月27日 下午10:12:17
 * 修改人：simon.xie
 * 修改时间：2017年4月27日 下午10:12:17
 * 修改备注：
 */
@WebListener
public class WebContextListener extends ContextLoaderListener {

    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
        if (!GlobalConstants.printKeyLoadMessage()) {
            return null;
        }
        return super.initWebApplicationContext(servletContext);
    }

    /**
     * @description 重写ContextLoaderListener的contextInitialized方法
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);
//		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());

        System.out.println("===========================启动REDIS开始===============================");
        new RedisDataTool().setRedisParameter();
        System.out.println("===========================启动REDIS结束===============================");

    }
}


