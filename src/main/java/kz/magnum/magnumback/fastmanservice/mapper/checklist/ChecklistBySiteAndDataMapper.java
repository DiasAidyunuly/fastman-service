package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistBySiteAndDataDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistBySiteAndDataModel;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ChecklistDataMapper.class, ChecklistMapper.class})
public interface ChecklistBySiteAndDataMapper {
    ChecklistBySiteAndDataDto toDto(ChecklistBySiteAndDataModel model);
}