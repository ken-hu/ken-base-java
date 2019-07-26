package com.hui.base.design.create.simplefactory;

/**
 * <b><code>SimpleFactoryDemo</code></b>
 * <p/>
 * Description 简单工厂模式DEMO
 * ## 个人小解读
 * 工厂的特性就是，产品交给工厂生产，并且我们不会去了解工厂怎么去生产这个产品
 *
 * ## 角色
 * 1. 工厂类：创建具体产品的类。
 * 2. 抽象产品：定义了产品的规范，描述了产品的主要特性和功能
 * 3. 具体产品：实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间一一对应
 *
 * ## 优点
 * 1. 用户只需要知道具体工厂的名称就可得到所要的产品，无须知道产品的具体创建过程；
 * 2. 在系统增加新的产品时只需要添加具体产品类和对应的具体工厂类，无须对原工厂进行任何修改，满足开闭原则；
 * ## 缺点
 * 1. 每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度
 *
 * ## 应用场景：
 * 1. SpringBean工厂
 * 2. HttpClient工厂
 * 3. JDBC创建
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 9:39.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class SimpleFactoryDemo {

    //客户端
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        simpleFactory.createProduct("A").hellow();
        simpleFactory.createProduct("B").hellow();
    }
    /**
     * 抽象产品
     */
    interface Product{
        void hellow();
    }


    /**
     * 具体产品A
     */
    static class concreteProductA implements Product{

        @Override
        public void hellow() {
            System.out.println("hellow A");
        }
    }

    /**
     * 具体产品B
     */
    static class concreteProductB implements Product{

        @Override
        public void hellow() {
            System.out.println("hellow B");
        }
    }


    /**
     * 简单工厂
     */
    static class SimpleFactory{
        public Product createProduct(String type){
            Product product = null;

            switch (type){
                case "A":
                    product = new concreteProductA();
                    break;
                case "B":
                    product = new concreteProductB();
                    break;
                default:
                    break;
            }
            return product;
        }
    }
}
