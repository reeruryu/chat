package org.example.chat.common.exception;

import lombok.Builder;
import lombok.Getter;

public class ExceptionDto {
    @Getter
    @Builder
    public static class Response {
        public String message;
    }
}
