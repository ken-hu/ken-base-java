package com.hui.base.design.structure.adapter;

/**
 * <b><code>ClassAdapterDemo</code></b>
 * <p/>
 * Description 类适配器DEMO
 *
 * ## 个人小解读
 * 适配器，让我们的东西和别人的东西通过一个适配器，达到接入效果。
 * 日常生活中常见的就是转接头
 *
 * ## 角色
 * 1. 目标（Target）接口：当前系统业务所期待的接口，它可以是抽象类或接口。
 * 2. 适配者（Adaptee）类：它是被访问和适配的现存组件库中的组件接口。
 * 3. 适配器（Adapter）类：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者。
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
 * 1. 产品的组成部分必须相同，这限制了其使用范围。
 * 2. 如果产品的内部变化复杂，该模式会增加很多的建造者类。
 *
 * ## 应用场景：
 * 1. 以前开发的系统存在满足新系统功能需求的类，但其接口同新系统的接口不一致。
 * 2. 使用第三方提供的组件，但组件接口定义和自己要求的接口定义不同。
 *
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 14:32.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class ClassAdapterDemo {
    //客户端
    public static void main(String[] args) {
        Target target = new ClassAdapter();
        target.request();
    }

    /**
     * 需要适配的目标类
     * 举例 这是一个USB，可以充电
     */
    interface Target{
        void request();
    }

    /**
     * 适配者
     * 举例 这是一个安卓头
     */
    static class Adaptee{
        public void adapteeRequest(){
            System.out.println("安卓充电.................");
        }
    }


    /**
     * 适配器类
     * 举例 转接头   ust->转接头->安卓头
     */
    static class ClassAdapter extends Adaptee implements Target{
        @Override
        public void request() {
            System.out.println("这里继承了安卓充电器实现了USB(target)充电功能。安卓充电适配器使用：");
            super.adapteeRequest();
        }
    }

}
