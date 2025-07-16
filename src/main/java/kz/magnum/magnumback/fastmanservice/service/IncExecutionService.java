package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncExecution;
import kz.magnum.magnumback.fastmanservice.model.IncExecutionLocal;

import java.util.List;

public interface IncExecutionService {
    List<IncExecution> findByIncidentId(Long incidentId);

    List<IncExecutionLocal> findIncExecutionsLocal();

    IncExecution save(IncExecution incExecution);

    void delete(Long id);

    IncExecution update(Long id, IncExecution incExecution);

    IncExecution findById(Long id);
}