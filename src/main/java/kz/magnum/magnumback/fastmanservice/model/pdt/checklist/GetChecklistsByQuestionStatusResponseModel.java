package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetChecklistsByQuestionStatusResponseModel {
    private Long checkListId;
    private Long status;
    private Long idTemplateType;
    private Long idTemplate;
    private String rolePerformer;
    private String nameCreator;
    private String tabNumCreator;
    private String roleCreator;
    private String commentCreator;
    private Integer questionsTotal;
    private Integer questionsDone;
    private Date startDate;
    private Date startEndDate;
    private ChecklistQuestionByChapterIsGroupedModel questions;
}