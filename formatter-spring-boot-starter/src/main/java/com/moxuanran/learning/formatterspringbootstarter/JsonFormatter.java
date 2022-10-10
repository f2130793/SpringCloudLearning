package com.moxuanran.learning.formatterspringbootstarter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/10/9 15:42
 */
public class JsonFormatter implements Formatter {
    private final ObjectMapper objectMapper;
    public JsonFormatter() {
        this.objectMapper = new ObjectMapper();
    }
    @Override
    public String format(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
