package com.moxuanran.learning.map.struct.mapper;

import com.moxuanran.learning.map.struct.dto.PersonDto;
import com.moxuanran.learning.map.struct.po.Person;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author wutao
 * @date 2022/10/17 14:32
 */
@Mapper
public interface PersonMapper {
    PersonMapper PERSON_MAPPER = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "personName",source = "name")
    PersonDto convert(Person person);

    @InheritInverseConfiguration
    Person convert(PersonDto personDto);
}
