package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncAction;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncActionWithIncidentIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IncidentMapper.class, ActionMapper.class})
public interface IncActionWithIncidentIdMapper {
    @Mapping(target = "incidentId", source = "incident.id")
    @Mapping(target = "action.id", source = "action.id")
    @Mapping(target = "id", source = "id")
    IncActionWithIncidentIdDto toDto(IncAction entity);
}
