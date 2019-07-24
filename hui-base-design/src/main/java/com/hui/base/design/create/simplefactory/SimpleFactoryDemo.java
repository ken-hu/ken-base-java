package com.hui.base.design.create.simplefactory;

/**
 * <b><code>SimpleFactoryDemo</code></b>
 * <p/>
 * Description 简单工厂
 * 1. 为了能都写在一个类容易看且在main函数运行，才把类加上static参数
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
