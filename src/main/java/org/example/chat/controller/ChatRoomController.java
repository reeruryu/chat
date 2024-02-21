package org.example.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.chat.entity.ChatParticipation;
import org.example.chat.entity.ChatRoom;
import org.example.chat.entity.User;
import org.example.chat.repository.ChatParticipationRepository;
import org.example.chat.repository.ChatRoomRepository;
import org.example.chat.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatParticipationRepository chatParticipationRepository;
    @GetMapping("/chat/rooms")
    public String getRooms() {
        return "chat/rooms";
    }

    @GetMapping("/chat/room")
    public String getRoom(Long chatRoomId, String nickname, Model model) {
        model.addAttribute("chatRoomId", chatRoomId);
        model.addAttribute("nickname", nickname);
        return "chat/room";
    }

    /* test api */
    @PostMapping("/api/chat/rooms")
    public ResponseEntity<Long> createRoom(
            @RequestParam("rName") String roomName,
            @RequestParam("uIds") List<Long> userIds
    ) {

        // 0. user 갖고 오기 (onetomany 안해씀)
        List<User> users = userRepository.findByIdIn(userIds);

        // 1. 채팅룸 만들기
        ChatRoom chatRoom = chatRoomRepository.save(
                ChatRoom.builder().roomName(roomName).build()
        );

        // 2. 채팅 참여 만들기
        chatParticipationRepository.saveAll(
                users.stream()
                        .map(user ->
                                ChatParticipation.builder()
                                        .user(user)
                                        .chatRoom(chatRoom)
                                        .build()
                        )
                        .collect(Collectors.toList())
        );

        // 3. 채팅방 구독 (FCM)
        // subscribeTopic인데, tokenList와 topic으로 변경해서 구독하기

        return ResponseEntity.ok(chatRoom.getId());
    }

}
