package com.moxuanran.learning.adapter;

/**
 * @author wutao
 * @date 2022/9/27 15:21
 */
public class TV implements DualPin {
    @Override
    public void electrify(int l, int n) {
        System.out.println("火线通电" + l + "零线通电" + n);
        System.out.println("电视开机");
    }
}
