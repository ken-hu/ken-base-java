package com.hui.base.design.create.abstractfactory.product;

/**
 * <b><code>AbstractDrink</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 23:55.
 *
 * @author HuWeihui
 */
public abstract class AbstractDrink {
    public abstract void prepare();

    public abstract void make();

    public void pack(){
        System.out.println("采用统一包装");
        System.out.println();
    }
}
