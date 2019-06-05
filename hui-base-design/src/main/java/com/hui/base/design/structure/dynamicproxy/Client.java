package com.hui.base.design.structure.dynamicproxy;

import com.hui.base.design.structure.dynamicproxy.proxyfactory.ProxyFactory;
import com.hui.base.design.structure.staticsproxy.subject.Subject;
import com.hui.base.design.structure.staticsproxy.subject.WebSubject;

/**
 * <b><code>Client</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/20 21:27.
 *
 * @author HuWeihui
 */
public class Client {
    public static void main(String[] args) {
        Subject webSubject = new WebSubject();
        System.out.println("原始类型："+ webSubject.getClass());

        Subject proxy = (Subject) new ProxyFactory(webSubject).getProxyInstance();

        proxy.access();
    }
}
