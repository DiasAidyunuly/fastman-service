package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.TypeOfExecution;
import kz.magnum.magnumback.fastmanservice.model.pdt.TypeOfExecutionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypeOfExecutionMapper {
    @Mapping(target = "id", source = "id")
    TypeOfExecutionDto toDto(TypeOfExecution entity);
}
