package com.moxuanran.learning.factory.stand;

import com.moxuanran.learning.factory.simple.Enemy;

/**
 * @author wutao
 * @date 2022/9/27 09:51
 */
public interface Factory {
    /**
     * 创建
     *
     * @param screenWidth 屏幕宽度
     * @return {@link Enemy}
     */
    Enemy create(int screenWidth);
}
