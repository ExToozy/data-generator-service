package ru.extoozy.datageneratorservice.api.mapper;

import org.mapstruct.Mapper;
import ru.extoozy.datageneratorservice.api.dto.DataTestOptionsDto;
import ru.extoozy.datageneratorservice.model.DataTestOptions;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper extends Mappable<DataTestOptions, DataTestOptionsDto> {
}
