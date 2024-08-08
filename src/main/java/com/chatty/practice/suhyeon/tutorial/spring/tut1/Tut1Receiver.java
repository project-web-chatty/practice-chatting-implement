package com.chatty.practice.suhyeon.tutorial.spring.tut1;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class Tut1Receiver {

    @RabbitHandler // 메시지를 받아온뒤 아래 메서드를 콜백의 형태로 비동기적 실행
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }
}
