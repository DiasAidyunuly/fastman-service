package kz.magnum.magnumback.fastmanservice.service.structure.impl;

import kz.magnum.magnumback.fastmanservice.entity.structure.Department;
import kz.magnum.magnumback.fastmanservice.entity.structure.Direction;
import kz.magnum.magnumback.fastmanservice.entity.structure.Group;
import kz.magnum.magnumback.fastmanservice.entity.structure.Subgroup;
import kz.magnum.magnumback.fastmanservice.model.enums.StructureLevelEnum;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.UpdateStructureLevelsParamModel;
import kz.magnum.magnumback.fastmanservice.repository.structure.DepartmentRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.DirectionRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.GroupRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.SubgroupRepository;
import kz.magnum.magnumback.fastmanservice.service.structure.StructureLevelService;
import kz.magnum.magnumback.fastmanservice.service.structure.StructureLevelUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StructureLevelServiceImpl implements StructureLevelService {
    private final DirectionRepository directionRepository;
    private final DepartmentRepository departmentRepository;
    private final GroupRepository groupRepository;
    private final SubgroupRepository subgroupRepository;
    private final DirectionUpdater directionUpdater;
    private final DepartmentUpdater departmentUpdater;
    private final GroupUpdater groupUpdater;
    private final SubgroupUpdater subgroupUpdater;

    @Override
    public List<Direction> findDirections() {
        List<Direction> resultList = new ArrayList<>();
        List<Direction> allDirections = directionRepository.findAll();

        // Departments grouped by directionCode
        Map<Long, List<Department>> groupedDepartments = departmentRepository.findAll()
            .stream()
            .filter(e -> e.getDirection() != null)
            .collect(Collectors.groupingBy(e -> e.getDirection().getCodeDirection()));

        // Groups grouped by departmentCode
        Map<Long, List<Group>> groupedGroups = groupRepository.findAll()
            .stream()
            .filter(e -> e.getDepartment() != null)
            .collect(Collectors.groupingBy(e -> e.getDepartment().getCodeDepartment()));

        // Subgroups grouped by groupCode
        Map<Long, List<Subgroup>> groupedSubgroups = subgroupRepository.findAll()
            .stream()
            .filter(e -> e.getGroup() != null)
            .collect(Collectors.groupingBy(e -> e.getGroup().getCodeGroup()));

        for (Direction direction : allDirections) {
            List<Department> departments = groupedDepartments.get(direction.getCodeDirection());
            direction.setDepartments(departments);

            for (Department department : departments) {
                List<Group> groups = groupedGroups.get(department.getCodeDepartment());
                department.setGroups(groups);

                for (Group group : groups) {
                    List<Subgroup> subgroups = groupedSubgroups.get(group.getCodeGroup());
                    group.setSubgroups(subgroups);
                }
            }
            resultList.add(direction);
        }
        return resultList;
    }

    @Transactional
    @Override
    public void updateStructureLevels(UpdateStructureLevelsParamModel param) {
        Map<StructureLevelEnum, StructureLevelUpdater> updaterMap = Map.of(
            StructureLevelEnum.DIRECTION, directionUpdater,
            StructureLevelEnum.DEPARTMENT, departmentUpdater,
            StructureLevelEnum.GROUP, groupUpdater,
            StructureLevelEnum.SUBGROUP, subgroupUpdater);

        try {
            StructureLevelUpdater structureLevelUpdater = updaterMap.get(StructureLevelEnum.fromLevel(param.getStrLevel()));
            structureLevelUpdater.updateStructureAndParent(param.getId(), param.getValueReception(), param.getValueCritical(), param.isActive());
        } catch (Exception e) {
            log.error("Error during updateStructureLevels: {}", e.getMessage());
        }
    }
}