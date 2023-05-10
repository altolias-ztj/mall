package com.woniuxy.mall;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQTest {
    private final static String QUEUE_NAME = "again";

    @Test
    public void TestRabbitMQ() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.11.157");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
        System.in.read();
        /*ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.11.157");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue_hello", false, false, false, null);
        String message = "Hello World!";
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "queue_hello", null, (message + i).getBytes());

        }
        System.out.println(" [x] Sent '" + message + "'");*/
    }
}
