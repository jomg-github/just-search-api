package com.just.sapi.blog.service;

import com.just.sapi.blog.dto.BlogDTO;
import com.just.sapi.blog.dto.BlogSearchLogDTO;
import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.blog.dto.BlogSearchRankDTO;
import com.just.sapi.blog.entity.BlogSearchLogEntity;
import com.just.sapi.blog.repository.BlogSearchLogRepository;
import com.just.sapi.blog.repository.BlogSearchRankRepository;
import com.just.sapi.common.dto.KakaoApiResponseDTO;
import com.just.sapi.common.dto.KakaoMetaDTO;
import com.just.sapi.common.helper.ApiHelper;
import com.just.sapi.common.utils.DatetimeUtil;
import com.just.sapi.common.utils.SimplePageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableAsync
public class BlogService {
    private final ApiHelper apiHelper;
    private final BlogSearchLogRepository blogSearchLogRepository;
    private final BlogSearchRankRepository blogSearchRankRepository;

    @Transactional
    public Page<BlogDTO> findBlogsByParameter(BlogSearchParamsDTO blogSearchParamsDTO) {
        setBlogSearchLog(blogSearchParamsDTO.getQuery());
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
    @Transactional
    @Scheduled(cron = "0/10 * * * * *")
    @Async
    public void findBlogSearchRank() {
        LocalDateTime startDate = DatetimeUtil.formattedDateTime(LocalDateTime.now(), DatetimeUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_00);
        LocalDateTime endDate = startDate.plusMinutes(10);
        List<BlogSearchLogDTO> result = blogSearchRankRepository.findBlogSearchLogGroupByKeywordBetween(startDate, endDate);

        System.out.println("스케쥴러 테스트");
    }

    private void setBlogSearchLog(String keyword) {
        blogSearchLogRepository.save(
                BlogSearchLogEntity.builder()
                        .keyword(keyword)
                        .build()
        );
    }

}
