package com.hui.base.design.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>Aggregate</code></b>
 * <p/>
 * Description
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
