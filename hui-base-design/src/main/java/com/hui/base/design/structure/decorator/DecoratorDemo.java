package com.hui.base.design.structure.decorator;

/**
 * <b><code>DecoratorDemo</code></b>
 * <p/>
 * Description: 装饰者模式
 *
 * ## 个人小解读
 *
 * ## 角色
 * 1. 抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象。
 * 2. 具体构件（Concrete    Component）角色：实现抽象构件，通过装饰角色为其添加一些职责。
 * 3. 抽象装饰（Decorator）角色：继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
 * 4. 具体装饰（ConcreteDecorator）角色：实现抽象装饰的相关方法，并给具体构件对象添加附加的责任。
 *
 * ## 优点
 * 1. 采用装饰模式扩展对象的功能比采用继承方式更加灵活。
 * 2. 可以设计出多个不同的具体装饰类，创造出多个不同行为的组合。
 *
 * ## 缺点
 * 1. 装饰模式增加了许多子类，如果过度使用会使程序变得很复杂。
 *
 * ## 应用场景：
 * 1. 当需要给一个现有类添加附加职责，而又不能采用生成子类的方法进行扩充时。例如，该类被隐藏或者该类是终极类或者采用继承方式会产生大量的子类。
 * 2. 当需要通过对现有的一组基本功能进行排列组合而产生非常多的功能时，采用继承关系很难实现，而采用装饰模式却很好实现。
 * 3. 当对象的功能要求可以动态地添加，也可以再动态地撤销时。
 *
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
