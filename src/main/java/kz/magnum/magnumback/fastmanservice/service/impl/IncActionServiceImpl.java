package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncAction;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.model.IncActionLocal;
import kz.magnum.magnumback.fastmanservice.model.IncActionWithoutIncidentId;
import kz.magnum.magnumback.fastmanservice.repository.fastman.IncActionRepository;
import kz.magnum.magnumback.fastmanservice.service.ActionService;
import kz.magnum.magnumback.fastmanservice.service.IncActionService;
import kz.magnum.magnumback.fastmanservice.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncActionServiceImpl implements IncActionService {
    private final IncActionRepository incActionRepository;
    private final IncidentService incidentService;
    private final ActionService actionService;

    @Override
    public List<IncAction> findByIncidentId(Long incidentId) {
        incidentService.findById(incidentId);
        return incActionRepository.findAllByIncidentId(incidentId);
    }

    @Override
    public List<IncActionLocal> findIncActionsLocal() {
        List<IncActionLocal> resultList = new ArrayList<>();
        List<IncAction> findAllResultList = incActionRepository.findAll();
        Map<Long, List<IncAction>> groupedMap = findAllResultList.stream().collect(
            Collectors.groupingBy(incAction -> incAction.getIncident().getId()));
        for (Map.Entry<Long, List<IncAction>> entry : groupedMap.entrySet()) {
            List<IncActionWithoutIncidentId> innerResultList = new ArrayList<>();
            for (IncAction i : entry.getValue()) {
                IncActionWithoutIncidentId incActionWithoutIncidentId = IncActionWithoutIncidentId.builder()
                    .actionId(i.getAction().getId())
                    .build();
                incActionWithoutIncidentId.setActionId(i.getAction().getId());
                innerResultList.add(incActionWithoutIncidentId);
            }
            IncActionLocal incActionLocal = IncActionLocal.builder()
                .incidentId(entry.getKey())
                .incActions(innerResultList)
                .build();
            incActionLocal.setIncidentId(entry.getKey());
            resultList.add(incActionLocal);
        }
        return resultList;
    }

    @Override
    public IncAction save(IncAction incActionParam) {
        incActionParam.setIncident(incidentService.findById(incActionParam.getIncident().getId()));
        incActionParam.setAction(actionService.findById(incActionParam.getAction().getId()));
        return incActionRepository.save(incActionParam);
    }

    @Override
    public void delete(Long id) {
        incActionRepository.deleteById(id);
    }

    @Override
    public IncAction update(Long id, IncAction incActionParam) {
        findById(id);
        incActionParam.setId(id);
        incActionParam.setIncident(incidentService.findById(incActionParam.getIncident().getId()));
        incActionParam.setAction(actionService.findById(incActionParam.getAction().getId()));
        return incActionRepository.save(incActionParam);
    }

    @Override
    public IncAction findById(Long id) {
        return incActionRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Инцидент с действием с таким ID %d не найден", id)));
    }
}