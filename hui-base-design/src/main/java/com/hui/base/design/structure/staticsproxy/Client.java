package com.hui.base.design.structure.staticsproxy;

import com.hui.base.design.structure.staticsproxy.subject.NginxSubjectProxy;
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
        WebSubject webSubject = new WebSubject();

        NginxSubjectProxy nginxSubjectProxy = new NginxSubjectProxy(webSubject);

        nginxSubjectProxy.access();
    }
}
