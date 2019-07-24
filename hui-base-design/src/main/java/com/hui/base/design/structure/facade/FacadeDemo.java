package com.hui.base.design.structure.facade;

/**
 * <b><code>ApperanceDemo</code></b>
 * <p/>
 * Description
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
