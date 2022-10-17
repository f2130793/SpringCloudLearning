package com.moxuanran.learning.map.struct;

import com.moxuanran.learning.map.struct.dto.PersonDto;
import com.moxuanran.learning.map.struct.mapper.PersonMapper;
import com.moxuanran.learning.map.struct.po.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author wutao
 * @date 2022/10/17 14:04
 */
@Component
public class Test {
    @Autowired
    PersonMapper personMapper;

    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(18);
        person.setName("wt");
        person.setId(1);

        PersonDto dto = PersonMapper.PERSON_MAPPER.convert(person);
        System.out.println(dto);

        Person person1 = PersonMapper.PERSON_MAPPER.convert(dto);
        System.out.println(person1);
    }
}
