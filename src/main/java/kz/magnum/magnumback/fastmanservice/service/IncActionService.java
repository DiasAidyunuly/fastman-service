package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncAction;
import kz.magnum.magnumback.fastmanservice.model.IncActionLocal;

import java.util.List;

public interface IncActionService {
    List<IncAction> findByIncidentId(Long incidentId);

    List<IncActionLocal> findIncActionsLocal();

    IncAction save(IncAction incAction);

    void delete(Long id);

    IncAction update(Long id, IncAction incAction);

    IncAction findById(Long id);
}