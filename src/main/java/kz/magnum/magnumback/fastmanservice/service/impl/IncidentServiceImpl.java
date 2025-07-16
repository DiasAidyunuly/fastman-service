package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Incident;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.fastman.*;
import kz.magnum.magnumback.fastmanservice.service.ActionService;
import kz.magnum.magnumback.fastmanservice.service.IncidentService;
import kz.magnum.magnumback.fastmanservice.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentServiceImpl implements IncidentService {
    private final IncidentRepository incidentRepository;
    private final ActionService actionService;
    private final TemplateService templateService;
    private final IncExecutionRepository incExecutionRepository;
    private final IncActionRepository incActionRepository;
    private final SiteExceptionRepository siteExceptionRepository;
    private final FastmanTaskRepository fastmanTaskRepository;

    @Override
    public List<Incident> findAll() {
        return incidentRepository.findAll();
    }

    @Override
    public Incident save(Incident incidentParam) {
        if (incidentParam.getAction() != null) {
            incidentParam.setAction(actionService.findById(incidentParam.getAction().getId()));
        }
        incidentParam.setTemplate(templateService.findById(incidentParam.getTemplate().getId()));
        incidentParam.setPriority((short) (incidentRepository.findMaxPriority() + 1));
        return incidentRepository.save(incidentParam);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        incExecutionRepository.deleteByIncidentId(id);
        incActionRepository.deleteByIncidentId(id);
        siteExceptionRepository.deleteByIncidentId(id);
        fastmanTaskRepository.setIncidentToNull(id);

        Short priority = findById(id).getPriority();
        List<Incident> incidents = incidentRepository.findByPriorityGreaterThan(priority);
        incidents.forEach(incident -> incident.setPriority((short) (incident.getPriority() - 1)));
        incidentRepository.deleteById(id);
        incidentRepository.saveAll(incidents);
    }

    @Override
    public Incident update(Long id, Incident incidentParam) {
        findById(id);
        incidentParam.setId(id);
        if (incidentParam.getAction() != null) {
            incidentParam.setAction(actionService.findById(incidentParam.getAction().getId()));
        }
        incidentParam.setTemplate(templateService.findById(incidentParam.getTemplate().getId()));
        return incidentRepository.save(incidentParam);
    }

    @Override
    public Incident findById(Long id) {
        return incidentRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Инцидент с таким ID %d не найден", id)));
    }
}