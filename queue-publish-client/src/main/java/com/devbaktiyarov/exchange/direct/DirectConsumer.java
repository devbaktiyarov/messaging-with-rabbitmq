package com.devbaktiyarov.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            DeliverCallback deliverCallback = (s, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Info of mobile " + message);
            };
            channel.basicConsume("Mobile", true, deliverCallback, s -> {
            });
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
