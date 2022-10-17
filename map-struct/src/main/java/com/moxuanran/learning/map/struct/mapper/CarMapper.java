package com.moxuanran.learning.map.struct.mapper;

import com.moxuanran.learning.map.struct.dto.CarDto;
import com.moxuanran.learning.map.struct.po.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author wutao
 * @date 2022/10/17 13:51
 */
@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);


    @Mapping(source = "numbersOfSeats",target = "seatCount")
    CarDto carToCarDto(Car car);
}
