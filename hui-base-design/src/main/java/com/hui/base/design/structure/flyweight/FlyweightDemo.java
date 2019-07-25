package com.hui.base.design.structure.flyweight;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <b><code>FlyweightDemo</code></b>
 * <p/>
 * Description: 享元模式
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 23:17.
 *
 * @author HuWeihui
 */
public class FlyweightDemo {
    //客户端
    public static void main(String[] args) {

        FlyweightFactory factory = new FlyweightFactory();

        Flyweight a = factory.getFlyweight("a");
        a.operation(new UnsharedConcreteFlyweight("access a x1"));

        Flyweight a1 = factory.getFlyweight("a");
        a1.operation(new UnsharedConcreteFlyweight("access a x2"));

        Flyweight a2 = factory.getFlyweight("a");
        a2.operation(new UnsharedConcreteFlyweight("access a x3"));

        Flyweight b = factory.getFlyweight("b");
        b.operation(new UnsharedConcreteFlyweight("access b!"));

    }

    /**
     * 抽象享元角色
     */
    interface Flyweight {
        void operation(UnsharedConcreteFlyweight unsharedConcreteFlyweight);
    }

    /**
     * 具体享元角色A
     */
    static class ConcreteFlyweight implements Flyweight {
        private String key;

        public ConcreteFlyweight(String key) {
            this.key = key;
        }

        @Override
        public void operation(UnsharedConcreteFlyweight unsharedConcreteFlyweight) {
            System.out.println("享元角色"+key + "被调用");
        }
    }

    /**
     * 享元角色工厂
     */
    static class FlyweightFactory {
        private Map<String, Flyweight> flyweightMap = new HashMap<>();

        public Flyweight getFlyweight(String key) {

            Flyweight flyweight = (Flyweight) flyweightMap.get(key);
            if (flyweightMap.containsKey(key)) {
                System.out.println("具体享元" + key + "已经存在，被成功获取！");
                return flyweightMap.get(key);
            }else {
                System.out.println("具体享元" + key + "被创建");

                Flyweight newFlyweight = new ConcreteFlyweight(key);
                flyweightMap.put(key, newFlyweight);
                return newFlyweight;
            }

        }

    }


    /**
     * 非享元角色
     */
    @Data
    static class UnsharedConcreteFlyweight {

        private String info;

        public UnsharedConcreteFlyweight(String info) {
            this.info = info;
        }
    }


}
