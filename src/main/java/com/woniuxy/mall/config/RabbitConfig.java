package com.woniuxy.mall.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange("mail_exchange");
    }

    @Bean
    public Queue mailQueue() {
        return new Queue("mail_queue");
    }

    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with("mail");
    }

    @Bean
    public FanoutExchange orderExchange1() {
        return new FanoutExchange("order_exchange_1");
    }

    @Bean
    public Queue orderQueue1() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 2 * 60 * 1000);
        return new Queue("order_queue_1", true, false, false, map);
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(mailQueue()).to(orderExchange1());
    }
}
