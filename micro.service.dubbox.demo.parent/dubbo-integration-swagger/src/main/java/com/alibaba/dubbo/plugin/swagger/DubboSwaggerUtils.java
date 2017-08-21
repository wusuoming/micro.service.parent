package com.alibaba.dubbo.plugin.swagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


class DubboSwaggerUtils {
    static final DubboSwaggerUtils EXCLUDE_EMPTY = new DubboSwaggerUtils(JsonInclude.Include.NON_EMPTY);
    private ObjectMapper mapper;
    private DubboSwaggerUtils(JsonInclude.Include include) {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(include);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    String toJson(Object target) {
        try {
            return mapper.writeValueAsString(target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}