package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Incident;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncidentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncidentMapper {
    @Mapping(target = "action.id", source = "action.id")
    @Mapping(target = "template.id", source = "template.id")
    @Mapping(target = "id", source = "id")
    IncidentDto toDto(Incident entity);

    @Mapping(target = "incExecutions", ignore = true)
    @Mapping(target = "incActions", ignore = true)
    @Mapping(target = "siteExceptions", ignore = true)
    @Mapping(target = "fastmanTasks", ignore = true)
    @Mapping(target = "action.id", source = "action.id")
    @Mapping(target = "template.id", source = "template.id")
    @Mapping(target = "id", source = "id")
    Incident toEntity(IncidentDto incidentDto);
}
