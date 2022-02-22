package com.devbaktiyarov.json;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class JSONPublisher {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            JSONObject message = new JSONObject();
            message.put("position", "hr");
            message.put("from_date", "20-02-2021");
            message.put("to_date", "11-10-2022");
            channel.basicPublish("", "Queue1", null, message.toString().getBytes());

        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
