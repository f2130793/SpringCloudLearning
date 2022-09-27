package com.moxuanran.learning.factory.stand;

import com.moxuanran.learning.factory.simple.Boss;
import com.moxuanran.learning.factory.simple.Enemy;

import java.util.Random;

/**Ø
 * @author wutao
 * @date 2022/9/27 09:52
 */
public class BossFactory implements Factory{
    @Override
    public Enemy create(int screenWidth) {
        Random random = new Random();
        return new Boss(random.nextInt(screenWidth /2),0 );
    }
}
