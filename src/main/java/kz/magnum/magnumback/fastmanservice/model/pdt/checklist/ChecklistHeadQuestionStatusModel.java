package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHeadStatus;
import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestionStatus;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistHeadQuestionStatusModel {
    List<ChecklistHeadStatus> headStatuses;
    List<ChecklistQuestionStatus> questionStatuses;
}