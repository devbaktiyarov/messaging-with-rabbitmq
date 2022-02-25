package com.arsenbaktiyarov.restrabbitmq;

import com.arsenbaktiyarov.restrabbitmq.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
public class RabbitConsumer {

    @RabbitListener(queues = "AC")
    public void getMessage(Person person) {
        System.out.println(person);
    }

    @RabbitListener(queues = "Mobile")
    public void getMessage(byte[] message) {
        try (ByteArrayInputStream bin = new ByteArrayInputStream(message);
             ObjectInput oi = new ObjectInputStream(bin)) {
            Person person = (Person) oi.readObject();
            System.out.println(person.getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
