package com.moxuanran.learning.builder;

/**
 * @author wutao
 * @date 2022/9/27 14:10
 */
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Building direct() {
        //Director主要用于控制流程
        System.out.println("===工程项目启动===");
        builder.buildBasement();
        builder.buildWall();
        builder.buildRoof();
        System.out.println("===工程项目竣工===");
        return builder.getBuilding();
    }
}
