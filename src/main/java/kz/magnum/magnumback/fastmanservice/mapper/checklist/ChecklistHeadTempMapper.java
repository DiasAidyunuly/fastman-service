package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHeadTemp;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistHeadTempDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ChecklistTemplateMapper.class)
public interface ChecklistHeadTempMapper {
    @Mapping(target = "checklistTemplateDto", source = "checklistTemplate")
    @Mapping(target = "isOnlyTheCreator", source = "onlyTheCreator")
    @Mapping(target = "isNeedPlanning", source = "needPlanning")
    @Mapping(target = "isPhotoGallery", source = "photoGallery")
    ChecklistHeadTempDto toDto(ChecklistHeadTemp entity);

    @Mapping(target = "checklistTemplate", source = "checklistTemplateDto")
    @Mapping(target = "isOnlyTheCreator", source = "onlyTheCreator")
    @Mapping(target = "isNeedPlanning", source = "needPlanning")
    @Mapping(target = "isPhotoGallery", source = "photoGallery")
    ChecklistHeadTemp toEntity(ChecklistHeadTempDto dto);
}