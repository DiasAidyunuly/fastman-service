package kz.magnum.magnumback.fastmanservice.mapper.checklist.converter;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHead;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.GetChecklistsByQuestionStatusResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GetChecklistsByQuestionStatusResponseConverter {

    @Mapping(target = "checkListId", source = "id")
    @Mapping(target = "status", source = "checklistHeadStatus.id")
    @Mapping(target = "idTemplateType", source = "checklistHeadTemp.checklistTemplate.id")
    @Mapping(target = "idTemplate", source = "checklistHeadTemp.id")
    @Mapping(target = "rolePerformer", source = "checklistHeadTemp.rolePerformer")
    @Mapping(target = "questionsTotal", source = "qtyQuestionsTotal")
    @Mapping(target = "questionsDone", source = "qtyQuestionsDone")
    GetChecklistsByQuestionStatusResponseModel toModel(ChecklistHead checklistHead);
}