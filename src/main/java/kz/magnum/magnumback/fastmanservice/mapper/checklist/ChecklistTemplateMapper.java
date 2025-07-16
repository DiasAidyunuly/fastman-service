package kz.magnum.magnumback.fastmanservice.mapper.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistTemplate;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistTemplateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChecklistTemplateMapper {
    ChecklistTemplateDto toDto(ChecklistTemplate entity);

    ChecklistTemplate toEntity(ChecklistTemplateDto dto);
}
