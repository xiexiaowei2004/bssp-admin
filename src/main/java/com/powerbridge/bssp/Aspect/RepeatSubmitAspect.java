package com.powerbridge.bssp.Aspect;

import com.powerbridge.bssp.common.constants.MessageConstants;
import com.powerbridge.bssp.common.dto.AjaxResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/21 0021.
 */
@Aspect
@Component
public class RepeatSubmitAspect {

    private final static Logger logger = LoggerFactory.getLogger(RepeatSubmitAspect.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Around("@annotation(repeatSubmitValidation)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint,RepeatSubmitValidation repeatSubmitValidation) throws Throwable {
        logger.info("**************** doAround start *******************");
        if (repeatSubmitValidation != null) {
            //方法入参列表
            Object[] args = proceedingJoinPoint.getArgs();
            //类名
            String className = proceedingJoinPoint.getTarget().getClass().getName();
            //方法名
            String methodName = proceedingJoinPoint.getSignature().getName();

            String fullName = className + "." + methodName;

            System.out.println(fullName);

            ValueOperations<String, Object> value = redisTemplate.opsForValue();
            //以类名加方法名组成的key,写入redis
            Long result = value.increment(fullName, 1);
            if(result > 1){
                logger.warn("**************** 重复提交 ****************");
                AjaxResult ajaxResult = new AjaxResult(MessageConstants.BSSP_STATUS_REPEAT_SUBMIT);
                return ajaxResult;
            }
            redisTemplate.expire(fullName, 3 ,TimeUnit.SECONDS);
        }
        logger.info("**************** doAround end *******************");

        return proceedingJoinPoint.proceed();
    }
}
