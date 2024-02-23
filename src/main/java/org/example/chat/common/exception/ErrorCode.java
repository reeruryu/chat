package org.example.chat.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public enum ErrorCode {
    ERROR_001("EXAMPLE001", "에러 메시지를 적어주세요");

    // 에러 코드의 '코드간 구분값'을 반환한다.
    private String divisionCode;

    // 에러 코드의 '코드 메시지'를 반환한다.
    private String message;

}
