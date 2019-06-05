package com.hui.base.design.create.simplefactory.product;

/**
 * <b><code>Milk</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 20:57.
 *
 * @author HuWeihui
 */
public class Milk implements Drink{
    @Override
    public void prepare() {
        System.out.println("the Milk staring to prepare material");
    }

    @Override
    public void make() {
        System.out.println("the Milk staring to make");
    }

    @Override
    public void pack() {
        System.out.println("the Milk staring to pack");
    }
}
