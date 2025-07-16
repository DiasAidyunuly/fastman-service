package kz.magnum.magnumback.fastmanservice.service.structure.impl;

import kz.magnum.magnumback.fastmanservice.entity.structure.Group;
import kz.magnum.magnumback.fastmanservice.entity.structure.Subgroup;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.structure.GroupRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.SubgroupRepository;
import kz.magnum.magnumback.fastmanservice.service.structure.StructureLevelUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupUpdater implements StructureLevelUpdater {
    private final GroupRepository groupRepository;
    private final SubgroupRepository subgroupRepository;

    public void updateStructureAndParent(Long id, Double valueReception, Double valueCritical, Boolean isActive) {
        Group group = groupRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Группа с таким ID = %d не найдена", id)));
        groupRepository.updateGroupById(id, valueReception, valueCritical, isActive);

        List<Long> subgroupCodes = group.getSubgroups()
            .stream()
            .map(Subgroup::getCodeSubgroup)
            .collect(Collectors.toList());
        subgroupRepository.updateSubgroups(subgroupCodes, valueReception, valueCritical, isActive);
    }
}