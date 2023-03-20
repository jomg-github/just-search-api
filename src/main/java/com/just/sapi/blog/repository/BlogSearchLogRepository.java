package com.just.sapi.blog.repository;

import com.just.sapi.blog.entity.BlogSearchLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSearchLogRepository extends JpaRepository<BlogSearchLogEntity, Long> {
}
