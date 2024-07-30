package com.chatty.practice.suhyeon.tutorial.spring.tut3;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut3", "pub-sub", "publish-subscribe"})
@Configuration
public class Tut3Config {
    // 어플리케이션이 실행되고 다음 빈들이 등록되면 메시지 브로커에 큐와 exchange, 바인딩이 생성
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
            // 익명 큐 : 컨슈머와의 연결이 끊겨지면 큐와 바인딩이 삭제된다. (exclusive or auto-delete)
            // exclusiv셧e queue? 특정 커넥션으로 생성된 큐는 해당 커넥션에서만 배타적으로 사용되면 연결이 끊기면 사라진다.
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1(FanoutExchange fanout,
                                Queue autoDeleteQueue1) { // 파라미터명 autoDeleteQueue1 로 큐를 찾아서 fanout에 바인딩
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout,
                                Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }

        @Bean
        public Tut3Receiver receiver() {
            return new Tut3Receiver();
        }
    }

    @Profile("sender")
    @Bean
    public Tut3Sender sender() {
        return new Tut3Sender();
    }
}
