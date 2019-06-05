package com.hui.base.design.create.singleton;

/**
 * <b><code>StaticSingleton</code></b>
 * <p/>
 * Description 一般称为饿汉模式，比较急切。和懒汉模式需要的时候再new刚好相反
 * JVM加载的时候便实例化该单一实例。能保证线程安全。
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 17:43.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class StaticSingleton {
    private static StaticSingleton singleton = new StaticSingleton();

    private StaticSingleton() {
    }

    private static StaticSingleton getInstance(){
        return singleton;
    }
}
