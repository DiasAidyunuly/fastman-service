package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestionStatus;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistQuestionStatusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistQuestionStatusMapper {
    ChecklistQuestionStatusDto toDto(ChecklistQuestionStatus entity);

    ChecklistQuestionStatus toEntity(ChecklistQuestionStatusDto dto);
}