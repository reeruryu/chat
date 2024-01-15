package org.example.chat.dto.pushTmp;

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
