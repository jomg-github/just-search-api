package com.just.sapi.blog.dto;

import com.just.sapi.common.dto.MetaDTO;
import com.just.sapi.common.utils.SimplePage;
import lombok.*;

import java.util.List;

@Getter
public class BlogSimplePageDTO extends SimplePage<BlogDTO> {
    @Builder
    public BlogSimplePageDTO(List<BlogDTO> items, MetaDTO meta) {
        super(items, meta);
    }
}
