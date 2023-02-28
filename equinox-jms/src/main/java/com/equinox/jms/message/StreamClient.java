package com.equinox.jms.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author boreas
 * @create 2023-02-27 16:21
 */
public interface StreamClient {

    // 接收消息、入口
    @Input("messageInput")
    SubscribableChannel input();

    // 发送消息
    @Output("messageOutput")
    MessageChannel output();

    @Input("messageOutput2")
    SubscribableChannel output2();
}
