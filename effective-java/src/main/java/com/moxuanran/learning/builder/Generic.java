package com.moxuanran.learning.builder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wutao
 * @date 2022/10/12 14:13
 */
public class Generic {
    public static <E> Set<E> union(Set<E> a, Set<E> b) {
        Set<E> result = new HashSet<>(a);
        result.addAll(b);

        return result;
    }
}
