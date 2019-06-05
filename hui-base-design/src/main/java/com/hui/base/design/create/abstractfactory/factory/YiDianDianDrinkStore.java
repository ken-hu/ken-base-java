package com.hui.base.design.create.abstractfactory.factory;

import com.hui.base.design.create.abstractfactory.product.AbstractDrink;
import com.hui.base.design.create.abstractfactory.product.YiDianDianCoffee;
import com.hui.base.design.create.abstractfactory.product.YiDianDianMilk;
import com.hui.base.design.create.abstractfactory.product.YiDianDianTea;

/**
 * <b><code>YiDianDianDrinkStore</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 23:42.
 *
 * @author HuWeihui
 */
public class YiDianDianDrinkStore extends AbstractDrinkStore{
    @Override
    protected AbstractDrink createDrink(String type) {
        AbstractDrink drink = null;

        switch (type) {
            case "tea":
                drink = new YiDianDianTea();
                break;
            case "coffee":
                drink = new YiDianDianCoffee();
                break;
            case "milk":
                drink = new YiDianDianMilk();
                break;
            default:
                break;
        }
        return drink;
    }
}
