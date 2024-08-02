package com.chatty.practice.suhyeon.tutorial.chatapp.service;


import com.chatty.practice.suhyeon.tutorial.chatapp.domain.ChatMessageDocument;
import com.chatty.practice.suhyeon.tutorial.chatapp.dto.ChatMessage;
import com.chatty.practice.suhyeon.tutorial.chatapp.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;
    public void saveMessageToMongoDB(ChatMessage chatMessage) {
        ChatMessageDocument document = ChatMessageDocument.from(chatMessage);

        chatMessageRepository.save(document);
    }

    public List<ChatMessage> getMessagesByChannelId(String channelId) {
        List<ChatMessageDocument> documents = chatMessageRepository.findByChannelIdOrderByTimestamp(channelId);
        return documents.stream().map((ChatMessage::from)).toList();
    }
}
