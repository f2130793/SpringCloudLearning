package com.moxuran.learning.exception.core;


import com.moxuran.learning.BaseSerializable;

import java.util.regex.Pattern;

/**
 * 文案定义
 *
 * @author wuqiang
 */
public class Copyright extends BaseSerializable {

    private int priority = Integer.MAX_VALUE;

    /**
     * 错误码匹配格式
     */
    private Pattern format;
    /**
     * 提示文案
     */
    private String tips;

    public Pattern getFormat() {
        return format;
    }

    public void setFormat(Pattern format) {
        this.format = format;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
