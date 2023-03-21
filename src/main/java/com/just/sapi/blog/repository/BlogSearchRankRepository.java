package com.just.sapi.blog.repository;

import com.just.sapi.blog.dto.BlogSearchLogDTO;
import com.just.sapi.blog.dto.BlogSearchRankCountMinMaxDTO;
import com.just.sapi.blog.dto.BlogSearchRankDTO;
import com.just.sapi.blog.entity.BlogSearchRankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogSearchRankRepository extends JpaRepository<BlogSearchRankEntity, Long>, BlogSearchRankCustomRepository {
}
