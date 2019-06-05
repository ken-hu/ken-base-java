package com.hui.base.design.create.simplefactory.product;

/**
 * <b><code>Coffee</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 20:58.
 *
 * @author HuWeihui
 */
public class Coffee implements Drink{
    @Override
    public void prepare() {
        System.out.println("开始准备咖啡的材料");
    }

    @Override
    public void make() {
        System.out.println("开始生产咖啡");
    }

    @Override
    public void pack() {
        System.out.println("开始统一包装");
    }
}
