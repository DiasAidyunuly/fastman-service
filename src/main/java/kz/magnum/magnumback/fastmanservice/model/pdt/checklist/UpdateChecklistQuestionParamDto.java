package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChecklistQuestionParamDto {
    private Long idQuestionStatus;
    private String comment;
    private String answerQuestion;
    private Short weightCompletion;
}