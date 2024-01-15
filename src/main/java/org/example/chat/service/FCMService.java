package org.example.chat.service;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chat.dto.pushTmp.RequestChatMessage;
import org.example.chat.dto.pushTmp.RequestSubscribe;
import org.example.chat.dto.pushTmp.RequestUnsubscribe;
import org.example.chat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FCMService {

    /*public void sendToTopic(ChatDto chatDto) {
        // Define the FCM topic based on your requirements
        String topic = "chatroom_" + chatDto.getChatRoomId();

        // Construct FCM message
        Message message = Message.builder()
                .setTopic(topic)
                .setNotification(Notification.builder().setBody(chatDto.getMessage()).build())
                .build();

        // Send FCM message
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
            // Handle the response if needed
        } catch (FirebaseMessagingException e) {
            // Handle exception
            e.printStackTrace();
        }

    }*/

    public void sendChatMessage(RequestChatMessage requestChatMessage) {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(requestChatMessage.getChatMessage())
                        .setBody(requestChatMessage.getUserName() + ": " + requestChatMessage.getChatMessage())
                        .build())
                .setTopic(requestChatMessage.getTopic())
                .build();

        send(message);
    }

    public void subscribeTopic(RequestSubscribe requestSubscribe) {
        List<String> registrationTokens = Arrays.asList(
                requestSubscribe.getToken()
        );

        try {
            for (String topic : requestSubscribe.getTopicList()) {
                TopicManagementResponse response = FirebaseMessaging
                        .getInstance()
                        .subscribeToTopic(registrationTokens, topic);

                log.info(response.getSuccessCount() + " tokens were subscribed successfully");
            }
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void unsubscribeTopic(RequestUnsubscribe requestUnsubscribe) {
        List<String> registrationTokens = Arrays.asList(
                requestUnsubscribe.getToken()
        );

        try {
            for (String topic : requestUnsubscribe.getTopicList()) {
                FirebaseMessaging
                        .getInstance()
                        .subscribeToTopic(registrationTokens, topic);
            }
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(Message message) {
        String response = String.valueOf(FirebaseMessaging.getInstance().sendAsync(message));
        log.info("Successfully sent message: " + response);
    }
}
