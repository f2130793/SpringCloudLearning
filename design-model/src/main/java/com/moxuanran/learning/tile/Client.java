package com.moxuanran.learning.tile;

/**
 * 享元模式
 *
 * @author wutao
 * @date 2022/9/27 15:56
 */
public class Client {
    public static void main(String[] args) {
        TileFactory factory = new TileFactory();
        factory.getDrawable("河流").draw(10,10);
        factory.getDrawable("河流").draw(10,20);
    }
}
