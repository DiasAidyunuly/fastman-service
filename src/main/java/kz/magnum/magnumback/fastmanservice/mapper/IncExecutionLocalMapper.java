package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.model.IncExecutionLocal;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncExecutionLocalDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncExecutionLocalMapper {
    IncExecutionLocalDto toDto(IncExecutionLocal model);
}
