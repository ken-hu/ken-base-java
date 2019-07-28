package com.hui.base.design.behavior.template;

/**
 * <b><code>TemplateDemo</code></b>
 * <p/>
 * Description 模板方法模式DEMO
 *
 * ## 个人小解读
 * 模板模式，做事情要有方法，套路。模板方法就是套路，套进去之后，根据事情的不同也可以通过子类重写。
 *
 * ## 角色
 * 1. 发起人（Originator）角色：记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能，实现其他业务功能，它可以访问备忘录里的所有信息。
 * 2. 备忘录（Memento）角色：负责存储发起人的内部状态，在需要的时候提供这些内部状态给发起人。
 * 3. 管理者（Caretaker）角色：对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录的内容进行访问与修改。
 *
 * ## 优点
 * 1. 它封装了不变部分，扩展可变部分。它把认为是不变部分的算法封装到父类中实现，而把可变部分算法由子类继承实现，便于子类继续扩展。
 * 2. 它在父类中提取了公共的部分代码，便于代码复用。
 * 3. 部分方法是由子类实现的，因此子类可以通过扩展方式增加相应的功能，符合开闭原则。
 *
 * ## 缺点
 * 1. 对每个不同的实现都需要定义一个子类，这会导致类的个数增加，系统更加庞大，设计也更加抽象。
 * 2. 父类中的抽象方法由子类实现，子类执行的结果会影响父类的结果，这导致一种反向的控制结构，它提高了代码阅读的难度。
 *
 * ## 应用场景：
 * 1. 算法的整体步骤很固定，但其中个别部分易变时，这时候可以使用模板方法模式，将容易变的部分抽象出来，供子类实现。
 * 2. 当多个子类存在公共的行为时，可以将其提取出来并集中到一个公共父类中以避免代码重复。首先，要识别现有代码中的不同之处，并且将不同之处分离为新的操作。最后，用一个调用这些新的操作的模板方法来替换这些不同的代码。
 * 3. 当需要控制子类的扩展时，模板方法只在特定点调用钩子操作，这样就只允许在这些点进行扩展。
 *
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 17:06.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class TemplateDemo {
   // 客户端
    public static void main(String[] args) {
        Template template = null;

        template = new ConcreteClassA();
        template.templateMeth();

        template = new ConcreteClassB();
        template.templateMeth();
    }

    /**
     * 模板类，提供一个模板方法
     */
     static abstract class Template{
        final void templateMeth(){
            method1();

            method2();

            specialmethod();

        }

        void method1(){
            System.out.println("电饭锅插电");
        }

        void method2(){
            System.out.println("放入材料");
        }

        abstract void specialmethod();

    }


    /**
     * 具体模板类A
     */
    static class ConcreteClassA extends Template{

        @Override
        void specialmethod() {
            System.out.println("按煮饭按钮，等30min");
        }
    }

    /**
     * 具体模板类B
     */
    static class ConcreteClassB extends Template{

        @Override
        void specialmethod() {
            System.out.println("按煲汤按钮，等1h");
        }
    }
}
