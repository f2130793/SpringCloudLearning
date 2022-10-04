package com.moxuanran.learning.observer;

/**
 * @author wutao
 * @date 2022/9/29 14:23
 */
public class PhoneFans extends Buyer {
    public PhoneFans(String name) {
        super(name);
    }

    @Override
    public void inform(String product) {
        if (product.contains("手机")) {
            System.out.println(name);
            System.out.println("购买:" + product);
        }
    }
}
