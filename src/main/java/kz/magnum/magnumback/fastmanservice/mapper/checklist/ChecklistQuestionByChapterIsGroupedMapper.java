package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistQuestionByChapterIsGroupedMapper {
    ChecklistQuestionByChapterIsGroupedDto toDto(ChecklistQuestionByChapterIsGroupedModel model);
}