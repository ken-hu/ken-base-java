package com.hui.base.design.create.singleton;

/**
 * <b><code>SynchronizedSingleton</code></b>
 * <p/>
 * Description 懒汉模式
 * 整个方法进行加同步锁，能保证线程安全，但整个方法加入同步锁，性能较低
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 17:41.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class SynchronizedSingleton {
    private static SynchronizedSingleton singleton;

    private SynchronizedSingleton() {
    }

    private static synchronized SynchronizedSingleton getInstance() {
        if (singleton == null) {
            singleton = new SynchronizedSingleton();
        }
        return singleton;
    }
}
