package com.hui.base.design.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>Aggregate</code></b>
 * <p/>
 * Description 迭代器模式
 *
 * ## 个人小解读
 * 迭代器，看JAVA的list的迭代器理解，
 *
 * ## 角色
 * 1. 抽象聚合（Aggregate）角色：定义存储、添加、删除聚合对象以及创建迭代器对象的接口。
 * 2. 具体聚合（ConcreteAggregate）角色：实现抽象聚合类，返回一个具体迭代器的实例。
 * 3. 抽象迭代器（Iterator）角色：定义访问和遍历聚合元素的接口，通常包含 hasNext()、first()、next() 等方法。
 * 4. 具体迭代器（Concretelterator）角色：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。
 *
 * ## 优点
 * 1. 访问一个聚合对象的内容而无须暴露它的内部表示。
 * 2. 遍历任务交由迭代器完成，这简化了聚合类。
 * 3. 它支持以不同方式遍历一个聚合，甚至可以自定义迭代器的子类以支持新的遍历。
 * 4. 增加新的聚合类和迭代器类都很方便，无须修改原有代码。
 * 5. 封装性良好，为遍历不同的聚合结构提供一个统一的接口。
 *
 * ## 缺点
 * 1. 增加了类的个数，这在一定程度上增加了系统的复杂性。
 *
 * ## 应用场景：
 * 1. 当需要为聚合对象提供多种遍历方式时。
 * 2. 当需要为遍历不同的聚合结构提供一个统一的接口时。
 * 3. 当访问一个聚合对象的内容而无须暴露其内部细节的表示时。
 *
 * <p/>
 * <b>Creation Time:</b> 2019/7/25 10:57.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class IteratorDemo {
    //客户端
    public static void main(String[] args) {
        Aggregate aggregate=new ConcreteAggregate();
        aggregate.add("鼠");
        aggregate.add("牛");
        aggregate.add("虎");
        aggregate.add("兔");
        aggregate.add("龙");
        aggregate.add("蛇");
        aggregate.add("马");
        aggregate.add("羊");
        aggregate.add("猴");
        aggregate.add("鸡");
        aggregate.add("狗");
        aggregate.add("猪");

        System.out.print("聚合的内容有：");
        Iterator iterator=aggregate.getIterator();
        while(iterator.hasNext())
        {
            Object ob=iterator.next();
            System.out.print(ob.toString()+"\t");
        }
        Object ob=iterator.first();
        System.out.println("\nFirst："+ob.toString());
    }


    /**
     * 抽象聚合类
     */
    interface Aggregate{
        void add(Object o);

        void remove(Object o);

        Iterator getIterator();
    }

    /**
     * 具体聚合类
     */
    static class ConcreteAggregate implements Aggregate{

        private List<Object> objects = new ArrayList<>();

        @Override
        public void add(Object o) {
            objects.add(o);
        }

        @Override
        public void remove(Object o) {
            objects.remove(o);
        }

        @Override
        public Iterator getIterator() {
            return new ConcreteIterator(objects);
        }
    }

    /**
     * 抽象迭代器
     */
    interface Iterator{
        Object first();

        Object next();

        boolean hasNext();
    }

    /**
     * 具体迭代器
     */
    static class ConcreteIterator implements Iterator{
        private List<Object> list;

        private int index;

        public ConcreteIterator(List<Object> list) {
            this.list = list;
            this.index = 0;
        }

        @Override
        public Object first() {
            return list.get(0);
        }

        @Override
        public Object next() {
            if (hasNext()){
                Object o = list.get(index);
                index++;
                return o;
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            if (index <= list.size()-1){
                return true;
            }
            return false;
        }

    }

}
