package kz.magnum.magnumback.fastmanservice.service.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistTypeOfAnswer;

import java.util.List;

public interface ChecklistTypeOfAnswerService {
    List<ChecklistTypeOfAnswer> findAll();

    ChecklistTypeOfAnswer save(ChecklistTypeOfAnswer checklistTypeOfAnswer);

    ChecklistTypeOfAnswer update(Long id, ChecklistTypeOfAnswer checklistTypeOfAnswer);

    void delete(Long id);

    ChecklistTypeOfAnswer findById(Long id);
}