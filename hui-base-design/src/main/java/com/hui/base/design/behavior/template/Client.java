package com.hui.base.design.behavior.template;

import com.hui.base.design.behavior.template.concrete.SuperMan;
import com.hui.base.design.behavior.template.concrete.SuperManWithHook;
import com.hui.base.design.behavior.template.template.BaseTemplate;

/**
 * <b><code>Client</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 11:35.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("===============场景1======================");
        BaseTemplate template = new SuperMan();
        template.beatMonster();


        System.out.println("===================场景2==================");
        BaseTemplate template1 = new SuperManWithHook();
        ((SuperManWithHook) template1).setChoose("n");
        template1.beatMonster();
    }
}
