package com.equinox.jms.messege;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author boreas
 * @create 2023-02-27 16:36
 */
@Slf4j
@Component
public class MqReceiver {

    /**
     * 接收消息并打印
     *
     * @param message message
     */
    @RabbitListener(queues = "myQueue")
    public void process(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info(message);
    }
}
