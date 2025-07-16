package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncAction;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncActionWithoutIncidentIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncActionWithoutIncidentIdMapper {
    @Mapping(target = "actionId", source = "action.id")
    IncActionWithoutIncidentIdDto toDto(IncAction entity);

    @Mapping(target = "action.id", source = "actionId")
    IncAction toDto(IncActionWithoutIncidentIdDto entity);
}
