package com.chatty.practice.suhyeon.tutorial.chatapp.domain;

import com.chatty.practice.suhyeon.tutorial.chatapp.dto.ChatMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_messages")
@Getter
@Setter
public class ChatMessageDocument {
    @Id
    private String id;
    private ChatMessage.MessageType type;
    private String workspaceId;
    private String channelId;
    private String sender;
    private String message;
    private LocalDateTime timestamp; // 메시지 작성 시각 추가
    public static ChatMessageDocument from(ChatMessage chatMessage){
        ChatMessageDocument document = new ChatMessageDocument();
        document.setWorkspaceId(chatMessage.getWorkspaceId());
        document.setChannelId(chatMessage.getChannelId());
        document.setSender(chatMessage.getSender());
        document.setMessage(chatMessage.getMessage());
        document.setType(chatMessage.getType());
        document.setTimestamp(LocalDateTime.now());
        return document;
    }

}
