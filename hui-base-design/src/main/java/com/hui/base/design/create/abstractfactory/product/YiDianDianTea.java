package com.hui.base.design.create.abstractfactory.product;

/**
 * <b><code>YiDianDianTea</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 23:57.
 *
 * @author HuWeihui
 */
public class YiDianDianTea extends AbstractDrink{
    @Override
    public void prepare() {
        System.out.println("**一点点** 开始准备 **茶** 材料 ");
    }

    @Override
    public void make() {
        System.out.println("**一点点** 开始制作 **茶** ");
    }

}
