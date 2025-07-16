package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncExecution;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncExecutionWithIncidentIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ExecutionMapper.class)
public interface IncExecutionWithIncidentIdMapper {
    @Mapping(target = "incidentId", source = "incident.id")
    @Mapping(target = "execution.id", source = "execution.id")
    @Mapping(target = "id", source = "id")
    IncExecutionWithIncidentIdDto toDto(IncExecution entity);
}
