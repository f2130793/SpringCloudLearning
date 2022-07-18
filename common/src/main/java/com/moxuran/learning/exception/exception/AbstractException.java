package com.moxuran.learning.exception.exception;

import com.alibaba.fastjson.JSON;
import com.moxuran.learning.JsonResult;
import com.moxuran.learning.exception.core.ExDefinition;
import com.moxuran.learning.exception.core.ExFactory;
import com.moxuran.learning.exception.core.IErrorEnum;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yjy
 * @date 2020/7/6 12:17
 **/
public abstract class AbstractException extends RuntimeException {

    private static final CharSequence DELIMITER = ";";

    private ExDefinition exDefinition;
    /**
     * 收集,延迟消息上下文
     */
    private Map<String, Object> context = new LinkedHashMap<>();

    public AbstractException(ExDefinition errorCode) {
        super(errorCode.getDesc());
        setExDefinition(errorCode);
    }

    public AbstractException(ExDefinition errorCode, Throwable e) {
        super(errorCode.getDesc(), e);
        setExDefinition(errorCode);
    }

    public AbstractException(IErrorEnum errorEnum, Object... messages) {
        this(ExFactory.of(errorEnum, messages), null);
    }

    public AbstractException(IErrorEnum errorEnum, Throwable cause, Object... messages) {
        this(ExFactory.of(errorEnum, messages), cause);
    }

    public <T> JsonResult<T> response() {
        ExFactory.withCopyRight(exDefinition);
        return JsonResult.failure(exDefinition);
    }

    /**
     * error context
     *
     * @param key
     * @param value
     * @return
     */
    public AbstractException context(String key, Object value) {
        context.put(key, value);
        return this;
    }

    /**
     * error context
     *
     * @param contexts
     * @return
     */
    public AbstractException context(final Map<String, ?> contexts) {
        context.putAll(contexts);
        return this;
    }

    public ExDefinition of() {
        return this.exDefinition;
    }

    public void setExDefinition(ExDefinition exDefinition) {
        this.exDefinition = exDefinition;
    }

    public ExDefinition getExDefinition() {
        return this.exDefinition;
    }

    /**
     * 构建完整的错误消息,后续可以根据此消息做告警规则,格式为:
     * 202495043;错误消息;key1=value2;key2=value2
     *
     * @return full message
     */
    @Override
    public String getMessage() {
        StringBuilder additionalInfo = new StringBuilder();
        if (exDefinition != null) {
            additionalInfo.append(exDefinition.getCode())
                    .append(DELIMITER);
        }
        additionalInfo.append(super.getMessage()).append(DELIMITER);
        context.forEach((k, v) -> additionalInfo
                .append(k).append("=").append(JSON.toJSONString(v))
                .append(DELIMITER));
        return additionalInfo.toString();
    }

}
