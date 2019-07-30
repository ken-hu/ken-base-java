package com.hui.base.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <b><code>KafkaTest</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/7/29 15:32.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class KafkaTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * ==================== 基本操作 ======================
     */

    @Test
    public void test(){
    }
}
