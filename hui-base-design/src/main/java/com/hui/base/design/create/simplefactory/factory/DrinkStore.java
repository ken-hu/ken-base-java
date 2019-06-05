package com.hui.base.design.create.simplefactory.factory;

import com.hui.base.design.create.simplefactory.product.Coffee;
import com.hui.base.design.create.simplefactory.product.Drink;
import com.hui.base.design.create.simplefactory.product.Milk;
import com.hui.base.design.create.simplefactory.product.Tea;

/**
 * <b><code>DrinkStore</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 20:33.
 *
 * @author HuWeihui
 */
public class DrinkStore {

    public Drink orderDrink(String type) {
        Drink drink = createDrink(type);

        drink.prepare();
        drink.make();
        drink.pack();

        return drink;
    }

    private Drink createDrink(String type) {
        Drink drink = null;

        switch (type) {
            case "tea":
                drink = new Tea();
                break;
            case "coffee":
                drink = new Coffee();
                break;
            case "milk":
                drink = new Milk();
                break;
            default:
                break;
        }
        return drink;
    }

}

