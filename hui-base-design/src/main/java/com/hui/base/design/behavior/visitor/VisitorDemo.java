package com.hui.base.design.behavior.visitor;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <b><code>VistorDemo</code></b>
 * <p/>
 * Description: 访问者模式
 *
 * ## 个人小解读
 *
 *
 *
 * ## 角色
 * 1. 抽象访问者（Visitor）角色：定义一个访问具体元素的接口，为每个具体元素类对应一个访问操作 visit() ，该操作中的参数类型标识了被访问的具体元素。
 * 2. 具体访问者（ConcreteVisitor）角色：实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么。
 * 3. 抽象元素（Element）角色：声明一个包含接受操作 accept() 的接口，被接受的访问者对象作为 accept() 方法的参数。
 * 4. 具体元素（ConcreteElement）角色：实现抽象元素角色提供的 accept() 操作，其方法体通常都是 visitor.visit(this) ，另外具体元素中可能还包含本身业务逻辑的相关操作。
 * 5. 对象结构（Object Structure）角色：是一个包含元素角色的容器，提供让访问者对象遍历容器中的所有元素的方法，通常由 List、Set、Map 等聚合类实现。
 *
 * ## 优点
 * 1. 扩展性好。能够在不修改对象结构中的元素的情况下，为对象结构中的元素添加新的功能。
 * 2. 复用性好。可以通过访问者来定义整个对象结构通用的功能，从而提高系统的复用程度。
 * 3. 灵活性好。访问者模式将数据结构与作用于结构上的操作解耦，使得操作集合可相对自由地演化而不影响系统的数据结构。
 * 4. 符合单一职责原则。访问者模式把相关的行为封装在一起，构成一个访问者，使每一个访问者的功能都比较单一。
 *
 * ## 缺点
 * 1. 增加新的元素类很困难。在访问者模式中，每增加一个新的元素类，都要在每一个具体访问者类中增加相应的具体操作，这违背了“开闭原则”。
 * 2. 破坏封装。访问者模式中具体元素对访问者公布细节，这破坏了对象的封装性。
 * 3. 违反了依赖倒置原则。访问者模式依赖了具体类，而没有依赖抽象类。
 *
 * ## 应用场景：
 * 1. 对象结构相对稳定，但其操作算法经常变化的程序。
 * 2. 对象结构中的对象需要提供多种不同且不相关的操作，而且要避免让这些操作的变化影响对象的结构。
 * 3. 对象结构包含很多类型的对象，希望对这些对象实施一些依赖于其具体类型的操作。
 *
 * <p/>
 * <b>Creation Time:</b> 2019/7/25 21:06.
 *
 * @author HuWeihui
 */
public class VisitorDemo {

    //客户端
    public static void main(String[] args) {
        ObjectStructure os=new ObjectStructure();
        os.add(new ConcreteElementA("具体元素A"));
        os.add(new ConcreteElementB("具体元素B"));
        Visitor visitor=new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor=new ConcreteVisitorB();
        os.accept(visitor);
    }

    /**
     * 抽象访问者
     */
    interface Visitor{
        void visit(ConcreteElementA concreteElementA);
        void visit(ConcreteElementB concreteElementB);
    }

    /**
     * 具体访问者
     */
    static class ConcreteVisitorA implements Visitor{

        @Override
        public void visit(ConcreteElementA concreteElementA) {
            System.out.println("A访问 -> " + concreteElementA.getName());
        }

        @Override
        public void visit(ConcreteElementB concreteElementB) {
            System.out.println("A访问 -> " + concreteElementB.getName());
        }
    }

    /**
     * 具体访问者
     */
    static class ConcreteVisitorB implements Visitor{

        @Override
        public void visit(ConcreteElementA concreteElementA) {
            System.out.println("B访问 -> " + concreteElementA.getName());

        }

        @Override
        public void visit(ConcreteElementB concreteElementB) {
            System.out.println("B访问 -> " + concreteElementB.getName() );

        }
    }


    /**
     * 抽象元素
     */
    interface Element{
        void accept(Visitor visitor);
    }

    /**
     * 具体元素
     */
    @Data
    static class ConcreteElementA implements Element{

        private String name;

        public ConcreteElementA(String name) {
            this.name = name;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * 具体元素
     */
    @Data
    static class ConcreteElementB implements Element{

        private String name;

        public ConcreteElementB(String name) {
            this.name = name;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }


    /**
     * 对象结构
     */
    static class ObjectStructure{
        private List<Element> elements = new ArrayList<>();

        public void accept(Visitor visitor){
            Iterator<Element> iterator = elements.iterator();
            while (iterator.hasNext()){
                iterator.next().accept(visitor);
            }
        }

        public void add(Element element){
            elements.add(element);
        }

        public void remove(Element element){
            elements.add(element);
        }
    }
}
