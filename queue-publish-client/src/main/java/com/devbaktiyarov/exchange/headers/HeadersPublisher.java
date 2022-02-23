package com.devbaktiyarov.exchange.headers;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersPublisher {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            String message = "Message to AC and Mobile using Headers Exchange";
            Map<String, Object> headers = new HashMap<>();
            headers.put("item1", "ac");
            headers.put("item2", "mob");
            BasicProperties properties = new BasicProperties().builder().headers(headers).build();
            channel.basicPublish("Headers-Exchange", "", properties, message.getBytes());

        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
