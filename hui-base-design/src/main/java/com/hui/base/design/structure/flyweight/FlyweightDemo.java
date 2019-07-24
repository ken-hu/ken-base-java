package com.hui.base.design.structure.flyweight;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <b><code>FlyweightDemo</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 23:17.
 *
 * @author HuWeihui
 */
public class FlyweightDemo {
    //客户端
    public static void main(String[] args) {

        FlyweightFactory factory = new FlyweightFactory();

        factory.addFlyweight("a",new ConcreteFlyweightA("a"));
        factory.addFlyweight("a1",new ConcreteFlyweightA("a1"));
        factory.addFlyweight("b",new ConcreteFlyweightB("b"));
        factory.addFlyweight("b1",new ConcreteFlyweightB("b1"));


        Flyweight a = factory.getFlyweight("a");
        Flyweight a1 = factory.getFlyweight("a1");
        Flyweight b = factory.getFlyweight("b");
        Flyweight b1 = factory.getFlyweight("b1");

        a.operation(new UnsharedConcreteFlyweight(""));
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
    static class ConcreteFlyweightA implements Flyweight {
        private String key;

        public ConcreteFlyweightA(String key) {
            this.key = key;
        }

        @Override
        public void operation(UnsharedConcreteFlyweight unsharedConcreteFlyweight) {

        }
    }

    /**
     * 具体享元角色B
     */
    static class ConcreteFlyweightB implements Flyweight {
        private String key;


        public ConcreteFlyweightB(String key) {
            this.key = key;
        }

        @Override
        public void operation(UnsharedConcreteFlyweight unsharedConcreteFlyweight) {

        }
    }

    /**
     * 享元角色工厂
     */
    static class FlyweightFactory {
        private Map<String, Flyweight> flyweightMap = new HashMap<>();

        public Flyweight getFlyweight(String key) {

            Flyweight flyweight = (Flyweight) flyweightMap.get(key);
            if (null != flyweight) {
                System.out.println("具体享元" + key + "已经存在，被成功获取！");
            }
            return flyweightMap.get(key);
        }

        public void addFlyweight(String key, Flyweight flyweight) {
            flyweightMap.put(key, flyweight);
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
