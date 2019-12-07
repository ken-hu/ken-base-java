package com.hui.base.springboot.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <b><code>WebLogAspect</code></b>
 * <p/>
 * Description: 日志收集切面
 * <p/>
 * <b>Creation Time:</b> 2018/12/4 22:14.
 *
 * @author HuWeihui
 */
@Component
@Aspect
//@Order(value = 1)
@Slf4j
public class WebLogAspect {

    /**
     * 定义controller层切面.
     *
     * @author HuWeihui
     * @since hui_project v1
     */
    @Pointcut(value = "execution(public * com.hui.base.springboot.controller..*.*(..))")
    public void webControllerLog(){}

    /**
     * 定义service层切面.
     *
     * @author HuWeihui
     * @since hui_project v1
     */
    @Pointcut(value = "execution(public * com.hui.base.springboot.server.service..*.*(..))")
    public void webServiceLog(){}


    /**
     * 前置通知 记录controller调用的日志.
     *
     * @param joinPoint the join point
     * @author HuWeihui
     * @since hui_project v1
     */
    @Before(value = "webControllerLog()")
    public void controllerBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("\n");
        log.info("-------------------Request Content-------------------");
        log.info("[Request IP] : {}",request.getRemoteAddr());
        log.info("[Request URL] : {} ",request.getRequestURL());
        log.info("[Request Method] : {}", request.getMethod());
        log.info("[Class Method] : {}",joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        if (args==null){
            log.info("[Args is NULL] : {}", "NULL");
        }else {
            log.info("[Class Method Args] : " + Arrays.toString(args));
        }
        log.info("-------------------Request Content-------------------");
        log.info("\n");
    }

    /**
     * 前置通知 记录service调用的日志.
     *
     * @param joinPoint the join point
     * @author HuWeihui
     * @since hui_project v1
     */
    @Before(value = "webServiceLog()")
    public void serviceBefore(JoinPoint joinPoint){
        log.info("\n");
        log.info("-------------------Service Content-------------------");
        log.info("[Service Class] : {}",joinPoint.getTarget().getClass().getName());
        log.info("[Service Method] : {}",joinPoint.getSignature().getName());
        log.info("[Service Params] : {}",joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        if (args==null){
            log.info("[Service Args] is NULL : {}", "NULL");
        }else {
            log.info("[Service Method] Args : " + Arrays.toString(args));
        }
        log.info("-------------------Service Content-------------------");
        log.info("\n");
    }


    /**
     * 异常通知 记录controller抛异常的信息.
     *
     * @param joinPoint the join point
     * @param error     the error
     * @author HuWeihui
     * @since hui_project v1
     */
    @AfterThrowing(value = "webControllerLog()",throwing = "error")
    public void controllerAfterThrowing(JoinPoint joinPoint,Throwable error){
        log.info("\n");
        log.error("-------------------Controller Throwable Content-------------------");
        log.error("[Controller Throwable Class] : {}",error.getClass().getName());
        log.error("[Controller Throwable Msg] : {}",error.getMessage());
        log.error("[Controller Throwable Method] : {}->{}()",joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (args==null){
            log.info("[Service Args] is NULL : {}", "NULL");
        }else {
            log.info("[Service Method] Args : " + Arrays.toString(args));
        }
        log.error("[Controller Throwable Method Args] : {}",joinPoint.getArgs());
        log.error("-------------------Controller Throwable Content-------------------");
        log.info("\n");
    }


}
