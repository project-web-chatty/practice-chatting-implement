package com.chatty.practice.suhyeon.tutorial.chatapp.controller;

import com.chatty.practice.suhyeon.tutorial.chatapp.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SimpMessageSendingOperations sendingOperations;


    // 웹소켓으로 특정 채널로 메시지를 보낸다.
    @MessageMapping("/message")
    public void send(@Payload ChatMessage message) {
        //
        String channelId = message.getChannelId();
        sendingOperations.convertAndSend("/topic/" + channelId,message);
        // db에 저장
        chatService.saveMessageToMongoDB(message);
    }

    // 특정 채널 페이지에 들어갔을때 db에서 메시지를 가져온다.
    @GetMapping("/channel/{channelId}")
    public List<ChatMessage> getMessagesByChannel(@PathVariable String channelId) {
        return chatService.getMessagesByChannelId(channelId);
    }

}
