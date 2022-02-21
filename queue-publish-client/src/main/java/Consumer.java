import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            DeliverCallback deliverCallback = (s, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("The message is " + message);
            };
            channel.basicConsume("Queue1", true, deliverCallback, s -> {});
        } finally {
            channel.close();
            connection.close();
        }
    }
}
