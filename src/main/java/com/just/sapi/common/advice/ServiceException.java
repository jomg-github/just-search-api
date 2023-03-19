package com.just.sapi.common.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ServiceException extends RuntimeException {
    private Integer code;
    private String message;
}
