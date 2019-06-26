package com.hui.base.thread.atomic;

import com.hui.base.thread.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

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
public class AtomicIntegerFieldUpdaterExample {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterExample> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterExample.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicIntegerFieldUpdaterExample example = new AtomicIntegerFieldUpdaterExample();

        if (updater.compareAndSet(example, 100, 120)) {
            log.info("update success 1, {}", example.getCount());
        }

        if (updater.compareAndSet(example, 100, 120)) {
            log.info("update success 2, {}", example.getCount());
        } else {
            log.info("update failed, {}", example.getCount());
        }
    }

}
