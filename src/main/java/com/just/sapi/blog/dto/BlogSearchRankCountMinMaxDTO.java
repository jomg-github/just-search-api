package com.just.sapi.blog.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BlogSearchRankCountMinMaxDTO {
    private Long count;
    private Long min;
    private Long max;
}
