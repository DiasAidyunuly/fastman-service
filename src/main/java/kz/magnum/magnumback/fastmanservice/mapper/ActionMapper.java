package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Action;
import kz.magnum.magnumback.fastmanservice.model.pdt.ActionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActionMapper {
    @Mapping(target = "id", source = "id")
    ActionDto toDto(Action entity);

    @Mapping(target = "incActions", ignore = true)
    @Mapping(target = "fastmanTasks", ignore = true)
    @Mapping(target = "id", source = "id")
    Action toEntity(ActionDto actionDto);
}
