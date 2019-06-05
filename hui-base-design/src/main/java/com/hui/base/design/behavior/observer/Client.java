package com.hui.base.design.behavior.observer;

import com.hui.base.design.behavior.observer.observer.WeChatAccount;
import com.hui.base.design.behavior.observer.observer.WeChatAccountA;
import com.hui.base.design.behavior.observer.subject.HuaWeiOfficialAccount;
import com.hui.base.design.behavior.observer.subject.OfficialAccounts;

/**
 * <b><code>Client</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 15:09.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class Client {
    public static void main(String[] args) {
        OfficialAccounts officialAccounts = new HuaWeiOfficialAccount();

        WeChatAccount weChatAccount = new WeChatAccountA();

        officialAccounts.registerObserver(weChatAccount);

        ((HuaWeiOfficialAccount) officialAccounts).authorFinishArcitle("HUA WEI 发布新手机");
    }
}
