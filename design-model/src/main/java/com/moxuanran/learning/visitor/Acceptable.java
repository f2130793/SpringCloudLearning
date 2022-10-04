package com.moxuanran.learning.visitor;

/**
 * @author wutao
 * @date 2022/9/29 13:45
 */
public interface Acceptable {
    void accept(Visitor visitor);
}
