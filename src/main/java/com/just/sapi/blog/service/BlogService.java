package com.just.sapi.blog.service;

import com.just.sapi.blog.dto.BlogDTO;
import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.common.dto.ApiCallDTO;
import com.just.sapi.common.dto.KakaoApiResponseDTO;
import com.just.sapi.common.dto.KakaoMetaDTO;
import com.just.sapi.common.helper.ApiHelper;
import com.just.sapi.common.utils.SimplePageUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final ApiHelper apiHelper;

    public Page<BlogDTO> findBlogsByParameter(BlogSearchParamsDTO blogSearchParamsDTO) {
        KakaoApiResponseDTO response = apiHelper.searchBlogs(blogSearchParamsDTO);
        KakaoMetaDTO meta = response.getMeta();

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

        // page, limit, total
        Pageable pageable = SimplePageUtil.kakaoPagableRequestOf(blogSearchParamsDTO.getPage(), blogSearchParamsDTO.getLimit(), meta.getIsEnd(), meta.getPageableCount());

        return new PageImpl<>(blogs, pageable, meta.getPageableCount());
    }

}
