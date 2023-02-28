package com.equinox.jms.controller;

import com.equinox.jms.dto.OrderDTO;
import com.equinox.jms.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author boreas
 * @create 2023-02-27 16:17
 */
@RequestMapping(value = "/message")
@RestController
public class SendMessageController {

    private final StreamClient streamClient;

    @Autowired
    public SendMessageController(StreamClient streamClient) {
        this.streamClient = streamClient;
    }

    @GetMapping("/send/msg")
    public void send() {
        for (int i = 0; i < 100; i++) {
            MessageBuilder<String> messageBuilder = MessageBuilder.withPayload("这是第" + i + "条消息");
            streamClient.output2().send(messageBuilder.build());
        }
    }

    /**
     * 发送OrderDTO对象
     */
    @GetMapping("/send/dto/msg")
    public void sendDto() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123465");

        MessageBuilder<OrderDTO> messageBuilder = MessageBuilder.withPayload(orderDTO);
        streamClient.output().send(messageBuilder.build());
    }
}
