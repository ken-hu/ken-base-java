package com.hui.base.design.behavior.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <b><code>MementoDemo</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/7/25 21:21.
 *
 * @author HuWeihui
 */
public class MementoDemo {

    public static void main(String[] args) {
        Originator originator=new Originator();
        Caretaker caretaker=new Caretaker();

        originator.setState("stay by ");
        System.out.println("初始状态:"+originator.getState());

        //保存状态
        caretaker.setMemento(originator.create());
        originator.setState("running");
        System.out.println("新的状态:"+originator.getState());

        //恢复状态
        originator.restore(caretaker.getMemento());
        System.out.println("恢复状态:"+originator.getState());
    }
    /**
     * 备忘录
     */
    @AllArgsConstructor
    @Data
    static class Memento{

        private String state;

    }

    /**
     * 发起人
     */
    @Data
    static class Originator{
        private String state;

        public Memento create(){
            return new Memento(state);
        }

        public void restore(Memento memento){
            this.setState(memento.getState());
        }
    }

    /**
     * 管理人
     */
    @Data
    static class Caretaker{
        private Memento memento;
    }

}
