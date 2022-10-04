package com.moxuanran.learning.builder;

/**
 * @author wutao
 * @date 2022/9/27 14:04
 */
public class HouseBuilder implements Builder{
    private final Building house;

    public HouseBuilder() {
        house = new Building();
    }

    @Override
    public void buildBasement() {
        System.out.println("挖土方。。。。。");
        house.setBasement("木");
    }

    @Override
    public void buildWall() {
        System.out.println("搭建木质框架");
        house.setWall("田");
    }

    @Override
    public void buildRoof() {
        System.out.println("搭建木质屋顶");
        house.setRoof("/\\");
    }

    @Override
    public Building getBuilding() {
        return house;
    }
}
