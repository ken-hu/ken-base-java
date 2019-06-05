package com.hui.base.design.create.abstractfactory.product;

/**
 * <b><code>ShangChaTea</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 23:58.
 *
 * @author HuWeihui
 */
public class ShangChaTea extends AbstractDrink{
    @Override
    public void prepare() {
        System.out.println("**上茶** 开始准备 **茶** 材料 ");
    }

    @Override
    public void make() {
        System.out.println("**上茶** 开始制作 **茶**  ");
    }

    @Override
    public void pack(){
        System.out.println("采用 **上茶** 特别包装");
    }
}
