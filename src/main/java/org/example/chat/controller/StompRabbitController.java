package org.example.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chat.config.rabbitmq.RabbitMQConstants;
import org.example.chat.dto.ChatDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompRabbitController {

    private final RabbitTemplate template;

    @MessageMapping("chat.enter.{chatRoomId}")
    public void enter(ChatDto chatDto, @DestinationVariable String chatRoomId) {
        chatDto.setMessage("입장하셨습니다.");
        chatDto.setRegDate(LocalDateTime.now());

        // exchange
        template.convertAndSend(RabbitMQConstants.CHAT_EXCHANGE_NAME, "room." + chatRoomId, chatDto);
        // template.convertAndSend("room." + chatRoomId, chat); //queue
        // template.convertAndSend("amq.topic", "room." + chatRoomId, chat); //topic
    }


    @MessageMapping("chat.message.{chatRoomId}")
    public void send(ChatDto chatDto, @DestinationVariable String chatRoomId) {
        chatDto.setRegDate(LocalDateTime.now());

        template.convertAndSend(RabbitMQConstants.CHAT_EXCHANGE_NAME, "room." + chatRoomId, chatDto);
        //template.convertAndSend( "room." + chatRoomId, chat);
        //template.convertAndSend("amq.topic", "room." + chatRoomId, chat);
    }


}
