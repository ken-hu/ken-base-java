package com.hui.base.design.create.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * <b><code>Singleton</code></b>
 * <p/>
 * Description 单例模式
 *
 * 下面DEMO为简单的单例模式，懒汉模式，且不能保证线程安全
 *
 * ## 个人小解读
 * 单例模式，顾名思义只有一个实例。我们要做的事情就是保证只创建一次并且只能访问同一个实例。
 *
 * ## 角色
 * 1. 单例类：包含一个实例且能自行创建这个实例的类。
 * 2. 访问类：使用单例的类。
 *
 * ## 特点
 * 1. 单例类只有一个实例对象；
 * 2. 该单例对象必须由单例类自行创建；
 * 3. 单例类对外提供一个访问该单例的全局访问点；
 *
 * ## 优点
 * 1. 节省内存，并加快对象访问速度
 * 2. 避免频繁的创建销毁对象, 提高性能
 * 3. 避免对共享资源的多重占用
 * ## 缺点
 * 1. 扩展比较困难
 * 2. 如果实例化后的对象长期不利用,系统将默认为垃圾进行回收,造成对象状态丢失
 *
 * ## 应用场景：
 * 1. 外部资源：每台计算机有若干个打印机，但只能有一个PrinterSpooler，以避免两个打印作业同时输出到打印机。内部资源：大多数软件都有一个（或多个）属性文件存放系统配置，这样的系统应该有一个对象管理这些属性文件
 * 2. Windows的Task Manager（任务管理器）就是很典型的单例模式（这个很熟悉吧），想想看，是不是呢，你能打开两个windows task manager吗？ 不信你自己试试看哦~
 * 3. windows的Recycle Bin（回收站）也是典型的单例应用。在整个系统运行过程中，回收站一直维护着仅有的一个实例。
 * 4. 网站的计数器，一般也是采用单例模式实现，否则难以同步。
 * 5. 应用程序的日志应用，一般都何用单例模式实现，这一般是由于共享的日志文件一直处于打开状态，因为只能有一个实例去操作，否则内容不好追加。
 * 6. Web应用的配置对象的读取，一般也应用单例模式，这个是由于配置文件是共享的资源。
 * 7. 数据库连接池的设计一般也是采用单例模式，因为数据库连接是一种数据库资源。数据库软件系统中使用数据库连接池，主要是节省打开或者关闭数据库连接所引起的效率损耗，这种效率上的损耗还是非常昂贵的，因为何用单例模式来维护，就可以大大降低这种损耗。
 * 8. 多线程的线程池的设计一般也是采用单例模式，这是由于线程池要方便对池中的线程进行控制。
 * 9. 操作系统的文件系统，也是大的单例模式实现的具体例子，一个操作系统只能有一个文件系统。
 * 10. HttpApplication 也是单位例的典型应用。熟悉ASP.Net(IIS)的整个请求生命周期的人应该知道HttpApplication也是单例模式，所有的HttpModule都共享一个HttpApplication实例.
 *
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 17:38.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
@Slf4j
public class Singleton {

    //客户端
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        log.info("不信？你看看，是不是同一个如花 {}" ,(instance == instance2));

        instance.result();

    }

    private static Singleton singleton;


    /**
     * 不允许外部进行构建
     */
    private Singleton() {
    }


    /**
     * 通过getInstance方法做初始化
     * @return
     */
    private static Singleton getInstance() {
        log.info("staring to new girfriend ...");

        if (singleton == null) {
            singleton = new Singleton();
            log.info("new girfriend success ...");

        }else {
            log.info("女朋友只有一个，你还想new个新的？！ 只能给你同一个女朋友");
        }

        return singleton;
    }

    /**
     * just a joke
     */
    public void result(){
        log.info("你的单例女朋友：如花 出来了");
    }

}
