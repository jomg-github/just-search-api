package com.just.sapi.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ResponseMetaDTO {
    private Boolean result;

    public ResponseMetaDTO(Boolean result) {
        this.result = result;
    }
}
