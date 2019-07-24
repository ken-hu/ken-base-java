package com.hui.base.design.create.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <b><code>BuilderDemo</code></b>
 * <p/>
 * Description 建造者模式DEMO
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 10:42.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class BuilderDemo {
    //客户端
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
