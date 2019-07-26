package com.hui.base.design.create.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <b><code>BuilderDemo</code></b>
 * <p/>
 * Description 建造者模式DEMO
 *
 * ## 个人小解读
 * 建造，顾名思义就是我要创建一个产品，我可以以自己想法去建造他，例如建房子，我喜欢有天台，不喜欢有阳台，那就建造的时候不把阳台建造起来。
 * 但是建造也有规则，你造的是房子，那不能当成蛋糕建，把面粉当砖头用，不然就是面粉渣工程了。
 *
 * ## 角色
 * 1. 产品角色（Product）：它是包含多个组成部件的复杂对象，由具体建造者来创建其各个滅部件。
 * 2. 抽象建造者（Builder）：它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法 getResult()。
 * 3. 具体建造者(Concrete Builder）：实现 Builder 接口，完成复杂产品的各个部件的具体创建方法。
 * 4. 指挥者（Director）：它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息。
 *
 *
 * ## 特点
 * 指将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示
 *
 * ## 优点
 * 1. 各个具体的建造者相互独立，有利于系统的扩展。
 * 2. 客户端不必知道产品内部组成的细节，便于控制细节风险。
 * ## 缺点
 * 1. 产品的组成部分必须相同，这限制了其使用范围。
 * 2. 如果产品的内部变化复杂，该模式会增加很多的建造者类。
 *
 * ## 应用场景：
 * 1. lombok 的 @Builder注解实现作用
 * 2. 很多Properties的Bean的创建初始化
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 10:42.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class BuilderDemo {
    //Client
    public static void main(String[] args) {

        //method 1
        Director director = new Director(new Builder());
        Product product1 = director.construct();
        System.out.println(product1);

        //method2
        Product product2 = Product.builder().info("info 3").name("name 3").build();
        System.out.println(product2);
    }

    /**
     * 产品类
     */
    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    static class Product{

        private String name;

        private String info;

        public static Product.ProductBuilder builder() {
            return new Product.ProductBuilder();
        }

        // 方法2 和lombok里面一样
        public static class ProductBuilder{

            private String name;

            private String info;

            ProductBuilder(){
            }

            Product.ProductBuilder name(String name){
                this.name = name;
                return this;
            }

            Product.ProductBuilder info(String info){
                this.info = info;
                return this;
            }

            public Product build(){
                return new Product(this.name,this.info);
            }
        }
    }

    /**
     * 方法1 有builder 和 director 一个是构建 一个是逻辑
     * 创造者
     */
    static class Builder{
        private Product product = new Product();

        Builder name(String name) {
            product.setName(name);
            return this;
        }

        Builder info(String info) {
            product.setInfo(info);
            return this;
        }

        Product build(){
            return product;
        }
    }

    /**
     * 指挥者
     */
    static class Director{
        private Builder builder;

        public Director(Builder builder){
            this.builder = builder;
        }

        public Product construct(){
            builder.name("name 2");
            builder.info("info 2");
            return builder.build();
        }
    }

}
