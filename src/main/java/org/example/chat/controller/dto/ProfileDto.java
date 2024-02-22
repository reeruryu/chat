package org.example.chat.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class ProfileDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {

        @NotBlank(message = "필수 입력입니다.")
        private String nickName;

    }

}
