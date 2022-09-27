package com.moxuanran.learning.builder;

/**
 * @author wutao
 * @date 2022/9/27 14:03
 */
public interface Builder {
    /**
     * 建立地下室
     */
    void buildBasement();

    /**
     * 建墙
     */
    void buildWall();

    /**
     * 建造屋顶
     */
    void buildRoof();

    /**
     * 获取建筑
     *
     * @return {@link Building}
     */
    Building getBuilding();
}
