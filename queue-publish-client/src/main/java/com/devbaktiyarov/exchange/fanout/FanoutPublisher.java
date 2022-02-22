package com.devbaktiyarov.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutPublisher {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            JSONObject fanoutMessage = new JSONObject();
            fanoutMessage.put("queue_1", "tv");
            fanoutMessage.put("queue_2", "ac");
            channel.basicPublish("Fanout-Exchange", "", null, fanoutMessage.toString().getBytes());

        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
