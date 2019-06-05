package com.hui.base.design.behavior.state;

import com.hui.base.design.behavior.state.context.LoginWindow;

/**
 * <b><code>Client</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/14 9:41.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class Client {
    public static void main(String[] args) {
        LoginWindow loginWindow = new LoginWindow("admin", "admin");
        loginWindow.login();

        System.out.println("=======================");

        LoginWindow loginWindow1 = new LoginWindow("admin", "");
        loginWindow1.login();

        System.out.println("=======================");

        LoginWindow loginWindow2 = new LoginWindow("admin", "fds");
        loginWindow2.login();

    }
}
