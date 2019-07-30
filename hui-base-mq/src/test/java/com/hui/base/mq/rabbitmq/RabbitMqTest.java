package com.hui.base.mq.rabbitmq;

import com.hui.base.mq.model.MqTestObj;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * <b><code>RabbitMqTest</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/7/29 14:28.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class RabbitMqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * ========================== 基本操作 =====================================
     */

    @Test
    public void testSend() {
        // 创建消息
        Message message = new Message("Hello world".getBytes(), new MessageProperties());

        // 普通发送，默认的路由和交换器
        rabbitTemplate.send(message);

        // 指定路由发送
        String routingKey = "Hu";
        rabbitTemplate.send(routingKey, message);

        // 指定路由和交换器发送
        String exchange = "Hu-ex";
        rabbitTemplate.send(exchange, routingKey, message);

        String uuid = UUID.randomUUID().toString();
        rabbitTemplate.send(exchange, routingKey, message, new CorrelationData(uuid));
    }


    @Test
    public void testSendObj() {
        // 创建对象
        MqTestObj mqTestObj = new MqTestObj("TEST");

        // 普通发送，默认的路由和交换器
        rabbitTemplate.convertAndSend(mqTestObj);

        // 指定路由发送
        String routingKey = "Hu";
        rabbitTemplate.convertAndSend(routingKey, mqTestObj);

        // 指定路由和交换器发送
        String exchange = "Hu-ex";
        rabbitTemplate.convertAndSend(exchange, routingKey, mqTestObj);


        String uuid = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(exchange, routingKey, mqTestObj, new CorrelationData(uuid));
    }

    @Test
    public void testReceive() {
        // 接收来自指定队列的消息，并设置超时时间
        Message msg = rabbitTemplate.receive("Hu-queue",2000l);


        // 接收消息转对象
        MqTestObj mqTestObj = (MqTestObj) rabbitTemplate.receiveAndConvert();
    }

}
