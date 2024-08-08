package com.chatty.practice.suhyeon.tutorial.chatapp.dto;

import com.chatty.practice.suhyeon.tutorial.chatapp.domain.ChatMessageDocument;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        CHAT,
    }
    private MessageType type;
    private String workspaceId; // 채널이 속한 워크스페이스
    private String channelId; // 채팅이 이루어지는 채널
    private String sender; // 보낸 사람
    private String message; // 메시지 내용

    public static ChatMessage from(ChatMessageDocument document){
        ChatMessage chatMessage =new ChatMessage();
        chatMessage.setType(document.getType());
        chatMessage.setChannelId(document.getChannelId());
        chatMessage.setSender(document.getSender());
        chatMessage.setMessage(document.getMessage());
        return chatMessage;
    }

}
