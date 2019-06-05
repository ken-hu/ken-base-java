package com.hui.base.design.behavior.observer.observer;

/**
 * <b><code>WeChatAccountA</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 14:40.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class WeChatAccountA implements WeChatAccount {
    private String msg;

    @Override
    public void updateMsg(String msg) {
        this.msg = msg;
        read();
    }

    public void read(){
        System.out.println("收到公众号消息："+ msg);
    }
}
