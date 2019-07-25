package com.hui.base.design.behavior.vistor;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <b><code>VistorDemo</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/7/25 21:06.
 *
 * @author HuWeihui
 */
public class VistorDemo {

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
