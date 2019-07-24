package com.hui.base.design.behavior.strategy;

import lombok.Data;

/**
 * <b><code>StrategyDemo</code></b>
 * <p/>
 * Description 策略模式DEMO
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 16:48.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class StrategyDemo {

    //客户端
    public static void main(String[] args) {

        Context context = new Context(new ConcreteStrategyA());
        context.execute();

    }

    /**
     * 环境上下文
     */
    @Data
    static class Context{
        private Strategy strategy;

        public Context(Strategy strategy) {
            this.strategy = strategy;
        }

        void execute(){
            strategy.execute();
        }
    }

    /**
     * 抽象策略类
     */
    interface Strategy {
        void execute();
    }


    /**
     * 具体策略A
     */
    static class ConcreteStrategyA implements Strategy{

        @Override
        public void execute() {
            System.out.println("execute strategy A");
        }
    }

    /**
     * 具体策略B
     */
    static class ConcreteStrategyB implements Strategy{

        @Override
        public void execute() {
            System.out.println("execute strategy B");
        }
    }


}
