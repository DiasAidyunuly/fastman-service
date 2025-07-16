package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.model.SiteWithCodeAndName;
import kz.magnum.magnumback.fastmanservice.model.pdt.SiteWithCodeAndNameDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiteWithCodeAndNameMapper {
    SiteWithCodeAndNameDto toDto(SiteWithCodeAndName model);
}
