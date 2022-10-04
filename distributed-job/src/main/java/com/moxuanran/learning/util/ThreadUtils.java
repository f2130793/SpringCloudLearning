package com.moxuanran.learning.util;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadUtils {


    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

}
