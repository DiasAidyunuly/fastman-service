package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.SiteException;
import kz.magnum.magnumback.fastmanservice.model.pdt.SiteExceptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = IncidentMapper.class)
public interface SiteExceptionMapper {
    @Mapping(target = "incident.id", source = "incident.id")
    @Mapping(target = "id", source = "id")
    SiteExceptionDto toDto(SiteException entity);

    @Mapping(target = "incident.id", source = "incident.id")
    @Mapping(target = "id", source = "id")
    SiteException toEntity(SiteExceptionDto dto);
}
