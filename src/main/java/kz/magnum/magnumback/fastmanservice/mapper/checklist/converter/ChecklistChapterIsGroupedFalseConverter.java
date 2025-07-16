package kz.magnum.magnumback.fastmanservice.mapper.checklist.converter;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestion;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistChapterIsGroupedFalseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChecklistChapterIsGroupedFalseConverter {

    @Mapping(target = "idQuestion", source = "id")
    @Mapping(target = "idQuestionStatus", source = "checklistQuestionStatus.id")
    @Mapping(target = "idQuestionTemp", source = "checklistQuestionTemp.id")
    @Mapping(target = "answerQuestion", source = "questionAnswer")
    @Mapping(target = "questionTitle", source = "checklistQuestionTemp.questionName")
    ChecklistChapterIsGroupedFalseModel toModel(ChecklistQuestion question);
}