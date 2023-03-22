package com.just.sapi.blog.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogSearchRankDTO {
    @JsonIgnore
    private LocalDateTime aggregatedAt;
    private String keyword;
    private Long count;
}
