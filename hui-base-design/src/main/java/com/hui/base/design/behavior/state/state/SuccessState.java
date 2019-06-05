package com.hui.base.design.behavior.state.state;

import com.hui.base.design.behavior.state.context.LoginWindow;

/**
 * <b><code>SuccessState</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 17:34.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class SuccessState implements UserState{
    private LoginWindow loginWindow;

    public SuccessState (LoginWindow loginWindow){
        this.loginWindow = loginWindow;
    }
    @Override
    public void inputInfo() {
        System.out.println("以输入过账号密码");
    }

    @Override
    public void userLogin() {
        System.out.println("以进行登录操作");
        if (loginWindow.getAccount().equals("admin") && loginWindow.getPassword().equals("admin")) {
            System.out.println("账号密码正确");
        }else {
            loginWindow.setUserState(loginWindow.getFailState());
        }
    }

    @Override
    public void jumpPage() {
        System.out.println("登录成功，跳转到个人主页");
    }
}
