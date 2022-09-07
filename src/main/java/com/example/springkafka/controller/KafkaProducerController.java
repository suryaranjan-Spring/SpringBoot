package com.example.springkafka.controller;

import com.example.springkafka.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author Dracula
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    private User user;
@GetMapping("/testkafkaproducer")
    public String testKafka() {
    kafkaTemplate.send("product","it is working fine");
        return "It is Working fine !!";
    }
    @GetMapping("/userTest")
    public User userTest(){
    kafkaTemplate.send("product",user);
    return user;
    }
    @PostConstruct
    public void setUser(){
        user=new User("surya","23","satapthy");
    }
    @GetMapping("/test")
    public String getStatus(){
    return "it is working fine !!";
    }
}
