package com.equinox.jms.messege;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author boreas
 * @create 2023-02-27 17:53
 */
@Slf4j
@Component
public class MqReceiver2 {


    //数码供应商服务
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("order-computer")
    ))
    public void processComputer(String message){
        log.info("数码供应商服务 消息接受：{}",message);
    }

    //数码供应商服务
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("order-computer")
    ))
    public void processComputer2(String message){
        log.info("数码供应商服务2 消息接受：{}",message);
    }

    //水果供应商服务
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("order-fruit")
    ))
    public void processFruit(String message){
        log.info("水果供应商服务 消息接受：{}",message);
    }


}