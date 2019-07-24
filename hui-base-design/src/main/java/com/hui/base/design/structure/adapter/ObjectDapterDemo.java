package com.hui.base.design.structure.adapter;

/**
 * <b><code>ObjectDapterDemo</code></b>
 * <p/>
 * Description 对象适配器DEMO
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 15:07.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class ObjectDapterDemo {
    //客户端
    public static void main(String[] args) {
        Target target = new ObjectAdapter(new Adapter());

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


    /**
     * 对象适配器
     */
    static class ObjectAdapter implements Target{
        private Adapter adapter;

        public ObjectAdapter(Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void request() {
            System.out.println("这里实现了USB(target)充电功能。对象引用了安卓充电适配器使用：");
            adapter.adapterRequest();
        }
    }
}
