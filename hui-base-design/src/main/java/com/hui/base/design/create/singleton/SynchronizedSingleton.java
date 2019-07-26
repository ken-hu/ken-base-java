package com.hui.base.design.create.singleton;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class SynchronizedSingleton {
    //Client
    public static void main(String[] args) {
        SynchronizedSingleton instance = SynchronizedSingleton.getInstance();
        SynchronizedSingleton instance2 = SynchronizedSingleton.getInstance();

        log.info("不信？你看看，是不是同一个如花 {}" ,(instance == instance2));

        instance.result();
    }


    private static SynchronizedSingleton singleton;

    private SynchronizedSingleton() {
    }

    private static synchronized SynchronizedSingleton getInstance() {
        log.info("staring to new girfriend ...");

        if (singleton == null) {
            singleton = new SynchronizedSingleton();
            log.info("new girfriend success ...");
        }else {
            log.info("女朋友只有一个，你还想new个新的？！ 只能给你同一个女朋友");
        }

        return singleton;
    }

    public void result(){
        log.info("你的单例女朋友：如花 出来了");
    }

}
