package com.moxuran.learning.exception.common;



import com.moxuran.learning.exception.core.ExType;
import com.moxuran.learning.exception.core.IErrorEnum;

import java.util.Arrays;

/**
 * 公共错误码
 * @author wutao
 */
public enum ErrorCode implements IErrorEnum {

    /**
     *  程序逻辑闭合，if else{xxx},switch default:xx
     */
    UNREACHABLE_ERROR(ExType.SYSTEM_ERROR, 2001, "unreachable error"),
    /**
     * 保证程序健壮性，生吞异常，不影响主流程
     */
    UNEXPECTED_ERROR(ExType.SYSTEM_ERROR, 2002, "unexpected error"),
    /**
     * 未定义异常，引用了错误码
     */
    UNDEFINED_ERROR(ExType.SYSTEM_ERROR, 2003, "undefined error {}"),
    /**
     * 程序中自定义文案系统异常
     */
    SYSTEM_ERROR(ExType.SYSTEM_ERROR, 2004, "{}"),
    /**
     * 传参JSR303校验失败
     */
    BUSINESS_ERROR(ExType.BUSINESS_ERROR, 2005, "{}"),
    /**
     * 参数错误
     */
    PARAM_ERROR(ExType.BUSINESS_ERROR, 2006, "参数错误: {}"),
    /**
     * 请先进行认证
     */
    UNAUTHORIZED(ExType.BUSINESS_ERROR, 2007, "未登入: {}"),
    /**
     * 无权查看
     */
    FORBIDDEN(ExType.BUSINESS_ERROR, 2008, "权限不足: {}"),
    /**
     * 未找到该路径
     */
    NOT_FOUND(ExType.BUSINESS_ERROR, 2009, "资源不存在: {}"),
    /**
     * 请求方式不支持
     */
    METHOD_NOT_ALLOWED(ExType.BUSINESS_ERROR, 2010, "请求方式不支持: {}"),
    /**
     * 409
     */
    CONFLICT(ExType.SYSTEM_ERROR, 2011, "请求资源状态冲突: {}"),
    /**
     * 429
     */
    TOO_MANY_REQUEST(ExType.SYSTEM_ERROR, 2012, "请求数过多: {}"),
    /**
     * 499
     */
    TIMEOUT(ExType.SYSTEM_ERROR, 2013, "请求超时: {}"),
    /**
     * 重复提交
     */
    REPEAT_REQUEST(ExType.SYSTEM_ERROR, 2014, "重复提交: {}"),
    /**
     * 503
     */
    SERVICE_UNAVAILABLE(ExType.SYSTEM_ERROR, 2015, "服务不可用: {}"),
    ;


    ErrorCode(int code, String msg) {
        this.exType = ExType.BUSINESS_ERROR;
        this.code = code;
        this.msg = msg;
    }

    ErrorCode(ExType exType, int code, String msg) {
        this.exType = exType;
        this.code = code;
        this.msg = msg;
    }

    private ExType exType;

    private int code;

    private String msg;

    @Override
    public ExType getExType() {
        return exType;
    }

    public void setExType(ExType exType) {
        this.exType = exType;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public ErrorCode ofCode(int code) {
        return Arrays.stream(ErrorCode.values()).filter(x -> x.getCode() == code).findFirst().orElse(ErrorCode.UNDEFINED_ERROR);
    }

}
