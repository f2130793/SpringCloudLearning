import com.moxuanran.learning.map.struct.CarType;
import com.moxuanran.learning.map.struct.dto.CarDto;
import com.moxuanran.learning.map.struct.mapper.CarMapper;
import com.moxuanran.learning.map.struct.po.Car;

/**
 * @author wutao
 * @date 2022/10/17 13:54
 */
public class Test {

    @org.junit.Test
    public void shouldMapCarToDto() {
        Car car = new Car();
        car.setMake("Morris");
        car.setNumbersOfSeats(5);
        car.setType(CarType.SILUN);

        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);
        System.out.println(carDto);
    }

}
