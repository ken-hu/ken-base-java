package com.hui.base.design.behavior.state.state;

/**
 * <b><code>UserState</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 17:30.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public interface UserState {
    void inputInfo();

    void userLogin();

    void jumpPage();
}
