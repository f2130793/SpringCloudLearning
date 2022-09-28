package com.moxuanran.learning.bridging;

/**
 * @author wutao
 * @date 2022/9/28 15:40
 */
public class BlackPen extends Pen{
    public BlackPen(Ruler ruler) {
        super(ruler);
    }

    @Override
    public void draw() {
        System.out.print("黑");
        ruler.regularize();
    }
}
