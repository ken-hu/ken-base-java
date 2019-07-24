package com.hui.base.design.structure.decorator;

/**
 * <b><code>DecoratorDemo</code></b>
 * <p/>
 * Description: 装饰者模式
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 21:47.
 *
 * @author HuWeihui
 */
public class DecoratorDemo {

    // 客户端
    public static void main(String[] args) {
        // 需要装饰的组件
        Component component = new ConcreteComponent();

        // 给组件 装饰点东西 - 1
        Component decoratorA = new ConcreteDecoratorA(component);
        decoratorA.operation();

        // 给组件 装饰点东西 - 2
        ConcreteDecoratorB decoratorB = new ConcreteDecoratorB(component);
        decoratorB.operation();
    }

    /**
     * 抽象组件
     */
    interface Component{
        void operation();
    }


    /**
     * 具体组件
     */
    static class ConcreteComponent implements Component{

        @Override
        public void operation() {
            System.out.println("具体组件：圣诞树");
        }
    }

    /**
     * 装饰者
     */
    static class Decorator implements Component{

        private Component component;

        public Decorator(Component component) {
            this.component = component;
        }

        @Override
        public void operation() {
            component.operation();
        }
    }

    /**
     * 具体装饰
     */
    static class ConcreteDecoratorA extends Decorator{

        public ConcreteDecoratorA(Component component) {
            super(component);
        }

        @Override
        public void operation() {
            super.operation();
            System.out.println("给圣诞树上 + 糖果");
        }
    }

    /**
     * 具体装饰
     */
    static class ConcreteDecoratorB extends Decorator{

        public ConcreteDecoratorB(Component component) {
            super(component);
        }

        @Override
        public void operation() {
            super.operation();
            System.out.println("给圣诞树上 + 礼物");
        }
    }


}
