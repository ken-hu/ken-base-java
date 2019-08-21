package com.hui.base.thread.springboot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * <b><code>SpringbootThreadService</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/8/21 14:54.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
@Service
@Slf4j
public class SpringbootThreadService {
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void testThread() throws InterruptedException {
        int countNum = 10000;
        final CountDownLatch countDownLatch = new CountDownLatch(countNum);
        for (int i = 0; i < countNum; i++) {
            threadPoolTaskExecutor.execute(new Counter(i,countDownLatch));
        }
        countDownLatch.await();
    }

    @AllArgsConstructor
    class Counter implements Runnable{
        private Integer count;
        private CountDownLatch countDownLatch;

        @Override
        public void run() {
            log.info("my - count {}" , count);
            log.info("latch - count {}",countDownLatch.getCount());
            countDownLatch.countDown();
        }
    }
}
