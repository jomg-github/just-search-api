package com.just.sapi.common.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ResponseDTO<T> {
    private ResponseMetaDTO meta;
    private T data;

    public ResponseDTO(ResponseMetaDTO meta, T data) {
        this.meta = meta;
        this.data = data;
    }
}
