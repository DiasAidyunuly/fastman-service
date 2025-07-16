package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestion;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistQuestionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.UpdateChecklistQuestionParamDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.UpdateChecklistQuestionParamModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChecklistQuestionMapper {
    @Mapping(target = "checklistHeadId", source = "checklistHead.id")
    @Mapping(target = "checklistQuestionStatusId", source = "checklistQuestionStatus.id")
    @Mapping(target = "checklistQuestionTempId", source = "checklistQuestionTemp.id")
    ChecklistQuestionDto toDto(ChecklistQuestion entity);

    UpdateChecklistQuestionParamModel toUpdateDomain(UpdateChecklistQuestionParamDto dto);
}