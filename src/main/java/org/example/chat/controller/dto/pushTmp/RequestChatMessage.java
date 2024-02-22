package org.example.chat.controller.dto.pushTmp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RequestChatMessage {
    String roomName;
    String userName;
    String chatMessage;
    String topic;
}
