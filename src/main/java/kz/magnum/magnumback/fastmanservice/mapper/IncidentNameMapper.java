package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Incident;
import kz.magnum.magnumback.fastmanservice.model.IncidentName;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncidentNameDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncidentNameMapper {
    IncidentNameDto toDto(IncidentName model);

    IncidentName toDomain(Incident entity);
}