package com.just.sapi.common.advice;

import com.just.sapi.common.dto.ResponseDTO;
import com.just.sapi.common.dto.ResponseErrorDTO;
import com.just.sapi.common.dto.ResponseListMetaDTO;
import com.just.sapi.common.dto.ResponseMetaDTO;
import com.just.sapi.common.enums.ErrorCode;
import com.just.sapi.common.utils.SimplePageUtil;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof SimplePageUtil) {
            SimplePageUtil simplePageResult = (SimplePageUtil) body;
            ResponseListMetaDTO listMeta = simplePageResult.getMeta();
            ResponseListMetaDTO meta = ResponseListMetaDTO.builder()
                    .result(Boolean.TRUE)
                    .page(listMeta.getPage())
                    .pageSize(listMeta.getPageSize())
                    .totalPages(listMeta.getTotalPages())
                    .totalCount(listMeta.getTotalCount())
                    .build();

            return ResponseDTO.builder()
                    .meta(meta)
                    .data(simplePageResult.getItems())
                    .build();
        }

        if (body instanceof ResponseErrorDTO) {
            ResponseErrorDTO responseWithMeta = (ResponseErrorDTO) body;

            return ResponseErrorDTO.builder()
                    .meta(responseWithMeta.getMeta())
                    .error(responseWithMeta.getError())
                    .build();
        }

        if (body instanceof ResponseDTO) {
            ResponseDTO responseWithMeta = (ResponseDTO) body;
            responseWithMeta.getMeta().setResult(Boolean.TRUE);

            return ResponseDTO.builder()
                    .meta(responseWithMeta.getMeta())
                    .data(responseWithMeta.getData())
                    .build();
        }

        return ResponseDTO.builder()
                .data(body)
                .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Object handle(MethodArgumentNotValidException e) {
        e.printStackTrace();

        final List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
        List<Map> result = new ArrayList<>();
        for (FieldError error : fieldErrorList) {
            Map map = new HashMap();
            map.put(error.getField(), error.getDefaultMessage());
            result.add(map);
        }

        return handleExceptionInternal(ErrorCode.INVALID_PARAMETER);
    }

    @ExceptionHandler
    public Object handle(BindException e) {
        e.printStackTrace();
        return handleExceptionInternal(ErrorCode.INVALID_PARAMETER);
    }

    @ExceptionHandler
    public Object handle(ServiceException e) {
        e.printStackTrace();
        return handleExceptionInternal(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Object handleException500(Exception e) {
        e.printStackTrace();
        return handleExceptionInternal(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseErrorDTO handleExceptionInternal(ErrorCode errorCode) {
        return ResponseErrorDTO.builder()
                .meta(new ResponseMetaDTO(Boolean.FALSE))
                .error(new ResponseErrorDTO.ResponseError(errorCode.httpStatus.value(), errorCode.message))
                .build();
    }

    private ResponseErrorDTO handleExceptionInternal(Integer errorCode, String message) {
        return ResponseErrorDTO.builder()
                .meta(new ResponseMetaDTO(Boolean.FALSE))
                .error(new ResponseErrorDTO.ResponseError(errorCode, message))
                .build();
    }




}
