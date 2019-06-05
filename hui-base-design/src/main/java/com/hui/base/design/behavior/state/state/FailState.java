package com.hui.base.design.behavior.state.state;

import com.hui.base.design.behavior.state.context.LoginWindow;

/**
 * <b><code>FailState</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 17:34.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class FailState implements UserState {

    private LoginWindow loginWindow;

    public FailState (LoginWindow loginWindow){
        this.loginWindow = loginWindow;
    }

    @Override
    public void inputInfo() {
        System.out.println("账号密码被清空，重新输入");
    }

    @Override
    public void userLogin() {
        System.out.println("等待登录校验");
    }

    @Override
    public void jumpPage() {
        System.out.println("账号或密码错误登录失败，跳转回到登录页面");
    }
}
