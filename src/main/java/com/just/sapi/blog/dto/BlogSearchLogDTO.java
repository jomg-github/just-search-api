package com.just.sapi.blog.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BlogSearchLogDTO {
    private String keyword;
    private Long count;
}
