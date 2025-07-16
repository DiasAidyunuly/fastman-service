package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.GetChecklistsByQuestionStatusResponseDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.GetChecklistsByQuestionStatusResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ChecklistQuestionByChapterIsGroupedMapper.class)
public interface GetChecklistsByQuestionStatusResponseMapper {
    GetChecklistsByQuestionStatusResponseDto toDto(GetChecklistsByQuestionStatusResponseModel model);
}