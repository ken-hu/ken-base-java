package com.hui.base.design.structure.staticsproxy.subject;

/**
 * <b><code>WebSubject</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/20 21:29.
 *
 * @author HuWeihui
 */
public class WebSubject implements Subject{
    @Override
    public void access() {
        System.out.println("WEB服务器被访问");
    }
}
