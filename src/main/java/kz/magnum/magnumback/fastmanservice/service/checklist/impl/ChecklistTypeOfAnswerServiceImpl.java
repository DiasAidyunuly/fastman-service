package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistTypeOfAnswer;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistTypeOfAnswerRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistTypeOfAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistTypeOfAnswerServiceImpl implements ChecklistTypeOfAnswerService {
    private final ChecklistTypeOfAnswerRepository checklistTypeOfAnswerRepository;

    @Override
    public List<ChecklistTypeOfAnswer> findAll() {
        return checklistTypeOfAnswerRepository.findAll();
    }

    @Override
    public ChecklistTypeOfAnswer save(ChecklistTypeOfAnswer checklistTypeOfAnswerParam) {
        return checklistTypeOfAnswerRepository.save(checklistTypeOfAnswerParam);
    }

    @Override
    public ChecklistTypeOfAnswer update(Long id, ChecklistTypeOfAnswer checklistTypeOfAnswerParam) {
        checklistTypeOfAnswerRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Чеклист тип ответа с таким ID = %d не найден", id)));
        checklistTypeOfAnswerParam.setId(id);
        return checklistTypeOfAnswerRepository.save(checklistTypeOfAnswerParam);
    }

    @Override
    public void delete(Long id) {
        checklistTypeOfAnswerRepository.deleteById(id);
    }

    @Override
    public ChecklistTypeOfAnswer findById(Long id) {
        return checklistTypeOfAnswerRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Чеклист тип ответа с таким ID = %d не найден", id)));
    }
}