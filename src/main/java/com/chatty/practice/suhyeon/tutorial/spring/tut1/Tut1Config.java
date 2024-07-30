package com.chatty.practice.suhyeon.tutorial.spring.tut1;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Profile({"tut1","hello-world"})
@Configuration
public class Tut1Config {

    // 큐를 빈으로 등록
    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    // receiver를 해당 프로파일이 등록된 경우에 빈으로 등록
    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    // sender를 프로파일이 등록된 경우에 빈으로 등록
    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }
}
