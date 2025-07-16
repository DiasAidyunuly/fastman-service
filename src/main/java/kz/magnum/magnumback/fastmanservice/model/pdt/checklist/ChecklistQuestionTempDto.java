package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistQuestionTempDto {
    private Long id;
    private ChecklistChapterDto checklistChapterDto;
    private ChecklistTypeOfAnswerDto checklistTypeOfAnswerDto;
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