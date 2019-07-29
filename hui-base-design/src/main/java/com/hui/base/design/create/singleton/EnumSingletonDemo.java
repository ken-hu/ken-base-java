package com.hui.base.design.create.singleton;

/**
 * <b><code>EnumSingleton</code></b>
 * <p/>
 * Description: 枚举类实现单例模式
 * <p/>
 * <b>Creation Time:</b> 2019/7/29 21:50.
 *
 * @author HuWeihui
 */
public enum EnumSingletonDemo {

    INSTANCE;
    private EnumSingleton enumSingleton = null;

    private EnumSingletonDemo() {
        enumSingleton = new EnumSingleton();
    }
    public EnumSingleton getEnumSingleton(){
        return this.enumSingleton;
    }


    static class EnumSingleton{

    }

    static class Client{
        public static void main(String[] args) {
            EnumSingleton enumSingleton1 = EnumSingletonDemo.INSTANCE.getEnumSingleton();
            EnumSingleton enumSingleton2 = EnumSingletonDemo.INSTANCE.getEnumSingleton();

            System.out.println(enumSingleton1 == enumSingleton2);
        }
    }
}
