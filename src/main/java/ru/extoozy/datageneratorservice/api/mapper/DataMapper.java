package ru.extoozy.datageneratorservice.api.mapper;

import org.mapstruct.Mapper;
import ru.extoozy.datageneratorservice.api.dto.DataDto;
import ru.extoozy.datageneratorservice.model.Data;

@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto> {
}
