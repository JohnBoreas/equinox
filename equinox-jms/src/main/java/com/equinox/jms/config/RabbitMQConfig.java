package com.equinox.jms.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author boreas
 * @create 2023-02-27 17:42
 */
@Component
public class RabbitMQConfig {
    /**
     * 定义交换机
     */
    private String EXCHANGE_SPRINGBOOT_NAME = "springboot_topic_exchange";

    /**
     * 短信队列
     */
    private String FANOUT_SMS_QUEUE = "springboot_topic_sms_queue";
    /**
     * 邮件队列
     */
    private String FANOUT_SMS_EMAIL = "springboot_topic_email_queue";

    /**
     * 创建短信队列
     */
    @Bean
    public Queue smsQueue() {
        return new Queue(FANOUT_SMS_QUEUE);
    }

    /**
     * 创建邮件队列
     */
    @Bean
    public Queue emailQueue() {
        return new Queue(FANOUT_SMS_EMAIL);
    }

    /**
     * 创建交换机
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_SPRINGBOOT_NAME);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("jms.fanout");
    }

    /**
     * 定义短信队列绑定交换机
     */
    @Bean
    public Binding smsBindingExchange(Queue smsQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(smsQueue).to(topicExchange).with("my.sms");
    }

    /**
     * 定义邮件队列绑定交换机
     */
    @Bean
    public Binding emailBindingExchange(Queue emailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(emailQueue).to(topicExchange).with("my.#");
    }
}