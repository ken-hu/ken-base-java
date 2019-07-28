package com.hui.base.design.behavior.chain;

import lombok.Data;

/**
 * <b><code>ChainOfResponsibilityDemo</code></b>
 * <p/>
 * Description 责任链模式
 *
 * ## 个人小解读
 * 责任链，一条按顺序的链路，每个环节都有要负责的责任。
 *
 * ## 角色
 * 1. 抽象处理者（Handler）角色：定义一个处理请求的接口，包含抽象处理方法和一个后继连接。
 * 2. 具体处理者（Concrete Handler）角色：实现抽象处理者的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者。
 * 3. 客户类（Client）角色：创建处理链，并向链头的具体处理者对象提交请求，它不关心处理细节和请求的传递过程。
 *
 *
 * ## 优点
 * 1. 降低了对象之间的耦合度。该模式使得一个对象无须知道到底是哪一个对象处理其请求以及链的结构，发送者和接收者也无须拥有对方的明确信息。
 * 2. 增强了系统的可扩展性。可以根据需要增加新的请求处理类，满足开闭原则。
 * 3. 增强了给对象指派职责的灵活性。当工作流程发生变化，可以动态地改变链内的成员或者调动它们的次序，也可动态地新增或者删除责任。
 * 4. 责任链简化了对象之间的连接。每个对象只需保持一个指向其后继者的引用，不需保持其他所有处理者的引用，这避免了使用众多的 if 或者 if···else 语句。
 * 5. 责任分担。每个类只需要处理自己该处理的工作，不该处理的传递给下一个对象完成，明确各类的责任范围，符合类的单一职责原则。
 *
 * ## 缺点
 * 1. 不能保证每个请求一定被处理。由于一个请求没有明确的接收者，所以不能保证它一定会被处理，该请求可能一直传到链的末端都得不到处理。
 * 2. 对比较长的职责链，请求的处理可能涉及多个处理对象，系统性能将受到一定影响。
 * 3. 职责链建立的合理性要靠客户端来保证，增加了客户端的复杂性，可能会由于职责链的错误设置而导致系统出错，如可能会造成循环调用
 *
 * ## 应用场景：
 * 1. 有多个对象可以处理一个请求，哪个对象处理该请求由运行时刻自动确定。
 * 2. 可动态指定一组对象处理请求，或添加新的处理者。
 * 3. 在不明确指定请求处理者的情况下，向多个处理者中的一个提交请求。
 *
 * <p/>
 * <b>Creation Time:</b> 2019/7/25 10:31.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class ChainOfResponsibilityDemo {

    //客户端
    public static void main(String[] args) {
        Handler a = new ConcreteHandlerA();
        Handler b = new ConcreteHandlerB();

        a.setNext(b);

        a.handleRequest("web 请求");
    }

    /**
     * 抽象处理者
     */
    @Data
    static abstract class Handler{
        private Handler next;

        public abstract void handleRequest(String request);
    }


    /**
     * 具体处理者A
     */
    static class ConcreteHandlerA extends Handler{

        @Override
        public void handleRequest(String request) {
            System.out.println("handlerA handle the request :" + request + "-->");

            if (null != getNext()){
                getNext().handleRequest(request);
            }else {
                System.out.println("为责任链末端，没有后续处理 ");
            }
        }
    }

    /**
     * 具体处理者B
     */
    static class ConcreteHandlerB extends Handler{

        @Override
        public void handleRequest(String request) {
            System.out.println("handlerB handle the request :" + request + "-->");

            if (null != getNext()){
                getNext().handleRequest(request);
            }else {
                System.out.println("为责任链末端，没有后续处理");
            }

        }
    }
}
