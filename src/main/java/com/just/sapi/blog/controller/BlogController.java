package com.just.sapi.blog.controller;

import com.just.sapi.blog.dto.BlogDTO;
import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.blog.dto.BlogSearchRankDTO;
import com.just.sapi.blog.service.BlogService;
import com.just.sapi.common.dto.ResponseAggregatedListMetaDTO;
import com.just.sapi.common.dto.ResponseDTO;
import com.just.sapi.common.dto.ResponseListMetaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<?> getBlogs(@Valid BlogSearchParamsDTO blogSearchParamsDTO) {
        Page<BlogDTO> pageResult = blogService.findBlogsByParameter(blogSearchParamsDTO);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .meta(ResponseListMetaDTO.builder()
                        .page(pageResult.getPageable().getPageNumber())
                        .pageSize(pageResult.getSize())
                        .totalPages(pageResult.getTotalPages())
                        .totalCount(pageResult.getTotalElements())
                        .build()
                )
                .data(pageResult.getContent())
                .build());
    }

    @GetMapping("/top10Keyword")
    public ResponseEntity<?> getTop10Keyword() {
        List<BlogSearchRankDTO> data = blogService.getBlogSearchRankList();

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .meta(ResponseAggregatedListMetaDTO.builder()
                        .totalCount(data.size())
                        .aggregatedAt(data.size() > 0 ? data.get(0).getAggregatedAt() : null)
                        .build()
                )
                .data(data)
                .build());
    }
}
