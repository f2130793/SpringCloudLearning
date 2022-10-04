package com.moxuanran.learning.factory.stand;

import com.moxuanran.learning.factory.simple.Airplane;
import com.moxuanran.learning.factory.simple.Enemy;

import java.util.Random;

/**
 * @author wutao
 * @date 2022/9/27 09:52
 */
public class AirplaneFactory implements Factory{
    @Override
    public Enemy create(int screenWidth) {
        Random random = new Random();
        return new Airplane(random.nextInt(screenWidth),0 );
    }
}
