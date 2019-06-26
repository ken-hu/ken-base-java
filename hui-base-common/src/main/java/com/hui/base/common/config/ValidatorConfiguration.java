package com.hui.base.common.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * <b><code>ValidatorConfiguration</code></b>
 * <p/>
 * Description: GET方法参数校验可以使用
 *  JSR和Hibernate validator的校验只能对Object的属性进行校验
 *  不能对单个的参数进行校验
 *  spring 在此基础上进行了扩展
 *  添加了MethodValidationPostProcessor拦截器
 *  可以实现对方法参数的校验
 * <p/>
 * <b>Creation Time:</b> 2018/12/20 16:04.
 *
 * @author Hu weihui
 */
@Configuration
public class ValidatorConfiguration {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        //设置validator模式为快速失败返回
        postProcessor.setValidator(validator());
        return postProcessor;
    }

    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}
