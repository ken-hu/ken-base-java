package com.hui.base.design.structure.facade;

/**
 * <b><code>ApperanceDemo</code></b>
 * <p/>
 * Description 外观模式
 *
 * ## 个人小解读
 *
 * ## 角色
 * 1. 外观（Facade）角色：为多个子系统对外提供一个共同的接口。
 * 2. 子系统（Sub System）角色：实现系统的部分功能，客户可以通过外观角色访问它。
 * 3. 客户（Client）角色：通过一个外观角色访问各个子系统的功能。
 *
 * ## 优点（外观（Facade）模式是“迪米特法则”的典型应用）
 * 1. 降低了子系统与客户端之间的耦合度，使得子系统的变化不会影响调用它的客户类。
 * 2. 对客户屏蔽了子系统组件，减少了客户处理的对象数目，并使得子系统使用起来更加容易。
 * 3. 降低了大型软件系统中的编译依赖性，简化了系统在不同平台之间的移植过程，因为编译一个子系统不会影响其他的子系统，也不会影响外观对象。
 *
 * ## 缺点
 * 1. 不能很好地限制客户使用子系统类。
 * 2. 增加新的子系统可能需要修改外观类或客户端的源代码，违背了“开闭原则”。
 *
 * ## 应用场景：
 * 1. 对分层结构系统构建时，使用外观模式定义子系统中每层的入口点可以简化子系统之间的依赖关系。
 * 2. 当一个复杂系统的子系统很多时，外观模式可以为系统设计一个简单的接口供外界访问。
 * 3. 当客户端与多个子系统之间存在很大的联系时，引入外观模式可将它们分离，从而提高子系统的独立性和可移植性。
 *
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 18:14.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class FacadeDemo {
    //客户端
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.method();
    }

    static class Facade{
        private SubSystemA subSystemA = new SubSystemA();
        private SubSystemB subSystemB = new SubSystemB();
        private SubSystemC subSystemC = new SubSystemC();

        public void method(){
            subSystemA.star();
            subSystemB.star();
            subSystemC.star();
        }
    }

    static class SubSystemA{
        public void star(){
            System.out.println("star the sys A...........");
        }
    }

    static class SubSystemB{
        public void star(){
            System.out.println("star the sys B...........");
        }
    }

    static class SubSystemC{
        public void star(){
            System.out.println("star the sys C...........");
        }
    }
}
