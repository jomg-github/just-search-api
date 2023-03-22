package com.just.sapi.common.dto;


import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class ResponseAggregatedListMetaDTO extends ResponseMetaDTO {
    private LocalDateTime aggregatedAt;
    private long totalCount;

    public ResponseAggregatedListMetaDTO(Boolean result, LocalDateTime aggregatedAt, long totalCount) {
        super(result);
        this.aggregatedAt = aggregatedAt;
        this.totalCount = totalCount;
    }
}
