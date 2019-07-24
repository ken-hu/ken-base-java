package com.hui.base.design.behavior.state;

import lombok.Data;

/**
 * <b><code>StateDemo</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 17:51.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class StateDemo {

    //客户端
    public static void main(String[] args) {

        Context context = new Context();

        context.handle();
        context.handle();


    }

    /**
     * 环境上下文
     */
    @Data
    static class Context{
        private State state;

        public Context() {
            this.state = new ConcreteStateA();
        }

        public void handle(){
            state.handle(this);
        }
    }

    /**
     * 状态类
     */
    interface State{
        void handle(Context context);
    }


    /**
     * 具体状态A
     */
    static class ConcreteStateA implements State{
        @Override
        public void handle(Context context) {
            System.out.println("当前状态：未登录");
            System.out.println("执行登录......");
            context.setState(new ConcreteStateB());
        }
    }

    /**
     * 具体状态B
     */
    static class ConcreteStateB implements State{

        @Override
        public void handle(Context context) {
            System.out.println("当前状态：登录");
            System.out.println("退出登录......");
            context.setState(new ConcreteStateA());
        }
    }
}
