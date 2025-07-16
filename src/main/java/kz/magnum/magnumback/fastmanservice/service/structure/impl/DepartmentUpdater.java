package kz.magnum.magnumback.fastmanservice.service.structure.impl;

import kz.magnum.magnumback.fastmanservice.entity.structure.Department;
import kz.magnum.magnumback.fastmanservice.entity.structure.Group;
import kz.magnum.magnumback.fastmanservice.entity.structure.Subgroup;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.structure.DepartmentRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.GroupRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.SubgroupRepository;
import kz.magnum.magnumback.fastmanservice.service.structure.StructureLevelUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentUpdater implements StructureLevelUpdater {
    private final DepartmentRepository departmentRepository;
    private final GroupRepository groupRepository;
    private final SubgroupRepository subgroupRepository;

    @Override
    public void updateStructureAndParent(Long id, Double valueReception, Double valueCritical, Boolean isActive) {
        Department department = departmentRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Департамент с таким ID = %d не найден", id)));
        departmentRepository.updateDepartmentById(id, valueReception, valueCritical, isActive);

        List<Long> groupCodes = department.getGroups()
            .stream()
            .map(Group::getCodeGroup)
            .collect(Collectors.toList());
        groupRepository.updateGroups(groupCodes, valueReception, valueCritical, isActive);

        List<Long> subgroupCodes = department.getGroups()
            .stream()
            .flatMap(dep -> dep.getSubgroups()
                .stream()
                .map(Subgroup::getCodeSubgroup))
            .collect(Collectors.toList());
        subgroupRepository.updateSubgroups(subgroupCodes, valueReception, valueCritical, isActive);
    }
}