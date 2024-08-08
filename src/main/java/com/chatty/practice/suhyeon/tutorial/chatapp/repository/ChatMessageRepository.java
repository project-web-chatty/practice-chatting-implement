package com.chatty.practice.suhyeon.tutorial.chatapp.repository;

import com.chatty.practice.suhyeon.tutorial.chatapp.domain.ChatMessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessageDocument, String> {
    List<ChatMessageDocument> findByChannelIdOrderByTimestamp(String channelId);
}
