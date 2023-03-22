package com.just.sapi.blog.repository;

import com.just.sapi.blog.dto.BlogSearchRankCountMinMaxDTO;
import com.just.sapi.blog.dto.BlogSearchRankDTO;

import java.time.LocalDateTime;

public interface BlogSearchRankCustomRepository {
    BlogSearchRankCountMinMaxDTO findCountMinMaxBetween(LocalDateTime startDate, LocalDateTime endDate);
    BlogSearchRankDTO findTop1OrderByRowIdDesc();
}
