package com.chatty.practice.suhyeon.tutorial.spring.tut2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StopWatch;


// 리스너 내부에서 exception이 터지면 channel.basicReject(deliveryTag, requeue) 이 호출된다.
// default는 다시 enqueueq
@RabbitListener(queues = "hello")
public class Tut2Receiver {
    private final int instance;

    public Tut2Receiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + this.instance +
                " [x] Received '" + in + "'");
        doWork(in); // 해당 task를 긴 시간 동안 해결
        watch.stop();
        System.out.println("instance " + this.instance +
                " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(500);
            }
        }
    }
}
