package com.just.sapi.blog.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogSearchParamsDTO {
    @NotNull
    private String query;
    @Builder.Default
    private String sort = "accuracy";
    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer limit = 10;
}
