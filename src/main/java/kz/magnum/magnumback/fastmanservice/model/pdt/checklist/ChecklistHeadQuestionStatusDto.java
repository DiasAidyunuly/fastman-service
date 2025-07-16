package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistHeadQuestionStatusDto {
    List<ChecklistHeadStatusDto> headStatuses;
    List<ChecklistQuestionStatusDto> questionStatuses;
}