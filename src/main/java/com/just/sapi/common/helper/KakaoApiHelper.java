package com.just.sapi.common.helper;

import com.just.sapi.blog.dto.BlogDTO;
import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.common.dto.ApiCallDTO;
import com.just.sapi.common.dto.KakaoApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoApiHelper implements ApiHelper {
    @Value("${TEST_URL}")
    private String SEARCH_API_URL;

    @Value("${TEST_AUTHORIZATION}")
    private String HEADER;
    private final WebClient webClient;

    @Override
    public Page<BlogDTO> searchBlogs(BlogSearchParamsDTO blogSearchParamsDTO, Pageable pageable) {
        KakaoApiResponseDTO response = apiCall(ApiCallDTO.builder()
                .headers(getHeaders())
                .method(HttpMethod.GET)
                .url(getRequestUrl(blogSearchParamsDTO))
                .build()
        );

        List<BlogDTO> blogs = response.getDocuments().stream()
                .map(blog -> BlogDTO.builder()
                        .title(blog.getTitle())
                        .contents(blog.getContents())
                        .url(blog.getUrl())
                        .blogName(blog.getBlogName())
                        .thumbnailUrl(blog.getThumbnailUrl())
                        .datetime(blog.getDatetime())
                        .build()
                )
                .toList();

        return new PageImpl<>(blogs, pageable, response.getMeta().getPageableCount());
    }

    @Override
    public KakaoApiResponseDTO apiCall(ApiCallDTO apiCallDTO) {
        KakaoApiResponseDTO result = webClient
                .mutate()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.addAll(apiCallDTO.getHeaders());
                })
                .build()
                .get()
                .uri(apiCallDTO.getUrl())
                .retrieve()
                .bodyToMono(KakaoApiResponseDTO.class).block();

        return result;
    }

    private String getRequestUrl(BlogSearchParamsDTO blogSearchParamsDTO) {
        return UriComponentsBuilder.fromUriString(SEARCH_API_URL)
                .queryParam("query", blogSearchParamsDTO.getQuery())
                .queryParam("sort", blogSearchParamsDTO.getSort())
                .queryParam("page", blogSearchParamsDTO.getPage())
                .queryParam("size", blogSearchParamsDTO.getLimit())
                .build().toUriString();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, HEADER);
        return headers;
    }
}
