package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestion;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistQuestionRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistQuestionServiceImpl implements ChecklistQuestionService {
    private final ChecklistQuestionRepository checklistQuestionRepository;
    @Override
    public ChecklistQuestion findById(Long id) {
        return checklistQuestionRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Вопрос по чеклистам с таким ID = %d не найден", id)));
    }
}