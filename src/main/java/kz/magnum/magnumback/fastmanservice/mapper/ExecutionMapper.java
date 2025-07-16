package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Execution;
import kz.magnum.magnumback.fastmanservice.model.pdt.ExecutionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExecutionMapper {
    ExecutionDto toDto(Execution entity);

    @Mapping(target = "incExecutions", ignore = true)
    Execution toEntity(ExecutionDto executionDto);
}