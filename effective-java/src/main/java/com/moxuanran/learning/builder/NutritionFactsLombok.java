package com.moxuanran.learning.builder;

import lombok.Builder;
import lombok.Data;

/**
 * @author wutao
 * @date 2022/9/30 13:52
 */
@Builder
@Data
public class NutritionFactsLombok {
    private  int servingSize;
    private  int servings;
    private  int calories;
    private  int fat;
    private  int sodium;
    private  int carbohydrate;

    public static void main(String[] args) {
        NutritionFactsLombok build = NutritionFactsLombok.builder().servings(1).calories(1).fat(1).build();
        System.out.println(build);
    }
}
