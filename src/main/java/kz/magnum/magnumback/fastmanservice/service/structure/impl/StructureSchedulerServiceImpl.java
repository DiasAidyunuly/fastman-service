package kz.magnum.magnumback.fastmanservice.service.structure.impl;

import kz.magnum.magnumback.fastmanservice.entity.structure.*;
import kz.magnum.magnumback.fastmanservice.mapper.converter.StructureAndGoldDataConverter;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.*;
import kz.magnum.magnumback.fastmanservice.repository.gold.StrucobjRepository;
import kz.magnum.magnumback.fastmanservice.repository.structure.*;
import kz.magnum.magnumback.fastmanservice.service.structure.StructureSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StructureSchedulerServiceImpl implements StructureSchedulerService {
    private final StrucobjRepository strucobjRepository;
    private final StructureAndGoldDataConverter structureAndGoldDataConverter;
    private final HeadStructureRepository headStructureRepository;
    private final DirectionRepository directionRepository;
    private final DepartmentRepository departmentRepository;
    private final GroupRepository groupRepository;
    private final SubgroupRepository subgroupRepository;

    @Override
    @Transactional
    public void saveToStructureTable() {
        saveToHeadStructure();
        saveToDirection();
        saveToDepartment();
        saveToGroup();
        saveToSubgroup();
    }

    private void saveToHeadStructure() {
        List<Object[]> goldData;
        Set<Long> headStructureCodes;
        Set<Long> goldCodes = new HashSet<>();

        try {
            goldData = strucobjRepository.findDataMapToHeadStructure();
            headStructureCodes = headStructureRepository.findHeadStructureCodes();
        } catch (Exception exception) {
            log.error("Error during get HeadStructure data, " + exception.getMessage());
            throw exception;
        }

        // Converting data from GOLD
        List<HeadStructure> goldList = goldData
            .stream()
            .map(row -> {
                GetGoldDataMapToHeadStructureModel mappedModel = GetGoldDataMapToHeadStructureModel.builder()
                    .sobcint(Long.valueOf(String.valueOf(row[0])))
                    .sobcext(String.valueOf(row[1]))
                    .tsobdesc(String.valueOf(row[2]))
                    .sobcextin(String.valueOf(row[3]))
                    .build();
                goldCodes.add(Long.valueOf(String.valueOf(row[0])));
                return structureAndGoldDataConverter.toHeadStructure(mappedModel);
            })
            .collect(Collectors.toList());

        // Deleting HeadStructures
        if (!headStructureCodes.isEmpty()) {
            Set<Long> toDeleteCodes = headStructureCodes
                .stream()
                .filter(code -> !goldCodes.contains(code))
                .collect(Collectors.toSet());
            if (!toDeleteCodes.isEmpty()) {
                try {
                    headStructureRepository.deleteByCodeHeadStructureIn(toDeleteCodes);
                } catch (Exception exception) {
                    log.error("Error during delete HeadStructure data, " + exception.getMessage());
                    throw exception;
                }
            }
        }

        // Adding new HeadStructures
        List<HeadStructure> toAddList = goldList
            .stream()
            .filter(headStructure -> !headStructureCodes.contains(headStructure.getCodeHeadStructure()))
            .collect(Collectors.toList());
        if (!toAddList.isEmpty()) {
            try {
                headStructureRepository.saveAll(toAddList);
            } catch (Exception exception) {
                log.error("Error during save HeadStructure data, " + exception.getMessage());
                throw exception;
            }
        }
    }

    private void saveToDirection() {
        List<Object[]> goldData;
        Set<Long> directionCodes;
        Set<Long> goldCodes = new HashSet<>();

        try {
            goldData = strucobjRepository.findDataMapToDirection();
            directionCodes = directionRepository.findDirectionCodes();
        } catch (Exception exception) {
            log.error("Error during get Direction data, " + exception.getMessage());
            throw exception;
        }

        // Get all HeadStructures
        Map<Long, HeadStructure> headStructureMap = headStructureRepository.findAll()
            .stream()
            .collect(Collectors.toMap(HeadStructure::getCodeHeadStructure, Function.identity()));

        // Converting data from GOLD
        List<Direction> goldList = goldData
            .stream()
            .map(row -> {
                GetGoldDataMapToDirectionModel mappedModel = GetGoldDataMapToDirectionModel.builder()
                    .sobcint(Long.valueOf(String.valueOf(row[0])))
                    .sobcext(String.valueOf(row[1]))
                    .tsobdesc(String.valueOf(row[2]))
                    .strpere(Long.valueOf(String.valueOf(row[3])))
                    .sobcextin(String.valueOf(row[4]))
                    .build();
                goldCodes.add(Long.valueOf(String.valueOf(row[0])));
                Direction direction = structureAndGoldDataConverter.toDirection(mappedModel);
                direction.setHeadStructure(headStructureMap.get(mappedModel.getStrpere()));
                return direction;
            })
            .collect(Collectors.toList());

        // Deleting Directions
        if (!directionCodes.isEmpty()) {
            Set<Long> toDeleteCodes = directionCodes
                .stream()
                .filter(code -> !goldCodes.contains(code))
                .collect(Collectors.toSet());
            if (!toDeleteCodes.isEmpty()) {
                try {
                    directionRepository.deleteByCodeDirectionIn(toDeleteCodes);
                } catch (Exception exception) {
                    log.error("Error during delete Direction data, " + exception.getMessage());
                    throw exception;
                }
            }
        }

        // Adding new Directions
        List<Direction> toAddList = goldList
            .stream()
            .filter(direction -> !directionCodes.contains(direction.getCodeDirection()))
            .collect(Collectors.toList());
        if (!toAddList.isEmpty()) {
            try {
                directionRepository.saveAll(toAddList);
            } catch (Exception exception) {
                log.error("Error during save Direction data, " + exception.getMessage());
                throw exception;
            }
        }
    }

    private void saveToDepartment() {
        List<Object[]> goldData;
        Set<Long> departmentCodes;
        Set<Long> goldCodes = new HashSet<>();

        try {
            goldData = strucobjRepository.findDataMapToDepartment();
            departmentCodes = departmentRepository.findDepartmentCodes();
        } catch (Exception exception) {
            log.error("Error during get Department data, " + exception.getMessage());
            throw exception;
        }

        // Get all Directions
        Map<Long, Direction> directionMap = directionRepository.findAll()
            .stream()
            .collect(Collectors.toMap(Direction::getCodeDirection, Function.identity()));

        // Converting data from GOLD
        List<Department> goldList = goldData
            .stream()
            .map(row -> {
                GetGoldDataMapToDepartmentModel mappedModel = GetGoldDataMapToDepartmentModel.builder()
                    .sobcint(Long.valueOf(String.valueOf(row[0])))
                    .sobcext(String.valueOf(row[1]))
                    .tsobdesc(String.valueOf(row[2]))
                    .strpere(Long.valueOf(String.valueOf(row[3])))
                    .sobcextin(String.valueOf(row[4]))
                    .build();
                goldCodes.add(Long.valueOf(String.valueOf(row[0])));
                Department department = structureAndGoldDataConverter.toDepartment(mappedModel);
                department.setDirection(directionMap.get(mappedModel.getStrpere()));
                return department;
            })
            .collect(Collectors.toList());

        // Deleting Departments
        if (!departmentCodes.isEmpty()) {
            Set<Long> toDeleteCodes = departmentCodes
                .stream()
                .filter(code -> !goldCodes.contains(code))
                .collect(Collectors.toSet());
            if (!toDeleteCodes.isEmpty()) {
                try {
                    departmentRepository.deleteByCodeDepartmentIn(toDeleteCodes);
                } catch (Exception exception) {
                    log.error("Error during delete Department data, " + exception.getMessage());
                    throw exception;
                }
            }
        }

        // Adding new Departments
        List<Department> toAddList = goldList
            .stream()
            .filter(department -> !departmentCodes.contains(department.getCodeDepartment()))
            .collect(Collectors.toList());
        if (!toAddList.isEmpty()) {
            try {
                departmentRepository.saveAll(toAddList);
            } catch (Exception exception) {
                log.error("Error during save Department data, " + exception.getMessage());
                throw exception;
            }
        }
    }

    private void saveToGroup() {
        List<Object[]> goldData;
        Set<Long> groupCodes;
        Set<Long> goldCodes = new HashSet<>();

        try {
            goldData = strucobjRepository.findDataMapToGroup();
            groupCodes = groupRepository.findGroupCodes();
        } catch (Exception exception) {
            log.error("Error during get Group data, " + exception.getMessage());
            throw exception;
        }

        // Get all Departments
        Map<Long, Department> departmentMap = departmentRepository.findAll()
            .stream()
            .collect(Collectors.toMap(Department::getCodeDepartment, Function.identity()));

        // Converting data from GOLD
        List<Group> goldList = goldData
            .stream()
            .map(row -> {
                GetGoldDataMapToGroupModel mappedModel = GetGoldDataMapToGroupModel.builder()
                    .sobcint(Long.valueOf(String.valueOf(row[0])))
                    .sobcext(String.valueOf(row[1]))
                    .tsobdesc(String.valueOf(row[2]))
                    .strpere(Long.valueOf(String.valueOf(row[3])))
                    .sobcextin(String.valueOf(row[4]))
                    .build();
                goldCodes.add(Long.valueOf(String.valueOf(row[0])));
                Group group = structureAndGoldDataConverter.toGroup(mappedModel);
                group.setDepartment(departmentMap.get(mappedModel.getStrpere()));
                return group;
            })
            .collect(Collectors.toList());

        // Deleting Groups
        if (!groupCodes.isEmpty()) {
            Set<Long> toDeleteCodes = groupCodes
                .stream()
                .filter(code -> !goldCodes.contains(code))
                .collect(Collectors.toSet());
            if (!toDeleteCodes.isEmpty()) {
                try {
                    groupRepository.deleteByCodeGroupIn(toDeleteCodes);
                } catch (Exception exception) {
                    log.error("Error during delete Group data, " + exception.getMessage());
                    throw exception;
                }
            }
        }

        // Adding new Groups
        List<Group> toAddList = goldList
            .stream()
            .filter(group -> !groupCodes.contains(group.getCodeGroup()))
            .collect(Collectors.toList());
        if (!toAddList.isEmpty()) {
            try {
                groupRepository.saveAll(toAddList);
            } catch (Exception exception) {
                log.error("Error during save Group data, " + exception.getMessage());
                throw exception;
            }
        }
    }

    private void saveToSubgroup() {
        List<Object[]> goldData;
        Set<Long> subgroupCodes;
        Set<Long> goldCodes = new HashSet<>();

        try {
            goldData = strucobjRepository.findDataMapToSubGroup();
            subgroupCodes = subgroupRepository.findSubgroupCodes();
        } catch (Exception exception) {
            log.error("Error during get Subgroup data, " + exception.getMessage());
            throw exception;
        }

        // Get all Groups
        Map<Long, Group> groupMap = groupRepository.findAll()
            .stream()
            .collect(Collectors.toMap(Group::getCodeGroup, Function.identity()));

        // Converting data from GOLD
        List<Subgroup> goldList = goldData
            .stream()
            .map(row -> {
                GetGoldDataMapToSubgroupModel mappedModel = GetGoldDataMapToSubgroupModel.builder()
                    .sobcint(Long.valueOf(String.valueOf(row[0])))
                    .sobcext(String.valueOf(row[1]))
                    .tsobdesc(String.valueOf(row[2]))
                    .strpere(Long.valueOf(String.valueOf(row[3])))
                    .sobcextin(String.valueOf(row[4]))
                    .build();
                goldCodes.add(Long.valueOf(String.valueOf(row[0])));
                Subgroup subgroup = structureAndGoldDataConverter.toSubgroup(mappedModel);
                subgroup.setGroup(groupMap.get(mappedModel.getStrpere()));
                return subgroup;
            })
            .collect(Collectors.toList());

        // Deleting Subgroups
        if (!subgroupCodes.isEmpty()) {
            Set<Long> toDeleteCodes = subgroupCodes
                .stream()
                .filter(code -> !goldCodes.contains(code))
                .collect(Collectors.toSet());
            if (!toDeleteCodes.isEmpty()) {
                try {
                    subgroupRepository.deleteByCodeSubgroupIn(toDeleteCodes);
                } catch (Exception exception) {
                    log.error("Error during delete Subgroup data, " + exception.getMessage());
                    throw exception;
                }
            }
        }

        // Adding new Subgroups
        List<Subgroup> toAddList = goldList
            .stream()
            .filter(subgroup -> !subgroupCodes.contains(subgroup.getCodeSubgroup()))
            .collect(Collectors.toList());
        if (!toAddList.isEmpty()) {
            try {
                subgroupRepository.saveAll(toAddList);
            } catch (Exception exception) {
                log.error("Error during save Subgroup data, " + exception.getMessage());
                throw exception;
            }
        }
    }
}