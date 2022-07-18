package com.moxuran.learning.exception.exception;


import com.moxuran.learning.exception.core.ExDefinition;

/**
 * 业务异常，直接封装返回结果，不需要打印线程栈
 *
 * @author wuqiang
 */
public class BusinessException extends AbstractException {

    public BusinessException(ExDefinition errorCode) {
        super(errorCode);
    }

}
