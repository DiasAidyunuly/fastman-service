package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHeadStatus;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistHeadStatusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistHeadStatusMapper {
    ChecklistHeadStatusDto toDto(ChecklistHeadStatus entity);

    ChecklistHeadStatus toEntity(ChecklistHeadStatusDto dto);
}