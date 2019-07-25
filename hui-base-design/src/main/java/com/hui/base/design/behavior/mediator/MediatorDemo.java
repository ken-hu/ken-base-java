package com.hui.base.design.behavior.mediator;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>MediatorDemo</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/7/25 20:28.
 *
 * @author HuWeihui
 */
public class MediatorDemo {
    //客户端
    public static void main(String[] args) {
        Mediator md=new ConcreteMediator();
        Colleague c1,c2;
        c1=new ConcreteColleagueA();
        c2=new ConcreteColleagueB();
        md.register(c1);
        md.register(c2);
        c1.send();
        System.out.println("-------------");
        c2.send();
    }


    static abstract class Mediator{

        public abstract void register(Colleague colleague);

        public abstract void relay(Colleague colleague);
    }

    static class ConcreteMediator extends Mediator{

        private List<Colleague> colleagues = new ArrayList<>();

        @Override
        public void register(Colleague colleague) {
            colleagues.add(colleague);
            colleague.setMediator(this);
        }

        @Override
        public void relay(Colleague colleague) {
            for (Colleague obj : colleagues) {
                if (colleague.equals(obj)){
                    obj.receive();
                }
            }
        }
    }

    @Setter
    static abstract class Colleague{

        protected Mediator mediator;

        public abstract void send();

        public abstract void receive();
    }

    static class ConcreteColleagueA extends Colleague{

        @Override
        public void send() {
            System.out.println("同事A 转交给中介处理");
            mediator.relay(this);
        }

        @Override
        public void receive() {
            System.out.println("同事A 接收到信息");
        }
    }

    static class ConcreteColleagueB extends Colleague{

        @Override
        public void send() {
            System.out.println("同事B 转交给中介处理");
            mediator.relay(this);
        }

        @Override
        public void receive() {
            System.out.println("同事B 接收到信息");
        }
    }
}
