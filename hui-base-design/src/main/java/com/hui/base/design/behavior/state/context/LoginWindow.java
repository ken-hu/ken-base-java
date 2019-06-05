package com.hui.base.design.behavior.state.context;

import com.hui.base.design.behavior.state.state.FailState;
import com.hui.base.design.behavior.state.state.NotLoginState;
import com.hui.base.design.behavior.state.state.SuccessState;
import com.hui.base.design.behavior.state.state.UserState;

/**
 * <b><code>LoginWindow</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 17:53.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class LoginWindow {
    UserState successState;
    UserState failState;
    UserState notLoginState;

    private String account;
    private String password;

    private UserState userState;

    public LoginWindow(String account,String password){
        this.account = account;
        this.password = password;

        successState = new SuccessState(this);
        failState = new FailState(this);
        notLoginState = new NotLoginState(this);

        this.userState = notLoginState;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public UserState getSuccessState() {
        return successState;
    }

    public void setSuccessState(UserState successState) {
        this.successState = successState;
    }

    public UserState getFailState() {
        return failState;
    }

    public void setFailState(UserState failState) {
        this.failState = failState;
    }

    public UserState getNotLoginState() {
        return notLoginState;
    }

    public void setNotLoginState(UserState notLoginState) {
        this.notLoginState = notLoginState;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserState getUserState() {
        return userState;
    }

    public void login(){
        userState.inputInfo();
        userState.userLogin();
        userState.jumpPage();
    };
}
