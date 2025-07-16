package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.general.TerritorialDirector;
import kz.magnum.magnumback.fastmanservice.model.pdt.TerritorialDirectorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TerritorialDirectorMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "regionalDirector.id", target = "regionalDirectorId")
    TerritorialDirectorDto toDto(TerritorialDirector entity);
}

