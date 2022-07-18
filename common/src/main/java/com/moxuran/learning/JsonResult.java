package com.moxuran.learning;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moxuran.learning.exception.common.ErrorCode;
import com.moxuran.learning.exception.core.ExDefinition;
import com.moxuran.learning.exception.core.ExFactory;
import com.moxuran.learning.exception.core.ExType;
import com.moxuran.learning.exception.core.IErrorEnum;

/**
 * <p>
 * 被访问时响应的数据结构
 * </p>
 *
 * @author jiang
 * @date 2019-11-02 10:35
 */
// 规避jackson反序列化问题
@JsonIgnoreProperties(ignoreUnknown = true)
public final class JsonResult<T> {

    /** 无data的成功响应 */
    public static final JsonResult<Void> OK = success();

    @JSONField
    private int code = 0;
    @JSONField
    private String msg;
    @JSONField
    private T data;
    @JSONField
    private ExDefinition exDefinition;

    private JsonResult() {
        this.data = null;
    }

    private JsonResult(ExDefinition exDefinition) {
        this.exDefinition = exDefinition;
        this.code = exDefinition.getCode();
        this.msg = exDefinition.getCopyright();
        this.data = null;
    }

    public static <T> JsonResult<T> success() {
        return new JsonResult<>();
    }

    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> result = success();
        result.setData(data);
        return result;
    }

    /**
     * 返回通用业务失败
     * @param msg 失败信息
     * @param <T> ..
     * @return res
     */
    public static <T> JsonResult<T> failure(String msg) {
        return failure(ErrorCode.BUSINESS_ERROR, msg);
    }

    /**
     * @param iErrorEnum 错误码
     * @param messages 占位信息
     * @param <T> ..
     * @return res
     */
    public static <T> JsonResult<T> failure(IErrorEnum iErrorEnum, Object... messages) {
        return new JsonResult<T>(ExFactory.of(iErrorEnum, messages));
    }

    /**
     * use like JsonResult.failure(ExFactory.of(ErrorCode.UNEXPECTED_ERROR));
     * @param exDefinition 错误定义
     * @param <T> ..
     * @return res
     */
    public static <T> JsonResult<T> failure(ExDefinition exDefinition) {
        return new JsonResult<T>(exDefinition);
    }

    /**
     * use like JsonResult.failure(ExFactory.of(ErrorCode.UNEXPECTED_ERROR));
     * @param exDefinition 错误定义
     * @param data 数据
     * @param <T> ..
     * @return res
     */
    public static <T> JsonResult<T> failure(ExDefinition exDefinition, T data) {
        JsonResult<T> result = new JsonResult<T>(exDefinition);
        result.setData(data);
        return result;
    }

    /**
     * 根据现有result构造 result
     * @param jsonResult 原result
     * @param <T> t
     * @return result
     */
    public static <T> JsonResult<T> failure(JsonResult jsonResult) {
        if (jsonResult.exDefinition != null) {
            return JsonResult.failure(jsonResult.exDefinition);
        }
        JsonResult<T> jsonResult1 = new JsonResult<>();
        jsonResult1.setCode(jsonResult.getCode());
        jsonResult1.setMsg(jsonResult.getMsg());
        return jsonResult1;
    }

    public String toJsonString(PropertyFilter... filters) {
        return JSON.toJSONString(this, filters);
    }

    public boolean isSuccess() {
        return this.code == 0;
    }

    /**
     * 判断当前的状态码是否为成功200状态
     */
    public boolean checkSuccess() {
        return isSuccess();
    }

    /**
     * 如果失败则抛出异常
     * @return 结果
     */
    public JsonResult<T> mustSuccess() {
        return mustSuccess(null);
    }

    /**
     * 如果失败则抛出异常
     * @return 结果
     */
    public JsonResult<T> mustSuccess(String diyMsg) {
        if (this.checkSuccess()) {
            return this;
        }
        if (this.exDefinition != null) {
            ExDefinition exDefinition = this.exDefinition;
            if (diyMsg != null) {
                exDefinition = new ExDefinition(this.exDefinition.getExType(), this.exDefinition.getCode(), diyMsg);
            }
            throw ExFactory.throwWith(exDefinition);
        }
        throw ExFactory.throwWith(new ExDefinition(ExType.BUSINESS_ERROR, this.code, diyMsg != null ? diyMsg : this.msg));
    }

    /**
     * 全局事物必备，code != 500 抛异常事务回滚
     */
    public JsonResult<T> checkTx() {
        return mustSuccess();
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public JsonResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * @return 获取简单错误码
     */
    public int getShortCode() {
        if (code > 100000000 && code < 999999999) {
            return code % 10000;
        }
        return code;
    }

    public ExDefinition getExDefinition() {
        return exDefinition;
    }

    public void setExDefinition(ExDefinition exDefinition) {
        this.exDefinition = exDefinition;
    }
}
