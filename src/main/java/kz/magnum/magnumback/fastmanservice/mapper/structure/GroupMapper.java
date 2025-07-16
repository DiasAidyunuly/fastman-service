package kz.magnum.magnumback.fastmanservice.mapper.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.Group;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.GroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SubgroupMapper.class)
public interface GroupMapper {
    @Mapping(target = "parentCodeDepartment", source = "department.codeDepartment")
    @Mapping(target = "isActiveGroup", source = "activeGroup")
    GroupDto toDto(Group entity);
}