package com.just.sapi.blog.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class BlogSearchParamsDTO {
    @NotNull(message = "검색어를 입력하세요.")
    private String query;
    private String sort = "accuracy";
    @Range(min = 1, max = 50)
    private Integer page = 1;
    @Range(min = 1, max = 50)
    private Integer limit = 10;
    @Builder
    public BlogSearchParamsDTO(String query, String sort, Integer page, Integer limit) {
        this.query = query;
        this.sort = sort == null ? this.sort : sort;
        this.page = page == null ? this.page : page;
        this.limit = limit == null ? this.limit : limit;
    }
}
