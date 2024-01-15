package org.example.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chat.dto.ChatDto;
import org.example.chat.dto.pushTmp.RequestChatMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitConsumer {

    private final static String CHAT_QUEUE_NAME = "chat.queue";

    private final ChatService chatService;
    private final FCMService fcmService;

    // receiver()는 단순히 큐에 들어온 메세지를 소비만 한다. (현재는 디버그 용도)
    @RabbitListener(queues = CHAT_QUEUE_NAME)
    public void receive(ChatDto chatDto) {
        // 1. msg 저장
        chatService.saveChatMessage(chatDto);

        // 2. push alarm
        fcmService.sendChatMessage(
                new RequestChatMessage(
                        "chatDto.chatDto.getChatRoomId(),roomName", "chatDto.getMemberId().name",
                        chatDto.getMessage(), chatDto.getChatRoomId().toString()));

        // 3. log
        System.out.println("chatting");
        log.info("chatDto.getRegDate() = {}", chatDto.getRegDate());
        log.info("chatDto.getMessage() = {}",chatDto.getMessage());

    }
}
