package org.example.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.chat.dto.pushTmp.RequestChatMessage;
import org.example.chat.dto.pushTmp.RequestSubscribe;
import org.example.chat.dto.pushTmp.RequestUnsubscribe;
import org.example.chat.service.FCMService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fcm")
public class FcmController {

    private final FCMService fcmService;

    /*
        해당 기기의 token과 구독할 주제(topic)들을 담아 구독을 요청할 수 있다.
     */
    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(@RequestBody RequestSubscribe requestSubscribe) {
        fcmService.subscribeTopic(requestSubscribe);

        return ResponseEntity.noContent().build();
    }

    /*
        구독(subscribe) 취소를 위한 API 이다.
     */
    @DeleteMapping("/unsubscribe")
    public ResponseEntity<Void> unsubscribe(@RequestBody RequestUnsubscribe requestUnsubscribe) {
        fcmService.unsubscribeTopic(requestUnsubscribe);

        return ResponseEntity.noContent().build();
    }

    /*
        채팅 알람 요청 API 이다.
        roomName(채팅방 이름), userName(유저 이름), chatMessage(채팅 메시지), topic(채팅방 ID) 를 넘겨주면
        해당 topic(채팅방 ID)를 구독한 사람들(모임원들)에게 채팅 알람을 보내준다.
     */
    @PostMapping("/sendChatMessage")
    public ResponseEntity<Void> sendChatMessage(@RequestBody RequestChatMessage requestChatMessage) {
        fcmService.sendChatMessage(requestChatMessage);

        return ResponseEntity.noContent().build();
    }
}
