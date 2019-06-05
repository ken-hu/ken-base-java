package com.hui.base.thread.count;

import com.hui.base.thread.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <b><code>com.hui.base.thread.ConcurrencyTest</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/4/30 14:28.
 *
 * @author Hu-Weihui
 * @since hui-base-thread ${PROJECT_VERSION}
 */
@Slf4j
@ThreadSafe
public class AtomicBooleanExample {

    //请求总数
    public static int clientTotal = 5000;

    //线程数
    public static int threadCount = 200;

    public static AtomicBoolean isHappend = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadCount);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    switchTest();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info(" is Happend ? {}",isHappend.get());
    }

    private static void switchTest(){
        if (isHappend.compareAndSet(false,true)){
            log.info("finish the work , change the switch");
        }
    }
}
