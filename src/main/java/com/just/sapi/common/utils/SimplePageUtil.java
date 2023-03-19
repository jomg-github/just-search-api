package com.just.sapi.common.utils;

import com.just.sapi.common.dto.ResponseListMetaDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class SimplePageUtil<T> {
    private List<T> items;
    private ResponseListMetaDTO meta;

    public SimplePageUtil(List<T> items, ResponseListMetaDTO meta) {
        this.items = items;
        this.meta = meta;
    }
}
