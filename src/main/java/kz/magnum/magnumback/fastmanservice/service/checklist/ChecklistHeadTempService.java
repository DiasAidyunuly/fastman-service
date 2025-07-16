package kz.magnum.magnumback.fastmanservice.service.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHeadTemp;

import java.util.List;

public interface ChecklistHeadTempService {
    List<ChecklistHeadTemp> findAll();

    ChecklistHeadTemp save(ChecklistHeadTemp checklistHeadTemp);

    ChecklistHeadTemp update(Long id, ChecklistHeadTemp checklistHeadTemp);

    void delete(Long id);

    ChecklistHeadTemp findById(Long id);
}