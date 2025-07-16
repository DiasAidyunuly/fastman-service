package kz.magnum.magnumback.fastmanservice.service.structure.impl;

import kz.magnum.magnumback.fastmanservice.entity.structure.Department;
import kz.magnum.magnumback.fastmanservice.entity.structure.Direction;
import kz.magnum.magnumback.fastmanservice.entity.structure.Group;
import kz.magnum.magnumback.fastmanservice.entity.structure.Subgroup;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.structure.DepartmentRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.DirectionRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.GroupRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.SubgroupRepository;
import kz.magnum.magnumback.fastmanservice.service.structure.StructureLevelUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DirectionUpdater implements StructureLevelUpdater {
    private final DirectionRepository directionRepository;
    private final DepartmentRepository departmentRepository;
    private final GroupRepository groupRepository;
    private final SubgroupRepository subgroupRepository;

    @Override
    @Transactional
    public void updateStructureAndParent(Long id, Double valueReception, Double valueCritical, Boolean isActive) {
        Direction direction = directionRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Дирекция с таким ID = %d не найдена", id)));
        directionRepository.updateDirectionById(id, valueReception, valueCritical, isActive);

        List<Long> departmentCodes = direction.getDepartments()
            .stream()
            .map(Department::getCodeDepartment)
            .collect(Collectors.toList());
        departmentRepository.updateDepartments(departmentCodes, valueReception, valueCritical, isActive);

        List<Long> groupCodes = direction.getDepartments()
            .stream()
            .flatMap(dir -> dir.getGroups()
                .stream()
                .map(Group::getCodeGroup))
            .collect(Collectors.toList());
        groupRepository.updateGroups(groupCodes, valueReception, valueCritical, isActive);

        List<Long> subgroupCodes = direction.getDepartments()
            .stream()
            .flatMap(dir -> dir.getGroups()
                .stream()
                .flatMap(group -> group.getSubgroups()
                    .stream()
                    .map(Subgroup::getCodeSubgroup)))
            .collect(Collectors.toList());
        subgroupRepository.updateSubgroups(subgroupCodes, valueReception, valueCritical, isActive);
    }
}