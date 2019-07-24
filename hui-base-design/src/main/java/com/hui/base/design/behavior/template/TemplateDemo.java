package com.hui.base.design.behavior.template;

/**
 * <b><code>TemplateDemo</code></b>
 * <p/>
 * Description 模板方法模式DEMO
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 17:06.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class TemplateDemo {
   // 客户端
    public static void main(String[] args) {
        Template template = null;

        template = new ConcreteClassA();
        template.templateMeth();

        template = new ConcreteClassB();
        template.templateMeth();
    }

     static abstract class Template{
        final void templateMeth(){
            method1();

            method2();

            specialmethod();

        }

        void method1(){
            System.out.println("电饭锅插电");
        }

        void method2(){
            System.out.println("放入材料");
        }

        abstract void specialmethod();

    }


    static class ConcreteClassA extends Template{

        @Override
        void specialmethod() {
            System.out.println("按煮饭按钮，等30min");
        }
    }

    static class ConcreteClassB extends Template{

        @Override
        void specialmethod() {
            System.out.println("按煲汤按钮，等1h");
        }
    }
}
