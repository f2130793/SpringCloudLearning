package com.moxuran.learning.exception.exception;


import com.moxuran.learning.exception.core.ExDefinition;

/**
 * 服务异常
 *
 * @author wutao
 */
public class SystemException extends AbstractException {


    public SystemException(ExDefinition errorCode) {
        super(errorCode);
    }

}
