package com.powerbridge.bssp.common.advice;

import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import com.powerbridge.bssp.common.util.ServletUtils;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.common.util.toolbox.StringUtil;
import com.powerbridge.bssp.base.entity.SystemLog;
import com.powerbridge.bssp.base.service.impl.SystemLogServiceImpl;
import com.powerbridge.bssp.base.service.ISystemLogService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 
 * @ClassName ControllerMethodInterceptor
 * @Description 拦截器（用户行为日志，接口权限判断）
 * @author Simon.xie
 * @Date 2017年5月3日 下午10:34:55
 * @version 1.0.0
 */
public class ControllerMethodInterceptor implements MethodInterceptor {
    
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ISystemLogService systemLogService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
    	//调用者ip
        String ip = ServletUtils.getIpAddr();
        //调用者应用id
        String applyId = ServletUtils.getApplyId();
        //调用者应用url
        String url = getUri(invocation);


        try {
            if (validRight(ip, applyId, url)) {

                long t1 = System.currentTimeMillis();
                Object result = invocation.proceed();
                long t2 = System.currentTimeMillis();
                String  msg = "";
                if (result != null) {
                    if (result instanceof AjaxResult) {
                        msg = ((AjaxResult) result).getMessage();
                    } else {
                        msg = "ok";
                    }
                }


                getSystemLog(ip, applyId, url,1, msg,(t2-t1));
                return result;
            } else {
                AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_INTERFACE_JURISDICTION);
                getSystemLog(ip, applyId, url,101, ajaxResult.getMessage(),0);
                return ajaxResult;
            }
        } catch (Throwable throwable) {
            AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_FAIL, throwable.getMessage());
            getSystemLog(ip,applyId,url,9999,ajaxResult.getMessage(),0);
            throw throwable;
        }


    }


	private void getSystemLog(String ip, String applyId, String url,int type, String result,long responseTime) {
        if(StringUtil.isEmpty(applyId)){
            applyId = "zzzzzz";
        }
        SystemLog writeLog = new SystemLog();
		writeLog.setId(UUIDGenerator.getUUID());
		writeLog.setApplyId(applyId);
		writeLog.setData(new Date());
		writeLog.setIp(ip);
		writeLog.setMethod(url);
		writeLog.setReponesData(String.valueOf(responseTime));
		writeLog.setType(type);
		writeLog.setCommite(result);
		writeLog(writeLog);
	}


    /*
    * 检查当前应用是否有调用接口的权限
    * */
    private boolean validRight(String ip, String applyId, String uri) {
        //此处进行逻辑
    	
    	
        if (2>1) {
            return true;
        }
        return false;
    }



    /*
    * 获取方法RequestMapping中的value
    * */
    private String getUri(MethodInvocation invocation) {
        StringBuffer uri = new StringBuffer();
        Method method = invocation.getMethod();
        Class clazz = method.getDeclaringClass();
        //判断是否存在requestMapping注释
        boolean present1 = clazz.isAnnotationPresent(RequestMapping.class);
        if (present1) {
            //得到requestMapping注释
            RequestMapping annotation2 = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (annotation2 != null) {
                String[] value = annotation2.value();
                if (value != null && value.length > 0) {
                    uri.append(value[0]);
                }
            }
        }


        //判断是否存在requestMapping注释
        boolean present = method.isAnnotationPresent(RequestMapping.class);
        if (present) {
            //得到requestMapping注释
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            String[] value = annotation.value();
            if (value != null && value.length > 0) {
                uri.append(value[0]);
            }
        }

        return uri.toString();
    }


    /*
    * 记录操作日志
    * */
    private void writeLog(SystemLog writeLog) {

        try {
            if (systemLogService == null) {//解决service为null无法注入问题
            	logger.info("systemLogService is null!!!");
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletUtils.getRequest().getServletContext());
                systemLogService = (SystemLogServiceImpl) factory.getBean("systemLogService");
            }

            systemLogService.insert(writeLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
