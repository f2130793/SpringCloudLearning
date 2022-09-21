package com.moxuanran.learning;

import lombok.Data;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/21 19:00
 */
@Data
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public enum Type {
        MEAT,FISH,OTHER
    }
}
