package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Template;
import kz.magnum.magnumback.fastmanservice.model.pdt.TemplateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    @Mapping(target = "id", source = "id")
    TemplateDto toDto(Template entity);

    @Mapping(target = "incidents", ignore = true)
    @Mapping(target = "id", source = "id")
    Template toEntity(TemplateDto templateDto);
}
