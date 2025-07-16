package kz.magnum.magnumback.fastmanservice.service.structure;

public interface StructureLevelUpdater {
    void updateStructureAndParent(Long id, Double valueReception, Double valueCritical, Boolean isActive);
}