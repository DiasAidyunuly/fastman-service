package kz.magnum.magnumback.fastmanservice.mapper;

import kz.magnum.magnumback.fastmanservice.entity.general.Division;
import kz.magnum.magnumback.fastmanservice.entity.general.FilialDirector;
import kz.magnum.magnumback.fastmanservice.entity.general.RegionalDirector;
import kz.magnum.magnumback.fastmanservice.entity.general.TerritorialDirector;
import kz.magnum.magnumback.fastmanservice.model.pdt.odstructure.DivisionModel;
import kz.magnum.magnumback.fastmanservice.model.pdt.odstructure.FilialDirectorModel;
import kz.magnum.magnumback.fastmanservice.model.pdt.odstructure.RegionalDirectorModel;
import kz.magnum.magnumback.fastmanservice.model.pdt.odstructure.TerritorialDirectorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StructureOdMapper {
    @Mapping(source = "id", target = "divisionId")
    @Mapping(source = "regionalDirectors", target = "regionalDirectorModels")
    DivisionModel toDivisionDto(Division entity);

    @Mapping(source = "id", target = "regionalDirectorId")
    @Mapping(source = "division.id", target = "parentId")
    @Mapping(source = "territorialDirectors", target = "territorialDirectorModels")
    RegionalDirectorModel toRegionalDirectorDto(RegionalDirector entity);

    @Mapping(source = "id", target = "territorialDirectorId")
    @Mapping(source = "regionalDirector.id", target = "parentId")
    @Mapping(source = "filialDirectors", target = "filialDirectorModels")
    TerritorialDirectorModel toTerritorialDirectorDto(TerritorialDirector entity);

    @Mapping(source = "id", target = "filialDirectorId")
    @Mapping(source = "territorialDirector.id", target = "parentId")
    @Mapping(source = "filial", target = "filialModel")
    FilialDirectorModel toFilialDirectorDto(FilialDirector entity);
}
