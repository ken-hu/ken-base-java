package com.hui.base.design.structure.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * <b><code>BridgeDemo</code></b>
 * <p/>
 * Description: 桥接模式
 *
 * ## 个人小解读
 *
 *
 * ## 角色
 * 1. 抽象化（Abstraction）角色：定义抽象类，并包含一个对实现化对象的引用。
 * 2. 扩展抽象化（Refined    Abstraction）角色：是抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法。
 * 3. 实现化（Implementor）角色：定义实现化角色的接口，供扩展抽象化角色调用。
 * 4. 具体实现化（Concrete Implementor）角色：给出实现化角色接口的具体实现。
 *
 * ## 优点
 * 1. 由于抽象与实现分离，所以扩展能力强；
 * 2. 其实现细节对客户透明。
 *
 * ## 缺点
 * 1. 由于聚合关系建立在抽象层，要求开发者针对抽象化进行设计与编程，这增加了系统的理解与设计难度。
 *
 * ## 应用场景：
 * 1. 当一个类存在两个独立变化的维度，且这两个维度都需要进行扩展时。
 * 2. 当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时。
 * 3. 当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。
 *
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 21:33.
 *
 * @author HuWeihui
 */
@Slf4j
public class BridgeDemo {

    //Client
    public static void main(String[] args) {
        Implementor implementor = new ConcreteImplementorA();
        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.operation();
    }

    /**
     * 实现化角色
     * 举例 文字
     */
    static abstract class Abstraction {
        protected Implementor implementor;

        protected Abstraction(Implementor implementor) {
            this.implementor = implementor;
        }

        public abstract void operation();
    }

    /**
     * 扩展化角色
     * 举例 正楷
     */
    static class RefinedAbstraction extends Abstraction {

        protected RefinedAbstraction(Implementor implementor) {
            super(implementor);
        }

        @Override
        public void operation() {
            log.info("字体-> 正楷");
            implementor.operation();
        }
    }

    /**
     * 抽象是实现化角色
     * 字体颜色
     */
    interface Implementor {
        void operation();
    }

    /**
     * 具体 实现化角色A
     * 绿色
     */
    static class ConcreteImplementorA implements Implementor {

        @Override
        public void operation() {
            log.info("字体 + 绿色");
        }
    }

    /**
     * 具体 实现化角色B
     * 红色
     */
    static class ConcreteImplementorB implements Implementor {

        @Override
        public void operation() {
            log.info("+红色");
        }
    }


}
