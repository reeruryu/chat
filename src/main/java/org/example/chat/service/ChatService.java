package org.example.chat.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.chat.dto.ChatDto;
import org.example.chat.entity.ChatMessage;
import org.example.chat.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public void saveChatMessage(ChatDto chatDto) {
        chatMessageRepository.save(ChatMessage.builder()
                .user(null)
                .chatRoom(null)
                .content(chatDto.getMessage())
                .build());
    }
}
