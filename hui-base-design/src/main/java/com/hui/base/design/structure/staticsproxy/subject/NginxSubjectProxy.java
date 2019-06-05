package com.hui.base.design.structure.staticsproxy.subject;

/**
 * <b><code>NginxSubjectProxy</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/20 21:28.
 *
 * @author HuWeihui
 */
public class NginxSubjectProxy implements Subject {
    private WebSubject webSubject;

    public NginxSubjectProxy(WebSubject webSubject) {
        this.webSubject = webSubject;
    }

    @Override
    public void access() {
        System.out.println("访问代理对象NGINX");
        System.out.println("NGINX代理去访问WEB服务器");
        webSubject.access();
    }




}
