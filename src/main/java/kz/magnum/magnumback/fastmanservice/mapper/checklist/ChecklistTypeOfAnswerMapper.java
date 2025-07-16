package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistTypeOfAnswer;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistTypeOfAnswerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistTypeOfAnswerMapper {
    ChecklistTypeOfAnswerDto toDto(ChecklistTypeOfAnswer checklistTypeOfAnswer);

    ChecklistTypeOfAnswer toEntity(ChecklistTypeOfAnswerDto dto);
}
