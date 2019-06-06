package com.hui.base.common.aspect;

import com.hui.base.common.exception.VerifyException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * <b><code>BindResultAspect</code></b>
 * <p/>
 * Description: Hibernate 参数校验 消息处理
 * <p/>
 * <b>Creation Time:</b> 2019/1/10 23:10.
 *
 * @author HuWeihui
 */
//@Component
//@Aspect
public class BindResultAspect {

    @Pointcut(value = "execution(public * com.hui.core.api.controller..*.*(..))")
    public void bindResult(){
    }

    @Around(value = "bindResult()")
    public Object bindResultAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (!(arg instanceof BindingResult)){
                continue;
            }
            BindingResult result = (BindingResult) arg;
            if (result.hasErrors()){
                List<ObjectError> allErrors = result.getAllErrors();
                StringBuilder errorStr = new StringBuilder();
                for (ObjectError fieldError : allErrors) {
                    System.out.println(fieldError.getDefaultMessage());
                    errorStr.append(fieldError.getDefaultMessage())
                            .append(",");
                }
                throw new VerifyException(errorStr.toString());
            }
        }
        return joinPoint.proceed();
    }
}
