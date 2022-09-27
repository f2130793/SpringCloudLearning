package com.moxuanran.learning.tile;

/**
 * @author wutao
 * @date 2022/9/27 15:48
 */
public class Grass implements Drawable{
    private final String image;

    public Grass() {
        this.image = "草地";
        System.out.println("从磁盘中加载" + image + "耗时500ms");
    }
    @Override
    public void draw(int x, int y) {
        System.out.println("在位置[" + x + ":" + y  + "]上绘制图片:" + image);
    }
}
