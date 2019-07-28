package com.hui.base.design.behavior.command;

import lombok.Data;

/**
 * <b><code>CommandDemo</code></b>
 * <p/>
 * Description 命令模式
 *
 * ## 个人小解读
 * 命令，定义一些命令，这些命令分别执行不同操作。
 *
 * ## 角色
 * 1. 抽象命令类（Command）角色：声明执行命令的接口，拥有执行命令的抽象方法 execute()。
 * 2. 具体命令角色（Concrete    Command）角色：是抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作。
 * 3. 实现者/接收者（Receiver）角色：执行命令功能的相关操作，是具体命令对象业务的真正实现者。
 * 4. 调用者/请求者（Invoker）角色：是请求的发送者，它通常拥有很多的命令对象，并通过访问命令对象来执行相关请求，它不直接访问接收者。
 *
 * ## 优点
 * 1. 降低系统的耦合度。命令模式能将调用操作的对象与实现该操作的对象解耦。
 * 2. 增加或删除命令非常方便。采用命令模式增加与删除命令不会影响其他类，它满足“开闭原则”，对扩展比较灵活。
 * 3. 可以实现宏命令。命令模式可以与组合模式结合，将多个命令装配成一个组合命令，即宏命令。
 * 4. 方便实现 Undo 和 Redo 操作。命令模式可以与后面介绍的备忘录模式结合，实现命令的撤销与恢复。
 *
 * ## 缺点
 * 1. 可能产生大量具体命令类。因为计对每一个具体操作都需要设计一个具体命令类，这将增加系统的复杂性。
 *
 * ## 应用场景：
 * 1. 当系统需要将请求调用者与请求接收者解耦时，命令模式使得调用者和接收者不直接交互。
 * 2. 当系统需要随机请求命令或经常增加或删除命令时，命令模式比较方便实现这些功能。
 * 3. 当系统需要执行一组操作时，命令模式可以定义宏命令来实现该功能。
 * 4. 当系统需要支持命令的撤销（Undo）操作和恢复（Redo）操作时，可以将命令对象存储起来，采用备忘录模式来实现。
 *
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
