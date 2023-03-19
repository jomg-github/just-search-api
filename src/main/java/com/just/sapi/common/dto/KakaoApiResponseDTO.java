package com.just.sapi.common.dto;

import com.just.sapi.blog.dto.BlogDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class KakaoApiResponseDTO {
    private KakaoMetaDTO meta;
    private List<BlogDTO> documents;
}
