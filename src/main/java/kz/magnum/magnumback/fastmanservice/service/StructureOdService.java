package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.general.*;
import kz.magnum.magnumback.fastmanservice.model.pdt.DivisionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.FilialDirectorDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.RegionalDirectorDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.TerritorialDirectorDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.odstructure.DivisionModel;
import kz.magnum.magnumback.fastmanservice.model.pdt.odstructure.StructureOdFilterParam;

import java.util.List;

public interface StructureOdService {
    List<DivisionModel> getStructureOd(StructureOdFilterParam filter);

    Division saveDivision(Division entity);

    void deleteDivision(Short id);

    Division updateDivision(Short id, DivisionDto dto);

    RegionalDirector saveRegionalDirector(RegionalDirectorDto dto);

    void deleteRegionalDirector(Short id);

    RegionalDirector updateRegionalDirector(Short id, RegionalDirectorDto dto);

    TerritorialDirector saveTerritorialDirector(TerritorialDirectorDto dto);

    void deleteTerritorialDirector(Short id);

    TerritorialDirector updateTerritorialDirector(Short id, TerritorialDirectorDto dto);

    List<Filial> getFilials();

    FilialDirector saveFilialDirector(FilialDirectorDto dto);

    void deleteFilialDirector(Short id);

    FilialDirector updateFilialDirector(Short id, FilialDirectorDto dto);

    List<Filial> getFilialsByTabNumber(String tabNumber);

    List<DivisionModel> filterDivisions(List<DivisionModel> divisions, StructureOdFilterParam filter);
}
