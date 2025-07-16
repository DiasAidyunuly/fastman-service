package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.model.IncActionLocal;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncActionLocalDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IncActionWithoutIncidentIdMapper.class)
public interface IncActionLocalMapper {
    IncActionLocalDto toDto(IncActionLocal model);
}
