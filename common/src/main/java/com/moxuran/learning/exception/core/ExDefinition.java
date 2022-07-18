package com.moxuran.learning.exception.core;

import com.moxuran.learning.BaseSerializable;
import org.slf4j.helpers.MessageFormatter;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 错误码定义
 *
 * @author wuqiang
 */
public class ExDefinition extends BaseSerializable {

    private static final long serialVersionUID = 1L;

    private ExType exType;
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误描述
     */
    private String desc;
    /**
     * 提示文案
     */
    private String copyright;

    public ExDefinition() {

    }

    public ExDefinition(ExType exType, int code, String desc) {
        this.exType = exType;
        this.code = code;
        this.desc = desc;
    }

    public ExDefinition(ExType exType, int code, String descFormat, Object... messages) {
        this.exType = exType;
        this.code = code;
        if (messages != null && messages.length > 0) {
            this.desc = MessageFormatter.arrayFormat(descFormat, messages).getMessage();
        } else {
            this.desc = descFormat;
        }
    }

    public ExDefinition(ExType exType, int code, String desc, String copyright) {
        this.exType = exType;
        this.code = code;
        this.desc = desc;
        this.copyright = copyright;
    }

    /**
     * 文案赋值
     *
     * @param copyrights 文案定义
     * @return 赋值文案后的错误码
     */
    public ExDefinition tipsErrorCode(List<Copyright> copyrights) {
        if (copyrights == null || copyrights.isEmpty()) {
            return this;
        }
        for (Copyright cp : copyrights) {
            Pattern pattern = cp.getFormat();
            if (pattern.matcher(String.valueOf(this.code)).matches()) {
                setCopyright(cp.getTips());
                break;
            }
        }
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCopyright() {
        return notBlank(copyright) ? copyright : desc;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ExType getExType() {
        return exType;
    }

    public void setExType(ExType exType) {
        this.exType = exType;
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

}
