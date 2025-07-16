package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHeadStatus;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistHeadStatusRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistHeadStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistHeadStatusServiceImpl implements ChecklistHeadStatusService {
    private final ChecklistHeadStatusRepository checklistHeadStatusRepository;

    @Override
    public ChecklistHeadStatus findById(Long id) {
        return checklistHeadStatusRepository.findById(id)
            .orElseThrow(() -> new FastmanNotFoundException(String.format("Справочника статусов по шапкам с таким ID = %d не найден", id)));
    }
}