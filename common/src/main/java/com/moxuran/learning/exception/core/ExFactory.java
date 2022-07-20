package com.moxuran.learning.exception.core;


import com.moxuran.learning.exception.common.ErrorCode;
import com.moxuran.learning.exception.exception.AbstractException;
import com.moxuran.learning.exception.exception.BusinessException;
import com.moxuran.learning.exception.exception.SystemException;

import java.util.Objects;

/**
 * 错误码定义仓库
 *
 * @author wutao
 */
public class ExFactory {

    private static ExConfigure configure;

    static void init(ExConfigure exConfigure) {
        ExFactory.configure = exConfigure;
    }

    public static ExDefinition of(IErrorEnum code, Object... messages) {
        if (messages == null || messages.length == 0) {
            return code.toDefinition(configure.getApplicationCode());
        }
        return code.toDefinition(configure.getApplicationCode(), messages);
    }

    public static AbstractException throwBusiness(Object message) {
        Objects.requireNonNull(message, "请描述业务异常");
        return throwWith(ErrorCode.BUSINESS_ERROR, message);
    }

    public static AbstractException throwSystem(Object message) {
        Objects.requireNonNull(message, "请描述系统异常");
        return throwWith(ErrorCode.SYSTEM_ERROR, message);
    }

    public static AbstractException throwCommon(ErrorCode code, Object... messages) {
        return throwWith(code, messages);
    }

    public static AbstractException throwParamError(Object message) {
        return throwWith(ErrorCode.PARAM_ERROR, message);
    }

    public static AbstractException throwWith(IErrorEnum code, Object... messages) {
        return throwWith(of(code, messages));
    }

    public static AbstractException throwWith(ExDefinition exDefinition) {
        if (exDefinition.getExType().equals(ExType.BUSINESS_ERROR)) {
            return new BusinessException(exDefinition);
        } else {
            return new SystemException(exDefinition);
        }
    }

    public static ExDefinition withCopyRight(ExDefinition code) {
        return code.tipsErrorCode(configure.getCopyrights());
    }

}
