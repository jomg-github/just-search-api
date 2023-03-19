package com.just.sapi.blog.service;

import com.just.sapi.blog.dto.BlogDTO;
import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.common.dto.ApiCallDTO;
import com.just.sapi.common.helper.ApiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final ApiHelper apiHelper;

    public Page<BlogDTO> findBlogsByParameter(BlogSearchParamsDTO blogSearchParamsDTO) {
        Pageable pageable = PageRequest.of(blogSearchParamsDTO.getPage() - 1, blogSearchParamsDTO.getLimit());
        return apiHelper.searchBlogs(blogSearchParamsDTO, pageable);
    }

}
