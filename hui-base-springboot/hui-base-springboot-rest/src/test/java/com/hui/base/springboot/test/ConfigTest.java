package com.hui.base.springboot.test;

import com.hui.base.springboot.common.utils.FtpClientConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * AnalysisController Tester.
 *
 * @author Hu Weihui
 * @version 1.0
 * @since <pre>11/30/2018</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigTest {

    @Resource
    private FtpClientConfig ftpClientConfig;
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testGetConfig() throws Exception {
        System.out.println(ftpClientConfig.getUsername());
        System.out.println(ftpClientConfig.getPort());
    }


}
