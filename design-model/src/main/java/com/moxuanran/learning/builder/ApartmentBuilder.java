package com.moxuanran.learning.builder;

/**
 * @author wutao
 * @date 2022/9/27 14:04
 */
public class ApartmentBuilder implements Builder{
    private final Building apartment;

    public ApartmentBuilder() {
        apartment = new Building();
    }

    @Override
    public void buildBasement() {
        System.out.println("挖地基。。。。。");
        apartment.setBasement("🔥");
    }

    @Override
    public void buildWall() {
        System.out.println("搭建木质框架");
        apartment.setWall("🛗");
    }

    @Override
    public void buildRoof() {
        System.out.println("搭建木质屋顶");
        apartment.setRoof("/\\");
    }

    @Override
    public Building getBuilding() {
        return apartment;
    }
}
