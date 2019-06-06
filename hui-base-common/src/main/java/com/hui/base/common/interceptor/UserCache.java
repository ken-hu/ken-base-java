package com.hui.base.common.interceptor;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <b><code>CacheConfig</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/11/29 19:38.
 *
 * @author Hu weihui
 */
@Configuration
public class UserCache {

    /**
     * 表单重复提交cache，有效期2秒.
     *
     * @return the cache
     * @author : Hu weihui
     * @since hui_project v1
     */
    @Bean
    public Cache<String,String> getUserCache(){
        return CacheBuilder.newBuilder().expireAfterAccess(2L,TimeUnit.SECONDS).build();
    }
}
