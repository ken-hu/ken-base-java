package com.hui.base.design.create.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * <b><code>AbstractFactoryDemo</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/7/26 15:22.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
@Slf4j
public class AbstractFactoryDemo {
    //Client
    public static void main(String[] args) {
        AbstractFactory huaweiFactory = new ConcreteFactoryA();
        Product1 product1 = huaweiFactory.createProduct1();
        product1.show();
    }

    /**
     * 抽象工厂
     */
    interface AbstractFactory{
        Product1 createProduct1();

        Product2 createProduct2();
    }


    /**
     * 具体工厂A
     * 举例 HUAWEI大厂
     */
    static class ConcreteFactoryA implements AbstractFactory{

        @Override
        public Product1 createProduct1() {
            log.info("HUAWEI 生产 耳机");
            return new ConcreteProductA1();
        }

        @Override
        public Product2 createProduct2() {
            log.info("HUAWIE 生产 手机");
            return new ConcreteProductA2();
        }
    }

    /**
     * 具体工厂B
     * 举例 苹果大厂
     */
    static class ConcreteFactoryB implements AbstractFactory{

        @Override
        public Product1 createProduct1() {
            log.info("APPLE 生产 耳机");
            return new ConcreteProductB1();
        }

        @Override
        public Product2 createProduct2() {
            log.info("APPLE 生产 手机");
            return new ConcreteProductB2();
        }
    }

    /**
     * 抽象产品1
     * 举例： 耳机
     */
    interface Product1{
        void show();
    }

    /**
     * 抽象产品2
     * 举例： 手机
     */
    interface Product2{
        void show();
    }

    /**
     * 具体产品A1
     * 举例： 华为耳机
     */
    static class ConcreteProductA1 implements Product1{

        @Override
        public void show() {
            log.info(" i am huawei headphone");
        }
    }

    /**
     * 具体产品B1
     * 举例： 苹果耳机
     */
    static class ConcreteProductB1 implements Product1{

        @Override
        public void show() {
            log.info(" i am apple headphone");
        }
    }

    /**
     * 具体产品A2
     * 举例： 华为手机
     */
    static class ConcreteProductA2 implements Product2{

        @Override
        public void show() {
            log.info(" i am huawei phone ");
        }
    }

    /**
     * 具体产品B2
     * 举例： 苹果手机
     */
    static class ConcreteProductB2 implements Product2{

        @Override
        public void show() {
            log.info(" i am iphone ");
        }
    }
}
