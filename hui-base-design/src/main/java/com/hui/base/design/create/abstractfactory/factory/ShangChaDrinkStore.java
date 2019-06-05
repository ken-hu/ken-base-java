package com.hui.base.design.create.abstractfactory.factory;

import com.hui.base.design.create.abstractfactory.product.AbstractDrink;
import com.hui.base.design.create.abstractfactory.product.ShangChaCoffee;
import com.hui.base.design.create.abstractfactory.product.ShangChaMilk;
import com.hui.base.design.create.abstractfactory.product.ShangChaTea;

/**
 * <b><code>ShangChaDrinkStore</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 23:21.
 *
 * @author HuWeihui
 */
public class ShangChaDrinkStore extends AbstractDrinkStore{
    @Override
    protected AbstractDrink createDrink(String type) {
        AbstractDrink drink = null;

        switch (type) {
            case "tea":
                drink = new ShangChaTea();
                break;
            case "coffee":
                drink = new ShangChaCoffee();
                break;
            case "milk":
                drink = new ShangChaMilk();
                break;
            default:
                break;
        }
        return drink;
    }
}
