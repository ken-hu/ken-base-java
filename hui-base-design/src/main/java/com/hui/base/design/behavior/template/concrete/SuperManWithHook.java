package com.hui.base.design.behavior.template.concrete;

import com.hui.base.design.behavior.template.template.BaseTemplate;

/**
 * <b><code>SuperManWithHook</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 11:54.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class SuperManWithHook extends BaseTemplate {
    private String isSuccess = "y";

    @Override
    public void transform() {
        System.out.println("套上红色内裤---------变身");
    }

    @Override
    public void chooseNirvana() {
        System.out.println("选择必杀技-------------一拳必杀");
    }

    @Override
    public boolean successTransformHook() {
        if (isSuccess.startsWith("y")){
            System.out.println("成功变身");
            return true;
        }else {
            System.out.println("变身失败........还是赶紧跑路吧");
            return false;
        }
    }

    public void setChoose(String isSuccess){
        this.isSuccess = isSuccess;
    }
}
