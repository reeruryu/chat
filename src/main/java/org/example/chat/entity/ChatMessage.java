package org.example.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CHAT_MESSAGE")
public class ChatMessage {

    @Id
    @Column(name = "chat_message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Column(name = "content")
    private String content;

    //private LocalDateTime localDateTime;
    @Builder
    public ChatMessage(User user, ChatRoom chatRoom, String content) {
        this.user = user;
        this.chatRoom = chatRoom;
        this.content = content;
    }

}
