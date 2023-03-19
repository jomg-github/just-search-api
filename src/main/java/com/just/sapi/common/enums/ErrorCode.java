package com.just.sapi.common.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터가 유효하지 않습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "시스템 오류가 발생했습니다."),
    ;

    public final HttpStatus httpStatus;
    public final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
