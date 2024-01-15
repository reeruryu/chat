package org.example.chat.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatDto implements Serializable {
    private Long id;
    private Long chatRoomId;
    private Long memberId;
    private String message;

    /*@JsonDeserialize(using = LocalDateTimeDeserializer.class)*/
    private LocalDateTime regDate;
}
