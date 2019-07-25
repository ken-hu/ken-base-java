package com.hui.base.design.behavior.command;

import lombok.Data;

/**
 * <b><code>CommandDemo</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/7/25 11:57.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class CommandDemo {
    //客户端
    public static void main(String[] args) {

        Command command = new ConcreteCommandA(new ReceiverA());
        Invoker invoker = new Invoker();
        invoker.setCommand(command);

        invoker.call();
    }

    /**
     * 命令执行（调度）者
     */
    @Data
    static class Invoker{
        private Command command;

        public void call(){
            command.execute();
        }
    }

    /**
     * 命令
     */
    interface Command{
        void execute();
    }


    /**
     * 具体命令
     */
    static class ConcreteCommandA implements Command{

        private ReceiverA receiverA;

        public ConcreteCommandA(ReceiverA receiverA) {
            this.receiverA = receiverA;
        }

        @Override
        public void execute() {
            receiverA.action();
        }
    }

    /**
     * 具体命令
     */
    static class ConcreteCommandB implements Command{
        private ReceiverB receiverB;

        public ConcreteCommandB(ReceiverB receiverB) {
            this.receiverB = receiverB;
        }

        @Override
        public void execute() {
            receiverB.action();
        }
    }

    /**
     * 抽象接收者
     */
    interface Receiver{
        void action();
    }

    /**
     * 具体接收者
     */
    static class ReceiverA implements Receiver{
        @Override
        public void action(){
            System.out.println("A action !!!");
        }
    }

    /**
     * 具体接收者
     */
    static class ReceiverB implements Receiver{
        @Override
        public void action(){
            System.out.println("B action !!!");
        }
    }
}
