package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistQuestionDto {
    private Long id;
    private Long checklistHeadId;
    private Long checklistQuestionStatusId;
    private Long checklistQuestionTempId;
    private String questionAnswer;
    private String comment;
    private Short weightCompletion;
    private String photoCompletion;
}