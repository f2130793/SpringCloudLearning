package com.moxuanran.learning.decorator;

/**
 * @author wutao
 * @date 2022/9/27 14:52
 */
public class Girl implements Showable{
    @Override
    public void show() {
        System.out.println("女生的素颜");
    }
}
