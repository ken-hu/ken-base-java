package com.hui.base.design.create.simplefactory;

import com.hui.base.design.create.simplefactory.factory.DrinkStore;

/**
 * <b><code>Client</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 21:08.
 *
 * @author HuWeihui
 */
public class Client {
    public static void main(String[] args) {
        DrinkStore drinkStore = new DrinkStore();
        drinkStore.orderDrink("coffee");
    }
}
