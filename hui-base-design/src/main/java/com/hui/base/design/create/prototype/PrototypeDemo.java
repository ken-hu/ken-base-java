package com.hui.base.design.create.prototype;

/**
 * <b><code>PrototypeDemo</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 21:06.
 *
 * @author HuWeihui
 */
public class PrototypeDemo {
    //客户端
    public static void main(String[] args) throws CloneNotSupportedException {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        ConcretePrototype cloneObject = (ConcretePrototype) concretePrototype.clone();

        System.out.println("浅克隆结果: concretePrototype == cloneObject ? " + (concretePrototype == cloneObject ));

    }

    interface Prototype{

    }

    /**
     * 浅克隆
     * 当被克隆的类中有引用对象（String或Integer等包装类型除外）时，克隆出来的类中的引用变量存储的还是之前的内存地址
     * 需要克隆的对象
     */
    static class ConcretePrototype implements Cloneable{
        private String name;

        private String address;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return (ConcretePrototype)super.clone();
        }
    }



}
