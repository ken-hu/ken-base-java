package com.hui.base.design.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * <b><code>DynamicProxy</code></b>
 * <p/>
 * Description
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
