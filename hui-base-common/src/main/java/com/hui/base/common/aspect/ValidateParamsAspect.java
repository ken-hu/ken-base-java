package com.hui.base.common.aspect;

import com.hui.base.common.annotations.ValidateParams;
import com.hui.base.common.response.ResultMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * <b><code>ParameterCheck</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/22 12:12.
 *
 * @author Hu-Weihui
 * @since hui-core-component ${PROJECT_VERSION}
 */
@Component
@Aspect
@Slf4j
public class ValidateParamsAspect {

    @Autowired
    private Validator validator;

    @Pointcut(value = "execution(public * com.hui.core.api.controller..*.*(..))")
    public void bindResult() {
    }

    @Around(value = "bindResult()")
    public Object bindResultAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ValidateParams validateParams = AnnotationUtils.findAnnotation(method, ValidateParams.class);
        if (validateParams == null) {
            return joinPoint.proceed();
        }
        Object[] args = joinPoint.getArgs();
        if (args.length < 1) {
            return joinPoint.proceed();
        }
        for (Object arg : args) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(arg);
            if (constraintViolations.size() < 1) {
                return joinPoint.proceed();
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                stringBuilder.append(constraintViolation.getMessage()).append(",");
            }
            return ResultMapper.illegalParams(stringBuilder.toString());
        }
        return joinPoint.proceed();
    }

}
