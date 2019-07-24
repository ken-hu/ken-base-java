package com.hui.base.design.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>CompositeDemo</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 22:58.
 *
 * @author HuWeihui
 */
public class CompositeDemo {

    //客户端
    public static void main(String[] args) {
        Component composite = new Composite("根节点");

        // 根节点下有 叶子1&2&树枝
        Component left1 = new Leaf("叶子节点1");
        Component left2 = new Leaf("叶子节点2");

        composite.add(left1);
        composite.add(left2);

        // 树枝节点下有 叶子A&B
        Component composite2 = new Composite("树枝节点");
        Component compositeA = new Leaf("叶子节点A");
        Component compositeB = new Leaf("叶子节点B");

        composite2.add(compositeA);
        composite2.add(compositeB);

        composite.add(composite2);

        // 遍历查看
        composite.operation();
    }

    /**
     * 抽象组件
     */
    interface Component{
        void add(Component component);

        void remove(Component component);

        Component getChildren(int id);

        void operation();
    }

    /**
     * 叶子组件
     */
    static class Leaf implements Component{

        private String name;

        public Leaf(String name) {
            this.name = name;
        }

        @Override
        public void add(Component component) {

        }

        @Override
        public void remove(Component component) {

        }

        @Override
        public Component getChildren(int id) {
            return null;
        }

        @Override
        public void operation() {
            System.out.println(name);
        }
    }

    /**
     * 树枝组件
     */
    static class Composite implements Component{

        private String name;

        private List<Component> components = new ArrayList<>();

        public Composite(String name) {
            this.name = name;
        }

        @Override
        public void add(Component component) {
            components.add(component);
        }

        @Override
        public void remove(Component component) {
            components.remove(component);
        }

        @Override
        public Component getChildren(int id) {
            Component component = components.get(id);
            return component;
        }

        @Override
        public void operation() {
            System.out.println(name);
            for (Component component : components) {
                component.operation();
            }
        }
    }

}
