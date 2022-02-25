package com.arsenbaktiyarov.restrabbitmq.controller;

import com.arsenbaktiyarov.restrabbitmq.model.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/api/v1")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String DIRECT_EXCHANGE = "Direct-Exchange";
    private static final String HEADERS_EXCHANGE = "Headers-Exchange";
    private static final String TOPIC_EXCHANGE = "Topic-Exchange";
    private static final String FANOUT_EXCHANGE = "Fanout-Exchange";

    @GetMapping("/person/{name}")
    public String createPerson(@PathVariable String name) {
        Person person = new Person(1L, name);
        rabbitTemplate.convertAndSend("ac", person);
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, "tv", person);
        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE, "", person);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, "tv.mobile.ac", person);
        return "Person's successfully created";
    }

    @GetMapping("person/headers/{name}")
    public String createPersonWithHeaders(@PathVariable String name) {
        Person person = new Person(1L, name);
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput ous = new ObjectOutputStream(bos)) {
            ous.writeObject(person);
            byte[] byteMessage = bos.toByteArray();

            Message message = MessageBuilder.withBody(byteMessage)
                    .setHeader("item1", "mobile")
                    .setHeader("item2", "television").build();

            rabbitTemplate.send(HEADERS_EXCHANGE, "", message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Person's successfully created with headers exchange";
    }

}
