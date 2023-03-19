package com.just.sapi.common.utils;

import com.just.sapi.common.dto.MetaDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class SimplePage<T> {
    private List<T> items;
    private MetaDTO meta;

    public SimplePage(List<T> items, MetaDTO meta) {
        this.items = items;
        this.meta = meta;
    }
}
