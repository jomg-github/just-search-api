package com.just.sapi.common.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
public class MetaDTO {
    private int page;
    private int pageSize;
    private long totalPages;
    private long totalCount;

    @Builder
    public MetaDTO(int page, int pageSize, long totalPages, long totalCount) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }
}
