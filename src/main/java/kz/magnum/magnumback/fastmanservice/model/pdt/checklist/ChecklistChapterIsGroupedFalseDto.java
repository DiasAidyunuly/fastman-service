package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistChapterIsGroupedFalseDto {
    private Long idQuestion;
    private Long idQuestionStatus;
    private Long idQuestionTemp;
    private String answerQuestion;
    private String questionTitle;
    private String comment;
    private List<String> fileAttachment;
    private List<String> photoAttachment;
    private List<String> photoAttachmentComplete;
}