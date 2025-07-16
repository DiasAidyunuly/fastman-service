package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHeadStatus;
import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestionStatus;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistHeadQuestionStatusModel;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistHeadStatusRepository;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistQuestionStatusRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistHeadQuestionStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistHeadQuestionStatusServiceImpl implements ChecklistHeadQuestionStatusService {
    private final ChecklistHeadStatusRepository checklistHeadStatusRepository;
    private final ChecklistQuestionStatusRepository checklistQuestionStatusRepository;

    @Override
    public ChecklistHeadQuestionStatusModel findAll() {
        List<ChecklistHeadStatus> checklistHeadStatuses = checklistHeadStatusRepository.findAll();
        List<ChecklistQuestionStatus> checklistQuestionStatuses = checklistQuestionStatusRepository.findAll();
        return ChecklistHeadQuestionStatusModel
            .builder()
            .headStatuses(checklistHeadStatuses)
            .questionStatuses(checklistQuestionStatuses)
            .build();
    }
}