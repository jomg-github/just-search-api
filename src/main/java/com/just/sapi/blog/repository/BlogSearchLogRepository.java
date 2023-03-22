package com.just.sapi.blog.repository;

import com.just.sapi.blog.entity.BlogSearchLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogSearchLogRepository extends JpaRepository<BlogSearchLogEntity, Long>, BlogSearchLogCustomRepository {
    List<BlogSearchLogEntity> findAllByKeywordEquals(String keyword);
    List<BlogSearchLogEntity> findBlogSearchLogEntitiesByKeywordIn(List<String> keywords);
}
