package kz.magnum.magnumback.fastmanservice.mapper.converter;

import kz.magnum.magnumback.fastmanservice.entity.structure.*;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StructureAndGoldDataConverter {
    Short FIRST_STRUCTURE_LEVEL_VALUE = 1;
    Short SECOND_STRUCTURE_LEVEL_VALUE = 2;
    Short THIRD_STRUCTURE_LEVEL_VALUE = 3;
    Short FOURTH_STRUCTURE_LEVEL_VALUE = 4;
    Short FIFTH_STRUCTURE_LEVEL_VALUE = 5;

    @Mapping(target = "codeHeadStructure", source = "sobcint")
    @Mapping(target = "shortNameHeadStructure", source = "sobcext")
    @Mapping(target = "longNameHeadStructure", source = "tsobdesc")
    @Mapping(target = "codeHeadSprut", source = "sobcextin")
    @Mapping(target = "strLevelHead", expression = "java(FIRST_STRUCTURE_LEVEL_VALUE)")
    HeadStructure toHeadStructure(GetGoldDataMapToHeadStructureModel model);

    @Mapping(target = "codeDirection", source = "sobcint")
    @Mapping(target = "shortNameDirection", source = "sobcext")
    @Mapping(target = "longNameDirection", source = "tsobdesc")
    @Mapping(target = "strLevelDirection", expression = "java(SECOND_STRUCTURE_LEVEL_VALUE)")
    @Mapping(target = "codeDirectionSprut", source = "sobcextin")
    @Mapping(target = "isActiveDirection", expression = "java(false)")
    Direction toDirection(GetGoldDataMapToDirectionModel model);

    @Mapping(target = "codeDepartment", source = "sobcint")
    @Mapping(target = "shortNameDepartment", source = "sobcext")
    @Mapping(target = "longNameDepartment", source = "tsobdesc")
    @Mapping(target = "strLevelDepartment", expression = "java(THIRD_STRUCTURE_LEVEL_VALUE)")
    @Mapping(target = "codeDepartmentSprut", source = "sobcextin")
    @Mapping(target = "isActiveDepartment", expression = "java(false)")
    Department toDepartment(GetGoldDataMapToDepartmentModel model);

    @Mapping(target = "codeGroup", source = "sobcint")
    @Mapping(target = "shortNameGroup", source = "sobcext")
    @Mapping(target = "longNameGroup", source = "tsobdesc")
    @Mapping(target = "strLevelGroup", expression = "java(FOURTH_STRUCTURE_LEVEL_VALUE)")
    @Mapping(target = "codeGroupSprut", source = "sobcextin")
    @Mapping(target = "isActiveGroup", expression = "java(false)")
    Group toGroup(GetGoldDataMapToGroupModel model);

    @Mapping(target = "codeSubgroup", source = "sobcint")
    @Mapping(target = "shortNameSubgroup", source = "sobcext")
    @Mapping(target = "longNameSubgroup", source = "tsobdesc")
    @Mapping(target = "strLevelSubgroup", expression = "java(FIFTH_STRUCTURE_LEVEL_VALUE)")
    @Mapping(target = "codeSubgroupSprut", source = "sobcextin")
    @Mapping(target = "isActiveSubgroup", expression = "java(false)")
    Subgroup toSubgroup(GetGoldDataMapToSubgroupModel model);
}