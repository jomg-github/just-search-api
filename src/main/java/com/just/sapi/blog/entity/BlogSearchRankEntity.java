package com.just.sapi.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "TB_BLOG_SEARCH_RANK", indexes = @Index(name = "IDX_BLOG_SEARCH_RANK_01", columnList = "AGGREGATED_AT DESC"))
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BlogSearchRankEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ROW_ID", nullable = false)
    private Long rowId;

    @Column(name = "AGGREGATED_AT", nullable = false)
    private LocalDateTime aggregatedAt;

    @Column(name = "KEYWORD", nullable = false, length = 100)
    private String keyword;

    @Column(name = "COUNT", nullable = false)
    private Long count;
}
