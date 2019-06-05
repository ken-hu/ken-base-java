package com.hui.base.design.behavior.template.template;

/**
 * <b><code>BaseTemplate</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 11:25.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public abstract class BaseTemplate {
    public void call() {
        System.out.println("大喊-------------变身");
    }

    public abstract void transform();

    public abstract void chooseNirvana();

    public void userNirvana() {
        System.out.println("使用-------------必杀技");
    }

    public boolean successTransformHook() {
        System.out.println("变身成功!!!");
        return true;
    }

    public final void beatMonster() {
        call();
        transform();
        if (successTransformHook()) {
            chooseNirvana();
            userNirvana();
        }
    }
}
