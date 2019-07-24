package com.hui.base.design.structure.proxy;

/**
 * <b><code>StaticProxy</code></b>
 * <p/>
 * Description 静态代理DEMO
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 15:14.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class StaticProxyDemo {

    // 客户端
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
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
    static class RealSubject implements Subject{

        @Override
        public void request() {
            System.out.println("Nginx代理跳转...........");
        }
    }


    /**
     * 代理
     */
    static class Proxy implements Subject{

        private RealSubject subject = new RealSubject();

        @Override
        public void request() {
            System.out.println("启用Nginx跳转");
            subject.request();
        }
    }
}
