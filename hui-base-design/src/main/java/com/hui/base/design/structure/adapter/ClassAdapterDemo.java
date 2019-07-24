package com.hui.base.design.structure.adapter;

/**
 * <b><code>ClassAdapterDemo</code></b>
 * <p/>
 * Description 类适配器DEMO
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 14:32.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class ClassAdapterDemo {
    //客户端
    public static void main(String[] args) {
        Target target = new ClassAdapter();
        target.request();
    }

    /**
     * 需要适配的目标类
     * 举例 这是一个USB，可以充电
     */
    interface Target{
        void request();
    }

    /**
     * 适配器
     * 举例 这是一个安卓头适配器
     */
    static class Adapter{
        public void adapterRequest(){
            System.out.println("安卓充电.................");
        }
    }


    static class ClassAdapter extends Adapter implements Target{
        @Override
        public void request() {
            System.out.println("这里继承了安卓充电器实现了USB(target)充电功能。安卓充电适配器使用：");
            super.adapterRequest();
        }
    }

}
