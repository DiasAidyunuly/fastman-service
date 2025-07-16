package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.general.FilialDirector;
import kz.magnum.magnumback.fastmanservice.model.pdt.FilialDirectorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface FilialDirectorMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "territorialDirector.id", target = "territorialDirectorId")
    @Mapping(source = "filial.codeFilial", target = "codeFilial")
    @Mapping(target = "dateOpen", expression = "java(convertToDate(entity.getDateOpen()))")
    @Mapping(target = "dateClose", expression = "java(convertToDate(entity.getDateClose()))")
    FilialDirectorDto toDto(FilialDirector entity);

    default Date convertToDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.of("GMT+5")).toInstant());
    }
}
