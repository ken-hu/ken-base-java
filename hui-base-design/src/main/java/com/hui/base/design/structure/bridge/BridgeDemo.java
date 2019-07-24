package com.hui.base.design.structure.bridge;

/**
 * <b><code>BridgeDemo</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/7/24 21:33.
 *
 * @author HuWeihui
 */
public class BridgeDemo {

    static abstract class Abstraction{

    }

    interface Implementor{

    }

    static class ConcreteImplementorA{

    }

    static class ConcreteImplementorB{

    }

    static class RefinedAbstraction extends Abstraction{

    }
}
