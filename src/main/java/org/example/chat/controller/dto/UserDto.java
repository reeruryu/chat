package org.example.chat.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.chat.entity.User;

public class UserDto {
    @Getter
    @Builder
    public static class SignUpRequest {
        @NotBlank(message = "필수 입력입니다.")
        private String email;

        @Setter
        @NotBlank(message = "필수 입력입니다.")
        private String password;

        @NotBlank(message = "필수 입력입니다.")
        private String userName;
    }

    @Getter
    @Builder
    public static class LoginRequest {
        @NotBlank(message = "필수 입력입니다.")
        private String email;

        @NotBlank(message = "필수 입력입니다.")
        private String password;
    }

}
