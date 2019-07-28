package com.hui.base.design.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * <b><code>DynamicProxy</code></b>
 * <p/>
 * Description 动态代理DEMO
 *
 * ## 个人小解读
 *
 * ## 角色
 * 1. 抽象主题（Subject）类：通过接口或抽象类声明真实主题和代理对象实现的业务方法。
 * 2. 真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
 * 3. 代理（Proxy）类：提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
 *
 * ## 优点
 * 1. 代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用；
 * 2. 代理对象可以扩展目标对象的功能；
 * 3. 代理模式能将客户端与目标对象分离，在一定程度上降低了系统的耦合度；
 *
 * ## 缺点
 * 1. 在客户端和目标对象之间增加一个代理对象，会造成请求处理速度变慢；
 * 2. 增加了系统的复杂度；
 *
 * ## 应用场景：
 * 1. 远程代理，这种方式通常是为了隐藏目标对象存在于不同地址空间的事实，方便客户端访问。例如，用户申请某些网盘空间时，会在用户的文件系统中建立一个虚拟的硬盘，用户访问虚拟硬盘时实际访问的是网盘空间。
 * 2. 虚拟代理，这种方式通常用于要创建的目标对象开销很大时。例如，下载一幅很大的图像需要很长时间，因某种计算比较复杂而短时间无法完成，这时可以先用小比例的虚拟代理替换真实的对象，消除用户对服务器慢的感觉。
 * 3. 安全代理，这种方式通常用于控制不同种类客户对真实对象的访问权限。
 * 4. 智能指引，主要用于调用目标对象时，代理附加一些额外的处理功能。例如，增加计算真实对象的引用次数的功能，这样当该对象没有被引用时，就可以自动释放它。
 * 5. 延迟加载，指为了提高系统的性能，延迟对目标的加载。例如，Hibernate 中就存在属性的延迟加载和关联表的延时加载。
 *
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 16:01.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class DynamicProxyDemo {
    //客户端
    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy(new RealSubject());
        Subject subject = (Subject) dynamicProxy.getProxyInstance();
        subject.request();
    }


    /**
     * 抽象主题
     */
    interface Subject{
        void request();
    }

    /**
     * 真实主题
     * 举例： web不直接访问，使用nginx代理访问
     */
    static class RealSubject implements Subject {

        @Override
        public void request() {
            System.out.println("Nginx代理跳转...........");
        }
    }


    /**
     * 代理
     */
    static class DynamicProxy  {

        private Object subject;

        public DynamicProxy(Object subject) {
            this.subject = subject;
        }

        public Object getProxyInstance (){
            return Proxy.newProxyInstance(
                    subject.getClass().getClassLoader(),
                    subject.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("动态代理访问真实Subject");

                            Object returnValue = method.invoke(subject, args);
                            return returnValue;
                        }
                    });
        }
    }

}
