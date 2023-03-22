package com.just.sapi.common.utils;

import com.just.sapi.common.dto.ResponseListMetaDTO;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
public class SimplePageUtil<T> {
    private List<T> items;
    private ResponseListMetaDTO meta;

    public SimplePageUtil(List<T> items, ResponseListMetaDTO meta) {
        this.items = items;
        this.meta = meta;
    }

    public static Pageable kakaoPagableRequestOf(Integer page, Integer limit, Boolean isEndPage, Integer total) {
        if (Boolean.TRUE.equals(isEndPage)) {
            page = total / limit + 1;
        }

        return PageRequest.of(page, limit);
    }
}
