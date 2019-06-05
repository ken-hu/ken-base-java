package com.hui.base.design.create.abstractfactory;

import com.hui.base.design.create.abstractfactory.factory.AbstractDrinkStore;
import com.hui.base.design.create.abstractfactory.factory.ShangChaDrinkStore;
import com.hui.base.design.create.abstractfactory.factory.YiDianDianDrinkStore;

/**
 * <b><code>Client</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/2/12 23:58.
 *
 * @author HuWeihui
 */
public class Client {
    public static void main(String[] args) {
        AbstractDrinkStore yidiandianStore = new YiDianDianDrinkStore();
        AbstractDrinkStore shangchaStore = new ShangChaDrinkStore();

        System.out.println("=====客户想在一点点购买咖啡和牛奶======");
        yidiandianStore.orderDrink("coffee");
        yidiandianStore.orderDrink("milk");

        System.out.println();
        System.out.println("=====客户想在上茶购买咖啡和茶======");
        shangchaStore.orderDrink("coffee");
        shangchaStore.orderDrink("tea");
    }
}
