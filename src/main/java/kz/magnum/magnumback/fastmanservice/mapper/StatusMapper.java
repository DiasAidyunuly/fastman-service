package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.Status;
import kz.magnum.magnumback.fastmanservice.model.pdt.StatusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusDto toDto(Status entity);

    Status toEntity(StatusDto dto);
}
