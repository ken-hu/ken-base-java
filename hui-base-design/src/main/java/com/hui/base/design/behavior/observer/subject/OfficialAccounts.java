package com.hui.base.design.behavior.observer.subject;

import com.hui.base.design.behavior.observer.observer.WeChatAccount;

/**
 * <b><code>OfficialAccounts</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 14:17.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public interface OfficialAccounts {
    void registerObserver(WeChatAccount account);

    void removeObserver(WeChatAccount account);

    void notifyObservers();
}
