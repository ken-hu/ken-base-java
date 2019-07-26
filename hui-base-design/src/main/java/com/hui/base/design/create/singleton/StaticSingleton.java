package com.hui.base.design.create.singleton;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class StaticSingleton {

    //客户端
    public static void main(String[] args) {
        StaticSingleton instance = StaticSingleton.getInstance();
        StaticSingleton instance2 = StaticSingleton.getInstance();

        log.info("好男人只有一个女朋友的，不信你看看是不是同一个如花 {} " ,(instance == instance2));

        instance.result();
    }

    private static StaticSingleton singleton = new StaticSingleton();

    private StaticSingleton() {
    }

    private static StaticSingleton getInstance(){
        log.info("给你找个女朋友");
        return singleton;
    }

    public void result(){
        log.info("你的单例女朋友：如花 出来了");
    }

}
