package kz.magnum.magnumback.fastmanservice.mapper.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.Direction;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.DirectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, GroupMapper.class, SubgroupMapper.class})
public interface DirectionMapper {
    @Mapping(target = "parentCodeHead", source = "headStructure.codeHeadStructure")
    @Mapping(target = "isActiveDirection", source = "activeDirection")
    DirectionDto toDto(Direction entity);
}