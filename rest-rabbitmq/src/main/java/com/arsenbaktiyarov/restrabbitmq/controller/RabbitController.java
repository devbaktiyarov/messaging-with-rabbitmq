package com.arsenbaktiyarov.restrabbitmq.controller;

import com.arsenbaktiyarov.restrabbitmq.model.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @GetMapping("/person/{name}")
    public String createPerson(@PathVariable String name) {
        Person person = new Person(1L, name);
        rabbitTemplate.convertAndSend("ac", person);
        rabbitTemplate.convertAndSend("Direct-Exchange", "tv", person);
        rabbitTemplate.convertAndSend("Fanout-Exchange","", person);
        rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", person);
        return "Person's successfully created";
    }

}
