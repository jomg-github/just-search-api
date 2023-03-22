package com.just.sapi.blog.repository;

import com.just.sapi.blog.dto.BlogSearchRankCountMinMaxDTO;
import com.just.sapi.blog.dto.BlogSearchRankDTO;
import com.just.sapi.blog.entity.QBlogSearchLogEntity;
import com.just.sapi.blog.entity.QBlogSearchRankEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class BlogSearchRankRepositoryImpl implements BlogSearchRankCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QBlogSearchLogEntity qBlogSearchLogEntity = QBlogSearchLogEntity.blogSearchLogEntity;
    private final QBlogSearchRankEntity qBlogSearchRankEntity = QBlogSearchRankEntity.blogSearchRankEntity;

    @Override
    public BlogSearchRankCountMinMaxDTO findCountMinMaxBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return jpaQueryFactory.select(
                Projections.constructor(
                        BlogSearchRankCountMinMaxDTO.class,
                        qBlogSearchLogEntity.rowId.count(),
                        qBlogSearchLogEntity.rowId.min(),
                        qBlogSearchLogEntity.rowId.max()
                ))
                .from(qBlogSearchLogEntity)
                .where(qBlogSearchLogEntity.createdAt.goe(startDate), qBlogSearchLogEntity.createdAt.lt(endDate))
                .fetchOne();
    }

    @Override
    public BlogSearchRankDTO findTop1OrderByRowIdDesc() {
        return jpaQueryFactory.select(
                Projections.constructor(
                        BlogSearchRankDTO.class,
                        qBlogSearchRankEntity.aggregatedAt,
                        qBlogSearchRankEntity.keyword,
                        qBlogSearchRankEntity.count
                ))
                .from(qBlogSearchRankEntity)
                .orderBy(qBlogSearchRankEntity.rowId.desc())
                .limit(1)
                .fetchOne();
    }
}
