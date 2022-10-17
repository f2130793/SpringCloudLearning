package com.moxuanran.learning.map.struct.po;

import com.moxuanran.learning.map.struct.CarType;
import lombok.Data;

/**
 * @author wutao
 * @date 2022/10/17 11:51
 */
@Data
public class Car {
    private String make;
    private int numbersOfSeats;
    private CarType type;
}
