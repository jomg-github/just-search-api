package com.just.sapi.blog.repository;

import com.just.sapi.blog.dto.BlogSearchLogDTO;
import com.just.sapi.blog.dto.BlogSearchRankCountMinMaxDTO;
import com.just.sapi.blog.dto.BlogSearchRankDTO;
import com.just.sapi.blog.entity.BlogSearchRankEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogSearchRankCustomRepository {
    BlogSearchRankCountMinMaxDTO findCountMinMaxBetween(LocalDateTime startDate, LocalDateTime endDate);
    BlogSearchRankDTO findTop1OrderByRowIdDesc();
}
