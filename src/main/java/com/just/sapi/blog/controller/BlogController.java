package com.just.sapi.blog.controller;

import com.just.sapi.blog.dto.BlogDTO;
import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.blog.dto.BlogSimplePageDTO;
import com.just.sapi.blog.service.BlogService;
import com.just.sapi.common.dto.MetaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<?> getBlogs(@Valid BlogSearchParamsDTO blogSearchParamsDTO) {
        Page<BlogDTO> pageResult = blogService.findBlogsByParameter(blogSearchParamsDTO);

        BlogSimplePageDTO simplePageResult = BlogSimplePageDTO.builder()
                .items(pageResult.getContent())
                .meta(MetaDTO.builder()
                        .page(pageResult.getPageable().getPageNumber() + 1)
                        .pageSize(pageResult.getSize())
                        .totalPages(pageResult.getTotalPages())
                        .totalCount(pageResult.getTotalElements())
                        .build()
                ).build();

        return ResponseEntity.status(HttpStatus.OK).body(simplePageResult);
    }

}
