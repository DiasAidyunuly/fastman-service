package kz.magnum.magnumback.fastmanservice.mapper.structure;

import kz.magnum.magnumback.fastmanservice.model.pdt.structure.UpdateStructureLevelsParamDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.UpdateStructureLevelsParamModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StructureLevelMapper {
    @Mapping(target = "isActive", source = "active")
    UpdateStructureLevelsParamDto toDto(UpdateStructureLevelsParamModel model);

    @Mapping(target = "isActive", source = "active")
    UpdateStructureLevelsParamModel toModel(UpdateStructureLevelsParamDto dto);
}