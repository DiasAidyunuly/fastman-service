package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncAction;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncActionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IncidentMapper.class, ActionMapper.class})
public interface IncActionMapper {
    @Mapping(target = "incident.id", source = "incident.id")
    @Mapping(target = "action.id", source = "action.id")
    @Mapping(target = "id", source = "id")
    IncActionDto toDto(IncAction entity);

    @Mapping(target = "incident.id", source = "incident.id")
    @Mapping(target = "action.id", source = "action.id")
    @Mapping(target = "id", source = "id")
    IncAction toEntity(IncActionDto dto);
}
