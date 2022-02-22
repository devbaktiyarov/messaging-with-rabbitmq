package com.devbaktiyarov.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectPublisher {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            JSONObject mobile = new JSONObject();
            mobile.put("name", "redmi 11");
            mobile.put("release_date", "28-10-2021");
            channel.basicPublish("Direct-Exchange", "mobile", null, mobile.toString().getBytes());

        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
