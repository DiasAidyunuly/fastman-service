package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.SiteException;
import kz.magnum.magnumback.fastmanservice.model.pdt.SiteExceptionWithIncidentNameDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = IncidentMapper.class)
public interface SiteExceptionWithIncidentNameMapper {
    @Mapping(target = "incidentName", source = "incident.incidentName")
    @Mapping(target = "id", source = "id")
    SiteExceptionWithIncidentNameDto toDto(SiteException entity);
}
