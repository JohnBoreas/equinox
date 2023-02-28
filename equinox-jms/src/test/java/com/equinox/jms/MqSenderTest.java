package com.equinox.jms;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author boreas
 * @create 2023-02-27 16:32
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqSenderTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        for (int i = 0; i < 100; i++) {
            amqpTemplate.convertAndSend("myQueue", "第" + i + "条消息");
            log.info("发送消息：" + i);

            String message = "发送的消息：" + new Date();
            amqpTemplate.convertAndSend("myOrder","computer", "第" + i + "条消息");// 通过exchange根据routing-key匹配发送
            amqpTemplate.convertAndSend("myOrder", "fruit", "第" + i + "条消息");
            amqpTemplate.convertAndSend("jms.fanout", null, "第" + i + "条消息");// 发送到所有的queue上，忽略routing-key
        }
    }
}