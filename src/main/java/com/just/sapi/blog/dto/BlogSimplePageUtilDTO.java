package com.just.sapi.blog.dto;

import com.just.sapi.common.dto.ResponseListMetaDTO;
import com.just.sapi.common.utils.SimplePageUtil;
import lombok.*;

import java.util.List;

@Getter
public class BlogSimplePageUtilDTO extends SimplePageUtil<BlogDTO> {
    @Builder
    public BlogSimplePageUtilDTO(List<BlogDTO> items, ResponseListMetaDTO meta) {
        super(items, meta);
    }
}
