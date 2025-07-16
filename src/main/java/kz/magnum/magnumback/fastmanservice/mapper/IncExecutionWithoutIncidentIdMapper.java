package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncExecution;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncExecutionWithoutIncidentIdDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncExecutionWithoutIncidentIdMapper {
    IncExecutionWithoutIncidentIdDto toDto(IncExecution entity);

    IncExecution toEntity(IncExecutionWithoutIncidentIdDto entity);
}
