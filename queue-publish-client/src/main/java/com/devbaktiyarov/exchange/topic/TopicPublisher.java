package com.devbaktiyarov.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicPublisher {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            String message = "Message to AC and Mobile using Topic Exchange";
            channel.basicPublish("Topic-Exchange", "pc.mobile.ac", null, message.getBytes());
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}

