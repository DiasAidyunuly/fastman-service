package kz.magnum.magnumback.fastmanservice.mapper.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.Subgroup;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.SubgroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubgroupMapper {
    @Mapping(target = "parentCodeGroup", source = "group.codeGroup")
    @Mapping(target = "isActiveSubgroup", source = "activeSubgroup")
    SubgroupDto toDto(Subgroup entity);
}