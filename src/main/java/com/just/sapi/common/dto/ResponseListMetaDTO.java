package com.just.sapi.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ResponseListMetaDTO extends ResponseMetaDTO {
    private int page;
    private int pageSize;
    private long totalPages;
    private long totalCount;

    public ResponseListMetaDTO(Boolean result, int page, int pageSize, long totalPages, long totalCount) {
        super(result);
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }
}
