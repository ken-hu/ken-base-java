package com.hui.base.thread.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b><code>ThreadSafe</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/4/30 14:25.
 *
 * @author Hu-Weihui
 * @since hui-base-thread ${PROJECT_VERSION}
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
    String value() default "";
}
