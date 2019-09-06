package com.hui.base.thread.springboot;

import com.hui.base.thread.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <b><code>SpringbootThreadService</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/8/21 14:54.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class SpringbootThreadServiceTest extends BaseJunitTest {

    @Autowired
    private SpringbootThreadService springbootThreadService;

    @Test
    public void testThread() throws InterruptedException {
        springbootThreadService.testThread();
    }
}
