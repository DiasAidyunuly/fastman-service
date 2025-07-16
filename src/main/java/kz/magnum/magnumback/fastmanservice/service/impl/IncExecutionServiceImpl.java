package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncExecution;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.model.IncExecutionLocal;
import kz.magnum.magnumback.fastmanservice.model.IncExecutionWithoutIncidentId;
import kz.magnum.magnumback.fastmanservice.repository.fastman.IncExecutionRepository;
import kz.magnum.magnumback.fastmanservice.service.ExecutionService;
import kz.magnum.magnumback.fastmanservice.service.IncExecutionService;
import kz.magnum.magnumback.fastmanservice.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncExecutionServiceImpl implements IncExecutionService {
    private final IncExecutionRepository incExecutionRepository;
    private final IncidentService incidentService;
    private final ExecutionService executionService;

    @Override
    public List<IncExecution> findByIncidentId(Long incidentId) {
        incidentService.findById(incidentId);
        return incExecutionRepository.findAllByIncidentId(incidentId);
    }

    @Override
    public List<IncExecutionLocal> findIncExecutionsLocal() {
        List<IncExecutionLocal> resultList = new ArrayList<>();
        List<IncExecution> findAllResultList = incExecutionRepository.findAll();
        Map<Long, List<IncExecution>> groupedMap = findAllResultList.stream().collect(
            Collectors.groupingBy(incExecution -> incExecution.getIncident().getId()));
        for (Map.Entry<Long, List<IncExecution>> entry : groupedMap.entrySet()) {
            List<IncExecutionWithoutIncidentId> innerResultList = new ArrayList<>();
            for (IncExecution i : entry.getValue()) {
                IncExecutionWithoutIncidentId incExecutionWithoutIncidentId = IncExecutionWithoutIncidentId.builder()
                    .execution(i.getExecution())
                    .mandatory(i.getMandatory())
                    .allowCheck(i.getAllowCheck())
                    .build();
                innerResultList.add(incExecutionWithoutIncidentId);
            }
            IncExecutionLocal incExecutionLocal = IncExecutionLocal.builder()
                .incidentId(entry.getKey())
                .incExecutions(innerResultList)
                .build();
            resultList.add(incExecutionLocal);
        }
        return resultList;
    }

    @Override
    public IncExecution save(IncExecution incExecutionParam) {
        incExecutionParam.setIncident(incidentService.findById(incExecutionParam.getIncident().getId()));
        incExecutionParam.setExecution(executionService.findById(incExecutionParam.getExecution().getId()));
        return incExecutionRepository.save(incExecutionParam);
    }

    @Override
    public void delete(Long id) {
        incExecutionRepository.deleteById(id);
    }

    @Override
    public IncExecution update(Long id, IncExecution incExecutionParam) {
        findById(id);
        incExecutionParam.setId(id);
        incExecutionParam.setIncident(incidentService.findById(incExecutionParam.getIncident().getId()));
        incExecutionParam.setExecution(executionService.findById(incExecutionParam.getExecution().getId()));
        return incExecutionRepository.save(incExecutionParam);
    }

    @Override
    public IncExecution findById(Long id) {
        return incExecutionRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Инцидент с отработкой с таким ID %d не найден", id)));
    }
}