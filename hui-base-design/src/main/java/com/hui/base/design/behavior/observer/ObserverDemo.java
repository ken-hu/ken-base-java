package com.hui.base.design.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>ObserverDemo</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 16:33.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class ObserverDemo {

    //客户端
    public static void main(String[] args) {
        Subject subject=new ConcreteSubject();
        Observer obs1=new ConcreteObserver();
        Observer obs2=new ConcreteObserver();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObservers();
    }


    /**
     * 抽象观察者
     */
    interface Observer{
        void response();
    }


    /**
     * 具体观察者
     */
    static class ConcreteObserver implements Observer{
        @Override
        public void response() {
            System.out.println("公众号发送文章啦!!! ");
        }
    }

    /**
     * 抽象被观察目标
     */
    static abstract class Subject {

        protected List<Observer> observers = new ArrayList<>();

        void add(Observer observer){
            System.out.println("一个订阅者新增");
            observers.add(observer);
        }

        void remove(Observer observer){
            System.out.println("一个订阅者取关");
            observers.remove(observer);
        }

        public abstract void notifyObservers();
    }


    /**
     * 具体被观察目标
     */
    static class ConcreteSubject extends Subject{
        @Override
        public void notifyObservers() {
            for (Observer observer : super.observers) {
                observer.response();
            }
        }
    }
}
