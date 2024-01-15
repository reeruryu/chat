package org.example.chat.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
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
