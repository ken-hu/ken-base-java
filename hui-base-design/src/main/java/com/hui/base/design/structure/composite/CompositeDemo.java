package com.hui.base.design.structure.composite;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>CompositeDemo</code></b>
 * <p/>
 * Description: 组合模式
 *
 * ## 个人小解读
 * 适配器，让我们的东西和别人的东西通过一个适配器，达到接入效果。
 * 日常生活中常见的就是转接头
 *
 * ## 角色
 * 1. 抽象构件（Component）角色：它的主要作用是为树叶构件和树枝构件声明公共接口，并实现它们的默认行为。在透明式的组合模式中抽象构件还声明访问和管理子类的接口；在安全式的组合模式中不声明访问和管理子类的接口，管理工作由树枝构件完成。
 * 2. 树叶构件（Leaf）角色：是组合中的叶节点对象，它没有子节点，用于实现抽象构件角色中 声明的公共接口。
 * 3. 树枝构件（Composite）角色：是组合中的分支节点对象，它有子节点。它实现了抽象构件角色中声明的接口，它的主要作用是存储和管理子部件，通常包含 Add()、Remove()、GetChild() 等方法。
 *
 *
 * ## 特点
 * 指将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示
 *
 * ## 优点
 * 1. 客户端通过适配器可以透明地调用目标接口。
 * 2. 复用了现存的类，程序员不需要修改原有代码而重用现有的适配者类。
 * 3. 将目标类和适配者类解耦，解决了目标类和适配者类接口不一致的问题。
 *
 * ## 缺点
 * 1. 设计较复杂，客户端需要花更多时间理清类之间的层次关系；
 * 2. 不容易限制容器中的构件；
 * 3. 不容易用继承的方法来增加构件的新功能；
 *
 * ## 应用场景：
 * 1. 在需要表示一个对象整体与部分的层次结构的场合。
 * 2. 要求对用户隐藏组合对象与单个对象的不同，用户可以用统一的接口使用组合结构中的所有对象的场合。
 * 3. File
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 22:58.
 *
 * @author HuWeihui
 */
@Slf4j
public class CompositeDemo {

    //Client
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
            log.info(this.name);
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
            log.info(this.name);
            for (Component component : components) {
                component.operation();
            }
        }
    }

}
