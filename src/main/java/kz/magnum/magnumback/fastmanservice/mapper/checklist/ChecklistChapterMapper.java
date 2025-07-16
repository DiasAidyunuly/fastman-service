package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistChapter;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistChapterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ChecklistHeadTempMapper.class, ChecklistTemplateMapper.class})
public interface ChecklistChapterMapper {
    @Mapping(target = "checklistHeadTempDto", source = "checklistHeadTemp")
    ChecklistChapterDto toDto(ChecklistChapter entity);

    @Mapping(target = "checklistHeadTemp", source = "checklistHeadTempDto")
    ChecklistChapter toEntity(ChecklistChapterDto dto);
}