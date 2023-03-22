package com.just.sapi.common.helper;

import com.just.sapi.blog.dto.BlogSearchParamsDTO;
import com.just.sapi.common.dto.ApiCallDTO;
import com.just.sapi.common.dto.KakaoApiResponseDTO;

public interface ApiHelper {
    public KakaoApiResponseDTO searchBlogs(BlogSearchParamsDTO blogSearchParamsDTO);
    public KakaoApiResponseDTO apiCall(ApiCallDTO apiCallDTO);

}
