package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.general.Filial;
import kz.magnum.magnumback.fastmanservice.model.pdt.FilialDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilialMapper {
    FilialDto toDto(Filial entity);

    Filial toEntity(FilialDto dto);
}
