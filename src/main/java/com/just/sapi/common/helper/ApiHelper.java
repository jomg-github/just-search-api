package com.just.sapi.common.helper;

import com.just.sapi.blog.dto.BlogDTO;
import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.common.dto.ApiCallDTO;
import com.just.sapi.common.dto.KakaoApiResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface ApiHelper {
    public KakaoApiResponseDTO searchBlogs(BlogSearchParamsDTO blogSearchParamsDTO);
    public KakaoApiResponseDTO apiCall(ApiCallDTO apiCallDTO);

}
