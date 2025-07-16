package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.model.NewSiteException;
import kz.magnum.magnumback.fastmanservice.model.pdt.NewSiteExceptionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SiteWithCodeAndNameMapper.class, IncidentNameMapper.class})
public interface NewSiteExceptionMapper {
    NewSiteExceptionDto toDto(NewSiteException model);
}
