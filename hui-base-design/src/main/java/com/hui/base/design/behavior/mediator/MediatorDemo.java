package com.hui.base.design.behavior.mediator;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>MediatorDemo</code></b>
 * <p/>
 * Description: 中介者模式
 *
 * ## 个人小解读
 * 中介，我们有时候需要处理的事情太多，找个中介，它能帮忙处理所有的事情。例如找房子，还得考虑装修，家具等，中介一应俱全
 *
 * ## 角色
 * 1. 抽象中介者（Mediator）角色：它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。
 * 2. 具体中介者（ConcreteMediator）角色：实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色。
 * 3. 抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能。
 * 4. 具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。
 *
 * ## 优点
 * 1. 降低了对象之间的耦合性，使得对象易于独立地被复用。
 * 2. 将对象间的一对多关联转变为一对一的关联，提高系统的灵活性，使得系统易于维护和扩展。
 *
 * ## 缺点
 * 1. 当同事类太多时，中介者的职责将很大，它会变得复杂而庞大，以至于系统难以维护。
 *
 * ## 应用场景：
 * 1. 当对象之间存在复杂的网状结构关系而导致依赖关系混乱且难以复用时。
 * 2. 当想创建一个运行于多个类之间的对象，又不想生成新的子类时。
 *
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
