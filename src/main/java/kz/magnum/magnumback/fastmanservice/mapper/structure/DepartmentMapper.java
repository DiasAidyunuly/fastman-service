package kz.magnum.magnumback.fastmanservice.mapper.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.Department;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.DepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, SubgroupMapper.class})
public interface DepartmentMapper {
    @Mapping(target = "parentCodeDirection", source = "direction.codeDirection")
    @Mapping(target = "isActiveDepartment", source = "activeDepartment")
    DepartmentDto toDto(Department entity);
}