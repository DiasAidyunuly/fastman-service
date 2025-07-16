package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistDto {
    private Long checkListId;
    private Date startDate;
    private Date startEndDate;
    private Long status;
    private Long templateTypeId;
    private Long templateId;
    private String rolePerformer;
    private String nameCreator;
    private String tabNumCreator;
    private String roleCreator;
    private String commentCreator;
    private Integer questionsTotal;
    private Integer questionsDone;
}