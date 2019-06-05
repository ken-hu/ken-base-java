package com.hui.base.design.behavior.strategy;

import com.hui.base.design.behavior.strategy.context.Context;
import com.hui.base.design.behavior.strategy.strategy.WordStrategy;

/**
 * <b><code>Client</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 10:23.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context(new WordStrategy());
        context.execute();

    }
}
