package com.hui.base.design.create.singleton;

/**
 * <b><code>Singleton</code></b>
 * <p/>
 * Description
 * 简单的单例模式，不能保证线程安全
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 17:38.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class Singleton {
    private static Singleton singleton;

    private Singleton() {
    }

    private Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
