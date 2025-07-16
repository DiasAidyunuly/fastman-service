package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistHeadQuestionStatusDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistHeadQuestionStatusModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ChecklistHeadStatusMapper.class, ChecklistQuestionStatusMapper.class})
public interface ChecklistHeadQuestionStatusMapper {
    ChecklistHeadQuestionStatusDto toDto(ChecklistHeadQuestionStatusModel model);
}