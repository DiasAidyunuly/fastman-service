package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncExecution;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncExecutionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IncidentMapper.class, ExecutionMapper.class})
public interface IncExecutionMapper {
    @Mapping(target = "incident.id", source = "incident.id")
    @Mapping(target = "execution.id", source = "execution.id")
    @Mapping(target = "id", source = "id")
    IncExecutionDto toDto(IncExecution entity);

    @Mapping(target = "incident.id", source = "incident.id")
    @Mapping(target = "execution.id", source = "execution.id")
    @Mapping(target = "id", source = "id")
    IncExecution toEntity(IncExecutionDto dto);
}
