package com.hui.base.design.structure.dynamicproxy.proxyfactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <b><code>ProxyFactory</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/20 21:41.
 *
 * @author HuWeihui
 */
public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理开始访问真实对象");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        return returnValue;
                    }
                }
        );
    }

}
