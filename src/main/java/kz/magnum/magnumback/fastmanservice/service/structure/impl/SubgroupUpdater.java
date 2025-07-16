package kz.magnum.magnumback.fastmanservice.service.structure.impl;

import kz.magnum.magnumback.fastmanservice.repository.structure.SubgroupRepository;
import kz.magnum.magnumback.fastmanservice.service.structure.StructureLevelUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubgroupUpdater implements StructureLevelUpdater {
    private final SubgroupRepository subgroupRepository;

    @Override
    public void updateStructureAndParent(Long id, Double valueReception, Double valueCritical, Boolean isActive) {
        subgroupRepository.updateSubgroupById(id, valueReception, valueCritical, isActive);
    }
}