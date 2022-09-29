package com.moxuanran.learning.util;

import java.util.List;

public class CollectionUtils {


    public static boolean isNullOrEmpty(List lst) {
        if (null == lst || lst.isEmpty()) {
            return true;
        }
        return false;
    }
}
