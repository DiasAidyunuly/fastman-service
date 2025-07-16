package kz.magnum.magnumback.fastmanservice.service.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestion;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.*;

import java.util.Date;
import java.util.List;

public interface ChecklistService {
    ChecklistBySiteAndDataModel findChecklists(Integer site,
                                               List<Long> status,
                                               List<Long> type,
                                               List<Long> temp,
                                               List<String> performer,
                                               List<String> role,
                                               Date startDate,
                                               Date entDate);

    ChecklistCreateResponse saveChecklistHead(ChecklistHeadDtoParam checklistHeadDtoParam);

    GetChecklistsByQuestionStatusResponseModel findChecklistsByQuestionStatus(Long headId, Long showQuestionStatus);

    ChecklistQuestion updateChecklistQuestion(Long id, UpdateChecklistQuestionParamModel updateChecklistQuestionParamModel);
}