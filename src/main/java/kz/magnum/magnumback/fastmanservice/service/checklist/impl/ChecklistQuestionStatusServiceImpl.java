package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestionStatus;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistQuestionStatusRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistQuestionStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistQuestionStatusServiceImpl implements ChecklistQuestionStatusService {
    private final ChecklistQuestionStatusRepository checklistQuestionStatusRepository;

    @Override
    public ChecklistQuestionStatus findById(Long id) {
        return checklistQuestionStatusRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Статус по управлению вопросами с таким ID = %d не найден", 1L)));
    }
}