package com.hui.base.design.create.simplefactory.product;

/**
 * <b><code>Tea</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 20:51.
 *
 * @author HuWeihui
 */
public class Tea implements Drink{

    @Override
    public void prepare() {
        System.out.println("the Tea staring to prepare material");
    }

    @Override
    public void make() {
        System.out.println("the Tea staring to make");
    }

    @Override
    public void pack() {
        System.out.println("the Tea staring to pack");
    }
}
