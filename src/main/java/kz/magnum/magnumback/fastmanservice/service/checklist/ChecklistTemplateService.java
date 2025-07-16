package kz.magnum.magnumback.fastmanservice.service.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistTemplate;

import java.util.List;

public interface ChecklistTemplateService {
    List<ChecklistTemplate> findAll();

    ChecklistTemplate save(ChecklistTemplate checklistTemplate);

    ChecklistTemplate update(Long id, ChecklistTemplate checklistTemplate);

    void delete(Long id);

    ChecklistTemplate findById(Long id);
}