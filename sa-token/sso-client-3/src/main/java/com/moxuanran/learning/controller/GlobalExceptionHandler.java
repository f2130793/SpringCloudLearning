package com.moxuanran.learning.controller;

import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wutao
 * @date 2023/4/17 10:55
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public SaResult handleException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }
}
