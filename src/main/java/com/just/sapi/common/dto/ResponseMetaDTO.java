package com.just.sapi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ResponseMetaDTO {
    private Boolean result;

    public ResponseMetaDTO(Boolean result) {
        this.result = result;
    }
}
