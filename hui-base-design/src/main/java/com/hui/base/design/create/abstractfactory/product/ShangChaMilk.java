package com.hui.base.design.create.abstractfactory.product;

/**
 * <b><code>ShangChaMilk</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 23:58.
 *
 * @author HuWeihui
 */
public class ShangChaMilk extends AbstractDrink{
    @Override
    public void prepare() {
        System.out.println("**上茶** 开始准备 **牛奶** 材料 ");
    }

    @Override
    public void make() {
        System.out.println("**上茶** 开始制作 **牛奶**  ");
    }


}
