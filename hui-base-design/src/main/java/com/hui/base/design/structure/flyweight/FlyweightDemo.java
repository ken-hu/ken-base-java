package com.hui.base.design.structure.flyweight;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <b><code>FlyweightDemo</code></b>
 * <p/>
 * Description: 享元模式
 *
 * ## 个人小解读
 *
 *
 * ## 角色
 * 1. 抽象享元角色（Flyweight）:是所有的具体享元类的基类，为具体享元规范需要实现的公共接口，非享元的外部状态以参数的形式通过方法传入。
 * 2. 具体享元（Concrete Flyweight）角色：实现抽象享元角色中所规定的接口。
 * 3. 非享元（Unsharable Flyweight)角色：是不可以共享的外部状态，它以参数的形式注入具体享元的相关方法中。
 * 4. 享元工厂（Flyweight Factory）角色：负责创建和管理享元角色。当客户对象请求一个享元对象时，享元工厂检査系统中是否存在符合要求的享元对象，如果存在则提供给客户；如果不存在的话，则创建一个新的享元对象。
 *
 * ## 优点
 * 1. 提供了一种可以恢复状态的机制。当用户需要时能够比较方便地将数据恢复到某个历史的状态。
 * 2. 实现了内部状态的封装。除了创建它的发起人之外，其他对象都不能够访问这些状态信息。
 * 3. 简化了发起人类。发起人不需要管理和保存其内部状态的各个备份，所有状态信息都保存在备忘录中，并由管理者进行管理，这符合单一职责原则。
 *
 * ## 缺点
 * 1. 为了使对象可以共享，需要将一些不能共享的状态外部化，这将增加程序的复杂性。
 * 2. 读取享元模式的外部状态会使得运行时间稍微变长。
 *
 * ## 应用场景：
 * 1. 系统中存在大量相同或相似的对象，这些对象耗费大量的内存资源。
 * 2. 大部分的对象可以按照内部状态进行分组，且可将不同部分外部化，这样每一个组只需保存一个内部状态。
 * 3. 由于享元模式需要额外维护一个保存享元的数据结构，所以应当在有足够多的享元实例时才值得使用享元模式。
 *
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
