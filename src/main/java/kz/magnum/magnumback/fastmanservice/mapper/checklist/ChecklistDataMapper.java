package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistDataDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistDataModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistDataMapper {
    ChecklistDataDto toDto(ChecklistDataModel model);
}