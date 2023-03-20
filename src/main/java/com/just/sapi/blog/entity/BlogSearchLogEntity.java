package com.just.sapi.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Table(name = "TB_BLOG_SEARCH_LOG")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogSearchLogEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ROW_ID", nullable = false)
    private Long rowId;

    @Column(name = "KEYWORD", nullable = false, length = 100)
    private String keyword;
}
