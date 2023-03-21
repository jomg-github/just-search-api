package com.just.sapi.blog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogSearchRankEntity is a Querydsl query type for BlogSearchRankEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlogSearchRankEntity extends EntityPathBase<BlogSearchRankEntity> {

    private static final long serialVersionUID = 2113426186L;

    public static final QBlogSearchRankEntity blogSearchRankEntity = new QBlogSearchRankEntity("blogSearchRankEntity");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final DateTimePath<java.time.LocalDateTime> aggregatedAt = createDateTime("aggregatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> count = createNumber("count", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath keyword = createString("keyword");

    public final NumberPath<Long> rowId = createNumber("rowId", Long.class);

    public QBlogSearchRankEntity(String variable) {
        super(BlogSearchRankEntity.class, forVariable(variable));
    }

    public QBlogSearchRankEntity(Path<? extends BlogSearchRankEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogSearchRankEntity(PathMetadata metadata) {
        super(BlogSearchRankEntity.class, metadata);
    }

}

