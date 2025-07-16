package kz.magnum.magnumback.fastmanservice.service.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.Direction;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.UpdateStructureLevelsParamModel;

import java.util.List;

public interface StructureLevelService {
    List<Direction> findDirections();

    void updateStructureLevels(UpdateStructureLevelsParamModel param);
}