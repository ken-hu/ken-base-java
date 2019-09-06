package com.hui.base.thread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <b><code>ThreadPoolExcutorsConfig</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/8/21 14:52.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
@Configuration
public class ThreadPoolExcutorsConfig {
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();

        // 核心线程数
        poolTaskExecutor.setCorePoolSize(5);

        //最大线程数
        poolTaskExecutor.setMaxPoolSize(20);

        //空闲存活时间
        poolTaskExecutor.setKeepAliveSeconds(60);

        //队列数量
        poolTaskExecutor.setQueueCapacity(2000);

        //线程名字前缀
        poolTaskExecutor.setThreadNamePrefix("Hui-Thread-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        poolTaskExecutor.initialize();

        return poolTaskExecutor;
    }

}
