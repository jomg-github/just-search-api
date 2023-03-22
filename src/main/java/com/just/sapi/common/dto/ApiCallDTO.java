package com.just.sapi.common.dto;

import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Data
public class ApiCallDTO {
    HttpHeaders headers;
    HttpMethod method;
    String url;
    BlogSearchParamsDTO params;

    @Builder
    public ApiCallDTO(HttpHeaders headers, HttpMethod method, String url, BlogSearchParamsDTO params) {
        this.headers = headers;
        this.method = method;
        this.url = url;
        this.params = params;
    }
}
