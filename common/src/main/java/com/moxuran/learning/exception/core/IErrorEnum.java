package com.moxuran.learning.exception.core;

/**
 * 错误码枚举接口
 * @author wutao
 */
public interface IErrorEnum {

    /**
     * 枚举转换成异常码定义
     *
     * @param applicationCode 错误码
     * @param messages 占位填充
     * @return 异常码定义
     */
    default ExDefinition toDefinition(int applicationCode, Object... messages) {
        int exTypeCode = getExType().getCode();
        String businessCode = String.valueOf(Math.abs(getCode()));
        if (businessCode.length() > 4) {
            businessCode = businessCode.substring(businessCode.length() - 4);
        }
        while (businessCode.length() < 4) {
            businessCode = "0" + businessCode;
        }
        String code = "" + exTypeCode + applicationCode + businessCode;
        return new ExDefinition(getExType(), Integer.parseInt(code), getMsg(), messages);
    }

    /**
     * 编码
     *
     * @return int
     */
    int getCode();

    /**
     * 描述信息
     *
     * @return str
     */
    String getMsg();

    /**
     * 错误类型, 默认BUSINESS_ERROR
     * @return 错误分类
     */
    ExType getExType();

}
