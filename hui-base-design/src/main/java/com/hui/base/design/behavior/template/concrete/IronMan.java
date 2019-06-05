package com.hui.base.design.behavior.template.concrete;

import com.hui.base.design.behavior.template.template.BaseTemplate;

/**
 * <b><code>IronMan</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 11:31.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class IronMan extends BaseTemplate {
    @Override
    public void transform() {
        System.out.println("穿上盔甲-------变身");
    }

    @Override
    public void chooseNirvana() {
        System.out.println("选择必杀技--------激光镭射");
    }
}
