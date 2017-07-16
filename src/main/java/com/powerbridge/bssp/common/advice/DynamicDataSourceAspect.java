package com.powerbridge.bssp.common.advice;

import com.powerbridge.bssp.common.datasource.DBTypeEnum;
import com.powerbridge.bssp.common.datasource.DbContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Simon.xie
 * @version 1.0.0
 * @ClassName DynamicDataSourceAspect
 * @Description 多数据源拦截器
 * @Date 2017年5月10日 上午10:21:44
 */
@Component
@Aspect
public class DynamicDataSourceAspect implements MethodInterceptor {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// logger.info("当前数据源----------------------------------" +
		// DbContextHolder.getDbType());
		String packageName = invocation.getThis().getClass().getPackage().getName();
		if (("com.powerbridge.bssp.cod_biz.service.impl").equals(packageName)
			|| ("com.powerbridge.bssp.cod_std.service.impl").equals(packageName)
			|| ("com.powerbridge.bssp.cod_cus.service.impl").equals(packageName)
			|| ("com.powerbridge.bssp.parameter.service.impl").equals(packageName)) {
			DbContextHolder.setDbType(DBTypeEnum.cod);
		} else if ("com.powerbridge.bssp.edi.service.impl".equals(packageName)) {
			DbContextHolder.setDbType(DBTypeEnum.edi);
		} else if ("com.powerbridge.bssp.ems.service.impl".equals(packageName)
			|| "com.powerbridge.bssp.ems_bws.service.impl".equals(packageName)){
			DbContextHolder.setDbType(DBTypeEnum.ems);
		} else if ("com.powerbridge.bssp.sas.service.impl".equals(packageName)
				|| "com.powerbridge.bssp.saspass.service.impl".equals(packageName)
				|| "com.powerbridge.bssp.sas_cmb.service.impl".equals(packageName)){
			DbContextHolder.setDbType(DBTypeEnum.sas);
		} else if ("com.powerbridge.bssp.inv.service.impl".equals(packageName)
				|| "com.powerbridge.bssp.saspass.service.impl".equals(packageName)){
			DbContextHolder.setDbType(DBTypeEnum.inv);
		} else if ("com.powerbridge.bssp.cop.service.impl".equals(packageName)) {
			DbContextHolder.setDbType(DBTypeEnum.cop);
		} else if ("com.powerbridge.bssp.dcr.service.impl".equals(packageName)) {
			DbContextHolder.setDbType(DBTypeEnum.dcr);
		} else if ("com.powerbridge.bssp.cop_et.service.impl".equals(packageName)) {
			DbContextHolder.setDbType(DBTypeEnum.cop_et);
		} else if ("com.powerbridge.bssp.erp.service.impl".equals(packageName)) {
			DbContextHolder.setDbType(DBTypeEnum.erp);
		} else if ("com.powerbridge.bssp.entry.service.impl".equals(packageName)) {
			DbContextHolder.setDbType(DBTypeEnum.entry);
		}
		else {
			DbContextHolder.setDbType(DBTypeEnum.bssp);
		}
		Object result = invocation.proceed();
		// logger.info("新数据源----------------------------------" +
		// DbContextHolder.getDbType());

		return result;
	}

	// @Pointcut("execution(* com.baomidou.mybatisplus.service.IService.*(..))
	// and target(com.powerbridge.bssp.*.service.*.*(..))")
	// public void serviceExecution(){}
	//
	// @Before("serviceExecution()")
	// public void setDynamicDataSource(JoinPoint jp) {
	// System.out.println("访问类名："+jp.getTarget().getClass().getPackage().getName());
	// logger.info("当前数据源----------------------------------"
	// +DbContextHolder.getDbType());
	// if
	// ("com.powerbridge.bssp.parameter.service.impl".equals(jp.getTarget().getClass().getPackage().getName())){
	// DbContextHolder.setDbType(DBTypeEnum.two);
	// } else {
	// DbContextHolder.setDbType(DBTypeEnum.one);
	// }
	// logger.info("新数据源----------------------------------"
	// +DbContextHolder.getDbType());
	//
	//// for(Object o : jp.getArgs()) {
	//// DbContextHolder.setDbType(DBTypeEnum.two);
	//// //处理具体的逻辑 ，根据具体的境况CustomerContextHolder.setCustomerType()选取DataSource
	//// }
	// }
}
