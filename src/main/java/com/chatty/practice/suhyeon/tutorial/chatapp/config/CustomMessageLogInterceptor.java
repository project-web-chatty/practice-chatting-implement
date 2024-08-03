package com.chatty.practice.suhyeon.tutorial.chatapp.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompDecoder;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;


@Component
public class CustomMessageLogInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        System.out.println("commend = "+ accessor.getCommand());
        String payload = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        System.out.println("payload = " + payload);

        return message;
    }
}
