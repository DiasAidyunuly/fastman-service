package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.general.Division;
import kz.magnum.magnumback.fastmanservice.model.pdt.DivisionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DivisionMapper {
    @Mapping(source = "id", target = "id")
    DivisionDto toDto(Division entity);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "regionalDirectors", ignore = true)
    Division toEntity(DivisionDto dto);
}
