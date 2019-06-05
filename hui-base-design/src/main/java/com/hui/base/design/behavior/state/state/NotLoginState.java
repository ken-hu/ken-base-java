package com.hui.base.design.behavior.state.state;

import com.hui.base.design.behavior.state.context.LoginWindow;

/**
 * <b><code>NotLoginState</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 17:33.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class NotLoginState implements UserState{
    private LoginWindow loginWindow;

    public NotLoginState (LoginWindow loginWindow){
        this.loginWindow = loginWindow;
    }

    @Override
    public void inputInfo() {
        if (loginWindow.getAccount().equals("") || loginWindow.getPassword().equals("") || loginWindow.getAccount() == null || loginWindow.getPassword() == null){
            System.out.println("未输入账号或者密码");
            loginWindow.setUserState(loginWindow.getFailState());
        }else {
            System.out.println("输入了账号密码");
            loginWindow.setUserState(loginWindow.getSuccessState());
        }
    }

    @Override
    public void userLogin() {
        System.out.println("未触发登录");
    }

    @Override
    public void jumpPage() {
        System.out.println("未跳转到用户页");
    }
}
