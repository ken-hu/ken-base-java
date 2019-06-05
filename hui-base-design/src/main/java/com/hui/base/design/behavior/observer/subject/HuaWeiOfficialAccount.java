package com.hui.base.design.behavior.observer.subject;

import com.hui.base.design.behavior.observer.observer.WeChatAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>HuaWeiOfficialAccount</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 14:36.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class HuaWeiOfficialAccount implements OfficialAccounts {
    private String msg;

    private List<WeChatAccount> accounts;

    public HuaWeiOfficialAccount(){
        accounts = new ArrayList<>();
    }
    @Override
    public void registerObserver(WeChatAccount account) {
        accounts.add(account);
    }

    @Override
    public void removeObserver(WeChatAccount account) {
        int index = accounts.indexOf(account);
        if (index > 0){
            accounts.remove(account);
        }
    }

    @Override
    public void notifyObservers() {
        for (WeChatAccount weChatAccount : accounts) {
            weChatAccount.updateMsg(msg);
        }
    }

    public void sendArcitle() {
        notifyObservers();
    }

    public void authorFinishArcitle(String msg) {
        this.msg = msg;
        sendArcitle();
    }

}
