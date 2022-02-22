package com.devbaktiyarov.json;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class JSONConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            DeliverCallback deliverCallback = (s, delivery) -> {
                String jsonMessage = new String(delivery.getBody());
                System.out.println(jsonMessage);
            };
            channel.basicConsume("Queue1", true, deliverCallback, s -> {
            });
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
