package com.moxuanran.learning.builder;

/**
 * @author wutao
 * @date 2022/9/27 14:12
 */
public class Client {
    public static void main(String[] args) {
        Director director = new Director(new HouseBuilder());
        System.out.println(director.direct());

        director.setBuilder(new ApartmentBuilder());
        System.out.println(director.direct());
    }
}
