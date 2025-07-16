package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistMapper {
    ChecklistDto toDto(ChecklistModel model);
}