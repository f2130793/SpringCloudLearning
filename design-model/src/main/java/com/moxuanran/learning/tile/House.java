package com.moxuanran.learning.tile;

/**
 * @author wutao
 * @date 2022/9/27 15:48
 */
public class House implements Drawable{
    private final String image;

    public House() {
        this.image = "房屋";
        System.out.println("从磁盘中加载" + image + "耗时500ms");
    }
    @Override
    public void draw(int x, int y) {
        System.out.println("将图层切换到顶层......");
        System.out.println("在位置[" + x + ":" + y  + "]上绘制图片:" + image);
    }
}
