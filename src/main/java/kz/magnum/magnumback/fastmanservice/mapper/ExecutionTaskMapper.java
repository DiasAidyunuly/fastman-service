package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.ExecutionTask;
import kz.magnum.magnumback.fastmanservice.model.pdt.ExecutionTaskDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.UpdateTaskPhotoCompletionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface ExecutionTaskMapper {
    @Mapping(target = "executionId", source = "execution.id")
    ExecutionTaskDto toDto(ExecutionTask entity);

    @Mapping(target = "execution.id", source = "executionId")
    ExecutionTask toEntity(ExecutionTaskDto dto);

    @Mapping(target = "fastmanTaskId", source = "fastmanTask.id")
    @Mapping(target = "executionTaskId", source = "id")
    @Mapping(target = "executionId", source = "execution.id")
    UpdateTaskPhotoCompletionResponse toUpdateTaskPhotoCompletionDto(ExecutionTask entity);
}