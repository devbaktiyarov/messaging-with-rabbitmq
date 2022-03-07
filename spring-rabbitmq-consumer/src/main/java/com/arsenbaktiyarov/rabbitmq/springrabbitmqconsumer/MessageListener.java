package com.arsenbaktiyarov.rabbitmq.springrabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public void dequeueMessage(CustomMessage message) {
        System.out.println(message);
    }
}
