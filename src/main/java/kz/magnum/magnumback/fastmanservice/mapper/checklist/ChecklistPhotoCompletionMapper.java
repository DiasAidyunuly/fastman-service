package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.UpdateChecklistPhotoCompletionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.UpdateChecklistPhotoCompletionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistPhotoCompletionMapper {
    UpdateChecklistPhotoCompletionDto toDto(UpdateChecklistPhotoCompletionModel model);

    UpdateChecklistPhotoCompletionModel toDomain(UpdateChecklistPhotoCompletionDto dto);
}