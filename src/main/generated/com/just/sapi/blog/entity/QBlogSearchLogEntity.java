package com.just.sapi.blog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogSearchLogEntity is a Querydsl query type for BlogSearchLogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlogSearchLogEntity extends EntityPathBase<BlogSearchLogEntity> {

    private static final long serialVersionUID = -1322188532L;

    public static final QBlogSearchLogEntity blogSearchLogEntity = new QBlogSearchLogEntity("blogSearchLogEntity");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath keyword = createString("keyword");

    public final NumberPath<Long> rowId = createNumber("rowId", Long.class);

    public QBlogSearchLogEntity(String variable) {
        super(BlogSearchLogEntity.class, forVariable(variable));
    }

    public QBlogSearchLogEntity(Path<? extends BlogSearchLogEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogSearchLogEntity(PathMetadata metadata) {
        super(BlogSearchLogEntity.class, metadata);
    }

}

