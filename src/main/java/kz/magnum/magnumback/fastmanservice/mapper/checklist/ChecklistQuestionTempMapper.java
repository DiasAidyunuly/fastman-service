package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestionTemp;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistQuestionTempDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistQuestionTempModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
    ChecklistChapterMapper.class
    , ChecklistHeadTempMapper.class
    , ChecklistTemplateMapper.class
    , ChecklistTypeOfAnswerMapper.class})
public interface ChecklistQuestionTempMapper {
    @Mapping(target = "checklistChapterDto", source = "checklistChapter")
    @Mapping(target = "checklistTypeOfAnswerDto", source = "checklistTypeOfAnswer")
    ChecklistQuestionTempDto toDto(ChecklistQuestionTemp entity);

    @Mapping(target = "checklistChapter", source = "checklistChapterDto")
    @Mapping(target = "checklistTypeOfAnswer", source = "checklistTypeOfAnswerDto")
    ChecklistQuestionTemp toEntity(ChecklistQuestionTempDto dto);

    @Mapping(target = "checklistChapter", source = "checklistChapterDto")
    @Mapping(target = "checklistTypeOfAnswer", source = "checklistTypeOfAnswerDto")
    ChecklistQuestionTempModel mapToDomain(ChecklistQuestionTempDto dto);

    @Mapping(target = "checklistChapterDto", source = "checklistChapter")
    @Mapping(target = "checklistTypeOfAnswerDto", source = "checklistTypeOfAnswer")
    ChecklistQuestionTempDto mapToDto(ChecklistQuestionTempModel model);
}