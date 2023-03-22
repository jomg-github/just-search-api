package com.just.sapi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ResponseErrorDTO<T> extends ResponseDTO<T> {

    private ResponseError error;

    @AllArgsConstructor
    @Getter
    public static class ResponseError {
        private Integer code;
        private String message;
    }

    public ResponseErrorDTO(ResponseMetaDTO meta, T data, ResponseError error) {
        super(meta, data);
        this.error = error;
    }
}
