package com.hui.base.thread.count;

import com.hui.base.thread.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

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
public class AtomicReferenceExample {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2); // 2
        count.compareAndSet(0, 1); // no
        count.compareAndSet(1, 3); // no
        count.compareAndSet(2, 4); // 4
        count.compareAndSet(3, 5); // no
        log.info("com.hui.base.thread.count:{}", count.get());
    }

}
