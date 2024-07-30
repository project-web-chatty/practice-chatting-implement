package com.chatty.practice.suhyeon.tutorial.spring.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut1Sender {

    // RabbitTemplate client class
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500) // 빈으로 등록시 다음 스케쥴링이 실행
    public void send() {
        String message = "Hello World!";
        this.template.convertAndSend(queue.getName(), message); // send message to broker
        System.out.println(" [x] Sent '" + message + "'");
    }
}
