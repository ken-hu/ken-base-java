package com.hui.base.design.create.singleton;

/**
 * <b><code>DoubleCheckSingleton</code></b>
 * <p/>
 * Description 懒汉模式（双重检查）
 * 双重检查，减少synchronized的性能损耗
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 17:51.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class DoubleCheckSingleton {
    /**
     * volatile（java5）：可以保证多线程下的可见性;
     * 读volatile：每当子线程某一语句要用到volatile变量时，都会从主线程重新拷贝一份，这样就保证子线程的会跟主线程的一致。
     * 写volatile: 每当子线程某一语句要写volatile变量时，都会在读完后同步到主线程去，这样就保证主线程的变量及时更新。
     */
    private volatile static DoubleCheckSingleton singleton;

    private DoubleCheckSingleton() {
    }

    private static DoubleCheckSingleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }

}
