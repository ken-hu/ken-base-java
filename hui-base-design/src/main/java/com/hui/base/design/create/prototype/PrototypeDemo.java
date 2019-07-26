package com.hui.base.design.create.prototype;

/**
 * <b><code>PrototypeDemo</code></b>
 * <p/>
 * Description: 原型模式
 *
 * ## 个人小解读
 * 原型，顾名思义就是某个东西的一个原型，后面的都是模仿，克隆它的。
 *
 * ## 角色
 * 1. 抽象原型类：规定了具体原型对象必须实现的接口。
 * 2. 具体原型类：实现抽象原型类的 clone() 方法，它是可被复制的对象。
 * 3. 访问类：使用具体原型类中的 clone() 方法来复制新的对象。
 *
 * ## 特点
 * 对象之间相同或相似，即只是个别的几个属性不同的时候。
 * 对象的创建过程比较麻烦，但复制比较简单的时候。
 *
 * ## 优点
 * 1. 通过克隆简化很多初始化工作
 *
 * ## 缺点
 * 1. 原型模式需要给每一个类都配备一个克隆的方法，这就需要在设计类时通盘考虑。因为在已有类的基础上添加clone操作比较困难。
 *
 * ## 应用场景：

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

    /**
     * 具体原型
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
