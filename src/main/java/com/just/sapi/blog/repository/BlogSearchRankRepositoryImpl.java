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
    public List<BlogSearchLogDTO> findBlogSearchLogGroupByKeywordBetween(LocalDateTime startDate, LocalDateTime endDate) {
        NumberPath<Long> aliasCount = Expressions.numberPath(Long.class, "count");
        List<BlogSearchLogDTO> list = jpaQueryFactory.select(
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

        return list;
    }

    @Override
    public void saveRankedBlogSearchLogs(List<BlogSearchRankDTO> rankedBlogSearchLogs) {
    }
}
