package com.moxuanran.learning;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/21 19:12
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Dish> menu = Menu.getMenu();
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        //求最大值
        Optional<Dish> mostCalorieDish = menu.stream().max(dishCaloriesComparator);
        System.out.println(mostCalorieDish);
        //求和
        Integer sum = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sum);
        //求平均
        OptionalDouble average = menu.stream().mapToInt(Dish::getCalories).average();
        System.out.println(average);
        //总和，平均，最大，最小
        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(intSummaryStatistics);
        //字符串拼接
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu);
        //分组groupingBy
        Map<Dish.Type, List<Dish>> groupBy = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(groupBy);
        //分区
        Map<Boolean, List<Dish>> partitionBy = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionBy);

        Integer integer = Stream.of("fa", "hello", "of")
                .map(String::length)
                .filter(l -> l <= 3)
                .max(Comparator.comparingInt(o -> o))
                .get();
        System.out.println(integer);
    }
}
