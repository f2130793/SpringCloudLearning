package com.moxuanran.learning.factory.abstracts;

/**
 * @author wutao
 * @date 2022/9/27 10:36
 */
public interface AbstractFactory {
    /**
     * 创建初级
     *
     * @return {@link LowClassUnit}
     */
    LowClassUnit createLowClass();

    /**
     * 创建中级
     *
     * @return {@link MiddleClassUnit}
     */
    MiddleClassUnit createMiddleClass();

    /**
     * 创建高类
     *
     * @return {@link HighClassUnit}
     */
    HighClassUnit createHighClass();
}
