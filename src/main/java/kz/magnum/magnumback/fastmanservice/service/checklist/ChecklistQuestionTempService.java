package kz.magnum.magnumback.fastmanservice.service.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestionTemp;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistQuestionTempModel;

import java.util.List;

public interface ChecklistQuestionTempService {
    List<ChecklistQuestionTemp> findAll(Long id);

    ChecklistQuestionTempModel save(ChecklistQuestionTempModel checklistQuestionTempModel);

    ChecklistQuestionTempModel update(Long id, ChecklistQuestionTempModel checklistQuestionTempModel);

    void delete(Long id);
}