package com.equinox.jms.message;

import com.equinox.jms.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author boreas
 * @create 2023-02-27 16:30
 */
@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

    /**
     * 接收OrderDTO对象
     * @param message message
     */
    @StreamListener("messageOutput")
    @SendTo("messageInput")
    public String process(OrderDTO message) {
        log.info("message : {}", message);
        return "success:" + message.getOrderId();
    }

    @StreamListener("messageInput")
    public void success(String message) {
        log.info("message 接收: {}", message);
    }

    @StreamListener("messageOutput2")
    @SendTo("messageInput")
    public String process(String message) {
        log.info("message : {}", message);
        return message;
    }
}
