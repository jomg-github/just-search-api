package com.just.sapi.blog.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BlogSearchRankDTO {
    private Long rowId;
    private LocalDateTime aggregatedAt;
    private String keyword;
}
