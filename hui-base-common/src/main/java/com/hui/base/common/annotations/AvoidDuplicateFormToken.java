package com.hui.base.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b><code>AvoidDuplicateSubmit</code></b>
 * <p/>
 * Description: 防止表单重复提交注解
 * <p/>
 * <b>Creation Time:</b> 2018/11/28 19:27.
 *
 * @author Hu weihui
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidDuplicateFormToken {

}
