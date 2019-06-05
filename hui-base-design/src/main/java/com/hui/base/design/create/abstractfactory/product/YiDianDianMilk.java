package com.hui.base.design.create.abstractfactory.product;

/**
 * <b><code>YiDianDianMilk</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 23:57.
 *
 * @author HuWeihui
 */
public class YiDianDianMilk extends AbstractDrink{
    @Override
    public void prepare() {
        System.out.println("**一点点** 开始准备 **牛奶** 材料 ");
    }

    @Override
    public void make() {
        System.out.println("**一点点** 开始制作 **牛奶** ");
    }

    @Override
    public void pack() {
        System.out.println("采用 **一点点** 特别包装 ");
    }
}
