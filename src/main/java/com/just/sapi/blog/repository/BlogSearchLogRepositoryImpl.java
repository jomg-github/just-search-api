package com.just.sapi.blog.repository;

import com.just.sapi.blog.dto.BlogSearchLogDTO;
import com.just.sapi.blog.dto.BlogSearchRankCountMinMaxDTO;
import com.just.sapi.blog.dto.BlogSearchRankDTO;
import com.just.sapi.blog.entity.QBlogSearchLogEntity;
import com.just.sapi.blog.entity.QBlogSearchRankEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BlogSearchLogRepositoryImpl implements BlogSearchLogCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QBlogSearchLogEntity qBlogSearchLogEntity = QBlogSearchLogEntity.blogSearchLogEntity;

    @Override
    public List<BlogSearchLogDTO> findBlogSearchLogGroupByKeywordBetween(LocalDateTime startDate, LocalDateTime endDate) {
        NumberPath<Long> aliasCount = Expressions.numberPath(Long.class, "count");
        return jpaQueryFactory.select(
                Projections.constructor(
                        BlogSearchLogDTO.class,
                        qBlogSearchLogEntity.keyword,
                        qBlogSearchLogEntity.keyword.count().as(aliasCount)
                ))
                .from(qBlogSearchLogEntity)
                .where(qBlogSearchLogEntity.createdAt.goe(startDate), qBlogSearchLogEntity.createdAt.lt(endDate))
                .groupBy(qBlogSearchLogEntity.keyword)
                .orderBy(aliasCount.desc())
                .limit(10)
                .fetch();
    }
}
