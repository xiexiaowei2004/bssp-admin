package com.powerbridge.bssp.common.advice;

import com.powerbridge.bssp.common.util.BeanKit;
import com.powerbridge.bssp.common.util.SingletonLoginUtils;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.regex.Pattern;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：WhenDidInterceptor
 * 类描述：公共字段填充控制器
 * 创建人：danagao
 * 创建时间：2017/5/21 10:31
 *
 * @version 1.0
 */


/**
 * mybatis日期处理拦截
 */
//method只定义了update，query，flushStatements，commit，rollback，createCacheKey，isCached，clearLocalCache，deferLoad，getTransaction，close，isClosed这几个方法
@Intercepts({
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class}),
//        @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class })
})
public class WhenDidInterceptor implements Interceptor {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private Properties properties;

    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object beanObject = args[1];
        Object target = invocation.getTarget();

        Configuration configuration = mappedStatement.getConfiguration();
        StatementHandler handler = configuration.newStatementHandler((Executor) target, mappedStatement, beanObject, RowBounds.DEFAULT, null, null);
        String methodName = invocation.getMethod().getName();

        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();


        //insert,update,delete均为update
        if ("update".equals(methodName)) {

            String now = DateUtil.now();
            String userId = "0";
            String userName = "zzzzzz";
            String inputCopNo = "";
            String inputCopName = "";
            try {
                userId = String.valueOf(SingletonLoginUtils.getSystemUserId());//当前登录人ID
                userName = SingletonLoginUtils.getSystemUserName();//当前登录人名称
                inputCopNo = SingletonLoginUtils.getSystemUser().getInputCopNo();//当前登录人企业ID
                inputCopName = SingletonLoginUtils.getSystemUser().getInputCopName();//当前登录人企业名称
            } catch (Exception e) {
//                e.printStackTrace();
            }

            if (isInsert(sql)) {//新增时
                // 创建时间
                setProperties(beanObject, "createTime", now);

                // 创建人ID
                setProperties(beanObject, "createBy", userId);

                // 创建人名称
                setProperties(beanObject, "createName", userName);

                // 申报（录入）人代码
                setProperties(beanObject, "inputerCode", userId);

                // 申报（录入）人名称
                setProperties(beanObject, "inputerName", userName);

                // 申报（录入）企业代码
                setProperties(beanObject, "inputCopNo", inputCopNo);

                //申报（录入）企业名称
                setProperties(beanObject, "inputCopName", inputCopName);

                //录入日期
                setProperties(beanObject, "decTime", now);

                /*-------------------------------------新增时也加入修改人修改时间---------------------------------*/

                // 修改时间
                setProperties(beanObject, "updateTime", now);

                // 修改人ID
                setProperties(beanObject, "updateBy", userId);

                // 修改人名称
                setProperties(beanObject, "updateName", userName);


            } else if (isUpdate(sql)) {//修改时

                // 修改时间
                setUpdProperties(beanObject, "updateTime", now);

                // 修改人ID
                setUpdProperties(beanObject, "updateBy", userId);

                // 修改人名称
                setUpdProperties(beanObject, "updateName", userName);
                //录入日期
                setUpdProperties(beanObject, "decTime", now);
            }
        }
        return invocation.proceed();
    }


    private void setProperties(Object beanObject, String propertyName, Object propertyValue) {
        // 判断属性是否存在并赋值
        if (BeanKit.propertyExists(beanObject, propertyName)) {
            Object object = BeanKit.getProperty(beanObject, propertyName);
            if (null == object) {
                BeanKit.setProperty(beanObject, propertyName, propertyValue);
            }
        }
    }


    private void setUpdProperties(Object beanObject, String propertyName, Object propertyValue) {
        // 判断属性是否存在并赋值
        if (BeanKit.propertyExists(beanObject, propertyName)) {
            BeanKit.setProperty(beanObject, propertyName, propertyValue);
        }
    }


    Pattern insertPattern = Pattern.compile("\\s*insert", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    protected boolean isInsert(String sql) {
        return insertPattern.matcher(sql).find();
    }

    Pattern updatePattern = Pattern.compile("\\s*update", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    protected boolean isUpdate(String sql) {
        return updatePattern.matcher(sql).find();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}