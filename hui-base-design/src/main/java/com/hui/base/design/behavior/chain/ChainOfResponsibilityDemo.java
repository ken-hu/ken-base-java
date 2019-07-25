package com.hui.base.design.behavior.chain;

import lombok.Data;

/**
 * <b><code>ChainOfResponsibilityDemo</code></b>
 * <p/>
 * Description
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

    @Data
    static abstract class Handler{
        private Handler next;

        public abstract void handleRequest(String request);
    }


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
