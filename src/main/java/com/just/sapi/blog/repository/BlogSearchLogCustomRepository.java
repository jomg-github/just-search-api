package com.just.sapi.blog.repository;

import com.just.sapi.blog.dto.BlogSearchLogDTO;
import com.just.sapi.blog.dto.BlogSearchRankCountMinMaxDTO;
import com.just.sapi.blog.dto.BlogSearchRankDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogSearchLogCustomRepository {
    List<BlogSearchLogDTO> findBlogSearchLogGroupByKeywordBetween(LocalDateTime startDate, LocalDateTime endDate);
}
