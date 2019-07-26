package com.hui.base.design.structure.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * <b><code>BridgeDemo</code></b>
 * <p/>
 * Description:
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
