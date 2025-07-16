package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHead;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistHeadRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistHeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistHeadServiceImpl implements ChecklistHeadService {
    private final ChecklistHeadRepository checklistHeadRepository;

    @Override
    public ChecklistHead findById(Long id) {
        return checklistHeadRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Шапка по чеклистам с таким ID = %d не найден", id)));
    }
}