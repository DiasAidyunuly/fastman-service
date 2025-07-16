package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Action;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.fastman.ActionRepository;
import kz.magnum.magnumback.fastmanservice.repository.fastman.FastmanTaskRepository;
import kz.magnum.magnumback.fastmanservice.repository.fastman.IncActionRepository;
import kz.magnum.magnumback.fastmanservice.repository.fastman.IncidentRepository;
import kz.magnum.magnumback.fastmanservice.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
    private final ActionRepository actionRepository;
    private final IncidentRepository incidentRepository;
    private final IncActionRepository incActionRepository;
    private final FastmanTaskRepository fastmanTaskRepository;

    @Override
    public List<Action> findAll() {
        return actionRepository.findAll();
    }

    @Override
    public Action save(Action actionParam) {
        return actionRepository.save(actionParam);
    }

    @Override
    @Transactional
    public void delete(Short id) {
        incidentRepository.setActionToNull(id);
        incActionRepository.deleteByActionId(id);
        fastmanTaskRepository.setActionToNull(id);
        actionRepository.deleteById(id);
    }

    @Override
    public Action update(Short id, Action actionParam) {
        findById(id);
        actionParam.setId(id);
        return actionRepository.save(actionParam);
    }

    @Override
    public Action findById(Short id) {
        return actionRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Действие с таким ID = %d не найдено", id)));
    }
}