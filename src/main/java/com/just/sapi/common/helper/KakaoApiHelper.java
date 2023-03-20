package com.just.sapi.common.helper;

import com.just.sapi.blog.dto.BlogDTO;
import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.common.advice.ServiceException;
import com.just.sapi.common.dto.ApiCallDTO;
import com.just.sapi.common.dto.KakaoApiResponseDTO;
import com.just.sapi.common.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KakaoApiHelper implements ApiHelper {
    @Value("${TEST_URL}")
    private String SEARCH_API_URL;

    @Value("${TEST_AUTHORIZATION}")
    private String HEADER;
    private final WebClient webClient;

    @Override
    public KakaoApiResponseDTO searchBlogs(BlogSearchParamsDTO blogSearchParamsDTO) {
        return apiCall(ApiCallDTO.builder()
                .headers(getHeaders())
                .method(HttpMethod.GET)
                .url(getRequestUrl(blogSearchParamsDTO))
                .build()
        );
    }

    @Override
    public KakaoApiResponseDTO apiCall(ApiCallDTO apiCallDTO) {
        try {
            Mono<KakaoApiResponseDTO> result = webClient
                    .mutate()
                    .defaultHeaders(httpHeaders -> {
                        httpHeaders.addAll(apiCallDTO.getHeaders());
                    })
                    .build()
                    .get()
                    .uri(apiCallDTO.getUrl())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatus -> HttpStatus.is4xxClientError(), response -> {
                        return Mono.error(new ServiceException(HttpStatus.BAD_REQUEST.value(), ErrorCode.INVALID_PARAMETER.message));
                    })
                    .bodyToMono(KakaoApiResponseDTO.class);
            return result.block();
        } catch (WebClientException e) {
            throw e;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

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
