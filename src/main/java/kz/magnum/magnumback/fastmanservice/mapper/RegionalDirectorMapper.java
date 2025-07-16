package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.general.RegionalDirector;
import kz.magnum.magnumback.fastmanservice.model.pdt.RegionalDirectorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegionalDirectorMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "division.id", target = "divisionId")
    RegionalDirectorDto toDto(RegionalDirector entity);
}
