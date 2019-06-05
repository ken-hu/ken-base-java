package com.hui.base.design.create.abstractfactory.factory;


import com.hui.base.design.create.abstractfactory.product.AbstractDrink;

/**
 * <b><code>AbstractDrinkStore</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 22:47.
 *
 * @author HuWeihui
 */
public abstract class AbstractDrinkStore {

    public AbstractDrink orderDrink(String type){
        AbstractDrink drink = createDrink(type);
        drink.prepare();
        drink.make();
        drink.pack();
        return drink;
    }

    protected abstract AbstractDrink createDrink(String type);
}
