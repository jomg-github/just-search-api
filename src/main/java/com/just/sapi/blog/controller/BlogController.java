package com.just.sapi.blog.controller;

import com.just.sapi.blog.dto.BlogDTO;
import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.blog.dto.BlogSimplePageUtilDTO;
import com.just.sapi.blog.service.BlogService;
import com.just.sapi.common.dto.ResponseErrorDTO;
import com.just.sapi.common.dto.ResponseListMetaDTO;
import com.just.sapi.common.dto.ResponseMetaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<?> getBlogs(@Valid BlogSearchParamsDTO blogSearchParamsDTO) {
        Page<BlogDTO> pageResult = blogService.findBlogsByParameter(blogSearchParamsDTO);

        BlogSimplePageUtilDTO simplePageResult = BlogSimplePageUtilDTO.builder()
                .items(pageResult.getContent())
                .meta(ResponseListMetaDTO.builder()
                        .page(pageResult.getPageable().getPageNumber())
                        .pageSize(pageResult.getSize())
                        .totalPages(pageResult.getTotalPages())
                        .totalCount(pageResult.getTotalElements())
                        .build()
                ).build();

        return ResponseEntity.status(HttpStatus.OK).body(simplePageResult);
    }

//    @ExceptionHandler
//    public Object handle(RuntimeException e) {
//        e.printStackTrace();
//        return ResponseErrorDTO.builder()
//                .meta(new ResponseMetaDTO(Boolean.FALSE))
//                .error(new ResponseErrorDTO.ResponseError(HttpStatus.BAD_REQUEST.value(), "테스트2"))
//                .build();
//    }
//    @ExceptionHandler
//    public ResponseEntity<?> handle(WebClientException e) {
//        throw e;
//    }
}
