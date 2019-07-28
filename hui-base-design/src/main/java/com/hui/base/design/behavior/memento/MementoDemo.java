package com.hui.base.design.behavior.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <b><code>MementoDemo</code></b>
 * <p/>
 * Description: 备忘录模式
 *
 *
 * ## 个人小解读
 * 备忘录，害怕自己忘记了之前，于是做个快照记录下来某个状态信息，然后再继续下一步。以后出了问题可以回到之前状态。
 *
 * ## 角色
 * 1. 发起人（Originator）角色：记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能，实现其他业务功能，它可以访问备忘录里的所有信息。
 * 2. 备忘录（Memento）角色：负责存储发起人的内部状态，在需要的时候提供这些内部状态给发起人。
 * 3. 管理者（Caretaker）角色：对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录的内容进行访问与修改。
 *
 * ## 优点
 * 1. 提供了一种可以恢复状态的机制。当用户需要时能够比较方便地将数据恢复到某个历史的状态。
 * 2. 实现了内部状态的封装。除了创建它的发起人之外，其他对象都不能够访问这些状态信息。
 * 3. 简化了发起人类。发起人不需要管理和保存其内部状态的各个备份，所有状态信息都保存在备忘录中，并由管理者进行管理，这符合单一职责原则。
 *
 * ## 缺点
 * 1. 资源消耗大。如果要保存的内部状态信息过多或者特别频繁，将会占用比较大的内存资源。
 *
 * ## 应用场景：
 * 1. 需要保存与恢复数据的场景，如玩游戏时的中间结果的存档功能。
 * 2. 需要提供一个可回滚操作的场景，如 Word、记事本、Photoshop，Eclipse 等软件在编辑时按 Ctrl+Z 组合键，还有数据库中事务操作。
 *
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
