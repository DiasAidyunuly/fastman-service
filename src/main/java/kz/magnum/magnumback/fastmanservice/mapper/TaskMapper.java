package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.kafka.FastmanTask;
import kz.magnum.magnumback.fastmanservice.model.pdt.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ExecutionTaskMapper.class)
public interface TaskMapper {

    @Mapping(source = "incident.id", target = "incidentCode")
    @Mapping(source = "status.id", target = "statusCode")
    @Mapping(source = "manual", target = "isManual")
    @Mapping(source = "action.id", target = "actionCode")
    FastmanTaskDto toFastmanTaskDto(FastmanTask entity);

    @Mapping(source = "incidentCode", target = "incident.id")
    @Mapping(source = "statusCode", target = "status.id")
    @Mapping(source = "manual", target = "isManual")
    @Mapping(source = "actionCode", target = "action.id")
    FastmanTask toEntity(FastmanTaskDto dto);

    FastmanItemDto toFastmanItemDto(FastmanItemModel model);

    @Mapping(target = "incidentCode", source = "incident.id")
    @Mapping(target = "statusCode", source = "status.id")
    @Mapping(target = "actionCode", source = "action.id")
    @Mapping(target = "fastmanTaskId", source = "id")
    GetFastmanTaskDto toGetFastmanTaskDto(FastmanTask entity);

    CompletedItemDto toCompletedItemDto(CompletedItemModel model);

    CreateTaskParamModel toCreateTaskModel(CreateTaskParamDto model);

    GetItemsStockDto toItemsStockDto(GetItemsStockModel model);

    GetItemsStockParamModel toItemsStockParamModel(GetItemsStockParamDto dto);
}