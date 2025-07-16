package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistChapter;
import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistTypeOfAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistQuestionTempModel {
    private ChecklistChapter checklistChapter;
    private ChecklistTypeOfAnswer checklistTypeOfAnswer;
    private String questionName;
    private boolean photoResolution;
    private Short weight;
    private boolean photoMandatory;
    private Short photoMaximum;
    private boolean commentResolution;
    private String photoCreate;
    private String photoExtension;
    private String fileCreate;
    private String fileExtension;
}